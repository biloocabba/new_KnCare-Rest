package com.knits.kncare.repository;

import com.knits.kncare.model.Group;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class GroupRepositoryH2Test {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    @DisplayName("Should Save Group Into DB")
    public void shouldSaveGroup(){
        // given
        Group group = new Group(null,"Group Estonia","Group Description");
        // when
        Group savedGroup = groupRepository.save(group);
        // then
        Assertions.assertThat(savedGroup).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(group);

    }

//    @Test
//    @Sql("classpath:junit-test-data.sql")
//    void shouldSaveGroupUsingSqlFile(){
//        Optional<Group> group01 = groupRepository.findById(1L);
//        Assertions.assertThat(group01).isNotEmpty();
//
//    }

}