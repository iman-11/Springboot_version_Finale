package com.alibou.security.contact;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibou.security.contact.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ContactService {
    @Autowired
    private ContactRepository ContactRepository;

    public Contact addContact(Contact contact) {
        return ContactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return ContactRepository.findAll();
    }

}
