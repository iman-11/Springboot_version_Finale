package com.alibou.security.detailsdoctor;

import com.alibou.security.user.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "experience") // Specify the MongoDB collection name
public class experience {
    @Id
    @GeneratedValue
    private String id;
    private Integer year;
    private String titre;
    private String lieu;


}
