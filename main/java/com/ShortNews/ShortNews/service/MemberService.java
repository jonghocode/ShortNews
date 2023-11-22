package com.ShortNews.ShortNews.service;

import com.ShortNews.ShortNews.SHA256;
import com.ShortNews.ShortNews.entity.Member;
import com.ShortNews.ShortNews.entity.Platform;
import com.ShortNews.ShortNews.repository.MemberRepository;
import com.ShortNews.ShortNews.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private EntityManager entityManager;

    private String createMemberId() {
        String str = "select member_id_create.nextval from dual";
        Query query = entityManager.createNativeQuery(str);
        String num = query.getSingleResult().toString();

        SimpleDateFormat formattype = new SimpleDateFormat("yyyyMMdd");
        String now = formattype.format(new Date()); // 현재 시간
        return now+num;
    }

    private String makeSalt() {
        int leftLimit = 48, rightLimit = 122, targetStringLength = 8;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String makePw(String pw, String member_salt) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        return sha256.encrypt(pw+member_salt);
    }

    public boolean join(Member member, String email, String pw) throws NoSuchAlgorithmException {
        List<Member> list = memberRepository.findByPhone(member.getPhone());
        List<Member> list2 = memberRepository.findByEmail(email);

        if (list.isEmpty() && list2.isEmpty()) { // 전화번호 중복 체크, 이메일 중복체크
            String id = createMemberId();
            String member_salt = makeSalt();
            String member_pw = makePw(pw, member_salt);
            member.setMem_id(id);
            member.setNickname(id);
            member.setSalt(member_salt);
            member.setPw(member_pw);
            member.setEmail(email);
            memberRepository.save(member);

            Platform platform = new Platform();
            platform.setMem_id(id);

            platformRepository.save(platform);
            return true;
        }
        else if(!list.isEmpty()){
            throw new IllegalArgumentException("전화번호가 중복됩니다.");
        }
        else {
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        }

    }

    public int login(String email, String pw) throws NoSuchAlgorithmException {
        List<Member> list = memberRepository.findByEmail(email);
        if (!list.isEmpty()) {
            String origin_pw = list.get(0).getPw();
            String salt = list.get(0).getSalt();

            String new_pw = makePw(pw, salt);
            if (origin_pw.equals(new_pw)) { // 비밀번호가 맞다면
                return 1;
            }
            else{ // 비밀번호가 틀리다면
                return 0;
            }
        }
        else { // 이메일이 없다면
            return -1;
        }

    }
}