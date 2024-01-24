package com.alibou.security.user;

import com.alibou.security.RendezVous.RendezVous;
import com.alibou.security.detailsdoctor.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final formationrepo formationrepo;

    private final UserRepository userRepository;
    public void changePassword(ChangePasswordRequest request) {
        // Retrieve the user based on the provided email address
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        // Check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // Check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // Update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // Save the new password
        userRepository.save(user);
    }




    public void updateDoctor(String userId, doctordto doctordto) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setAddress(doctordto.getAddress());
            existingUser.setTelephone(doctordto.getTelephone());
            existingUser.setDate_naissance(doctordto.getDate_naissance());
            existingUser.setGender(doctordto.getGender());
            existingUser.setSpeciality(doctordto.getSpeciality());
            existingUser.setLangues(doctordto.getLangues());
            existingUser.setTarif_const(doctordto.getTarif_const());
            existingUser.setTarif_video(doctordto.getTarif_video());

            if (existingUser.getHoraires() == null) {
                existingUser.setHoraires(new ArrayList<>());
            }
            if (existingUser.getFormations() == null) {
                existingUser.setFormations(new ArrayList<>());
            }
            if (existingUser.getDiplomas() == null) {
                existingUser.setDiplomas(new ArrayList<>());
            }
            if (existingUser.getExperiences() == null) {
                existingUser.setExperiences(new ArrayList<>());
            }
            if (doctordto.getFormations() == null) {
                // Handle the case where formations is null (initialize it, log a warning, or handle it in an appropriate way)
                doctordto.setFormations(new ArrayList<>());
            }
            if (doctordto.getExperiences() == null) {
                // Handle the case where experiences is null (initialize it, log a warning, or handle it in an appropriate way)
                doctordto.setExperiences(new ArrayList<>());
            }
            if (doctordto.getDiplomas() == null) {
                // Handle the case where experiences is null (initialize it, log a warning, or handle it in an appropriate way)
                doctordto.setDiplomas(new ArrayList<>());
            }

            // Add new formations to the existing user
            List<formation> formations = doctordto.getFormations().stream()
                    .map(this::convertToFormation)
                    .toList();
            List<experience> experiences = doctordto.getExperiences().stream()
                    .map(this::convertToexperience)
                    .toList();
            List<diploma> diplomas = doctordto.getDiplomas().stream()
                    .map(this::convertTodiploma)
                    .toList();
            List<horaire> horaires = doctordto.getHoraires().stream()
                    .map(this::convertTohoraire)
                    .toList();

            existingUser.getFormations().addAll(formations);
            existingUser.getExperiences().addAll(experiences);
            existingUser.getDiplomas().addAll(diplomas);
            existingUser.getHoraires().addAll(horaires);




            // Save the updated user to the database
            userRepository.save(existingUser);
        } else {
            // Handle the case where the user with the specified ID is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
        }
    }

    private formation convertToFormation(formation inputFormation) {
        formation newFormation = new formation();
        newFormation.setYear(inputFormation.getYear());
        newFormation.setSpeciality(inputFormation.getSpeciality());
        newFormation.setInstitute(inputFormation.getInstitute());
        return newFormation;
    }
    private experience convertToexperience(experience experience) {
        experience newexperience = new experience();
        newexperience.setYear(experience.getYear());
        newexperience.setTitre(experience.getTitre());
        newexperience.setLieu(experience.getLieu());
        return newexperience;
    }
    private diploma convertTodiploma(diploma diploma) {
        diploma ndiploma = new diploma();
        ndiploma.setYear(diploma.getYear());
        ndiploma.setSpeciality(diploma.getSpeciality());
        ndiploma.setInstitute(diploma.getInstitute());
        return ndiploma;
    }
    private horaire convertTohoraire(horaire horaire) {
        horaire horaire1 = new horaire();
        horaire1.setJour(horaire.getJour());
        horaire1.setHeure_depart(horaire.getHeure_depart());
        horaire1.setHeure_arrivee(horaire.getHeure_arrivee());
        return horaire1;
    }

    public List<User> findByRole(Role role) {
        return repository.findByRole(role);
    }

    public Optional<User> findByRoleAndId(Role role, String id) {
        return userRepository.findByRoleAndId(role, id);
    }



    public List<RendezVous> getDoctorAppointments(Role role, String id) {
        Optional<User> optionalUser = userRepository.findByRoleAndId(role, id);

        if (optionalUser.isPresent()) {
            User doctor = optionalUser.get();
            return doctor.getDoctorAppointments();
        }

        return Collections.emptyList();
    }


}
