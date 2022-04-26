package com.airplane.booking.app.usecase;


import com.airplane.booking.app.AirplaneSeatingApp;
import com.airplane.booking.app.model.AirplaneSeatingRequest;
import com.airplane.booking.app.service.SequenceSeatingStrategyService;
import com.airplane.booking.app.util.AirplaneSeatingUtil;
import static org.junit.Assert.assertEquals;

public class AirplaneSeatingAppTest {

    void setUp() {
    }

    /**
     * Test case to verify the valid scenario
     */
    void  validBookingScenario() {

        System.out.println("Executing validBookingScenario test case...");
        int[][] seats = { { 3,2 }, { 4,3 }, {2,3}, {3,4} };
        int numOfPassengers = 30;
        AirplaneSeatingRequest request = new AirplaneSeatingRequest(seats, numOfPassengers);
        SequenceSeatingStrategyService seatingService = new SequenceSeatingStrategyService(request);
        AirplaneSeatingApp seatingApp = new AirplaneSeatingApp(seatingService);
        int[][][] result;
        try {
            result = seatingApp.allocateSeatsToPassenger();
            AirplaneSeatingUtil.INSTANCE.printResultInVisual(result, request.getSeats());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test case to verify the case when the required passengers exceeded the allowed passenger.
     */
    void  passengerExceededSeatScenario() {
        System.out.println("Executing passengerExceededSeatScenario test case...");

        int[][] seats = { { 3,2 }, { 4,3 }, {2,3}, {3,4} };
        int numOfPassengers = 40;
        AirplaneSeatingRequest request = new AirplaneSeatingRequest(seats, numOfPassengers);
        SequenceSeatingStrategyService seatingService = new SequenceSeatingStrategyService(request);
        AirplaneSeatingApp seatingApp = new AirplaneSeatingApp(seatingService);
        int[][][] result;
        try {
            result = seatingApp.allocateSeatsToPassenger();
            AirplaneSeatingUtil.INSTANCE.printResultInVisual(result, request.getSeats());
        } catch (Exception e) {
            assertEquals("Seats are not available for all the passengers.", e.getMessage());
        }
    }

    /**
     * Test case to verify the application handles the invalid seat value.
     */
    void  emptySeatScenario() {

        System.out.println("Executing the empty seat scenario...");
        int[][] seats = { };
        int numOfPassengers = 1;
        AirplaneSeatingRequest request = new AirplaneSeatingRequest(seats, numOfPassengers);
        SequenceSeatingStrategyService seatingService = new SequenceSeatingStrategyService(request);
        AirplaneSeatingApp seatingApp = new AirplaneSeatingApp(seatingService);
        int[][][] result;
        try {
            result = seatingApp.allocateSeatsToPassenger();
            AirplaneSeatingUtil.INSTANCE.printResultInVisual(result, request.getSeats());
        } catch (Exception e) {
            assertEquals("Invalid seats input.", e.getMessage());
        }
    }

    /**
     * Test case to verify the invalid passenger value input.
     */
    void  invalidPassengerValue() {

        System.out.println("Executing the invalidPassengerValue test case...");
        int[][] seats = { { 3,2 }, { 4,3 }, {2,3}, {3,4} };
        int numOfPassengers = -1;
        AirplaneSeatingRequest request = new AirplaneSeatingRequest(seats, numOfPassengers);
        SequenceSeatingStrategyService seatingService = new SequenceSeatingStrategyService(request);
        AirplaneSeatingApp seatingApp = new AirplaneSeatingApp(seatingService);
        int[][][] result;
        try {
            result = seatingApp.allocateSeatsToPassenger();
            AirplaneSeatingUtil.INSTANCE.printResultInVisual(result, request.getSeats());
        } catch (Exception e) {
            assertEquals("Passenger value should be greater than 0.", e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Test cases started running...");
        AirplaneSeatingAppTest seatingAppTest = new AirplaneSeatingAppTest();
        seatingAppTest.emptySeatScenario();;
        seatingAppTest.passengerExceededSeatScenario();
        seatingAppTest.invalidPassengerValue();
        seatingAppTest.validBookingScenario();
    }

}
