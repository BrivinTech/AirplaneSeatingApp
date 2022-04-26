package com.airplane.booking.app.model;

import com.airplane.booking.app.service.SequenceSeatingStrategyService;

/**
 * Immutable class for mapping the user request to Java model. This request will be shared between the
 * {@link com.airplane.booking.app.AirplaneSeatingApp} and {@link SequenceSeatingStrategyService}
  */

public class AirplaneSeatingRequest {

    public AirplaneSeatingRequest(final int[][] seats, final int numOfPassengers) {
        this.seats = seats;
        this.numOfPassengers = numOfPassengers;
    }

    int[][] seats;
    int numOfPassengers;

    public int[][] getSeats() {
        return seats;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }
}
