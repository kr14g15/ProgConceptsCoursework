import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class Controller {
    @FXML
    Pane paneMainDisplay;
    @FXML
    public void initialize() {
        final SwingNode node = new SwingNode();
        node.setContent(new TopViewRunway2DVisualization());
        paneMainDisplay.getChildren().add(node);
    }
}
