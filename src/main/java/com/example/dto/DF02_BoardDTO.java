package com.example.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DF02_BoardDTO {
    private int bno;
    private String title;
    private String content;
    private int like_counts;
    private int view_counts;
    private Timestamp written_date;
    private Timestamp updated_date;
    private String category;
    private int mno;
}
