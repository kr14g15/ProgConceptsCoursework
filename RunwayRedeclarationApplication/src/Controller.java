import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class Controller {
    @FXML BorderPane borderPaneMainWindow;
    @FXML BorderPane borderPaneLeftWindow;
    @FXML
    public void initialize() {
        borderPaneMainWindow.setCenter(new TopViewRunway2DVisualization());
        borderPaneLeftWindow.setCenter(new SideViewRunway2DVisualization());

    }
}
