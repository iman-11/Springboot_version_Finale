package com.alibou.security.contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/add")
    public ResponseEntity<String> addContact(@RequestBody Contact contact) {
        Contact newContact = contactService.addContact(contact);
        String successMessage = "Le message envoyé avec succès.";
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();

    }

}