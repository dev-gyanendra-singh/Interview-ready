package LLD.ParkingLot;

import LLD.ParkingLot.Charges.FeeStrategy;
import LLD.ParkingLot.Charges.ParkingTicket;
import LLD.ParkingLot.VehicleFactory.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingFloorManager {
    List<ParkingFloor> floors;
    private final Map<String, ParkingTicket> activeTickets;
    private final FeeStrategy feeStrategy;

    public ParkingFloorManager( FeeStrategy feeStrategy) {
        this.floors = new ArrayList<>();
        this.feeStrategy = feeStrategy;
        this.activeTickets = new HashMap<>();
    }

    public void addFloor(int carSpots, int bikeSpots) {
        ParkingFloor floor = new ParkingFloor(floors.size(), carSpots, bikeSpots);
        floors.add(floor);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.getFreeSpot(vehicle.getType());
            if (spot != null && spot.park(vehicle)) {
                ParkingTicket ticket = new ParkingTicket(vehicle, feeStrategy);
                activeTickets.put(vehicle.getNumber(), ticket);
                System.out.println(vehicle.getNumber() + " parked at floor " + floor.floorId + ", spot " + spot.spotId);
                return ticket;
            }
        }
        System.out.println("No spot available for vehicle: " + vehicle.getNumber());
        return null;
    }

    public double unParkVehicle(String number) {
        ParkingTicket ticket = activeTickets.get(number);
        if (ticket == null) throw new RuntimeException("Vehicle not found");
        ticket.markExit();
        double fee = ticket.calculateFee();
        activeTickets.remove(number);
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.spots) {
                if (spot.isOccupied && spot.parkedVehicle.getNumber().equals(number)) {
                    spot.unpark();
                    System.out.println("Vehicle " + number + " unparked from floor " + floor.floorId + ", spot " + spot.spotId);
                    return fee;
                }
            }
        }
        System.out.println("Vehicle not found: " + number);
        return fee;
    }
}
