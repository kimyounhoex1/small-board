package com.jungle.board.service;

import com.jungle.board.domain.Member;
import com.jungle.board.repository.MemberRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMember(){
        return memberRepository.getAllMembers();
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }
    public boolean join(Member member) {
        if(memberRepository.isDuplicateNickname(member.getNickname())){
           throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        return memberRepository.join(member);
    }

    public boolean login(String nickname, String password) {
        String passwordByNickname = memberRepository.findPasswordByNickname(nickname);
        if (passwordByNickname.equals(password)) {
            return true;
        }
        return false;
    }

    public Long getMemberId(String nickname) {
        return memberRepository.findMemberIdByNickname(nickname);
    }

}
