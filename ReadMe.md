# Drone Application

#### Technologies Used
- Java
- Spring Boot
- Maven
- Rest API
- JPA
- H2(In memory Database)

#### Prerequisite:
- Java 19
- Intellij or Eclipse IDE
- PostMan (for testing API)

#### Build
- Clone the git repo https://github.com/rubika011/Drone_Application.git
- Import the project in IDE and Run the spring boot application(double-click spring-boot:run)
![img_1.png](images/Springboot_run.PNG)

#### Test API calls

##### 1.Registering a drone
    
- URL: http://localhost:8080/registerDrone
- Sample data:
{
    "serial_number": "TECH-123",
    "model": "CruiserWeight",
    "weight_limit": 400,
    "battery_capacity": 75.5,
    "state": "IDLE"
}
![img_2.png](images/RegisterDrone.PNG)

##### 2.Loading a drone with medication items
- URL: http://localhost:8080/loadMedications/TECH-123
- Sample data:
[{
    "name": "Panadol",
    "code": "PANADOL123",
    "weight": 100
  },
  {
    "name": "Amoxicilin",
    "code": "AMOX123",
    "weight": 100
}]
![img_3.png](images/LoadMedications.PNG)

##### 3.checking loaded medication items for a given drone 
- URL: http://localhost:8080/getLoadedMedications/FIX123456789

![img_4.png](images/getLoadedMedications.PNG)

##### 4.checking available drones for loading
- URL: http://localhost:8080/getAvailableDrones

![img_4.png](images/getAvailableDrones.PNG)

##### 5.check drone battery level for a given drone;
- URL: http://localhost:8080/checkDroneBatteryLevel/FIX123456789

![img_4.png](images/checkBatteryLevel.PNG)


