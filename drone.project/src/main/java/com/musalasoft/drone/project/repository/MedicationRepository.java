package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, String> {

}