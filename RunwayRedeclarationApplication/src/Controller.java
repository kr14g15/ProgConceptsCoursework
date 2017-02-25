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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {
    //region displays
    @FXML private BorderPane borderPaneMainWindow;
    @FXML private BorderPane borderPaneLeftWindow;
    @FXML private BorderPane borderPaneUKMap;
    //endregion

    //region airportAdding
    @FXML private TextField txtNewAirportName;
    @FXML private TextField txtNewAirportXPosition;
    @FXML private TextField txtNewAirportYPosition;
    @FXML private Button btnAddAirport;
    @FXML private Button btnMarkAirportPositionForAdding;
    //endregion

    //region airportEditing
    @FXML private TextField txtEditedAirportName;
    @FXML private TextField txtEditedAirportXPosition;
    @FXML private TextField txtEditedAirportYPosition;
    @FXML private Button btnApplyChangesToAirport;
    @FXML private Button btnMarkAirportPositionForEditing;
    @FXML private Button btnRemoveSelectedAirport;
    //endregion

    //region airportSelection
    @FXML private ComboBox<Airport> cboAirportList;
    @FXML private ImageView imageViewAirportImage;
    @FXML private BorderPane borderPaneAirportImage;
    private Airport selectedAirport;
    private Image currentAirportImage;
    private FileChooser imageAirportChooser = new FileChooser();
    private boolean editMode = false;
    //endregion

    //region runwaySelection
    @FXML private ComboBox<Runway> cboRunwayList;
    //endregion

    private ObservableList<Airport> airportList;
    private Runway selectedRunway;
    private TopViewRunway2DVisualization topViewRunway2DVisualization;
    private SideViewRunway2DVisualization sideViewRunway2DVisualization;
    private UKMapAirportSelection2DVisualization ukMapAirportSelection2DVisualization;

    //region addRunway
    @FXML private Button btnAddRunway;
    @FXML private TextField txtAddDirection;
    @FXML private TextField txtAddDegree;
    @FXML private TextField txtAddTORA;
    @FXML private TextField txtAddTODA;
    @FXML private TextField txtAddASDA;
    @FXML private TextField txtAddLDA;
    @FXML private TextField txtAddRunwayStripWidth;

    @FXML private TextField txtAddThresholdStripLength;
    @FXML private TextField txtAddThresholdStripWidth;
    @FXML private TextField txtAddThresholdStripNumber;

    @FXML private TextField txtAddHorizontalStripesLength;
    @FXML private TextField txtAddHorizontalStripesWidth;
    @FXML private TextField txtAddHorizontalStripesDifference;
    @FXML private TextField txtAddStripEnd;
    @FXML private TextField txtAddVisualStrip;
    @FXML private TextField txtAddCGA;
    @FXML private TextField txtAddInstrumentStrip;
    @FXML private TextField txtAddBlastProtection;

    @FXML private TextField txtAddSkyHeight;
    @FXML private TextField txtAddGroundHeight;
    @FXML private TextField txtAddAirportHeight;

    //endregion
    @FXML
    public void initialize() {
        airportList = FXCollections.observableArrayList();
        cboAirportList.setItems(airportList);
        //imageViewAirportImage.fitWidthProperty().bind(borderPaneAirportImage.widthProperty());
        //imageViewAirportImage.fitHeightProperty().bind(borderPaneAirportImage.heightProperty());

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

        ukMapAirportSelection2DVisualization.setOnAirportDeSelected(event -> {
            selectAirport(null);
        });

        cboAirportList.setOnAction((event) -> {
            ukMapAirportSelection2DVisualization.refresh();
            if(cboAirportList.getSelectionModel().getSelectedItem() != null) {
                selectAirport(cboAirportList.getSelectionModel().getSelectedItem());
            };
        });

        cboRunwayList.setOnAction((event) -> {
            if(cboRunwayList.getSelectionModel().getSelectedItem() != null) {
                selectRunway(cboRunwayList.getSelectionModel().getSelectedItem());
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

        cboRunwayList.setCellFactory(new Callback<ListView<Runway>, ListCell<Runway>>(){
            @Override
            public ListCell<Runway> call(ListView<Runway> p) {
                return new ListCell<Runway>(){
                    protected void updateItem(Runway t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getDirection() + ""+t.getDegree());
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

        imageViewAirportImage.setOnMouseClicked(event -> {
            File file = imageAirportChooser.showOpenDialog(null);
            if (file !=null) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(file);
                    currentAirportImage = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageViewAirportImage.setImage(currentAirportImage);
                    borderPaneAirportImage.setCenter(imageViewAirportImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnAddRunway.setOnAction(event -> {
            Runway runway = new Runway();
            runway.setDirection(txtAddDirection.getText().charAt(0));
            runway.setDegree(Integer.parseInt(txtAddDegree.getText()));
            runway.setTORA(Double.parseDouble(txtAddTORA.getText()));
            runway.setTODA(Double.parseDouble(txtAddTODA.getText()));
            runway.setASDA(Double.parseDouble(txtAddASDA.getText()));
            runway.setLDA(Double.parseDouble(txtAddLDA.getText()));
            runway.setRunwayStripWidth(Double.parseDouble(txtAddRunwayStripWidth.getText()));

            runway.setThresholdStripLength(Double.parseDouble(txtAddThresholdStripLength.getText()));
            runway.setThresholdStripWidth(Double.parseDouble(txtAddThresholdStripWidth.getText()));
            runway.setThresholdStripeNumber(Double.parseDouble(txtAddThresholdStripNumber.getText()));

            runway.setHorizontalStripesLength(Double.parseDouble(txtAddHorizontalStripesLength.getText()));
            runway.setHorizontalStripesWidth(Double.parseDouble(txtAddHorizontalStripesWidth.getText()));
            runway.setHorizontalStripesDifference(Double.parseDouble(txtAddHorizontalStripesDifference.getText()));
            runway.setStripEnd(Double.parseDouble(txtAddStripEnd.getText()));
            runway.setVisualStrip(Double.parseDouble(txtAddVisualStrip.getText()));
            runway.setCGA(Double.parseDouble(txtAddCGA.getText()));
            runway.setInstrumentStrip(Double.parseDouble(txtAddInstrumentStrip.getText()));
            runway.setBlastProtection(Double.parseDouble(txtAddBlastProtection.getText()));

            runway.setSkyHeight(Double.parseDouble(txtAddSkyHeight.getText()));
            runway.setGroundHeight(Double.parseDouble(txtAddGroundHeight.getText()));
            runway.setAirportHeight(Double.parseDouble(txtAddAirportHeight.getText()));

            selectedAirport.getRunwayList().add(runway);
        });

    }

    private void refreshAirportListComboBox(){
        cboAirportList.getSelectionModel().clearSelection();
        cboAirportList.setItems(null);
        cboAirportList.setItems(airportList);
    }

    private void selectAirport(Airport airport){
        selectedAirport = airport;
        if(airport != null) {
            txtEditedAirportName.setText(airport.getName());
            txtEditedAirportXPosition.setText(String.valueOf(airport.getUKMapPosition().getX()));
            txtEditedAirportYPosition.setText(String.valueOf(airport.getUKMapPosition().getY()));
            imageViewAirportImage.setImage(airport.getImage());
            cboRunwayList.setItems(airport.getRunwayList());
            ukMapAirportSelection2DVisualization.selectAirport(airport);
        }else{
            cboAirportList.getSelectionModel().clearSelection();
            txtEditedAirportName.clear();
            txtEditedAirportXPosition.clear();
            txtEditedAirportYPosition.clear();
            imageViewAirportImage.setImage(null);
        }
    }

    private void selectRunway(Runway runway){
        runway = cboRunwayList.getSelectionModel().getSelectedItem();
        if(selectedRunway != null) {
            //txtEditedAirportName.setText(selectedAirport.getName());
            //txtEditedAirportXPosition.setText(String.valueOf(selectedAirport.getUKMapPosition().getX()));
            //txtEditedAirportYPosition.setText(String.valueOf(selectedAirport.getUKMapPosition().getY()));
            //imageViewAirportImage.setImage(selectedAirport.getImage());
            //cboRunwayList.setItems(selectedAirport.getRunwayList());
        }
    }

}
