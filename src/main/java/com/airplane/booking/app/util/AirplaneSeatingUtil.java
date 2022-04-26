package com.airplane.booking.app.util;

/**
 * A singleton utility class, which provides the utility function for the {@link com.airplane.booking.app.AirplaneSeatingApp}
 */
public enum AirplaneSeatingUtil {
    INSTANCE;

    /**
     * This method will return the maximum column valur possible by iterating all the layout of the seats.
     * @return int maximum column value
     */
    public int findMaximumColumnSize(final int[][] seats) {
        int maxColumn = 0;
        for(int i = 0; i < seats.length; i++) {
            maxColumn = Math.max(maxColumn, seats[i][1]);
        }
        return maxColumn;
    }

    /**
     * A Utility function to display the result in a visual manner.
     * @param result the result
     * @param seats input of the seats
     */
    public void printResultInVisual(final int[][][] result, final int[][] seats) {
        for(int row = 0; row < 4; row++) {
            for(int i = 0; i < result.length; i++) {
                for(int j = 0; j < result[i][0].length; j++) {
                    if(row > seats[i][1] - 1) {
                        System.out.print("  ");
                    } else {
                        System.out.print(result[i][row][j] + " ");
                    }
                }
                System.out.print("|| ");
            }
            System.out.println("");
        }
    }
}
