package com.max.alpha.repository;

import com.max.alpha.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, String> {

  @Query("SELECT m FROM Member m WHERE m.mid = :mid")
  Member findByMid(@Param("mid") String mid);

}
