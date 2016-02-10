package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.enums.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for TeamService. Contains utility for team creation
 *
 * @author Kish Alexey
 */
public class TeamCreator {

    /**
     * Returns list of team members, according to the needs of the crew for a specific aircraft.
     *
     * @param plane - aircraft for which formed the team
     * @return list of members of the crew for a specific aircraft
     */
    public static List<String> getPlanePositions(Plane plane) {
        List<String> positions = new ArrayList<>();
        Map<Position, Integer> team = plane.getPlaneCrew();
        for (int i = 0; i < team.get(Position.PILOT); i++) {
            positions.add(Position.PILOT.toString());
        }
        for (int i = 0; i < team.get(Position.NAVIGATOR); i++) {
            positions.add(Position.NAVIGATOR.toString());
        }
        for (int i = 0; i < team.get(Position.RADIOOPERATOR); i++) {
            positions.add(Position.RADIOOPERATOR.toString());
        }
        for (int i = 0; i < team.get(Position.STEWARDESS); i++) {
            positions.add(Position.STEWARDESS.toString());
        }
        return positions;
    }
}
