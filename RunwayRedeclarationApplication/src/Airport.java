import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Airport{

    //region privateVariables
    private String strName;
    private Point2D pointUKMapPosition;
    private Image image;
    //endregion

    //region publicMethods
    public Airport(String name, Point2D position){
        this.strName = name;
        this.pointUKMapPosition = position;
    }

    public String getName() {
        return strName;
    }

    public Point2D getUKMapPosition() {
        return pointUKMapPosition;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String strName) {
        this.strName = strName;
    }

    public void setUKMapPosition(Point2D pointUKMapPosition) {
        this.pointUKMapPosition = pointUKMapPosition;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    //endregion

    //region publicEvents

    @Override
    public String toString() {
        return strName;
    }

    //endregion
}
