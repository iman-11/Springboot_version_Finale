package com.alibou.security.RendezVous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/CodePostal")
@CrossOrigin(origins = "http://localhost:4200")
public class CodePostalController {

    @Autowired
    private CodePostalServices CodePostalServices;

    @GetMapping("/get")
    public String getPostalCode(@RequestParam String city) {
        return CodePostalServices.getPostalCode(city);
    }

    @GetMapping("/all")
    public Map<String, String> getAllPostalCodes() {
        return CodePostalServices.getAllPostalCodes();
    }
}
