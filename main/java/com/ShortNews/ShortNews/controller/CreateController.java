package com.ShortNews.ShortNews.controller;

import com.ShortNews.ShortNews.entity.Member;
import com.ShortNews.ShortNews.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String createCheck(String email, String pw, String phone, HttpServletRequest request) throws NoSuchAlgorithmException {
        Member member = new Member();
        member.setPhone(phone);

        boolean state = memberService.join(member, email, pw);
        if (state) {
            HttpSession session = request.getSession();
            session.setAttribute("id", member.getMem_id());
            return "SelectCategory";
        }
        else {
            return "Create";
        }
    }

    @PostMapping("/select/cate")
    public String selectCate(@RequestParam("check") String[] categories, @RequestParam("nickname") String nickname, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        memberService.joinCate(id, categories, nickname);
        return "Login";
    }
}
