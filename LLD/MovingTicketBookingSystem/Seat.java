package LLD.MovingTicketBookingSystem;

class Seat {
    private int seatNumber;
    private SeatStatus status;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.status = SeatStatus.AVAILABLE;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public boolean bookSeat() {
        if (status == SeatStatus.AVAILABLE) {
            status = SeatStatus.BOOKED;
            return true;
        }
        return false;
    }
}

