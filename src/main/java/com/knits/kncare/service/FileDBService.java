package com.knits.kncare.service;

import com.knits.kncare.dto.FileDBDto;
import com.knits.kncare.dto.MemberDto;
import com.knits.kncare.mapper.MapperInterface;
import com.knits.kncare.model.FileDB;
import com.knits.kncare.model.Member;
import com.knits.kncare.repository.FileDBRepository;
import com.knits.kncare.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileDBService extends ServiceBase<FileDB, FileDBDto>{

    private final FileDBRepository fileDBRepository;

    public FileDBService(MapperInterface<FileDB, FileDBDto> mapper, FileDBRepository fileDBRepository) {
        super(mapper);
        this.fileDBRepository = fileDBRepository;
    }

    public FileDBDto store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());


        return toDto(fileDBRepository.save(FileDB));
    }

    public FileDBDto getFile(Long id) throws Exception {
        return toDto(fileDBRepository.findById(id).orElseThrow(()-> new Exception("File not found")));
    }
}