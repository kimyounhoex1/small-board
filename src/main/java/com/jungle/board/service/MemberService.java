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
}
