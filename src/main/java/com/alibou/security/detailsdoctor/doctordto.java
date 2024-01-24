package com.alibou.security.detailsdoctor;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class doctordto {
    private List<formation> formations;
    private List<experience> experiences;
    private List<diploma> diplomas;


    private String speciality;
    private String address;
    private Long telephone;
    private Date date_naissance;
    private String gender;
    private List<String> langues;
    private List<horaire> horaires;
    private Integer tarif_const;
    private Integer tarif_video;

}
