package com.alibou.security.RendezVous;
import com.alibou.security.user.Role;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service


public class RendezVousServices {
    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private UserRepository repository;


    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    public void updateAppointmentDecision(String id, String decision) {
        System.out.println("Updating appointment decision. Id: " + id + ", Decision: " + decision);

        Optional<RendezVous> optionalAppointment = rendezVousRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            RendezVous appointment = optionalAppointment.get();
            appointment.setDecision(decision);

            try {
                rendezVousRepository.save(appointment);
                System.out.println("Decision updated successfully.");
            } catch (Exception e) {
                System.err.println("Error saving appointment: " + e.getMessage());
                throw new RuntimeException("Error updating decision");
            }
        } else {
            throw new NoSuchElementException("Appointment not found with id: " + id);
        }
    }



    public List<RendezVous> getAppointmentsByDoctorId(String doctorId) {
        return rendezVousRepository.findByidDoctor(doctorId);
    }

    public List<RendezVous> getAcceptedAppointmentsForDoctor(String idDoctor) {
        return rendezVousRepository.findByidDoctorAndDecision(idDoctor, "Accepted");
    }

    public RendezVous addRendezVous(RendezVous rendezVous) {
        // Retrieve the doctor and patient from the database
        User doctor = repository.findByRoleAndId(Role.DOCTOR, rendezVous.getIdDoctor()).orElse(null);
        User patient = repository.findByRoleAndId(Role.PATIENT, rendezVous.getId_patient()).orElse(null);

        if (doctor != null && patient != null) {
            // Initialize the lists if they are null
            if (doctor.getDoctorAppointments() == null) {
                doctor.setDoctorAppointments(new ArrayList<>());
            }
            if (patient.getPatientAppointments() == null) {
                patient.setPatientAppointments(new ArrayList<>());
            }

            // Save the updated doctor and patient back to the database
            doctor.getDoctorAppointments().add(rendezVous);
            patient.getPatientAppointments().add(rendezVous);

            // Save the doctor and patient
            repository.save(doctor);
            repository.save(patient);

            // Save the appointment
            RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);

            if (savedRendezVous.getId() != null) {
                return savedRendezVous;
            } else {
                // Handle the case where _id is null (e.g., return an error message or throw an exception)
                return null;
            }
        } else {
            // Handle the case where either the doctor or patient is not found
            // (e.g., return an error message or throw an exception)
            return null;
        }


    }

    public void updateAppointment(String id, RendezVous updatedAppointment) {
        System.out.println("Updating appointment. Id: " + id);

        Optional<RendezVous> optionalAppointment = rendezVousRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            RendezVous existingAppointment = optionalAppointment.get();

            // Update the fields you want to change
            existingAppointment.setFirstName(updatedAppointment.getFirstName());
            existingAppointment.setLastName(updatedAppointment.getLastName());
            existingAppointment.setPhone(updatedAppointment.getPhone());
            existingAppointment.setSexe(updatedAppointment.getSexe());
            existingAppointment.setDateNaissance(updatedAppointment.getDateNaissance());
            existingAppointment.setAdresse(updatedAppointment.getAdresse());
            existingAppointment.setVille(updatedAppointment.getVille());
            existingAppointment.setDateRendezVous(updatedAppointment.getDateRendezVous());
            existingAppointment.setLongueur(updatedAppointment.getLongueur());
            existingAppointment.setPoids(updatedAppointment.getPoids());
            existingAppointment.setMaladie(updatedAppointment.getMaladie());
            existingAppointment.setZipCode(updatedAppointment.getZipCode());
            existingAppointment.setAppointmentType(updatedAppointment.getAppointmentType());
            existingAppointment.setOwner(updatedAppointment.getOwner());
            existingAppointment.setEmail(updatedAppointment.getEmail());
            existingAppointment.setDecision(updatedAppointment.getDecision());
            existingAppointment.setStatus(updatedAppointment.getStatus());


            // Add more fields as needed

            try {
                rendezVousRepository.save(existingAppointment);
                System.out.println("Appointment updated successfully.");
            } catch (Exception e) {
                System.err.println("Error saving appointment: " + e.getMessage());
                throw new RuntimeException("Error updating appointment");
            }
        } else {
            throw new NoSuchElementException("Appointment not found with id: " + id);
        }
    }

    public void deleteAppointment(String id) {
        System.out.println("Deleting appointment. Id: " + id);

        Optional<RendezVous> optionalAppointment = rendezVousRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            try {
                rendezVousRepository.deleteById(id);
                System.out.println("Appointment deleted successfully.");
            } catch (Exception e) {
                System.err.println("Error deleting appointment: " + e.getMessage());
                throw new RuntimeException("Error deleting appointment");
            }
        } else {
            throw new NoSuchElementException("Appointment not found with id: " + id);
        }
    }

}