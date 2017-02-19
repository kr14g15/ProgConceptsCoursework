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

    Group root;
    Scene scene;
    Canvas canvas;
    GraphicsContext gc;

    private int intCanvasWidth;
    private int intCanvasHeight;

    private int intXOffset = 0;
    private int intYOffset = 0;

    private int intAirportWidth;
    private int intAirportHeight;

    private int intInstrumentStripX;
    private int intInstrumentStripY;
    private int intInstrumentStripWidth;
    private int intInstrumentStripHeight;

    private int intCGAX;
    private int intCGAY;
    private int intCGAWidth;
    private int intCGAHeight;

    private int intRunwayStripX;
    private int intRunwayStripY;
    private int intRunwayStripWidth;
    private int intRunwayStripHeight;
    //endregion

    //region PublicMethods
    public TopViewRunway2DVisualization() {
        this.intCanvasWidth = 1000;
        this.intCanvasHeight = 500;
        runway = new Runway();
        Platform.runLater(this::init);
    }
    //endregion

    //region PrivateMethods
    private void init() {
        root = new Group();
        scene =  new  Scene(root, intCanvasWidth, intCanvasHeight, Color.rgb(173,255,47));
        canvas = new Canvas(scene.getWidth(),scene.getHeight());
        gc = canvas.getGraphicsContext2D();

        scene.widthProperty().addListener(observable -> redraw());
        scene.heightProperty().addListener(observable -> redraw());

        root.getChildren().add(canvas);

        this.setScene(scene);
    }

    private void redraw() {
        intCanvasWidth = this.getParent().getWidth();
        intCanvasHeight = this.getParent().getHeight();

        drawBackground(gc);
        drawInstrumentStrip(gc);
        drawCGA(gc);
        drawRunwayStrip(gc);
    }

    private void drawBackground(GraphicsContext gc) {
        gc.setFill(Color.rgb(34,139,34));
        intAirportWidth = 2 * runway.getBlastProtection() + 2* runway.getStripEnd() + runway.getTORA();
        intAirportHeight = 2 * runway.getInstrumentStrip();

        gc.fillRect(0, 0, intCanvasWidth, intCanvasHeight);
    }

    private void drawInstrumentStrip(GraphicsContext gc) {
        gc.setFill(Color.rgb(60,179,113));
        intInstrumentStripX = runway.getBlastProtection();
        intInstrumentStripY = 0;
        intInstrumentStripWidth = 2*runway.getStripEnd() + runway.getTORA();
        intInstrumentStripHeight = 2*runway.getInstrumentStrip();

        gc.fillRect(normalize(intInstrumentStripX, intAirportWidth, intCanvasWidth),
                normalize(intInstrumentStripY, intAirportHeight, intCanvasHeight),
                normalize(intInstrumentStripWidth, intAirportWidth, intCanvasWidth),
                normalize(intInstrumentStripHeight, intAirportHeight, intCanvasHeight));
    }

    private void drawCGA(GraphicsContext gc) {
        gc.setFill(Color.rgb(144,238,144));
        intCGAX = intInstrumentStripX;
        intCGAY = intInstrumentStripY+runway.getInstrumentStrip()-runway.getVisualStrip();
        intCGAWidth = 2*runway.getStripEnd() + runway.getTORA();
        intCGAHeight = 2*runway.getVisualStrip();

        gc.fillRect(normalize(intCGAX, intAirportWidth, intCanvasWidth),
                normalize(intCGAY, intAirportHeight, intCanvasHeight),
                normalize(intCGAWidth, intAirportWidth, intCanvasWidth),
                normalize(intCGAHeight, intAirportHeight, intCanvasHeight));
    }

    private void drawRunwayStrip(GraphicsContext gc) {
        Text text = new Text("Runway 100m");
        gc.setFill(Color.GRAY);
        intRunwayStripX = intCGAX + runway.getStripEnd();
        intRunwayStripY = intCGAY+runway.getVisualStrip();
        intRunwayStripWidth = runway.getTORA();
        intRunwayStripHeight = runway.getStripHeight();

        gc.fillRect(normalize(intRunwayStripX, intAirportWidth, intCanvasWidth),
                normalize(intRunwayStripY - intRunwayStripHeight/2, intAirportHeight, intCanvasHeight),
                normalize(intRunwayStripWidth, intAirportWidth, intCanvasWidth),
                normalize(intRunwayStripHeight, intAirportHeight, intCanvasHeight));
    }

    private int normalize(int value,int from, int to){
        return value*to/from;
    }

    //endregion

    //region Events
    //endregion
}