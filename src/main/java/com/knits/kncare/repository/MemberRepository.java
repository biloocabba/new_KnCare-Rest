package com.knits.kncare.repository;

import com.knits.kncare.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    @Query("select u from Member u where u.employee.internationalName like %?1")
    List<Member> findByFirstnameEndsWith(String internationalName);

    @Query( "select m from Member m INNER JOIN FETCH m.employee e where m.id in :ids" )
    List<Member> findByIds(@Param("ids") Set<Long> memberIds);

    @Query( "select m from Member m INNER JOIN FETCH m.employee e INNER JOIN e.office o INNER JOIN o.country c where c.id=:countryId" )
    List<Member> findByCountry(@Param("countryId") Long countryId);


    @Query( "select m from Member m INNER JOIN FETCH m.employee e where e.pdmId=:pdmId" )
    Optional<Member> findByEmployeePdmId(@Param("pdmId") Long pdmId);



}
