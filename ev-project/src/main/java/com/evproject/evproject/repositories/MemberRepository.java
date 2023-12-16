package com.evproject.evproject.repositories;

import com.evproject.evproject.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // You can add custom query methods if needed
}
