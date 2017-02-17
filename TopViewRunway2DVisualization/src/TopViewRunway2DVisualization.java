import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
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

    private Scene createScene() {
        Group root = new Group();
        Scene scene =  new  Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        Canvas canvas = new Canvas(this.getWidth(), this.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawRunway(gc);
        root.getChildren().add(canvas);

        return (scene);
    }

    private void drawRunway(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
    }
}