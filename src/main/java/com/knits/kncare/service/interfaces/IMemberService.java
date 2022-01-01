package com.knits.kncare.service.interfaces;

import com.knits.kncare.model.Member;

import java.util.List;
import java.util.Optional;

//let's create interfaces for all services and add list of methods here,
// service will implements this interfaces
public interface IMemberService {

    List<Member> getAll();
    Optional<Member> getbyId(long id);
}
