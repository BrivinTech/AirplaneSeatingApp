package com.airplane.booking.app.base;

import com.airplane.booking.app.exception.SeatsNotAvailableException;

/**
 * A interface for the Seating service. Other classes will implement this interface and provide
 * custom handling of seat allocation.
 */
public interface SeatingService {

    public int[][][] allocateSeats() throws Exception;
}
