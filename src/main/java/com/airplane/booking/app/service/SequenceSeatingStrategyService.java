package com.airplane.booking.app.service;

import com.airplane.booking.app.base.SeatingService;
import com.airplane.booking.app.exception.SeatsNotAvailableException;
import com.airplane.booking.app.model.AirplaneSeatingRequest;
import com.airplane.booking.app.util.AirplaneSeatingUtil;

import java.util.*;

/**
 * Strategy pattern class to allocate the seats to the passengers.
 *
 */
public class SequenceSeatingStrategyService implements SeatingService {

    AirplaneSeatingRequest request;
    Map<Integer, List<Integer>> asileSeatIndexMap = new LinkedHashMap<>();
    Map<Integer, List<Integer>> middleSeatIndexMap = new LinkedHashMap<>();
    Map<Integer, List<Integer>> windowSeatIndexMap = new LinkedHashMap<>();

    public SequenceSeatingStrategyService(final AirplaneSeatingRequest request) {
        this.request = request;
    }

    /**
     * This overridden method runs the business logic and return the result in 3-dimensional array.
     * @return 3-d array
     */
    @Override
    public int[][][] allocateSeats() throws Exception {
        validate();
        final int maxColumn = AirplaneSeatingUtil.INSTANCE.findMaximumColumnSize(request.getSeats());
        populateSeatIndexes();
        return allocateSeatsToPassengers(maxColumn);

    }

    private void validate() throws Exception {
        int maxPassengerAllowed = 0;
        int[][] seats = request.getSeats();
        if(seats.length == 0) {
            throw new Exception("Invalid seats input.");
        } else if(request.getNumOfPassengers() <= 0) {
            throw new Exception("Passenger value should be greater than 0.");
        }
        for(int i = 0; i <seats.length; i++) {
            maxPassengerAllowed += seats[i][0] * seats[i][1];
        }
        if(request.getNumOfPassengers() > maxPassengerAllowed)
            throw new SeatsNotAvailableException("Seats are not available for all the passengers.");

    }

    /**
     * This method will populate the cache based on the specified seats.
     * This will populate the cache for ezch type (i.e Aisle, Window and Middle) and which will be used while
     * allocating the seats.
     */
    private void populateSeatIndexes() {
        int[][] seats = request.getSeats();
        for(int i = 0; i < request.getSeats().length; i++) {
            asileSeatIndexMap.put(i, new ArrayList<>(1));
            if(i == 0) {
                asileSeatIndexMap.get(i).add(seats[i][0] - 1);
                windowSeatIndexMap.put(i, new ArrayList<>(1));
                windowSeatIndexMap.get(i).add(0);
            } else if (i == request.getSeats().length - 1) {
                asileSeatIndexMap.get(i).add(0);
                windowSeatIndexMap.put(i, new ArrayList<>(1));
                windowSeatIndexMap.get(i).add(seats[i][0] - 1);
            } else {
                asileSeatIndexMap.get(i).add(0);
                asileSeatIndexMap.get(i).add(seats[i][0] - 1);
            }
            if(seats[i][0] - 2 > 0)  {
                middleSeatIndexMap.put(i, new ArrayList<>(seats[i][0] - 2));
                for(int j = 1; j <= seats[i][0] - 2; j++) {
                    middleSeatIndexMap.get(i).add(j);
                }
            }

        }
    }

    /**
     * This method will allocate the seats to passengers.
     * @param maxColumn maximum column possible
     * @return the result in 3-d array
     */
    private int[][][] allocateSeatsToPassengers(final int maxColumn) throws SeatsNotAvailableException {
        int[][][] result = new int[request.getSeats().length][][];
        int passengerStartIndex = 1;
        passengerStartIndex = allocateSeatsToPassenger(passengerStartIndex, maxColumn, result, asileSeatIndexMap);
        passengerStartIndex = allocateSeatsToPassenger(passengerStartIndex, maxColumn, result, windowSeatIndexMap);
        passengerStartIndex = allocateSeatsToPassenger(passengerStartIndex, maxColumn, result, middleSeatIndexMap);

        return result;

    }

    /**
     * This method will allocate the aisle seats to the passenger.
     * @param passengerStartIndex the starting index of the passenger
     * @param maxColumn maximum column
     * @param result the populated result
     * @return the last passenger index
     */
    private int allocateSeatsToPassenger(int passengerStartIndex, final int maxColumn,
                                         final int[][][] result, final Map<Integer, List<Integer>> seatIndexMap) throws SeatsNotAvailableException {

        int[][] seats = request.getSeats();
        for (int i = 0; i < maxColumn; i++) {
            for (int j = 0; j < seats.length; j++) {
                if(i < seats[j][1]) {
                    if(result[j] == null)
                        result[j] = new int[seats[j][1]][seats[j][0]];
                    for(int k = 0; k < (seatIndexMap.get(j) != null ? seatIndexMap.get(j).size() : 0 );k++) {
                        if(passengerStartIndex <= request.getNumOfPassengers()) {
                            result[j][i][seatIndexMap.get(j).get(k)] =  passengerStartIndex++;
                        }
                    }
                }
            }
        }
        return passengerStartIndex;
    }
}
