package com.knits.kncare.service;

import com.knits.kncare.dto.FileDBDto;
import com.knits.kncare.dto.PracticeDto;
import com.knits.kncare.mapper.MapperInterface;
import com.knits.kncare.mapper.PracticeMapper;
import com.knits.kncare.model.FileDB;
import com.knits.kncare.model.Practice;
import com.knits.kncare.model.PracticeAttachment;
import com.knits.kncare.repository.FileDBRepository;
import com.knits.kncare.repository.PracticeAttachmentRepository;
import com.knits.kncare.repository.PracticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service


public class PracticeService {
    private final PracticeRepository practiceRepository;
    private final FileDBRepository fileDBRepository;
    private final PracticeAttachmentRepository practiceAttachmentRepository;
    private final PracticeMapper mapper;

    public PracticeService(PracticeMapper mapper, PracticeRepository practiceRepository, FileDBRepository fileDBRepository, PracticeAttachmentRepository practiceAttachmentRepository) {
        this.mapper = mapper;
        this.practiceRepository = practiceRepository;
        this.fileDBRepository = fileDBRepository;
        this.practiceAttachmentRepository = practiceAttachmentRepository;
    }

    public Page<PracticeDto> getAll() {
        List<Practice> practices = practiceRepository.findAll();
        return new PageImpl<>(mapper.toDtoList(practices));
    }

    public Page<PracticeDto> getAllMatchingByTitle(String snippet){
        List<Practice> practices = practiceRepository.findByTitleContainingIgnoreCase(snippet);
        return new PageImpl<>(mapper.toDtoList(practices));
    }

    public Page<PracticeDto> getAllMatchingByDescription(String snippet){
        List<Practice> practices = practiceRepository.findByDescriptionContainingIgnoreCase(snippet);
        return new PageImpl<>(mapper.toDtoList(practices));
    }

    public Optional<PracticeDto> getById(long id) {
        Optional<Practice> requiredObject = practiceRepository.findById(id);
        return requiredObject.map(mapper::toDto);
    }

    public PracticeDto add(PracticeDto practiceDto) {
        Practice generatedPractice = practiceRepository.save(mapper.toModel(practiceDto));
        return PracticeDto.builder()
                .id(generatedPractice.getId())
                .title(generatedPractice.getTitle())
                .description(generatedPractice.getDescription())
                .rates(generatedPractice.getRates())
                .tags(generatedPractice.getTags())
                .build();
    }

    public void delete(Long id){
        practiceRepository.deleteById(id);
    }
    public void deleteAll(){
        practiceRepository.deleteAll();
    }

    public PracticeDto update(long id, PracticeDto practiceDto) {
        Practice practice = practiceRepository.findById(id).orElseThrow(() -> new RuntimeException("No such practice!"));
        practice.setDescription(practiceDto.getDescription());
        practice.setRates(practiceDto.getRates());
        practice.setTags(practiceDto.getTags());
        practice.setTitle(practiceDto.getTitle());
        practiceRepository.save(practice);
        return mapper.toDto(practice);
    }

    public PracticeDto store(FileDB fileDB, PracticeDto practiceDto)  {

        FileDB practiceFile = fileDBRepository.save(fileDB);

        Practice practice = practiceRepository.save(mapper.toModel(practiceDto));

        PracticeAttachment practiceAttachment = new PracticeAttachment();
        practiceAttachment.setFileDB(practiceFile);
        practiceAttachment.setPractice(practice);

        practiceAttachment = practiceAttachmentRepository.save(practiceAttachment);


        List<PracticeAttachment> practiceAttachments = new ArrayList<>();
        practiceAttachments.add(practiceAttachment);
        practiceDto.setPracticeAttachments(practiceAttachments);

        // auto mapper doesn't work so it's done manually here
        practice.setPracticeAttachments(practiceDto.getPracticeAttachments());
        practice.setTitle(practiceDto.getTitle());
        practice.setDescription(practiceDto.getDescription());
        practice.setRates(practiceDto.getRates());
        practice.setTags(practiceDto.getTags());

        practiceRepository.save(practice);

        return mapper.toDto(practice);
    }

}
