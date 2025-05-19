package LLD.ParkingLot.Charges;

public class FLatRate implements FeeStrategy{
    private final double flatRate;
    public FLatRate(double flatRate) {
        this.flatRate = flatRate;
    }
    @Override
    public double calculateFee(long durationInHours) {
        return flatRate;
    }
}
