package LLD.ParkingLot;

import LLD.ParkingLot.VehicleFactory.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloorManager {
    List<ParkingFloor> floors;

    public ParkingFloorManager() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(int carSpots, int bikeSpots) {
        ParkingFloor floor = new ParkingFloor(floors.size(), carSpots, bikeSpots);
        floors.add(floor);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.getFreeSpot(vehicle.getType());
            if (spot != null && spot.park(vehicle)) {
                System.out.println(vehicle.getNumber() + " parked at floor " + floor.floorId + ", spot " + spot.spotId);
                return true;
            }
        }
        System.out.println("No spot available for vehicle: " + vehicle.getNumber());
        return false;
    }

    public boolean unParkVehicle(String number) {
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.spots) {
                if (spot.isOccupied && spot.parkedVehicle.getNumber().equals(number)) {
                    spot.unpark();
                    System.out.println("Vehicle " + number + " unparked from floor " + floor.floorId + ", spot " + spot.spotId);
                    return true;
                }
            }
        }
        System.out.println("Vehicle not found: " + number);
        return false;
    }
}
