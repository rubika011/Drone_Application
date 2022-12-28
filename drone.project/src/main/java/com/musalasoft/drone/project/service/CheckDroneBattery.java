package com.musalasoft.drone.project.service;

import com.musalasoft.drone.project.model.Drone;
import com.musalasoft.drone.project.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableScheduling
@EnableAsync
public class CheckDroneBattery {

    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(CheckDroneBattery.class);

    @Scheduled(fixedRate = 3000)
    public void checkDroneBattery() throws InterruptedException {
        List<Drone> drones = (List<Drone>) droneRepository.findAll();

        for(Drone drone: drones) {
            log.info("Battery level for drone with Serial Number: "+ drone.getSerial_number()+" is "+ drone.getBattery_capacity());
        }
        Thread.sleep(3000);
    }

}
