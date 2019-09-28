package com.max.alpha.repository;

import com.max.alpha.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
