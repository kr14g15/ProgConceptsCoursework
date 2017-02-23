import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;

public class UKMapAirportSelectionEvent extends Event {

    private Point2D position;

    public static final EventType<UKMapAirportSelectionEvent> POSITION_SELECTED = new EventType(ANY, "POSITION_SELECTED");
    public static final EventType<UKMapAirportSelectionEvent> POSITION_CHANGED = new EventType(ANY, "POSITION_CHANGED");

    public UKMapAirportSelectionEvent(EventType<UKMapAirportSelectionEvent> eventType, Point2D position) {
        super(eventType);
        this.position = position;
    }

    public Point2D getPosition(){
        return position;
    }

}