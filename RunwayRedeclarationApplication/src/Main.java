import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double doubleScreenWidth = screenSize.getWidth();
        double doubleScreenHeight = screenSize.getHeight();
        primaryStage.setTitle("Runway Re-declaration Tool");
        primaryStage.setScene(new Scene(root, doubleScreenWidth*0.75, doubleScreenHeight*0.75));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
