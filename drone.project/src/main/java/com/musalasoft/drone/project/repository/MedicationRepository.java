package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, String> {


    @Query(value = "select * from medication where serial_number= :serialNumber", nativeQuery = true)
    List<Medication> findMedicationsLoaded(@Param("serialNumber") String serialNumber);
}