import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {

    private List<String> collection;
    private Place place;

    public User() {
        place = Place.GATE;
        collection = new ArrayList<String>();
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<String> getCollection() {
        return collection;
    }
}
