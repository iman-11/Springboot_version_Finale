package com.alibou.security.user;

import com.alibou.security.RendezVous.RendezVous;
import com.alibou.security.detailsdoctor.doctordto;
import com.alibou.security.detailsdoctor.horaire;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    private final UserService service;
    private final UserRepository userRepository;
    private  final searchrepository srepo;


    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request
    ) {
        try {
            service.changePassword(request);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with the provided email: " + request.getEmail());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, String>> updateDoctor(@PathVariable String userId, @RequestBody doctordto doctordto) {
        service.updateDoctor(userId, doctordto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Doctor updated successfully.");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    }
    @GetMapping("/get/getall")
    public List<User> getallUsers() {
        return userRepository.findAll();
    }

    @GetMapping("search/{text}")
    public List<User> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }

    @GetMapping("/alldoctor")
    public List<User> getUserByRole() {
        return service.findByRole(Role.DOCTOR);
    }

    // Endpoint for finding users by role and id
    @GetMapping("/getdoctor/{id}")

    public ResponseEntity<User> getUserByRoleAndId(@PathVariable String id) {
        Optional<User> user = service.findByRoleAndId(Role.DOCTOR, id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("appointments/{doctorId}")
    public List<RendezVous> getDoctorAppointments(@PathVariable String doctorId) {
        return service.getDoctorAppointments(Role.DOCTOR, doctorId);
    }


    @GetMapping("/{userId}/horaires")
    public ResponseEntity<List<horaire>> getHorairesByUserId(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<horaire> horaires = user.getHoraires();

        return ResponseEntity.ok().body(horaires);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllUsers() {
        userRepository.deleteAll();
        return ResponseEntity.ok("All users deleted successfully");
    }





}
