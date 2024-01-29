package com.example.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DF01_MemberDTO {
    private int mno;
    private String id;
    private String password;
    private String email;
    private String name;
    private String nick_name;
    private Long zipcode;
    private String streetAddress;
    private String detailAddress;
    private String gender;
    private String phone;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;
    private Timestamp regdate;
    private Timestamp updated_date;
    private int member_level;
}
