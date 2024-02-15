package com.example.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DF03_ReplyDTO {
    private int rno;
    private String content;
    private Timestamp written_date;
    private Timestamp updated_date;
    private int bno;
    private int mno;
}
