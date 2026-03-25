import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // id -> (station, time)
    private Map<Integer, CheckIn> checkInMap;

    // "start#end" -> (totalTime, count)
    private Map<String, Trip> tripMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        tripMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.get(id);

        String route = checkIn.station + "#" + stationName;
        int travelTime = t - checkIn.time;

        Trip trip = tripMap.getOrDefault(route, new Trip());
        trip.totalTime += travelTime;
        trip.count++;

        tripMap.put(route, trip);
        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "#" + endStation;
        Trip trip = tripMap.get(route);
        return (double) trip.totalTime / trip.count;
    }

    // Helper class for check-in info
    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    // Helper class for trip stats
    class Trip {
        int totalTime = 0;
        int count = 0;
    }
}