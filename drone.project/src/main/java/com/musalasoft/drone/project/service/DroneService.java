package com.musalasoft.drone.project.service;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.Medication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DroneService {

    void registerDrone(Drone drone);

    void addMedicationsToDrone(String serialNumber, List<Medication> medicationsToLoad);

    List<Medication> getAllMedications(String serialNumber);

    List<Drone> getAvailableDrones();

    double checkDroneBatteryLevel(String serialNumber);

}
