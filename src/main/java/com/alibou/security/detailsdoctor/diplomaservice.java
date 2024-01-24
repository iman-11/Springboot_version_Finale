package com.alibou.security.detailsdoctor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor


public class diplomaservice {

    private final diplomarepo diplomarepo;

    public diploma saveFormation(diploma diploma) {
        return diplomarepo.save(diploma);
    }
}
