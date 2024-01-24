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
@Document(collection = "formation") // Specify the MongoDB collection name

public class formation {
    @Id
    @GeneratedValue
    private String id;
    private Integer year;
    private String speciality;
    private String institute;


}
