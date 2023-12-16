package com.evproject.evproject.services;

import com.evproject.evproject.entities.Member;
import com.evproject.evproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

      // get member point
    public Integer getPoint(Long customerId)
    {
        Optional<Member> member = memberRepository.findById(customerId);

        if (member.isPresent()) {
            return member.get().getPoint();
        }

        // throw exception
        return -1; // 返回默认积分 0
    }

    public void updatePoint(Long customerId, Integer point) {
        Optional<Member> member = memberRepository.findById(customerId);

        if (member.isPresent()) {
            Member m = member.get();
            m.setPoint(point);
            memberRepository.save(m);
        }
    }
}
