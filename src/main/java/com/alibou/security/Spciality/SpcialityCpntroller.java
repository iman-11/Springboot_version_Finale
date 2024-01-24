package com.alibou.security.Spciality;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/speciality")
@CrossOrigin(origins = "http://localhost:4200")
public class SpcialityCpntroller {

    @Autowired
    private SpcialityServices SpcialityServices;

    @GetMapping("/all")
    public List<Spciality>getAllSpecialities() {

        return SpcialityServices.getAllSpecialities();
    }
}
