package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.Medication;

import java.util.List;

public interface CustomDroneRepo {

    void addMedicationsToDrone(String serialNoOfDrone, List<Medication> medicationsToLoad);

    List<Medication> getAllMedications(String serialNumber);

    List<Drone> getAvailableDrones();

}
