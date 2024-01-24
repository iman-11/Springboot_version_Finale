package com.alibou.security.RendezVous;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RendezVousRepository extends MongoRepository<RendezVous, String> {
    List<RendezVous> findByidDoctor(String idDoctor);
    List<RendezVous> findByidDoctorAndDecision(String idDoctor, String decision);

}
