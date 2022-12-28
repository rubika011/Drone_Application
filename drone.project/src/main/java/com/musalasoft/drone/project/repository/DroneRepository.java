package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Drone;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findDronesByState(@Param("state") String state);

    @Modifying
    @Query(value = "update DRONE set state = :state where serial_number= :serialNumber", nativeQuery = true)
    @Transactional
    void updateDroneState(@Param("state") String state, @Param("serialNumber") String serialNumber);

}
