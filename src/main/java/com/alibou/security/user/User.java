package com.alibou.security.user;

import com.alibou.security.RendezVous.RendezVous;
import com.alibou.security.detailsdoctor.diploma;
import com.alibou.security.detailsdoctor.experience;
import com.alibou.security.detailsdoctor.formation;
import com.alibou.security.detailsdoctor.horaire;
import com.alibou.security.token.Token;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user") // Specify the MongoDB collection name
public class User implements UserDetails {

  @Id
  private String id; // Use String as the type for MongoDB

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private List<formation> formations;

  private List<experience> experiences;
  private List<diploma> diplomas;
  private String speciality;
  private String address;
  private Long telephone;
  private Date date_naissance;
  private String gender;
  private boolean mfaEnabled;
  private String secret;

  private ImageData imageData;

  private List<String> langues;

  private List<horaire> horaires;
  private Integer tarif_const;
  private Integer tarif_video;
  private List<RendezVous> patientAppointments; // Appointments made by the user as a patient
  private List<RendezVous> doctorAppointments; // Appointments related to the user as a doctor



  @Enumerated(EnumType.STRING)
  private Role role;

  // Adjust the relationship as needed for MongoDB
  // Note: MongoDB does not support joins in the same way as relational databases
  // Consider embedding or referencing the Token documents
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }


  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
