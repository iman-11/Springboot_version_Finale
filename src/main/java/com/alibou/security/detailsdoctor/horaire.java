package com.alibou.security.detailsdoctor;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class horaire {
    private String jour;
    private Integer heure_depart;
    private Integer heure_arrivee;
}
