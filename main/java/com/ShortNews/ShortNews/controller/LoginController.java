package com.ShortNews.ShortNews.controller;

import com.ShortNews.ShortNews.entity.Platform;
import com.ShortNews.ShortNews.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login/form")
    public String loginForm() {
        return "Login"; //Login.html로 이동
    }

    @PostMapping("/login/password")
    public String loginPassword() { //로그인 체크 해야함
        return "LoginPassword";
    }

    @PostMapping("/login/check")
    public String loginCheck(String email, String pw) throws NoSuchAlgorithmException {
        //테이블에 있는지 검사 후 로그인 시키기
        //테이블에 있다면 return Main, 없다면 return 로그인
        int state = memberService.login(email, pw);
        //1이면 로그인 성공
        if (state == 1) {
            return "Main";
        }
        //0이면 비밀번호 틀림
        else if (state == 0) {
            return "LoginPassword";
        }
        //-1이면 이메일 없음
        else {
            return "Login";
        }
    }

    @PostMapping("/again")
    public String again() {
        return "Login";
    }

    @PostMapping("/miss/password")
    public String missPassword() {
        return "MissPassword";
    }

    @PostMapping("/new/password")
    public String newPassword(String email) {
        //여기서 이메일을 받고 테이블에 이메일이 있다면 url을 이메일로 보내주기
        return "MissPassword";
    }
}