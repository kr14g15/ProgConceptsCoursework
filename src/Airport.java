import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airport implements Serializable {

    //region privateVariables
    private String strName;
    private Point2D.Double pointUKMapPosition;
    private Image image;
    private List<Runway> listRunways;
    //endregion

    //region publicMethods
    public Airport(String name, Point2D.Double position, Image image){
        this.strName = name;
        this.pointUKMapPosition = position;
        this.image = image;
        this.listRunways = new ArrayList<>();
    }

    public String getName() {
        return strName;
    }

    public Point2D.Double getUKMapPosition() {
        return pointUKMapPosition;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String strName) {
        this.strName = strName;
    }

    public void setUKMapPosition(Point2D.Double pointUKMapPosition) {
        this.pointUKMapPosition = pointUKMapPosition;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Runway> getRunwayList() {
        return listRunways;
    }

    //endregion

    //region publicEvents
    @Override
    public String toString() {
        return strName;
    }

    //endregion
}
