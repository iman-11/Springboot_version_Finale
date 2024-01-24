package com.alibou.security.detailsdoctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class formationservice {
    @Autowired
    private formationrepo formationRepository;

    public formation saveFormation(formation formation) {
        return formationRepository.save(formation);
    }

}
