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
    private ColorScheme colorScheme;

    private double doubleLeftClearwayX;
    private double doubleLeftClearwayY;
    private double doubleLeftClearwayLength;
    private double doubleLeftClearwayHeight;

    private double doubleRightClearwayX;
    private double doubleRightClearwayY;
    private double doubleRightClearwayLength;
    private double doubleRightClearwayHeight;

    private double doubleLeftStopwayX;
    private double doubleLeftStopwayY;
    private double doubleLeftStopwayLength;
    private double doubleLeftStopwayHeight;

    private double doubleRightStopwayX;
    private double doubleRightStopwayY;
    private double doubleRightStopwayLength;
    private double doubleRightStopwayHeight;
    //endregion

    //region publicMethods
    SideViewRunway2DVisualization() {
        colorScheme = ColorScheme.DEFAULT;
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

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }
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
                doubleAirportLength = 2 * runway.getRESA() + 2 * runway.getStripEnd() + runway.getRunwayStripLength() + runway.getLeftClearwayLength() + runway.getRightClearwayLength();
                doubleAirportHeight = runway.getAirportHeight();
                doubleSkyAndGround = runway.getSkyHeight() + runway.getGroundHeight() + doubleAirportHeight;
                drawSky(gc);
                drawGround(gc);
                drawRunwayStrip(gc);
                drawLeftClearway(gc);
                drawRightClearway(gc);
                drawLeftStopway(gc);
                drawRightStopway(gc);
                drawText(gc);
                drawDirection(gc);
            }
        }

        private void drawSky(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.SkyColor);
            gc.fillRect(0, 0, doubleCanvasWidth, normalize(runway.getSkyHeight(), doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }

        private void drawGround(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.GroundColor);
            gc.fillRect(0,
                    normalize(runway.getSkyHeight(), doubleSkyAndGround, doubleCanvasHeight),
                    doubleCanvasWidth,
                    normalize(runway.getGroundHeight(), doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }

        private void drawRunwayStrip(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.RunwayStripColor);
            doubleRunwayX =  runway.getRESA()+runway.getLeftClearwayLength()+runway.getStripEnd();
            doubleRunwayY = runway.getSkyHeight();
            doubleRunwayLength = runway.getRunwayStripLength();
            doubleRunwayHeight = runway.getAirportHeight();

            gc.fillRect(normalize(doubleRunwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRunwayY - doubleAirportHeight/2, doubleSkyAndGround, doubleCanvasHeight),
                    normalize(doubleRunwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleAirportHeight, doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }

        private void drawLeftStopway(GraphicsContext gc) {
            gc.save();
            gc.setFill(Color.rgb(20,20,20,0.5));
            doubleLeftStopwayX = runway.getRESA() + runway.getStripEnd() + runway.getLeftClearwayLength()-runway.getLeftStopwayLength();
            doubleLeftStopwayY = runway.getSkyHeight();
            doubleLeftStopwayLength = runway.getLeftStopwayLength();
            doubleLeftStopwayHeight = runway.getAirportHeight();

            gc.fillRect(normalize(doubleLeftStopwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftStopwayY - doubleLeftStopwayHeight/2, doubleSkyAndGround, doubleCanvasHeight),
                    normalize(doubleLeftStopwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftStopwayHeight, doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }

        private void drawRightStopway(GraphicsContext gc) {
            gc.save();
            gc.setFill(Color.rgb(20,20,20,0.5));
            doubleRightStopwayX = doubleLeftStopwayX + doubleLeftStopwayLength + runway.getRunwayStripLength();
            doubleRightStopwayY = runway.getSkyHeight();
            doubleRightStopwayLength = runway.getRightStopwayLength();
            doubleRightStopwayHeight = runway.getAirportHeight();

            gc.fillRect(normalize(doubleRightStopwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightStopwayY - doubleLeftStopwayHeight/2, doubleSkyAndGround, doubleCanvasHeight),
                    normalize(doubleRightStopwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightStopwayHeight, doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }

        private void drawLeftClearway(GraphicsContext gc) {
            gc.save();
            gc.setFill(Color.rgb(120,120,120,0.5));
            doubleLeftClearwayX =  runway.getRESA() + runway.getStripEnd();
            doubleLeftClearwayY = runway.getSkyHeight();
            doubleLeftClearwayLength = runway.getLeftClearwayLength();
            doubleLeftClearwayHeight = runway.getAirportHeight();

            gc.fillRect(normalize(doubleLeftClearwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftClearwayY - doubleLeftClearwayHeight/2, doubleSkyAndGround, doubleCanvasHeight),
                    normalize(doubleLeftClearwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftClearwayHeight, doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }

        private void drawRightClearway(GraphicsContext gc) {
            gc.save();
            gc.setFill(Color.rgb(120,120,120,0.5));
            doubleRightClearwayX =  runway.getRESA() + runway.getLeftClearwayLength() + runway.getStripEnd() + runway.getRunwayStripLength();
            doubleRightClearwayY = runway.getSkyHeight();
            doubleRightClearwayLength = runway.getRightClearwayLength();
            doubleRightClearwayHeight = runway.getAirportHeight();

            gc.fillRect(normalize(doubleRightClearwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightClearwayY - doubleRightClearwayHeight/2, doubleSkyAndGround, doubleCanvasHeight),
                    normalize(doubleRightClearwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightClearwayHeight, doubleSkyAndGround, doubleCanvasHeight));
            gc.restore();
        }


        private void drawLinedText(GraphicsContext gc, String text, double x1, double y1, double length) {
            gc.save();
            gc.setFill(Color.BLACK);
            Text txt = new Text(text);
            Double newFontSizeDouble = Math.hypot(this.getWidth(), this.getHeight())/100;
            int newFontSizeInt = newFontSizeDouble.intValue();
            gc.setFont(new javafx.scene.text.Font(newFontSizeInt));
            txt.setFont(new javafx.scene.text.Font(newFontSizeInt));
            double doubleTextLength = txt.getLayoutBounds().getWidth();
            double doubleTextHeight = txt.getLayoutBounds().getHeight();
            gc.setLineWidth(1);

            double a = normalize(x1, doubleAirportLength, doubleCanvasWidth);
            double b = normalize(y1, doubleSkyAndGround, doubleCanvasHeight)-doubleTextHeight/2;
            double c = normalize(x1, doubleAirportLength, doubleCanvasWidth);
            double d = normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/2;
            gc.strokeLine(normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleSkyAndGround, doubleCanvasHeight)-doubleTextHeight/4,
                    normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/4);

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
                    normalize(y1, doubleSkyAndGround, doubleCanvasHeight)-doubleTextHeight/4,
                    normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleSkyAndGround, doubleCanvasHeight)+doubleTextHeight/4);
            gc.restore();
        }
        private void drawText(GraphicsContext gc) {
            gc.save();
            Text txt = new Text("DIRECTION");
            Double newFontSizeDouble = Math.hypot(this.getWidth(), this.getHeight())/100;
            gc.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            txt.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            double doubleTextHeight = txt.getLayoutBounds().getHeight();

            drawLinedText(gc, "" + (int)runway.getLeftClearwayLength(), doubleLeftClearwayX, doubleLeftClearwayY - doubleLeftClearwayHeight/2 - doubleTextHeight, doubleLeftClearwayLength);
            drawLinedText(gc, "" + (int)runway.getLeftStopwayLength(), doubleLeftStopwayX, doubleLeftStopwayY - doubleLeftStopwayHeight / 2 - 2* doubleTextHeight, doubleLeftStopwayLength);

            drawLinedText(gc, "" + (int)runway.getRightClearwayLength(), doubleRightClearwayX, doubleRightClearwayY-doubleRightClearwayHeight/2 - doubleTextHeight, doubleRightClearwayLength);
            drawLinedText(gc, "" + (int)runway.getRightStopwayLength(), doubleRightStopwayX, doubleRightStopwayY - doubleRightStopwayHeight / 2 - 2* doubleTextHeight, doubleRightStopwayLength);

            drawLinedText(gc, "TORA " + (int)runway.getTORA(), doubleRunwayX, doubleRunwayY + doubleRunwayHeight / 2 + doubleTextHeight, runway.getTORA());
            drawLinedText(gc, "TODA " + (int)runway.getTODA(), doubleRunwayX, doubleRunwayY + doubleRunwayHeight / 2 + 2 * doubleTextHeight, runway.getTODA());
            drawLinedText(gc, "ASDA " + (int)runway.getASDA(), doubleRunwayX, doubleRunwayY + doubleRunwayHeight / 2 + 3 * doubleTextHeight, runway.getASDA());
            gc.restore();
        }

        private void drawDirection(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.CharactersColor);
            Text txt = new Text("DIRECTION");
            Double newFontSizeDouble = Math.hypot(this.getWidth(), this.getHeight())/100;
            gc.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            txt.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            double doubleTextLength = txt.getLayoutBounds().getWidth();
            double doubleTextHeight = txt.getLayoutBounds().getHeight();

            double doubleDirectionX = doubleRunwayX;
            double doubleDirectionY = doubleRunwayY - (doubleRunwayY - 0)/2;
            double doubleDirectionLength = runway.getRunwayStripLength()/4;

            double doubleDirectionTriangleLength = runway.getRunwayStripLength()/60;
            double doubleDirectionTriangleWidth = runway.getRunwayStripWidth()/10;

            gc.fillText(txt.getText(),
                    normalize(doubleDirectionX+doubleDirectionLength/2, doubleAirportLength, doubleCanvasWidth) - doubleTextLength/2,
                    normalize(doubleDirectionY, doubleSkyAndGround, doubleCanvasHeight) - doubleTextHeight/4);

            gc.strokeLine(normalize(doubleDirectionX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleDirectionY, doubleSkyAndGround, doubleCanvasHeight),
                    normalize(doubleDirectionX + doubleDirectionLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleDirectionY, doubleSkyAndGround, doubleCanvasHeight));

            gc.fillPolygon(new double[]{normalize(doubleDirectionX + doubleDirectionLength, doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleDirectionX + doubleDirectionLength, doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleDirectionX + doubleDirectionLength + doubleDirectionTriangleLength, doubleAirportLength, doubleCanvasWidth)},
                    new double[]{normalize(doubleDirectionY - doubleDirectionTriangleWidth, doubleSkyAndGround, doubleCanvasHeight),
                            normalize(doubleDirectionY + doubleDirectionTriangleWidth, doubleSkyAndGround, doubleCanvasHeight),
                            normalize(doubleDirectionY, doubleSkyAndGround, doubleCanvasHeight)}, 3);

            gc.restore();
        }

        private double normalize(double value,double from, double to){
            return value*to/from;
        }
        //endregion

    }
}