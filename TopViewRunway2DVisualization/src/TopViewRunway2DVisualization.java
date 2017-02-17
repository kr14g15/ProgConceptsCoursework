import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class TopViewRunway2DVisualization extends JFXPanel {
    Runway runway;

    public TopViewRunway2DVisualization() {
        Platform.runLater(this::init);
    }

    private void init() {
        Scene scene = createScene();
        this.setScene(scene);
    }

    private static Scene createScene() {
        Group root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        Text text  =  new  Text();

        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Java FX test");

        root.getChildren().add(text);

        return (scene);
    }
}