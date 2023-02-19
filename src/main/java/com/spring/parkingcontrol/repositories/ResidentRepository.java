package com.spring.parkingcontrol.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.parkingcontrol.models.ResidentModel;

@Repository
public interface ResidentRepository extends JpaRepository<ResidentModel, UUID>{

}
