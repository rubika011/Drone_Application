package com.musalasoft.drone.project.controller;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.model.Medication;
import com.musalasoft.drone.project.service.DroneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class DroneController {

    @Autowired
    private DroneServiceImpl droneService;

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

    @ExceptionHandler({
            NoSuchElementException.class, InternalError.class, NotAcceptableStatusException.class
    })
    public ResponseEntity<Map<String, String>> handleException(Exception e) {

        Map<String, String> errorResponse = Map.of(
                "message", e.getLocalizedMessage(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/registerDrone", method=RequestMethod.POST, consumes = "application/json")
    public String registerDrone(@RequestBody Drone drone) {
        droneService.registerDrone(drone);
        return "Drone registered successfully.";
    }

    @RequestMapping(value = "/loadMedications/{serial_number}", method=RequestMethod.PUT, consumes = "application/json")
    public String loadMedications(@PathVariable("serial_number") String serialNumber,
                                  @RequestBody List<Medication> medications) {
        droneService.addMedicationsToDrone(serialNumber, medications);
        return "Medications loaded successfully.";
    }

    @RequestMapping(value = "/getLoadedMedications/{serial_number}", method=RequestMethod.GET,
            produces = "application/json")
    public List<Medication> getLoadedMedications(@PathVariable("serial_number") String serialNumber) {
        return droneService.getAllMedications(serialNumber);
    }

    @RequestMapping(value = "/getAvailableDrones", method=RequestMethod.GET, produces = "application/json")
    public List<Drone> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @RequestMapping(value = "/checkDroneBatteryLevel/{serial_number}", method=RequestMethod.GET,
            produces = "application/json")
    public double checkDroneBatteryLevel(@PathVariable("serial_number") String serialNumber) {
        return droneService.checkDroneBatteryLevel(serialNumber);
    }
}
