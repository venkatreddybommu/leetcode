import java.util.HashMap;
import java.util.Map;
class UndergroundSystem {
    private Map<Integer, CheckIn> checkInMap;
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
    class CheckIn {
        String station;
        int time;
        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
    class Trip {
        int totalTime = 0;
        int count = 0;
    }
}