package LLD.ParkingLot.VehicleFactory;

abstract public class Vehicle {
    String number;
    VehicleType type;

    public Vehicle(String number, VehicleType type) {
        this.number = number;
        this.type = type;
    }

   public VehicleType getType() {
        return type;
    }
    public String getNumber() {
        return number;
    }
}