# AirplaneSeatingApp

This application will helpful to allocate the seats to the required passenger.

## Package Info
   
   ** base ** - package contains base interface files
   ** model ** - package contains the required models for the seating application
   ** service ** - package containing service classes for the application. business logic will be in this package.
   ** util ** - package contains the utility classes
   
## To run the application

  The AirplaneSeatingApp class holds the main function which will be invoked when we run the application. This creates a request which contains the seat arrangement and number of passengers input and will trigger the SeatingService base class to trigger the business logic of this application. This Seating service class provides a way to add customized allocation strategy for the future purpose. Currently, we created SequenceSeatingStratgeyService class, which will allocate the seat in the sequence order and aisle, winow and middle seats order. Finally, AirplaneServiceApp will get the 3-D array from the service class. This result will displayed in the sequencial order (visually) to understand the allocated seats.
  
## Test cases covered

  1. Valid cases where the seats are allocated correctly
  2. Invalid seat scenario
  3. Invalid passenger count scenario
  4. Number of Passenger exceeded the avaliable seats.
  
