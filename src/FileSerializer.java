import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSerializer {
    public void serializeAirportList(Path path, ObservableList<Airport> airportList) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            ArrayList<Airport> airports = new ArrayList<Airport>(airportList);
            oos.writeObject(airports);
            oos.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ObservableList<Airport> deSerializeAirportList(Path path) {

        List<Airport> airportList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            airportList = (List<Airport>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableList(airportList);
    }
}
