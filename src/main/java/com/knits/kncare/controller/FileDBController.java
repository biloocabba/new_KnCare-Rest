package com.knits.kncare.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.FileDBDto;
import com.knits.kncare.dto.Views;
import com.knits.kncare.service.EmployeeService;
import com.knits.kncare.service.FileDBService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/files")
@JsonView(Views.Common.class)
public class FileDBController {

    private final FileDBService service;

    public FileDBController(FileDBService service) {
        this.service = service;
    }


    @Operation(summary = "create a file")
    @PostMapping
    @JsonView(Views.FileDb.class)
    public ResponseEntity<FileDBDto> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(service.store(file), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "find a file by id")
    @GetMapping("{id}")
    @JsonView(Views.FileDb.class)
    public ResponseEntity<FileDBDto> getFile(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getFile(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
