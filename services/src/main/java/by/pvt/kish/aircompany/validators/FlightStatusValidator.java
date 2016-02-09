package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.enums.FlightStatus;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.FlightService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Kish Alexey
 */
public class FlightStatusValidator {

    private static Logger logger = Logger.getLogger(FlightStatusValidator.class.getName());
    private static final String PAST = "Past";
    private static final String TODAY = "Today";
    private static final String FUTURE = "Future";
    private static final String MATCHED = "Matched";
    private static final String EMPTY = "Empty";

    public static void updateFlightsStatus() throws ServiceException {
        List<Flight> flights = FlightService.getInstance().getAll();
        for (Flight flight : flights) {
            if (checkDate(flight).equals(FUTURE) && checkTeam(flight).equals(EMPTY)) {
                if (flight.getStatus() != (FlightStatus.CREATED)){
                    FlightService.getInstance().setStatus(flight.getFid(), FlightStatus.CREATED.toString());
                    logger.info("Flight #" + flight.getFid() + " status changed to " + FlightStatus.CREATED);
                }
            } else if (checkDate(flight).equals(FUTURE) && checkTeam(flight).equals(MATCHED)) {
                if (flight.getStatus() != (FlightStatus.READY)){
                    FlightService.getInstance().setStatus(flight.getFid(), FlightStatus.READY.toString());
                    logger.info("Flight #" + flight.getFid() + " status changed to " + FlightStatus.READY);
                }
            } else if (checkDate(flight).equals(TODAY) && checkTeam(flight).equals(EMPTY)) {
                if (flight.getStatus() != (FlightStatus.CANCELED)){
                    FlightService.getInstance().setStatus(flight.getFid(), FlightStatus.CANCELED.toString());
                    logger.info("Flight #" + flight.getFid() + " status changed to " + FlightStatus.CANCELED);
                }
            } else if (checkDate(flight).equals(TODAY) && checkTeam(flight).equals(MATCHED)) {
                if (flight.getStatus() != (FlightStatus.DEPARTED)){
                    FlightService.getInstance().setStatus(flight.getFid(), FlightStatus.DEPARTED.toString());
                    logger.info("Flight #" + flight.getFid() + " status changed to " + FlightStatus.DEPARTED);
                }
            } else if (checkDate(flight).equals(PAST) && checkTeam(flight).equals(EMPTY)) {
                if (flight.getStatus() != (FlightStatus.CANCELED)){
                    FlightService.getInstance().setStatus(flight.getFid(), FlightStatus.CANCELED.toString());
                    logger.info("Flight #" + flight.getFid() + " status changed to " + FlightStatus.CANCELED);
                }
            } else if (checkDate(flight).equals(PAST) && checkTeam(flight).equals(MATCHED)) {
                if (flight.getStatus() != (FlightStatus.ARRIVAL)){
                    FlightService.getInstance().setStatus(flight.getFid(), FlightStatus.ARRIVAL.toString());
                    logger.info("Flight #" + flight.getFid() + " status changed to " + FlightStatus.ARRIVAL);
                }
            }
        }
    }

    private static String checkDate(Flight flight) {
        java.util.Date yesterdayDate = new java.util.Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        java.util.Date todayDate = new java.util.Date(System.currentTimeMillis());
        java.util.Date flightDate = flight.getDate();
        if (yesterdayDate.after(flightDate)) {
            return PAST;
        } else if (todayDate.before(flightDate)) {
            return FUTURE;
        } else {
            return TODAY;
        }
    }

    private static String checkTeam(Flight flight) {
        if (flight.getTeam().size() > 0) {
            return MATCHED;
        } else {
            return EMPTY;
        }
    }
}
