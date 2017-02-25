import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class UKMapAirportSelection2DVisualization extends Pane {
    //region PrivateVariables
    private double doubleCanvasWidth = 100;
    private double doubleCanvasHeight = 200;

    private double doubleXOffset = 0;
    private double doubleYOffset = 0;

    private double scale;

    private double doubleUKWidth = 437;
    private double doubleUKHeight = 967;

    private double doubleUKMapX;
    private double doubleUKMapY;

    private double doubleAirportWidth = 5;
    private double doubleAirportHeight = 5;

    private double doubleImageXToRealWorldX;
    private double doubleImageYToRealWorldY;

    private boolean checkMousePosition = false;

    private Image imgUKMap;
    private ObservableList<Airport> airportList;
    //endregion

    private ResizableCanvas canvas;

    UKMapAirportSelection2DVisualization(ObservableList<Airport> airportList) {
        this.imgUKMap = new Image(getClass().getResourceAsStream("UK.png"));
        this.canvas = new ResizableCanvas();
        this.airportList = airportList;

        this.canvas.widthProperty().bind(widthProperty());
        this.canvas.heightProperty().bind(heightProperty());

        addHandlers();

        getChildren().add(canvas);
    }

    private void addHandlers() {
        this.setOnMouseMoved(event -> {
                double x = event.getX() - doubleUKMapX;
                double y = event.getY();
                if (x >= 0 && x <= scale * imgUKMap.getWidth() && y <= scale * imgUKMap.getHeight()) {
                    doubleImageXToRealWorldX = canvas.normalize(x, scale * imgUKMap.getWidth(), doubleUKWidth);
                    doubleImageYToRealWorldY = canvas.normalize(y, scale * imgUKMap.getHeight(), doubleUKHeight);
                    if(checkMousePosition)
                        this.fireEvent(new UKMapAirportSelectionEvent(UKMapAirportSelectionEvent.POSITION_CHANGED, new Point2D(doubleImageXToRealWorldX, doubleImageYToRealWorldY)));
                }

        });

        this.setOnMouseClicked(event -> {
            if(checkMousePosition) {
                this.fireEvent(new UKMapAirportSelectionEvent(UKMapAirportSelectionEvent.POSITION_SELECTED, new Point2D(doubleImageXToRealWorldX, doubleImageYToRealWorldY)));
            }else{
                double x1,x2,y1,y2;
                boolean airportSelected = false;
                refresh();
                for(Airport airport:airportList){
                    x1 = airport.getUKMapPosition().getX()-canvas.normalize(scale * doubleAirportWidth/2, imgUKMap.getWidth()*scale, doubleUKWidth);
                    x2 = airport.getUKMapPosition().getX()+canvas.normalize(scale * doubleAirportWidth/2, imgUKMap.getWidth()*scale, doubleUKWidth);

                    y1 = airport.getUKMapPosition().getY()-canvas.normalize(scale * doubleAirportHeight/2, imgUKMap.getHeight()*scale, doubleUKHeight);
                    y2 = airport.getUKMapPosition().getY()+canvas.normalize(scale * doubleAirportHeight/2, imgUKMap.getHeight()*scale, doubleUKHeight);

                    if(doubleImageXToRealWorldX >= x1 && doubleImageXToRealWorldX <= x2 && doubleImageYToRealWorldY >= y1 && doubleImageYToRealWorldY <=y2){
                        canvas.drawAirport(airport, Color.RED);
                        this.fireEvent(new UKMapAirportSelectionEvent(UKMapAirportSelectionEvent.AIRPORT_SELECTED, airport));
                        airportSelected = true;
                        break;
                    }
                }
                if(!airportSelected) this.fireEvent(new UKMapAirportSelectionEvent(UKMapAirportSelectionEvent.AIRPORT_DESELECTED));
            }
        });

    }
    public final void setOnPositionChanged(EventHandler<? super UKMapAirportSelectionEvent> value) {
        this.addEventHandler(UKMapAirportSelectionEvent.POSITION_CHANGED, value);
    }

    public final void setOnPositionSelected(EventHandler<? super UKMapAirportSelectionEvent> value) {
        this.addEventHandler(UKMapAirportSelectionEvent.POSITION_SELECTED, value);
    }

    public final void setOnAirportSelected(EventHandler<? super UKMapAirportSelectionEvent> value) {
        this.addEventHandler(UKMapAirportSelectionEvent.AIRPORT_SELECTED, value);
    }

    public final void setOnAirportDeSelected(EventHandler<? super UKMapAirportSelectionEvent> value) {
        this.addEventHandler(UKMapAirportSelectionEvent.AIRPORT_DESELECTED, value);
    }

    public void refresh(){
        canvas.draw();
    }

    public void lookForAirportPosition() {
        checkMousePosition = true;
    }

    public void stopLookingForAirportPosition() {
        checkMousePosition = false;
    }

    public void selectAirport(Airport airport) {
        canvas.drawAirport(airport, Color.RED);
    }

    class ResizableCanvas extends Canvas {
        GraphicsContext  gc;

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
            gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());

            scale = Math.min(doubleCanvasWidth / imgUKMap.getWidth(), doubleCanvasHeight / imgUKMap.getHeight());

            drawBackground();
            drawImage();
            drawAirports();
        }

        private void drawBackground() {
            gc.setFill(Color.BLUE);

            //gc.fillRect(0, 0, doubleCanvasWidth, doubleCanvasHeight);
        }
        private void drawImage() {
            doubleUKMapX = (doubleCanvasWidth - scale * imgUKMap.getWidth())/2;
            doubleUKMapY = 0;
            gc.drawImage(imgUKMap,
                    doubleUKMapX,
                    doubleUKMapY,
                    scale * imgUKMap.getWidth(),
                    scale * imgUKMap.getHeight());
        }
        private void drawAirports() {
            for(Airport airport:airportList)
                drawAirport(airport,Color.BLUE);
        }
        private void drawAirport(Airport airport, Color color){
            gc.setFill(color);
            gc.fillRect(doubleUKMapX - scale * doubleAirportWidth / 2 + normalize(airport.getUKMapPosition().getX(), doubleUKWidth, scale * imgUKMap.getWidth()),
                    -scale * doubleAirportHeight/2 + normalize(airport.getUKMapPosition().getY(), doubleUKHeight, scale * imgUKMap.getHeight()),
                    scale * doubleAirportWidth,
                    scale * doubleAirportHeight);
        }
        private double normalize(double value,double from, double to){
            return value*to/from;
        }
        //endregion
    }

    //endregion

    //region Events
    //endregion
}