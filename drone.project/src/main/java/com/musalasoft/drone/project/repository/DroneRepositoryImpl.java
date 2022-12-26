package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.DroneState;
import com.musalasoft.drone.project.model.Medication;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DroneRepositoryImpl implements CustomDroneRepo {

    @Autowired
    DroneRepository droneRepository;

    @Override
    public void addMedicationsToDrone(String serialNumber, List<Medication> medications) {
        if (droneRepository.findById(serialNumber).stream().iterator().hasNext()) {
            Drone drone = droneRepository.findById(serialNumber).stream().iterator().next();
            drone.setState(DroneState.LOADING);
            droneRepository.save(drone);
            double maxWeight = drone.getWeight_limit();
            double totalWeight = 0;
            List<Medication> medicationsAllowed = new ArrayList<>();
            for (Medication medication: medications) {
                totalWeight = totalWeight + medication.getWeight();
                if (totalWeight > maxWeight) {
                    break;
                }
                medicationsAllowed.add(medication);
            }
            drone.setMedications(medicationsAllowed);
            drone.setState(DroneState.LOADED);
            droneRepository.save(drone);
        }
    }
}
