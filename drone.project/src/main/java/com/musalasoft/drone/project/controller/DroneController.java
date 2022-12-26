package com.musalasoft.drone.project.controller;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneController {

    @Autowired
    DroneRepository droneRepository;

    @PostMapping("/registerDrone")
    public String registerDrone(@RequestBody Drone drone) {
        droneRepository.save(drone);
        return "Drone registered successfully.";
    }
}
