package com.ShortNews.ShortNews.repository;

import com.ShortNews.ShortNews.entity.Member;
import com.ShortNews.ShortNews.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public List<Member> findByPhone(String phone);
    public List<Member> findByEmail(String email);
    @Query("select m from Member m where m.mem_id= :mem_id")
    public List<Member> findByMember(@Param("mem_id") String mem_id);


    @Modifying
    @Transactional
    @Query(value = "update Member m set m.nickname = :nickname where m.mem_id = :mem_id", nativeQuery = true)
    public void updateNickName(@Param("nickname") String nickname, @Param("mem_id") String mem_id);
}
