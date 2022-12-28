package com.musalasoft.drone.project.service;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.DroneState;
import com.musalasoft.drone.project.model.Medication;
import com.musalasoft.drone.project.repository.DroneRepository;
import com.musalasoft.drone.project.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public void registerDrone(Drone drone) {
        droneRepository.save(drone);
    }

    @Override
    public void addMedicationsToDrone(String serialNumber, List<Medication> medications) {

        if (droneRepository.findById(serialNumber).stream().iterator().hasNext()) {
            Drone drone = droneRepository.findById(serialNumber).stream().iterator().next();
            if (drone.getBattery_capacity() < 25) {
               throw new NotAcceptableStatusException("Cannot load medications when the battery level is below 25%");
            }
            double totalWeight = 0;
            double alreadyLoaded = 0;
            String messageToAppend = "";
            if (drone.getState().equals(DroneState.LOADING.toString()) ||
                    drone.getState().equals(DroneState.LOADED.toString())) {
                alreadyLoaded = checkTotalWeightLoadedInDrone(serialNumber);
                totalWeight = alreadyLoaded;
                messageToAppend = alreadyLoaded != 0 ? ("and " + alreadyLoaded + "grame is alreadyLoaded") : "";
            }
            drone.setState(DroneState.LOADING.toString());
            droneRepository.updateDroneState(DroneState.LOADING.toString(), serialNumber);
            double maxWeight = drone.getWeight_limit();

            List<Medication> medicationsAllowed = new ArrayList<>();

            for (Medication medication: medications) {
                totalWeight = totalWeight + medication.getWeight();
                if (totalWeight > maxWeight) {
                    throw new InternalError("The medication load exceeded the weight limit. " +
                            "Maximum weight limit is " + maxWeight + messageToAppend);
                }
                medicationRepository.save(medication);
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
        return droneRepository.findDronesByState(DroneState.IDLE.toString());
    }

    @Override
    public double checkDroneBatteryLevel(String serialNumber) {
        if (droneRepository.findById(serialNumber).stream().iterator().hasNext()) {
            Drone drone = droneRepository.findById(serialNumber).stream().iterator().next();
            return drone.getBattery_capacity();
        }
        throw new NoSuchElementException("No drone with given serial number exist");
    }

    private double checkTotalWeightLoadedInDrone(String serialNumber) {
        List<Medication> medicationsLoaded = medicationRepository.findMedicationsLoaded(serialNumber);
        double totalWeight = 0;
        for (Medication medication: medicationsLoaded) {
            totalWeight = totalWeight + medication.getWeight();
        }
        return totalWeight;
    }
}
