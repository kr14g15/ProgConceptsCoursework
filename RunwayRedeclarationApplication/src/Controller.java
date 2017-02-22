import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class Controller {
    @FXML BorderPane borderPaneMainWindow;
    @FXML BorderPane borderPaneLeftWindow;
    @FXML BorderPane borderPaneUKMap;
    @FXML BorderPane borderPaneSmallAirportImage;
    @FXML
    public void initialize() {
        borderPaneMainWindow.setCenter(new TopViewRunway2DVisualization());
        borderPaneLeftWindow.setCenter(new SideViewRunway2DVisualization());
        borderPaneUKMap.setCenter(new UKMapAirportSelection2DVisualization());
    }
}
