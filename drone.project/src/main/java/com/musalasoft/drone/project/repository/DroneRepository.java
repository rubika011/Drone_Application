package com.musalasoft.drone.project.repository;

import com.musalasoft.drone.project.model.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, String> {

}
