import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;

public class UKMapAirportSelectionEvent extends Event {

    private Point2D position;
    private Airport airport;

    static final EventType<UKMapAirportSelectionEvent> POSITION_SELECTED = new EventType(ANY, "POSITION_SELECTED");
    static final EventType<UKMapAirportSelectionEvent> POSITION_CHANGED = new EventType(ANY, "POSITION_CHANGED");
    static final EventType<UKMapAirportSelectionEvent> AIRPORT_SELECTED = new EventType(ANY, "AIRPORT_SELECTED");

    public UKMapAirportSelectionEvent(EventType<UKMapAirportSelectionEvent> eventType, Airport airport) {
        super(eventType);
        this.airport = airport;
    }

    UKMapAirportSelectionEvent(EventType<UKMapAirportSelectionEvent> eventType, Point2D position) {
        super(eventType);
        this.position = position;
    }

    public Point2D getPosition(){
        return position;
    }
    public Airport getAirport() {return airport;}

}