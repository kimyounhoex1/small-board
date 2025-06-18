package com.jungle.board.service;

import com.jungle.board.dao.MemberDAO;
import com.jungle.board.domain.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDAO memberDAO;

    public List<Member> getAllMember(){
        return memberDAO.getAllMembers();
    }

    public Member findMemberById(Long memberId) {
        return memberDAO.findMemberById(memberId);
    }
    public boolean join(Member member) {
        if(memberDAO.isDuplicateNickname(member.getNickname())){
           throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        return memberDAO.join(member);
    }

    public boolean login(String nickname, String password) {
        String passwordByNickname = memberDAO.findPasswordByNickname(nickname);
        if (passwordByNickname.equals(password)) {
            return true;
        }
        return false;
    }


}
