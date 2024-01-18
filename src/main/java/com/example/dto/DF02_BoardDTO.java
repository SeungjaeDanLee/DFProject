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
    private int likeCounts;
    private int viewPoints;
    private Timestamp writtenDate;
    private Timestamp updatedDate;
    private String category;
    private int mno;
}
