import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML private BorderPane borderPaneMainWindow;
    @FXML private BorderPane borderPaneLeftWindow;
    @FXML private BorderPane borderPaneUKMap;
    @FXML private BorderPane borderPaneSmallAirportImage;

    @FXML private TextField txtNewAirportName;
    @FXML private TextField txtNewAirportXPosition;
    @FXML private TextField txtNewAirportYPosition;
    @FXML private Button btnAddAirport;
    @FXML private Button btnMarkAirportPositionForAdding;

    @FXML private TextField txtEditedAirportName;
    @FXML private TextField txtEditedAirportXPosition;
    @FXML private TextField txtEditedAirportYPosition;
    @FXML private Button btnApplyChangesToAirport;
    @FXML private Button btnMarkAirportPositionForEditing;
    @FXML private Button btnRemoveSelectedAirport;
    @FXML private Label lblAirportImage;

    @FXML private ComboBox<Airport> cboAirportList;

    private ObservableList<Airport> airportList;
    private Airport selectedAirport;
    private Image currentAirportImage;
    private ImageView imageViewAirportImage;
    private FileChooser imageAirportChooser = new FileChooser();
    private boolean editMode = false;

    private TopViewRunway2DVisualization topViewRunway2DVisualization;
    private SideViewRunway2DVisualization sideViewRunway2DVisualization;
    private UKMapAirportSelection2DVisualization ukMapAirportSelection2DVisualization;

    @FXML
    public void initialize() {
        airportList = FXCollections.observableArrayList();
        cboAirportList.setItems(airportList);
        imageViewAirportImage = new ImageView();
        System.out.println(lblAirportImage.getWidth());
        imageViewAirportImage.fitWidthProperty().bind(borderPaneSmallAirportImage.widthProperty());
        imageViewAirportImage.fitHeightProperty().bind(borderPaneSmallAirportImage.heightProperty());

        imageViewAirportImage.setPreserveRatio(true);

        topViewRunway2DVisualization = new TopViewRunway2DVisualization();
        sideViewRunway2DVisualization = new SideViewRunway2DVisualization();
        ukMapAirportSelection2DVisualization = new UKMapAirportSelection2DVisualization(airportList);

        borderPaneMainWindow.setCenter(topViewRunway2DVisualization);
        borderPaneLeftWindow.setCenter(sideViewRunway2DVisualization);
        borderPaneUKMap.setCenter(ukMapAirportSelection2DVisualization);

        addHandlers();
    }

    private void addHandlers() {

        btnAddAirport.setOnAction(event -> {
            String name = txtNewAirportName.getText();
            double xPosition = Double.parseDouble(txtNewAirportXPosition.getText());
            double yPosition = Double.parseDouble(txtNewAirportYPosition.getText());
            airportList.add(new Airport(name, new Point2D(xPosition, yPosition), currentAirportImage));
            ukMapAirportSelection2DVisualization.refresh();
            txtNewAirportName.clear();
            txtNewAirportXPosition.clear();
            txtNewAirportYPosition.clear();

        });

        btnMarkAirportPositionForAdding.setOnAction(event -> {
            ukMapAirportSelection2DVisualization.lookForAirportPosition();
            editMode = false;
        });
        btnMarkAirportPositionForEditing.setOnAction(event -> {
            ukMapAirportSelection2DVisualization.lookForAirportPosition();
            editMode = true;
        });

        ukMapAirportSelection2DVisualization.setOnPositionChanged(event -> {
            if(!editMode) {
                txtNewAirportXPosition.setText(String.valueOf(event.getPosition().getX()));
                txtNewAirportYPosition.setText(String.valueOf(event.getPosition().getY()));
            }else{
                txtEditedAirportXPosition.setText(String.valueOf(event.getPosition().getX()));
                txtEditedAirportYPosition.setText(String.valueOf(event.getPosition().getY()));
            }
        });

        ukMapAirportSelection2DVisualization.setOnPositionSelected(event -> {
            ukMapAirportSelection2DVisualization.stopLookingForAirportPosition();
            editMode = false;
        });

        ukMapAirportSelection2DVisualization.setOnAirportSelected(event -> {
            cboAirportList.setValue(event.getAirport());
            selectAirport(event.getAirport());
        });

        cboAirportList.setOnAction((event) -> {
            ukMapAirportSelection2DVisualization.refresh();
            if(cboAirportList.getSelectionModel().getSelectedItem() != null) {
                selectAirport(cboAirportList.getSelectionModel().getSelectedItem());
                ukMapAirportSelection2DVisualization.selectAirport(cboAirportList.getSelectionModel().getSelectedItem());
            };
        });

        cboAirportList.setCellFactory(new Callback<ListView<Airport>, ListCell<Airport>>(){
            @Override
            public ListCell<Airport> call(ListView<Airport> p) {
                return new ListCell<Airport>(){
                    protected void updateItem(Airport t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getName());
                        }
                    }
                };
            }
        });

        btnApplyChangesToAirport.setOnAction(event -> {
            String name = txtEditedAirportName.getText();
            double xPosition = Double.parseDouble(txtEditedAirportXPosition.getText());
            double yPosition = Double.parseDouble(txtEditedAirportYPosition.getText());
            if(selectedAirport != null) {
                selectedAirport.setName(name);
                selectedAirport.setUKMapPosition(new Point2D(xPosition, yPosition));
                selectedAirport.setImage(currentAirportImage);
                refreshAirportListComboBox();
                ukMapAirportSelection2DVisualization.refresh();
            }
        });

        btnRemoveSelectedAirport.setOnAction(event -> {
            airportList.remove(selectedAirport);
            selectedAirport = null;
            imageViewAirportImage.setImage(null);
            refreshAirportListComboBox();
            ukMapAirportSelection2DVisualization.refresh();
        });

        lblAirportImage.setOnMouseClicked(event -> {
            File file = imageAirportChooser.showOpenDialog(null);
            if (file !=null) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(file);
                    currentAirportImage = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageViewAirportImage.setImage(currentAirportImage);
                    borderPaneSmallAirportImage.setCenter(imageViewAirportImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void refreshAirportListComboBox(){
        cboAirportList.getSelectionModel().clearSelection();
        cboAirportList.setItems(null);
        cboAirportList.setItems(airportList);
    }

    private void selectAirport(Airport airport){
        selectedAirport = cboAirportList.getSelectionModel().getSelectedItem();
        if(selectedAirport != null) {
            txtEditedAirportName.setText(selectedAirport.getName());
            txtEditedAirportXPosition.setText(String.valueOf(selectedAirport.getUKMapPosition().getX()));
            txtEditedAirportYPosition.setText(String.valueOf(selectedAirport.getUKMapPosition().getY()));
            imageViewAirportImage.setImage(selectedAirport.getImage());
        }
    }

}
