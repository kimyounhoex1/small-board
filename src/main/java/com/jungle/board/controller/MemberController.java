package com.jungle.board.controller;

import com.jungle.board.domain.Member;
import com.jungle.board.service.MemberService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public List<Member> getAllMembers(){
        List<Member> allMember = memberService.getAllMember();
        System.out.println(allMember);
        return allMember;
    }

    @GetMapping("/{memberId}")
    public Member findMember(@PathVariable Long memberId){
        return memberService.findMemberById(memberId);
    }

    @PostMapping("/join")
    public boolean joinMember(@RequestBody Member member){
        return memberService.join(member);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, String> loginData) {
        String nickname = loginData.get("nickname");
        String password = loginData.get("password");
        if(memberService.login(nickname, password)){
//            Cookie
            return true;
        }
        return false;
    }

}
