package com.example.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DF05_FileDTO {
    private int fno;
    private String file_name;
    private String origin_name;
    private Timestamp uploaded_date;
//    private Timestamp updated_date;
    private String path;
    private int bno;
    private boolean yn;
}
