package com.alibou.security.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/rendezvous")
@CrossOrigin(origins = "http://localhost:4200")
public class RendezVousController {

    @Autowired
    private RendezVousServices rendezVousServices;
    @Autowired
    private RendezVousRepository rendezVousRepository;

    @GetMapping("/all")
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousServices.getAllRendezVous();
        return ResponseEntity.ok(rendezVousList);
    }

    @PostMapping("/updateDecision/{id}")
    public ResponseEntity<String> updateAppointmentDecision(
            @PathVariable String id,
            @RequestBody Map<String, String> decisionMap) {

        try {
            String decision = decisionMap.get("decision");
            rendezVousServices.updateAppointmentDecision(id, decision);
            return new ResponseEntity<>("Decision mise à jour avec succès", HttpStatus.OK);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite lors de la mise à jour de la décision", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }








    @PostMapping( "/add")
    public ResponseEntity<RendezVous> addRendezVous(
            @RequestBody RendezVous rendezVous

    ) {
        RendezVous addedRendezVous = rendezVousServices.addRendezVous(rendezVous);
        return ResponseEntity.ok(addedRendezVous);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<RendezVous>> getAppointmentsByDoctor(@PathVariable String doctorId) {
        List<RendezVous> rendezVousList=rendezVousServices.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(rendezVousList);

    }
    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable String id) {
        Optional<RendezVous> rendezVousOptional = rendezVousRepository.findById(id);

        if (rendezVousOptional.isPresent()) {
            RendezVous rendezVous = rendezVousOptional.get();
            return ResponseEntity.ok(rendezVous);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/doctor-appointments/{idDoctor}")
    public ResponseEntity<List<RendezVous>> getDoctorAppointments(@PathVariable String idDoctor) {
        List<RendezVous> doctorAppointments = rendezVousServices.getAcceptedAppointmentsForDoctor(idDoctor);

        if (!doctorAppointments.isEmpty()) {
            return new ResponseEntity<>(doctorAppointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable String id, @RequestBody RendezVous updatedAppointment) {
        rendezVousServices.updateAppointment(id, updatedAppointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        rendezVousServices.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}