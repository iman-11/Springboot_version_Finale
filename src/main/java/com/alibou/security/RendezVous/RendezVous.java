package com.alibou.security.RendezVous;
import com.alibou.security.user.ImageData;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RendezVous")
public class RendezVous {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String sexe;
    private String dateNaissance;
    private String adresse;
    private String ville;
    private String dateRendezVous;
    private String idDoctor;
    private Double longueur;
    private Double poids;
    private String maladie;
    private String zipCode;
    private String appointmentType;
    private String owner;
    private String email;
    private String id_patient;
    private String decision;
    private String status;
    private ImageData imageData;

}
