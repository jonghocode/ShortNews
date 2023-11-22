package com.ShortNews.ShortNews.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class Member {

    @Id
    private String mem_id;
    private String email;
    private String phone;
    private String salt;
    private String pw;
    private String nickname;
    private Integer alarm = 1;
}
