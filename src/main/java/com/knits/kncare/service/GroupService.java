package com.knits.kncare.service;

import com.knits.kncare.dto.GroupDto;
import com.knits.kncare.dto.MemberDto;
import com.knits.kncare.dto.pages.JsonPageImpl;
import com.knits.kncare.dto.search.GroupSearchDto;
import com.knits.kncare.mapper.GroupMapper;
import com.knits.kncare.mapper.MapperInterface;
import com.knits.kncare.mapper.MemberMapper;
import com.knits.kncare.model.Group;
import com.knits.kncare.model.GroupMembership;
import com.knits.kncare.repository.GroupRepository;
import com.knits.kncare.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberMapper memberMapper;
    private final GroupMapper groupMapper;

    public GroupService(MemberMapper memberMapper,
                        GroupRepository groupRepository,
                        GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.memberMapper = memberMapper;
    }


    public GroupDto create(GroupDto groupDto) {

        Group existingName = groupRepository.findByName(groupDto.getName());
        if (existingName != null) {
            throw new RuntimeException(String.format("Group with the name %s already exists", groupDto.getName()));
        }

        if (CollectionUtils.isEmpty(groupDto.getMembers())) {
            log.debug("creating a group without new members");
            return groupMapper.toDto(groupRepository.save(groupMapper.toModel(groupDto)));
        }

        Group group = groupMapper.toModel(groupDto);
        addMembersToGroup(group, groupDto.getMembers());
        groupRepository.save(group);

        GroupDto savedGroupDto = groupMapper.toDto(group);

        return savedGroupDto;
    }


    public GroupDto update(Long id, GroupDto groupDto) {

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no group with ID: " + id.toString()));

        groupMapper.updateGroupFromDto(groupDto, group);

        addMembersToGroup(group, groupDto.getMembers());
        groupRepository.save(group);

        GroupDto updatedGroupDto = groupMapper.toDto(group);

        return updatedGroupDto;
    }

    public void addMembersToGroup(Group group, Set<MemberDto> members) {

        for (MemberDto memberDto : members) {
            GroupMembership groupMembership = new GroupMembership(group, memberMapper.toModel(memberDto));
            group.getGroupMemberships().add(groupMembership);
        }
    }

    public GroupDto getbyId(long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return groupMapper.toDto(group);
    }

    public Page<GroupDto> search(GroupSearchDto groupSearchDto) {
        Page<Group> groupPage = groupRepository.findAll(groupSearchDto.getSpecification(), groupSearchDto.getPageable());
        return new JsonPageImpl<>(groupMapper.toDtoList(groupPage.getContent()), groupSearchDto.getPageable(), groupSearchDto.getPageable().getPageSize());
    }
}