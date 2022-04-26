package com.airplane.booking.app;

import com.airplane.booking.app.base.SeatingService;
import com.airplane.booking.app.exception.SeatsNotAvailableException;
import com.airplane.booking.app.model.AirplaneSeatingRequest;
import com.airplane.booking.app.service.SequenceSeatingStrategyService;
import com.airplane.booking.app.util.AirplaneSeatingUtil;

/**
 * This is the main class which will invoke when the application started.
 * This application will create the request by initializing the seats and number of passenger parameter and
 * invoke {@link SeatingService} class to allocate the seats.
 */


public class AirplaneSeatingApp {

    SeatingService seatingService;

    public AirplaneSeatingApp(final SeatingService seatingService) {
        this.seatingService = seatingService;
    }

    /**
     * Create {@link AirplaneSeatingRequest} for the application.
     * @return
     */
    private static AirplaneSeatingRequest createRequest() {
        int[][] seats = { { 3,2 }, { 4,3 }, {2,3}, {3,4} };
        int numOfPassengers = 30;
        AirplaneSeatingRequest request = new AirplaneSeatingRequest(seats, numOfPassengers);
        return request;
    }

    /**
     * This method invoke {@link SequenceSeatingStrategyService} logic to assign the seats to the passenger
     * @return the result
     */
    public int[][][] allocateSeatsToPassenger() throws Exception {
        return this.seatingService.allocateSeats();
    }

    public static void main(String[] args) {
        AirplaneSeatingRequest request = createRequest();
        SeatingService seatingService = new SequenceSeatingStrategyService(request);
        AirplaneSeatingApp seatingApp = new AirplaneSeatingApp(seatingService);
        int[][][] result;
        try {
            result = seatingApp.allocateSeatsToPassenger();
            AirplaneSeatingUtil.INSTANCE.printResultInVisual(result, request.getSeats());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}