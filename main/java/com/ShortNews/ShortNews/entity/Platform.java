package com.ShortNews.ShortNews.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Platform {

    @Id
    private String mem_id;
    private String google;
    private String kakao;
    private String naver;

}
