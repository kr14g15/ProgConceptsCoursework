import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class TopViewRunway2DVisualization extends Pane {

    //region PrivateVariables
    private Runway runway;
    private ResizableCanvas canvas;

    private double doubleCanvasWidth = 100;
    private double doubleCanvasHeight = 200;

    private double doubleXOffset = 0;
    private double doubleYOffset = 0;

    private double doubleAirportLength;
    private double doubleAirportWidth;

    private double doubleInstrumentStripX;
    private double doubleInstrumentStripY;
    private double doubleInstrumentStripLength;
    private double doubleInstrumentStripWidth;

    private double doubleCGAX;
    private double doubleCGAY;
    private double doubleCGALength;
    private double doubleCGAWidth;

    private double intThresholdStripNumber;
    private double doubleRunwayStripX;
    private double doubleRunwayStripY;
    private double doubleRunwayStripLength;
    private double doubleRunwayStripWidth;

    private double doubleLeftThresholdX;
    private double doubleLeftThresholdY;
    private double doubleLeftThresholdMarkingsStripLength;
    private double doubleLeftThresholdMarkingsStripWidth;

    private double doubleRightThresholdX;
    private double doubleRightThresholdY;
    private double doubleRightThresholdLength;
    private double doubleRightThresholdWidth;

    private double doubleRightRunwayNumberX;
    private double doubleRightRunwayNumberY;
    private double doubleRightRunwayNumberLength;
    private double doubleRightRunwayNumberWidth;

    private double doubleLeftRunwayNumberX;
    private double doubleLeftRunwayNumberY;
    private double doubleLeftRunwayNumberLength;
    private double doubleLeftRunwayNumberWidth;

    private double doubleStripesX;
    private double doubleStripesY;
    private double doubleStripesLength;
    private double doubleStripesWidth;

    private ColorScheme colorScheme;
    //endregion

    //region publicMethods
    public void setRunway(Runway runway){
        this.runway = runway;
    }

    TopViewRunway2DVisualization() {
        colorScheme = ColorScheme.DEFAULT;
        canvas = new ResizableCanvas();
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        getChildren().add(canvas);
    }

    public void refresh(){
        canvas.draw();
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
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

            drawBackground(gc);
            if(runway != null) {
                doubleAirportLength = 2 * runway.getBlastProtection() + 2* runway.getStripEnd() + runway.getTORA();
                doubleAirportWidth = 2 * runway.getInstrumentStrip();
                doubleRunwayStripWidth = runway.getRunwayStripWidth();

                if(doubleRunwayStripWidth <= 18.3) intThresholdStripNumber = 4;
                else if(doubleRunwayStripWidth <= 22.9) intThresholdStripNumber = 6;
                else if(doubleRunwayStripWidth <= 30.5) intThresholdStripNumber = 8;
                else if(doubleRunwayStripWidth <= 45.7) intThresholdStripNumber = 12;
                else intThresholdStripNumber = 16;

                drawInstrumentStrip(gc);
                drawCGA(gc);
                drawRunwayStrip(gc);
                drawLeftThresholdsMarkings(gc);
                drawRightThresholdsMarkings(gc);
                drawLeftRunwayNumber(gc);
                drawRightRunwayNumber(gc);
                drawStripes(gc);

                double doubleTextHeight = (new Text()).getLayoutBounds().getHeight();
                drawLinedText(gc, "TORA " + runway.getTORA(), doubleRunwayStripX, doubleRunwayStripY + doubleRunwayStripWidth / 2 + doubleTextHeight / 2, doubleRunwayStripLength);
                drawLinedText(gc, "TODA " + runway.getTODA(), doubleRunwayStripX, doubleRunwayStripY + doubleRunwayStripWidth / 2 + 2 * doubleTextHeight / 2, doubleRunwayStripLength);
                drawLinedText(gc, "ASDA " + runway.getASDA(), doubleRunwayStripX, doubleRunwayStripY + doubleRunwayStripWidth / 2 + 3 * doubleTextHeight / 2, doubleRunwayStripLength);
            }
        }

        private void drawBackground(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.BackgroundColor);
            gc.fillRect(0, 0, doubleCanvasWidth, doubleCanvasHeight);
            gc.restore();
        }

        private void drawInstrumentStrip(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.CAAColor);
            doubleInstrumentStripX = runway.getBlastProtection();
            doubleInstrumentStripY = 0;
            doubleInstrumentStripLength = 2*runway.getStripEnd() + runway.getTORA();
            doubleInstrumentStripWidth = 2*runway.getInstrumentStrip();

            gc.fillRect(normalize(doubleInstrumentStripX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleInstrumentStripY, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleInstrumentStripLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleInstrumentStripWidth, doubleAirportWidth, doubleCanvasHeight));
            gc.restore();
        }

        private void drawCGA(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.CAAColor);
            doubleCGAX = doubleInstrumentStripX;
            doubleCGAY = doubleInstrumentStripY+runway.getInstrumentStrip()-runway.getVisualStrip();
            doubleCGALength = 2*runway.getStripEnd() + runway.getTORA();
            doubleCGAWidth = 2*runway.getVisualStrip();

            gc.fillRect(normalize(doubleCGAX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleCGAY, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleCGALength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleCGAWidth, doubleAirportWidth, doubleCanvasHeight));
            gc.restore();
        }

        private void drawRunwayStrip(GraphicsContext gc) {
            gc.save();
            Text text = new Text("Runway 100m");
            gc.setFill(colorScheme.RunwayStripColor);
            doubleRunwayStripX = doubleCGAX + runway.getStripEnd();
            doubleRunwayStripY = doubleCGAY + runway.getVisualStrip();
            doubleRunwayStripLength = runway.getTORA();
            doubleRunwayStripWidth = runway.getRunwayStripWidth();

            gc.fillRect(normalize(doubleRunwayStripX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRunwayStripY - doubleRunwayStripWidth/2, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleRunwayStripLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRunwayStripWidth, doubleAirportWidth, doubleCanvasHeight));
            gc.restore();
        }

        private void drawLeftThresholdsMarkings(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.StripesColor);
            doubleLeftThresholdX = doubleRunwayStripX + runway.getStartToThresholdMarkingsLength();
            doubleLeftThresholdY = doubleRunwayStripY;
            doubleLeftThresholdMarkingsStripLength = runway.getThresholdMarkingsStripLength();
            doubleLeftThresholdMarkingsStripWidth = runway.getThresholdMarkingsStripWidth();

            for(int i = 0; i < intThresholdStripNumber; i++){
                gc.fillRect(normalize(doubleLeftThresholdX, doubleAirportLength, doubleCanvasWidth),
                        normalize(doubleLeftThresholdY - doubleRunwayStripWidth/2 +
                                        ((doubleRunwayStripWidth/intThresholdStripNumber - doubleLeftThresholdMarkingsStripWidth))/2 +
                                        (i*(doubleRunwayStripWidth/intThresholdStripNumber)),
                                doubleAirportWidth, doubleCanvasHeight),
                        normalize(doubleLeftThresholdMarkingsStripLength, doubleAirportLength, doubleCanvasWidth),
                        normalize(doubleLeftThresholdMarkingsStripWidth, doubleAirportWidth, doubleCanvasHeight));
            }
            gc.restore();
        }

        private void drawRightThresholdsMarkings(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.StripesColor);
            doubleRightThresholdX = doubleRunwayStripX + doubleRunwayStripLength - doubleRightThresholdLength - runway.getStartToThresholdMarkingsLength();
            doubleRightThresholdY = doubleRunwayStripY;
            doubleRightThresholdLength = runway.getThresholdMarkingsStripLength();
            doubleRightThresholdWidth = runway.getThresholdMarkingsStripWidth();

            for(int i = 0; i < intThresholdStripNumber; i++){
                gc.fillRect(normalize(doubleRightThresholdX, doubleAirportLength, doubleCanvasWidth),
                        normalize(doubleRightThresholdY - doubleRunwayStripWidth/2 +
                                        ((doubleRunwayStripWidth/intThresholdStripNumber - doubleRightThresholdWidth))/2 +
                                        (i*(doubleRunwayStripWidth/intThresholdStripNumber)),
                                doubleAirportWidth, doubleCanvasHeight),
                        normalize(doubleRightThresholdLength, doubleAirportLength, doubleCanvasWidth),
                        normalize(doubleRightThresholdWidth, doubleAirportWidth, doubleCanvasHeight));
            }
            gc.restore();
        }

        private void drawRightRunwayNumber(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.StripesColor);
            gc.setFont(new javafx.scene.text.Font(20));

            int newDegree = 36 - runway.getDegree();

            Text direction = new Text((runway.getDirection() == 'L')? "R" : "L");
            Text degree = new Text((newDegree < 10)? "0" + String.valueOf(newDegree) : String.valueOf(newDegree));

            double doubleDirectionLength = direction.getLayoutBounds().getWidth();
            double doubleDirectionHeight = direction.getLayoutBounds().getHeight();

            double doubleDegreeLength = degree.getLayoutBounds().getWidth();
            double doubleDegreeHeight = degree.getLayoutBounds().getHeight();

            doubleRightRunwayNumberLength = normalize(doubleDirectionHeight + doubleDegreeHeight, doubleCanvasWidth, doubleAirportLength) + runway.getLetterToNumberLength();
            doubleRightRunwayNumberX = doubleRightThresholdX - doubleRightRunwayNumberLength - runway.getThresholdMarkingsToLetterLength();
            doubleRightRunwayNumberY = doubleLeftThresholdY;
            doubleRightRunwayNumberWidth = runway.getCharacterWidth();

            gc.translate(normalize(doubleRightRunwayNumberX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightRunwayNumberY , doubleAirportWidth, doubleCanvasHeight) - doubleDirectionLength/2) ;
            gc.rotate(-90);

            gc.fillText(degree.getText(), -doubleDegreeLength, doubleDegreeHeight);
            gc.fillText(direction.getText(), -doubleDirectionLength, doubleDirectionHeight + doubleDegreeHeight  + normalize(runway.getLetterToNumberLength(), doubleAirportLength, doubleCanvasWidth));

            gc.restore();
        }

        private void drawLeftRunwayNumber(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.StripesColor);
            gc.setFont(new javafx.scene.text.Font(20));

            Text direction = new Text(String.valueOf(runway.getDirection()));
            Text degree = new Text((runway.getDegree() < 10)? "0" + String.valueOf(runway.getDegree()) : String.valueOf(runway.getDegree()));

            double doubleDirectionLength = direction.getLayoutBounds().getWidth();
            double doubleDirectionHeight = direction.getLayoutBounds().getHeight();

            double doubleDegreeLength = degree.getLayoutBounds().getWidth();
            double doubleDegreeHeight = degree.getLayoutBounds().getHeight();

            doubleLeftRunwayNumberX = doubleLeftThresholdX + doubleLeftThresholdMarkingsStripLength + runway.getThresholdMarkingsToLetterLength();
            doubleLeftRunwayNumberY = doubleLeftThresholdY;
            doubleLeftRunwayNumberLength = normalize(doubleDirectionHeight + doubleDegreeHeight, doubleCanvasWidth, doubleAirportLength) + runway.getLetterToNumberLength();
            doubleLeftRunwayNumberWidth = 0;

            gc.translate(normalize(doubleLeftRunwayNumberX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftRunwayNumberY , doubleAirportWidth, doubleCanvasHeight) - doubleDirectionLength/2) ;
            gc.rotate(90);
            gc.fillText(direction.getText(), 0,0);
            gc.fillText(degree.getText(), -doubleDegreeLength/2,- doubleDirectionHeight - normalize(runway.getLetterToNumberLength(), doubleAirportLength, doubleCanvasWidth));

            gc.restore();
        }

        private void drawStripes(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.StripesColor);
            doubleStripesX = doubleLeftRunwayNumberX +  doubleLeftRunwayNumberLength + runway.getNumberToHorizontalStripeLength();
            doubleStripesY = doubleRunwayStripY - runway.getStripesWidth()/2;
            doubleStripesLength = runway.getStripesLength();
            doubleStripesWidth = runway.getStripesWidth();

            int i = 0;
            do {
                gc.fillRect(normalize(doubleStripesX +
                                (i) * runway.getStripesDifference() +
                                (i) * runway.getStripesLength()
                        , doubleAirportLength, doubleCanvasWidth),
                        normalize(doubleStripesY, doubleAirportWidth, doubleCanvasHeight),
                        normalize(doubleStripesLength, doubleAirportLength, doubleCanvasWidth),
                        normalize(doubleStripesWidth, doubleAirportWidth, doubleCanvasHeight));
                i++;
            }while(doubleStripesX + (i)*runway.getStripesDifference() + (i+1)*runway.getStripesLength() + runway.getNumberToHorizontalStripeLength() < doubleRightRunwayNumberX);
            gc.restore();
        }

        private void drawLinedText(GraphicsContext gc, String text, double x1, double y1, double length) {
            gc.save();
            gc.setFill(colorScheme.CharactersColor);
            Text txt = new Text(text);
            double doubleTextLength = txt.getLayoutBounds().getWidth();
            double doubleTextHeight = txt.getLayoutBounds().getHeight();
            gc.setLineWidth(1);

            gc.strokeLine(normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)-doubleTextHeight/2,
                    normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)+doubleTextHeight/2);

            gc.strokeLine(normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight),
                    normalize((x1 + x1 + length)/2, doubleAirportLength, doubleCanvasWidth) - doubleTextLength/2,
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight));
            gc.fillText(txt.getText(),
                    normalize((x1 + x1 + length)/2, doubleAirportLength, doubleCanvasWidth) - doubleTextLength/2,
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)+doubleTextHeight/4);

            gc.strokeLine(normalize((x1 + x1 + length)/2, doubleAirportLength, doubleCanvasWidth) + doubleTextLength/2,
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight),
                    normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight));

            gc.strokeLine(normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)-doubleTextHeight/2,
                    normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)+doubleTextHeight/2);
            gc.restore();
        }

        private double normalize(double value,double from, double to){
            return value*to/from;
        }
        //endregion

    }
}