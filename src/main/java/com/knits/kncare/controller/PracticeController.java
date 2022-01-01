package com.knits.kncare.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.PracticeDto;
import com.knits.kncare.dto.Views;
import com.knits.kncare.model.FileDB;
import com.knits.kncare.model.Practice;
import com.knits.kncare.service.PracticeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/practices")
@RestController
@JsonView(Views.Common.class)
public class PracticeController {

    private final PracticeService service;

    @Autowired
    public PracticeController(PracticeService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<PracticeDto> getPracticeById(@PathVariable("id") long id) {
        Optional<PracticeDto> practiceDto = service.getById(id);
        return practiceDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Page<PracticeDto>> getAllPractices(@RequestParam(required = false) String title,
                                                             @RequestParam(required = false) String description) {
        try {
            if (title != null)
                return new ResponseEntity<>(service.getAllMatchingByTitle(title), HttpStatus.OK);
            if (description != null)
                return new ResponseEntity<>(service.getAllMatchingByDescription(description), HttpStatus.OK);
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<PracticeDto> createPractice(@RequestBody PracticeDto practiceDto) {

        try {
            PracticeDto dto = service.add(practiceDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<PracticeDto> updatePractice(@PathVariable("id") long id,
                                                      @RequestBody PracticeDto practiceDto) {
        PracticeDto dto = service.update(id, practiceDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    // TODO change response to newly made dto with files download uri
    @Operation(summary = "add a BestPractice with an attachment")
    @PostMapping("/upload")
    public ResponseEntity<PracticeDto> createPracticeWithAttachment(MultipartFile file, PracticeDto practice) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
            return new ResponseEntity<>(service.store(FileDB, practice), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePractice(@PathVariable("id") long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPractices() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
