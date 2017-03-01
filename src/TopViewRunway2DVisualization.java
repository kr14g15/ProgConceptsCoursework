import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
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

    private double doubleSmallVisualStripX;
    private double doubleSmallVisualStripY;
    private double doubleSmallVisualStripLength;
    private double doubleSmallVisualStripWidth;

    private double doubleLeftClearwayX;
    private double doubleLeftClearwayY;
    private double doubleLeftClearwayLength;
    private double doubleLeftClearwayWidth;

    private double doubleRightClearwayX;
    private double doubleRightClearwayY;
    private double doubleRightClearwayLength;
    private double doubleRightClearwayWidth;

    private double doubleLeftStopwayX;
    private double doubleLeftStopwayY;
    private double doubleLeftStopwayLength;
    private double doubleLeftStopwayWidth;

    private double doubleRightStopwayX;
    private double doubleRightStopwayY;
    private double doubleRightStopwayLength;
    private double doubleRightStopwayWidth;

    private double doubleLargeVisualStripX;
    private double doubleLargeVisualStripY;
    private double doubleLargeVisualStripLength;
    private double doubleLargeVisualStripWidth;

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

            if(runway != null) {
                doubleAirportLength = 2 * runway.getRESA() + 2* runway.getStripEnd() + runway.getRunwayStripLength()+runway.getLeftClearwayLength()+runway.getRightClearwayLength();
                doubleAirportWidth = 2 * runway.getInstrumentStrip();
                doubleRunwayStripWidth = runway.getRunwayStripWidth();

                if(doubleRunwayStripWidth <= 18.3) intThresholdStripNumber = 4;
                else if(doubleRunwayStripWidth <= 22.9) intThresholdStripNumber = 6;
                else if(doubleRunwayStripWidth <= 30.5) intThresholdStripNumber = 8;
                else if(doubleRunwayStripWidth <= 45.7) intThresholdStripNumber = 12;
                else intThresholdStripNumber = 16;

                drawBackground(gc);
                drawInstrumentStrip(gc);
                drawCGA(gc);
                drawRunwayStrip(gc);
                drawClearway(gc);
                drawStopway(gc);
                drawDisplacedThreshold(gc);
                drawLeftThresholdsMarkings(gc);
                drawRightThresholdsMarkings(gc);
                drawLeftRunwayNumber(gc);
                drawRightRunwayNumber(gc);
                drawStripes(gc);
                drawDirection(gc);
                drawText(gc);
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
            gc.setFill(colorScheme.RunwayStripColor);
            doubleInstrumentStripX = runway.getRESA();
            doubleInstrumentStripY = 0;
            doubleInstrumentStripLength = 2*runway.getStripEnd() + runway.getRunwayStripLength()+runway.getLeftClearwayLength()+runway.getRightClearwayLength();
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
            doubleSmallVisualStripX = doubleInstrumentStripX;
            doubleSmallVisualStripY = doubleInstrumentStripY+runway.getInstrumentStrip()-runway.getSmallVisualStripWidth();
            doubleSmallVisualStripLength = 2*runway.getStripEnd() + runway.getRunwayStripLength()+runway.getLeftClearwayLength()+runway.getRightClearwayLength();
            doubleSmallVisualStripWidth = 2*runway.getSmallVisualStripWidth();

            doubleLargeVisualStripX = doubleSmallVisualStripX + runway.getStripEnd() + runway.getRunwayToLargeVisualStripLength()+runway.getLeftClearwayLength();
            doubleLargeVisualStripY = doubleInstrumentStripY+runway.getInstrumentStrip()-runway.getLargeVisualStripWidth();
            doubleLargeVisualStripLength = runway.getRunwayStripLength() - 2*runway.getRunwayToLargeVisualStripLength();
            doubleLargeVisualStripWidth = 2*runway.getLargeVisualStripWidth();

            gc.setFill(colorScheme.CAAColor);

            gc.fillRect(normalize(doubleSmallVisualStripX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleSmallVisualStripY, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleSmallVisualStripLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleSmallVisualStripWidth, doubleAirportWidth, doubleCanvasHeight));

            gc.fillRect(normalize(doubleLargeVisualStripX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLargeVisualStripY, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleLargeVisualStripLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLargeVisualStripWidth, doubleAirportWidth, doubleCanvasHeight));

            gc.fillPolygon(new double[]{normalize(doubleSmallVisualStripX + runway.getStripEnd() + runway.getRunwayToSmallVisualStripLength() +runway.getLeftClearwayLength(), doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleLargeVisualStripX, doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleLargeVisualStripX, doubleAirportLength, doubleCanvasWidth)},
                    new double[]{normalize(doubleSmallVisualStripY, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleSmallVisualStripY, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleLargeVisualStripY, doubleAirportWidth, doubleCanvasHeight)}, 3);

            gc.fillPolygon(new double[]{normalize(doubleSmallVisualStripX + runway.getStripEnd() + runway.getRunwayToSmallVisualStripLength() +runway.getLeftClearwayLength(), doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleLargeVisualStripX, doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleLargeVisualStripX, doubleAirportLength, doubleCanvasWidth)},
                    new double[]{normalize(doubleSmallVisualStripY + doubleSmallVisualStripWidth, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleSmallVisualStripY + doubleSmallVisualStripWidth, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleLargeVisualStripY + doubleLargeVisualStripWidth, doubleAirportWidth, doubleCanvasHeight)}, 3);

            gc.fillPolygon(new double[]{normalize(doubleSmallVisualStripX + doubleSmallVisualStripLength - runway.getStripEnd() - runway.getRunwayToSmallVisualStripLength()-runway.getRightClearwayLength(), doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleSmallVisualStripX + doubleSmallVisualStripLength - runway.getStripEnd() - runway.getRunwayToLargeVisualStripLength()-runway.getRightClearwayLength(), doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleSmallVisualStripX + doubleSmallVisualStripLength - runway.getStripEnd() - runway.getRunwayToLargeVisualStripLength()-runway.getRightClearwayLength(), doubleAirportLength, doubleCanvasWidth)},
                    new double[]{normalize(doubleSmallVisualStripY, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleSmallVisualStripY, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleLargeVisualStripY, doubleAirportWidth, doubleCanvasHeight)}, 3);

            gc.fillPolygon(new double[]{normalize(doubleSmallVisualStripX + doubleSmallVisualStripLength - runway.getStripEnd() - runway.getRunwayToSmallVisualStripLength()-runway.getRightClearwayLength(), doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleSmallVisualStripX + doubleSmallVisualStripLength - runway.getStripEnd() - runway.getRunwayToLargeVisualStripLength()-runway.getRightClearwayLength(), doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleSmallVisualStripX + doubleSmallVisualStripLength - runway.getStripEnd() - runway.getRunwayToLargeVisualStripLength()-runway.getRightClearwayLength(), doubleAirportLength, doubleCanvasWidth)},
                    new double[]{normalize(doubleSmallVisualStripY + doubleSmallVisualStripWidth, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleSmallVisualStripY + doubleSmallVisualStripWidth, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleLargeVisualStripY + doubleLargeVisualStripWidth, doubleAirportWidth, doubleCanvasHeight)}, 3);
            gc.restore();
        }

        private void drawRunwayStrip(GraphicsContext gc) {
            gc.save();
            Text text = new Text("Runway 100m");
            gc.setFill(colorScheme.RunwayStripColor);
            doubleRunwayStripX = doubleInstrumentStripX + runway.getStripEnd() + runway.getLeftClearwayLength();
            doubleRunwayStripY = doubleInstrumentStripY + runway.getInstrumentStrip();
            doubleRunwayStripLength = runway.getRunwayStripLength();
            doubleRunwayStripWidth = runway.getRunwayStripWidth();

            gc.fillRect(normalize(doubleRunwayStripX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRunwayStripY - doubleRunwayStripWidth/2, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleRunwayStripLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRunwayStripWidth, doubleAirportWidth, doubleCanvasHeight));
            gc.restore();
        }

        private void drawClearway(GraphicsContext gc) {
            gc.save();
            gc.setFill(Color.rgb(120,120,120,0.5));
            doubleLeftClearwayX = doubleInstrumentStripX + runway.getStripEnd();
            doubleLeftClearwayY = doubleInstrumentStripY + runway.getInstrumentStrip();
            doubleLeftClearwayLength = runway.getLeftClearwayLength();
            doubleLeftClearwayWidth = runway.getLeftClearwayWidth();

            doubleRightClearwayX = doubleLeftClearwayX + doubleLeftClearwayLength + runway.getRunwayStripLength();
            doubleRightClearwayY = doubleInstrumentStripY + runway.getInstrumentStrip();
            doubleRightClearwayLength = runway.getRightClearwayLength();
            doubleRightClearwayWidth = runway.getRightClearwayWidth();

            gc.fillRect(normalize(doubleLeftClearwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftClearwayY - doubleLeftClearwayWidth/2, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleLeftClearwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftClearwayWidth, doubleAirportWidth, doubleCanvasHeight));

            gc.fillRect(normalize(doubleRightClearwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightClearwayY - doubleRightClearwayWidth/2, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleRightClearwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightClearwayWidth, doubleAirportWidth, doubleCanvasHeight));
            gc.restore();
        }

        private void drawStopway(GraphicsContext gc) {
            gc.save();
            gc.setFill(colorScheme.RunwayStripColor);
            doubleLeftStopwayX = doubleInstrumentStripX + runway.getStripEnd() + runway.getLeftClearwayLength()-runway.getLeftStopwayLength();
            doubleLeftStopwayY = doubleInstrumentStripY + runway.getInstrumentStrip();
            doubleLeftStopwayLength = runway.getLeftStopwayLength();
            doubleLeftStopwayWidth = runway.getRunwayStripWidth();

            doubleRightStopwayX = doubleLeftStopwayX + doubleLeftStopwayLength + runway.getRunwayStripLength();
            doubleRightStopwayY = doubleInstrumentStripY + runway.getInstrumentStrip();
            doubleRightStopwayLength = runway.getRightStopwayLength();
            doubleRightStopwayWidth = runway.getRunwayStripWidth();

            double doubleLeftStopwayStripesNumber = 4;
            gc.fillRect(normalize(doubleLeftStopwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftStopwayY - doubleLeftStopwayWidth/2, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleLeftStopwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleLeftStopwayWidth, doubleAirportWidth, doubleCanvasHeight));

            gc.fillRect(normalize(doubleRightStopwayX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightStopwayY - doubleRightStopwayWidth/2, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleRightStopwayLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleRightStopwayWidth, doubleAirportWidth, doubleCanvasHeight));

            gc.restore();
        }

        private void drawDisplacedThreshold(GraphicsContext gc) {
            gc.save();
            //for second increment
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
            gc.setFont(new javafx.scene.text.Font(runway.getCharacterLength()));

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
            gc.setFont(new javafx.scene.text.Font(runway.getCharacterLength()));

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
            doubleStripesX = doubleLeftRunwayNumberX +  doubleLeftRunwayNumberLength + runway.getNumberToStripeLength();
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
            }while(doubleStripesX + (i)*runway.getStripesDifference() + (i+1)*runway.getStripesLength() + runway.getNumberToStripeLength() < doubleRightRunwayNumberX);
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

            double doubleDirectionX = doubleRunwayStripX;
            double doubleDirectionY = doubleRunwayStripY - (doubleRunwayStripY - doubleSmallVisualStripY)/2;
            double doubleDirectionLength = runway.getRunwayStripLength()/4;

            double doubleDirectionTriangleLength = runway.getRunwayStripLength()/60;
            double doubleDirectionTriangleWidth = runway.getRunwayStripWidth()/10;

            gc.fillText(txt.getText(),
                    normalize(doubleDirectionX+doubleDirectionLength/2, doubleAirportLength, doubleCanvasWidth) - doubleTextLength/2,
                    normalize(doubleDirectionY, doubleAirportWidth, doubleCanvasHeight) - doubleTextHeight/4);

            gc.strokeLine(normalize(doubleDirectionX, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleDirectionY, doubleAirportWidth, doubleCanvasHeight),
                    normalize(doubleDirectionX + doubleDirectionLength, doubleAirportLength, doubleCanvasWidth),
                    normalize(doubleDirectionY, doubleAirportWidth, doubleCanvasHeight));

            gc.fillPolygon(new double[]{normalize(doubleDirectionX + doubleDirectionLength, doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleDirectionX + doubleDirectionLength, doubleAirportLength, doubleCanvasWidth),
                            normalize(doubleDirectionX + doubleDirectionLength + doubleDirectionTriangleLength, doubleAirportLength, doubleCanvasWidth)},
                    new double[]{normalize(doubleDirectionY - doubleDirectionTriangleWidth, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleDirectionY + doubleDirectionTriangleWidth, doubleAirportWidth, doubleCanvasHeight),
                            normalize(doubleDirectionY, doubleAirportWidth, doubleCanvasHeight)}, 3);

            gc.restore();
        }

        private void drawText(GraphicsContext gc){
            gc.save();
            Text txt = new Text("DIRECTION");
            Double newFontSizeDouble = Math.hypot(this.getWidth(), this.getHeight())/100;
            gc.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            txt.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            double doubleTextHeight = txt.getLayoutBounds().getHeight();

            drawLinedText(gc, "" + (int)doubleSmallVisualStripLength, doubleInstrumentStripX, doubleSmallVisualStripY+doubleTextHeight/3, doubleInstrumentStripLength);
            drawLinedText(gc, "" + (int)doubleLargeVisualStripLength, doubleLargeVisualStripX, doubleLargeVisualStripY+doubleTextHeight/3, doubleLargeVisualStripLength);

            drawLinedText(gc, "" + (int)runway.getLeftClearwayLength(), doubleLeftClearwayX, doubleLeftClearwayY-doubleLeftClearwayWidth/2, doubleLeftClearwayLength);
            drawLinedText(gc, "" + (int)runway.getRightClearwayLength(), doubleRightClearwayX, doubleRightClearwayY-doubleRightClearwayWidth/2, doubleRightClearwayLength);
            drawLinedText(gc, "" + (int)runway.getLeftStopwayLength(), doubleLeftStopwayX, doubleLeftStopwayY - doubleLeftStopwayWidth / 2, doubleLeftStopwayLength);
            drawLinedText(gc, "" + (int)runway.getRightStopwayLength(), doubleRightStopwayX, doubleRightStopwayY - doubleRightStopwayWidth / 2, doubleRightStopwayLength);

            drawLinedText(gc, "TORA " + (int)runway.getTORA(), doubleRunwayStripX, doubleRunwayStripY + doubleRunwayStripWidth / 2 + doubleTextHeight / 2, runway.getTORA());
            drawLinedText(gc, "TODA " + (int)runway.getTODA(), doubleRunwayStripX, doubleRunwayStripY + doubleRunwayStripWidth / 2 + 2 * doubleTextHeight / 2, runway.getTODA());
            drawLinedText(gc, "ASDA " + (int)runway.getASDA(), doubleRunwayStripX, doubleRunwayStripY + doubleRunwayStripWidth / 2 + 3 * doubleTextHeight / 2, runway.getASDA());
            gc.restore();
        }

        private void drawLinedText(GraphicsContext gc, String text, double x1, double y1, double length) {
            gc.save();
            gc.setFill(colorScheme.CharactersColor);
            Text txt = new Text(text);
            Double newFontSizeDouble = Math.hypot(this.getWidth(), this.getHeight())/100;
            gc.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            txt.setFont(new javafx.scene.text.Font(newFontSizeDouble.intValue()));
            double doubleTextLength = txt.getLayoutBounds().getWidth();
            double doubleTextHeight = txt.getLayoutBounds().getHeight();
            gc.setLineWidth(1);

            gc.strokeLine(normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)-doubleTextHeight/4,
                    normalize(x1, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)+doubleTextHeight/4);

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
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)-doubleTextHeight/4,
                    normalize(x1+length, doubleAirportLength, doubleCanvasWidth),
                    normalize(y1, doubleAirportWidth, doubleCanvasHeight)+doubleTextHeight/4);
            gc.restore();
        }



        private double normalize(double value,double from, double to){
            return value*to/from;
        }
        //endregion

    }
}