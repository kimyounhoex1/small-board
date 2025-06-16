package com.jungle.board.controller;

import com.jungle.board.domain.Member;
import com.jungle.board.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> getAllMembers(){
        List<Member> allMember = memberService.getAllMember();
        System.out.println(allMember);
        return allMember;
    }
//    @GetMapping("/members")
//    public String getAllMembers(){
//        List<Member> allMember = memberService.getAllMember();
//        System.out.println(allMember);
//        return "success";
//    }

    @PostMapping("/join")
    public String joinMember(@RequestBody Member member){

        return "";
    }
}
