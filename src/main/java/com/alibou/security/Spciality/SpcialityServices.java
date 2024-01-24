package com.alibou.security.Spciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpcialityServices {
    @Autowired
    private SpcialityRepository  SpcialityRepository ;

    public List<Spciality> getAllSpecialities() {
        return SpcialityRepository .findAll();
    }
}
