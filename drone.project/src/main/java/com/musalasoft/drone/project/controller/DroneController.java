package com.musalasoft.drone.project.controller;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.Medication;
import com.musalasoft.drone.project.repository.DroneRepository;
import com.musalasoft.drone.project.repository.MedicationRepository;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DroneController {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<Map<String, String>> handleException(IllegalArgumentException e) {

        Map<String, String> errorResponse = Map.of(
                "message", e.getLocalizedMessage(),
                "status", HttpStatus.BAD_REQUEST.toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registerDrone")
    public String registerDrone(@RequestBody Drone drone) {
        droneRepository.save(drone);
        return "Drone registered successfully.";
    }

    @PostMapping("/loadMedications")
    public String loadMedications(@RequestBody String droneSerialNumber, List<Medication> medications) {
        for(Medication medication: medications) {
            medicationRepository.save(medication);
        }
        droneRepository.addMedicationsToDrone(droneSerialNumber, medications);
        return "Medications loaded successfully.";
    }

    @GetMapping("/getLoadedMedications/{serial_number}")
    public List<Medication> getLoadedMedications(@PathVariable("serial_number") String serialNumber) {
        return droneRepository.getAllMedications(serialNumber);
    }

    @GetMapping("/getAvailableDrones")
    public List<Drone> getAvailableDrones() {
        return droneRepository.getAvailableDrones();
    }
}
