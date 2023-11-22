package com.ShortNews.ShortNews.controller;

import com.ShortNews.ShortNews.entity.Member;
import com.ShortNews.ShortNews.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class CreateController { //컨트롤러 -> 서비스 -> 리포지토리

    @Autowired
    private MemberService memberService;

    @PostMapping("/create")
    public String create() {
        return "Create";
    }

    @PostMapping("/create/check")
    public String createCheck(String email, String pw, String phone) throws NoSuchAlgorithmException {
        Member member = new Member();
        member.setPhone(phone);

        boolean state = memberService.join(member, email, pw);
        if (state) {
            return "SelectCategory";
        }
        else {
            return "Create";
        }
    }
}
