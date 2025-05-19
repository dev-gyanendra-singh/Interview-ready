package LLD.ParkingLot.Charges;

import LLD.ParkingLot.VehicleFactory.Vehicle;

public class ParkingTicket {
    private final Vehicle vehicle;
    private final long entryTime;
    private long exitTime;
    private final FeeStrategy feeStrategy;

    public ParkingTicket(Vehicle vehicle, FeeStrategy feeStrategy) {
        this.vehicle = vehicle;
        this.entryTime = System.currentTimeMillis();
        this.feeStrategy = feeStrategy;
    }

    public void markExit() {
        this.exitTime = System.currentTimeMillis();
    }

    public double calculateFee() {
        long durationInHours = Math.max(1, (exitTime - entryTime) / (1000 * 60 * 60));
        return feeStrategy.calculateFee(durationInHours);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

