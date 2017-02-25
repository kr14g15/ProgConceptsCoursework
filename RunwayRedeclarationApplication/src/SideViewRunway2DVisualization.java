import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SideViewRunway2DVisualization extends Pane {
    //region PrivateVariables
    private Runway runway;
    private ResizableCanvas canvas;

    private double doubleCanvasWidth = 100;
    private double doubleCanvasHeight = 200;

    private double doubleXOffset = 0;
    private double doubleYOffset = 0;

    private double doubleAirportLength;
    private double doubleAirportHeight;
    private double doubleSkyAndGround;

    private double doubleRunwayX;
    private double doubleRunwayY;
    private double doubleRunwayLength;
    private double doubleRunwayHeight;
    //endregion

    //region publicMethods

    SideViewRunway2DVisualization() {
        canvas = new ResizableCanvas();
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        getChildren().add(canvas);
    }

    public void refresh(){
        canvas.draw();
    }

    public void setRunway(Runway runway){
        this.runway = runway;
    }
    //endregion


    class ResizableCanvas extends Canvas {

    ResizableCanvas() {
        widthProperty().addListener(it -> draw());
        heightProperty().addListener(it -> draw());
    }

    //region PublicMethods
    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
    //endregion

    //region PrivateMethods
    private void draw() {
        doubleCanvasWidth = this.getWidth();
        doubleCanvasHeight = this.getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        if(runway != null) {
            doubleAirportLength = 2 * runway.getBlastProtection() + 2 * runway.getStripEnd() + runway.getTORA();
            doubleAirportHeight = runway.getAirportHeight();
            doubleSkyAndGround = runway.getSkyHeight() + runway.getGroundHeight();
            drawSky(gc);
            drawGround(gc);
            drawRunwayStrip(gc);

            double doubleTextHeight = (new Text()).getLayoutBounds().getHeight();
            drawLinedText(gc, "TORA " + runway.getTORA(), doubleRunwayX, doubleRunwayY + doubleRunwayHeight + doubleTextHeight, doubleRunwayLength);
            drawLinedText(gc, "TODA " + runway.getTODA(), doubleRunwayX, doubleRunwayY + doubleRunwayHeight + 3 * doubleTextHeight, doubleRunwayLength);
            drawLinedText(gc, "ASDA " + runway.getASDA(), doubleRunwayX, doubleRunwayY + doubleRunwayHeight + 5 * doubleTextHeight, doubleRunwayLength);
        }
    }

    private void drawSky(GraphicsContext gc) {
        gc.setFill(Color.rgb(178,178,254));
        gc.fillRect(0, 0, doubleCanvasWidth, normalize(runway.getSkyHeight(), doubleSkyAndGround, doubleCanvasHeight));
    }

    private void drawGround(GraphicsContext gc) {
        gc.setFill(Color.rgb(222,184,135));
        gc.fillRect(0,
                normalize(runway.getSkyHeight(), doubleSkyAndGround, doubleCanvasHeight),
                doubleCanvasWidth,
                normalize(runway.getGroundHeight(), doubleSkyAndGround, doubleCanvasHeight));
    }

    private void drawRunwayStrip(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        doubleRunwayX =  runway.getBlastProtection();
        doubleRunwayY = runway.getSkyHeight();
        doubleRunwayLength = 2*runway.getStripEnd() + runway.getTORA();
        doubleRunwayHeight = runway.getAirportHeight();

        gc.fillRect(normalize(doubleRunwayX, doubleAirportLength, doubleCanvasWidth),
                normalize(doubleRunwayY - doubleAirportHeight/2, doubleSkyAndGround, doubleCanvasHeight),
                normalize(doubleRunwayLength, doubleAirportLength, doubleCanvasWidth),
                normalize(doubleAirportHeight, doubleSkyAndGround, doubleCanvasHeight));
    }

    private void drawLinedText(GraphicsContext gc, String text, double x1, double y1, double length) {
        gc.setFill(Color.BLACK);
        Text txt = new Text(text);
        double doubleTextLength = txt.getLayoutBounds().getWidth();
        double doubleTextHeight = txt.getLayoutBounds().getHeight();
        gc.setLineWidth(1);

        double a = normalize(x1, doubleAirportLength, doubleCanvasWidth);
        double b = normalize(y1, doubleSkyAndGround, doubleCanvasHeight)-doubleTextHeight/2;
        double c = normalize(x1, doubleAirportLength, doubleCanvasWidth);
        double d = normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/2;
        gc.strokeLine(normalize(x1, doubleAirportLength, doubleCanvasWidth),
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight)-doubleTextHeight/2,
                normalize(x1, doubleAirportLength, doubleCanvasWidth),
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/2);

        gc.strokeLine(normalize(x1, doubleAirportLength, doubleCanvasWidth),
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight),
                normalize((x1 + x1 + length)/2, doubleAirportLength, doubleCanvasWidth) - doubleTextLength/2,
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight));
        gc.fillText(txt.getText(),
                normalize((x1 + x1 + length)/2, doubleAirportLength, doubleCanvasWidth) - doubleTextLength/2,
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/4);

        gc.strokeLine(normalize((x1 + x1 + length)/2, doubleAirportLength, doubleCanvasWidth) + doubleTextLength/2,
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight),
                normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight));

        gc.strokeLine(normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight)-doubleTextHeight/2,
                normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/2);
    }

    private double normalize(double value,double from, double to){
        return value*to/from;
    }
    //endregion

}
}