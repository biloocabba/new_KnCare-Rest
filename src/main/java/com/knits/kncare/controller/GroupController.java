package com.knits.kncare.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.GroupDto;
import com.knits.kncare.dto.Views;
import com.knits.kncare.dto.search.GroupSearchDto;
import com.knits.kncare.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/groups")
@JsonView(Views.Common.class)
@Slf4j
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @Operation(summary = "create a new group")
    @PostMapping
    @JsonView(Views.Common.class)
    public ResponseEntity<GroupDto> createGroup(@Valid @RequestBody GroupDto groupDto) {
        try {
            return new ResponseEntity<>(groupService.create(groupDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "update the existing group with new members")
    @PutMapping("{id}")
    @JsonView(Views.Common.class)
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") long id, @RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.update(id, groupDto), HttpStatus.OK);
    }

    @Operation(summary = "find a Group by id")
    @GetMapping("{id}")
    @JsonView(Views.Common.class)
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") long id) {
        return ResponseEntity.ok(groupService.getbyId(id));
    }

    @Operation(summary = "search groups by the name")
    @GetMapping
    @JsonView(Views.Common.class)
    public Page<GroupDto> searchGroups(GroupSearchDto groupSearchDto) {
        return groupService.search(groupSearchDto);
    }
}
