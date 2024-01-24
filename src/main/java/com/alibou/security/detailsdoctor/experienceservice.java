package com.alibou.security.detailsdoctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class experienceservice {
    @Autowired
    private experiencerepo experiencerepo1;

    public experience saveFormation(experience experience1) {
        return experiencerepo1.save(experience1);
    }

}
