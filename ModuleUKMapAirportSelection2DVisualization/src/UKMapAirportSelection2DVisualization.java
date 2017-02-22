import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UKMapAirportSelection2DVisualization extends Pane {
    //region PrivateVariables
    private double doubleCanvasWidth = 100;
    private double doubleCanvasHeight = 200;

    private double doubleXOffset = 0;
    private double doubleYOffset = 0;

    private double doubleUKWidth = 1000;
    private double doubleUKHeight = 1000;

    Image imgUKMap;
    //endregion

    private ResizableCanvas canvas;

    UKMapAirportSelection2DVisualization() {
        imgUKMap = new Image(getClass().getResourceAsStream("UK.png"));
        canvas = new ResizableCanvas();
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        getChildren().add(canvas);
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

            drawBackground(gc);
            drawImage(gc);
        }

        private void drawBackground(GraphicsContext gc) {
            gc.setFill(Color.BLUE);

            //gc.fillRect(0, 0, doubleCanvasWidth, doubleCanvasHeight);
        }
        private void drawImage(GraphicsContext gc) {
            double scale = Math.min(doubleCanvasWidth/imgUKMap.getWidth(), doubleCanvasHeight/imgUKMap.getHeight());
            gc.drawImage(imgUKMap,
                    doubleCanvasWidth/2 - (scale * imgUKMap.getWidth())/2,
                    0,
                    scale * imgUKMap.getWidth(),
                    scale * imgUKMap.getHeight());
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