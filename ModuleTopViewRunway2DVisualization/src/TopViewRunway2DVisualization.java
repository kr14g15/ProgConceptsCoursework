import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;

public class TopViewRunway2DVisualization extends JFXPanel {
    //region PublicVariables
    //endregion

    //region PrivateVariables
    private Runway runway;
    private int intXOffset = 0;
    private int intYOffset = 0;
    //endregion

    //region PublicMethods
    public TopViewRunway2DVisualization() {
        Platform.runLater(this::init);
    }
    //endregion

    //region PrivateMethods
    private void init() {
        Scene scene = createScene();
        this.setScene(scene);
    }

    private Scene createScene() {
        Group root = new Group();
        Scene scene =  new  Scene(root, 500, 250, Color.DARKBLUE);
        Canvas canvas = new Canvas(scene.getWidth(),scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawBackground(gc, canvas);
        drawRunway(gc,canvas);

        root.getChildren().add(canvas);
        return (scene);
    }

    private void drawBackground(GraphicsContext gc, Canvas canvas) {
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawRunway(GraphicsContext gc, Canvas canvas) {
        Text text = new Text("Runway 100m");
        double runwayWidth = canvas.getWidth()*0.75;
        double runwayHeight = canvas.getHeight()*0.25;

        gc.setFill(Color.GRAY);
        gc.fillRect(canvas.getWidth()/2 - runwayWidth/2 + intXOffset,
                canvas.getHeight()/2 - runwayHeight/2 + intYOffset,
                runwayWidth,
                runwayHeight);

        gc.setStroke(Color.WHITE);
        gc.strokeText(text.getText(),
                canvas.getWidth()/2 + intXOffset - text.getLayoutBounds().getWidth()/2,
                canvas.getHeight()/2 + runwayHeight/2 + text.getLayoutBounds().getHeight() + intYOffset);
    }

    //endregion

    //region Events
    //endregion
}