package com.knits.kncare.service;

import com.knits.kncare.dto.GroupDto;
import com.knits.kncare.mapper.GroupMapper;
import com.knits.kncare.mapper.MemberMapper;
import com.knits.kncare.model.Group;
import com.knits.kncare.repository.GroupRepository;
import com.knits.kncare.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private GroupMapper groupMapper;
    @Mock
    private MemberMapper memberMapper;

    private GroupService groupService; //BeforeEach

    @BeforeEach
    void setUp() {
        groupService = new GroupService(memberMapper, groupRepository, groupMapper);
    }

    @Test
//    @Disabled
    @DisplayName("Should Find Group By Id")
    void shouldFindGroupById() {
        // given
        Group group = new Group(100L, "Group Estonia", "Group Description");
        GroupDto expectedResponseGroupDto = new GroupDto(100L, "Group Estonia", "Group Description");
        Mockito.when(groupRepository.findById(100L))
                .thenReturn(Optional.of(group));
        Mockito.when(groupMapper.toDto(Mockito.any(Group.class)))
                .thenReturn(expectedResponseGroupDto);
        // when
        GroupDto actualResponse =  groupService.getbyId(100L);
        // then
        Assertions.assertThat(actualResponse.getId()).isEqualTo(expectedResponseGroupDto.getId());
        Assertions.assertThat(actualResponse.getName()).isEqualTo(expectedResponseGroupDto.getName());
    }


    @Test
    @DisplayName("Should Create a New Group Without Members") //groupService.create()
    void shouldCreateNewGroupWithoutMembers() {
        // given
        GroupDto groupRequestDto = new GroupDto(null, "Group Estonia", "Group Description");
        Group group = new Group(null,"Group Estonia", "Group Description");
        Group savedGroup = new Group(100L, "Group Estonia", "Group Description");
        GroupDto expectedResponseGroupDto = new GroupDto(100L,"Group Estonia", "Group Description");
        Mockito.when(groupMapper.toModel(groupRequestDto))
                .thenReturn(group);
        Mockito.when(groupMapper.toDto(savedGroup))
                .thenReturn(expectedResponseGroupDto);
        Mockito.when(groupRepository.save(Mockito.any(Group.class)))
                .thenReturn(savedGroup);


        // when
        GroupDto actualResponse = groupService.create(groupRequestDto);

        // then
        Mockito.verify(groupRepository, Mockito.times(1)).save(group);
        Assertions.assertThat(actualResponse.getId()).isEqualTo(expectedResponseGroupDto.getId());
        Assertions.assertThat(actualResponse.getName()).isEqualTo(expectedResponseGroupDto.getName());

    }

    @Test
    @DisplayName("Should Create a New Group With Members")
    void shouldCreateNewGroupWithMembers() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("Should Update The Group With New Members")
    void shouldUpdateGroupWithMembers() {
    }

}