package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.DroneState;
import com.musalasoft.drone.project.model.Medication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DroneRepositoryImpl implements CustomDroneRepo {

    @Autowired
    DroneRepository droneRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addMedicationsToDrone(String serialNumber, List<Medication> medications) {
        if (droneRepository.findById(serialNumber).stream().iterator().hasNext()) {
            Drone drone = droneRepository.findById(serialNumber).stream().iterator().next();
            if (drone.getState().equals(DroneState.LOADING.toString()) ||
                    drone.getState().equals(DroneState.LOADED.toString())) {

            }
            drone.setState(DroneState.LOADING.toString());
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
            drone.setState(DroneState.LOADED.toString());
            droneRepository.save(drone);
        } else {
            throw new NoSuchElementException("No drone with given serial number exist");
        }
    }

    @Override
    public List<Medication> getAllMedications(String serialNumber) {
        if (droneRepository.findById(serialNumber).stream().iterator().hasNext()) {
            Drone drone = droneRepository.findById(serialNumber).stream().iterator().next();
            return drone.getMedications();
        }
        throw new NoSuchElementException("No drone with given serial number exist");
    }

    @Override
    public List<Drone> getAvailableDrones() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Drone WHERE state = ?", Drone.class);
        query.setParameter(1, DroneState.IDLE.toString());
        return query.getResultList() != null ? query.getResultList() : new ArrayList<>();
    }


}
