package com.jungle.board.service;

import com.jungle.board.config.JwtUtil;
import com.jungle.board.dao.MemberDao;
import com.jungle.board.domain.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public List<Member> getAllMember(){
        return memberDao.getAllMembers();
    }

    public Member findMemberById(Long memberId) {
        return memberDao.findMemberById(memberId);
    }
    public boolean join(Member member) {
        if(memberDao.isDuplicateNickname(member.getNickname())){
           throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        return memberDao.join(member);
    }

    public boolean login(String nickname, String password) {
        String passwordByNickname = memberDao.findPasswordByNickname(nickname);
        if (passwordByNickname.equals(password)) {
            return true;
        }
        return false;
    }


}
