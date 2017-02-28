import com.sun.javafx.scene.control.skin.ColorPalette;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    //region displays
    @FXML private BorderPane borderPaneMainWindow;
    @FXML private BorderPane borderPaneLeftWindow;
    @FXML private BorderPane borderPaneUKMap;

    private TopViewRunway2DVisualization topViewRunway2DVisualization;
    private SideViewRunway2DVisualization sideViewRunway2DVisualization;
    private UKMapAirportSelection2DVisualization ukMapAirportSelection2DVisualization;
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
    @FXML private GridPane gridEditAirport;
    @FXML private GridPane gridRemoveAirport;
    //endregion

    //region airportSelection
    @FXML private ComboBox<Airport> cboAirportList;
    @FXML private ImageView imageViewAirportImage;
    @FXML private BorderPane borderPaneAirportImage;
    @FXML private TextField txtSelectedAirport;
    private ObservableList<Airport> airportList;
    private Airport selectedAirport;
    private Image currentAirportImage;
    private FileChooser imageAirportChooser = new FileChooser();
    private boolean editMode = false;
    //endregion

    //region runwaySelection
    @FXML private ComboBox<Runway> cboRunwayList;
    protected ListProperty<Runway> runwayListProperty = new SimpleListProperty<>();
    private Runway selectedRunway;

    //endregion

    //region addRunway
    @FXML private ListView<Runway> listViewRunwayList;
    @FXML private GridPane gridAddRunway;
    @FXML private Button btnAddRunway;
    @FXML private TextField txtAddDirection;
    @FXML private TextField txtAddDegree;
    @FXML private TextField txtAddStopwayLength;
    @FXML private TextField txtAddClearwayLength;
    @FXML private TextField txtAddClearwayWidth;

    @FXML private TextField txtAddRunwayStripWidth;
    @FXML private TextField txtAddRunwayStripLength;

    @FXML private TextField txtAddThresholdStripLength;
    @FXML private TextField txtAddThresholdStripWidth;
    @FXML private TextField txtAddDisplacedThresholdLength;

    @FXML private TextField txtAddStartToThresholdMarkingsLength;
    @FXML private TextField txtAddThresholdMarkingsStripLength;
    @FXML private TextField txtAddThresholdMarkingsStripWidth;
    @FXML private TextField txtAddThresholdMarkingsToLetterLength;
    @FXML private TextField txtAddLetterToNumberLength;
    @FXML private TextField txtAddNumberToHorizontalStripeLength;
    @FXML private TextField txtAddCharacterLength;
    @FXML private TextField txtAddCharacterWidth;

    @FXML private TextField txtAddHorizontalStripesLength;
    @FXML private TextField txtAddHorizontalStripesWidth;
    @FXML private TextField txtAddHorizontalStripesDifference;
    @FXML private TextField txtAddStripEnd;
    @FXML private TextField txtAddSmallVisualStripWidth;
    @FXML private TextField txtAddLargeVisualStripWidth;
    @FXML private TextField txtAddInstrumentStrip;
    @FXML private TextField txtAddBlastProtection;
    @FXML private TextField txtAddRESA;
    @FXML private TextField txtAddRunwayToSmallVisualStripLength;
    @FXML private TextField txtAddRunwayToLargeVisualStripLength;

    @FXML private TextField txtAddSkyHeight;
    @FXML private TextField txtAddGroundHeight;
    @FXML private TextField txtAddAirportHeight;

    Map<TextField, String> textFieldTodefaultValue;
    //endregion

    //region editRunway
    @FXML private GridPane gridEditRunway;
    @FXML private GridPane gridRemoveRunway;

    @FXML private Button btnEditRunway;
    @FXML private TextField txtEditDirection;
    @FXML private TextField txtEditDegree;
    @FXML private TextField txtEditTORA;
    @FXML private TextField txtEditTODA;
    @FXML private TextField txtEditASDA;
    @FXML private TextField txtEditLDA;
    @FXML private TextField txtEditRunwayStripWidth;

    @FXML private TextField txtEditThresholdStripLength;
    @FXML private TextField txtEditThresholdStripWidth;

    @FXML private TextField txtEditStartToThresholdMarkingsLength;
    @FXML private TextField txtEditThresholdMarkingsStripLength;
    @FXML private TextField txtEditThresholdMarkingsStripWidth;
    @FXML private TextField txtEditThresholdMarkingsToLetterLength;
    @FXML private TextField txtEditLetterToNumberLength;
    @FXML private TextField txtEditNumberToHorizontalStripeLength;
    @FXML private TextField txtEditCharacterLength;
    @FXML private TextField txtEditCharacterWidth;

    @FXML private TextField txtEditHorizontalStripesLength;
    @FXML private TextField txtEditHorizontalStripesWidth;
    @FXML private TextField txtEditHorizontalStripesDifference;
    @FXML private TextField txtEditStripEnd;
    @FXML private TextField txtEditVisualStrip;
    @FXML private TextField txtEditCGA;
    @FXML private TextField txtEditInstrumentStrip;
    @FXML private TextField txtEditBlastProtection;
    @FXML private TextField txtEditRESA;

    @FXML private TextField txtEditSkyHeight;
    @FXML private TextField txtEditGroundHeight;
    @FXML private TextField txtEditAirportHeight;

    //endregion

    //region settings
    @FXML private Button btnLoadSettings;
    @FXML private Button btnSaveSettings;
    @FXML private Button btnApplyColorScheme;
    @FXML private ComboBox cboColorScheme;
    private ColorScheme colorScheme;
    //endregion

    //region removeRunway
    @FXML private Button btnRemoveSelectedRunway;
    //endregion

    //region publicMethods
    @FXML
    public void initialize() {
        airportList = FXCollections.observableArrayList();
        textFieldTodefaultValue = new HashMap<>();
        cboAirportList.setItems(airportList);
        listViewRunwayList.itemsProperty().bind(runwayListProperty);
        colorScheme = ColorScheme.DEFAULT;
        cboColorScheme.setItems(FXCollections.observableArrayList(ColorScheme.values()));

        saveDefaultValues(gridAddRunway);
        setAllComponentsInGrid(gridEditAirport, false);
        setAllComponentsInGrid(gridRemoveAirport, false);
        setAllComponentsInGrid(gridAddRunway, false);
        setAllComponentsInGrid(gridEditRunway, false);
        setAllComponentsInGrid(gridRemoveRunway, false);

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
    //endregion

    //region privateMethods
    private void addHandlers() {

        btnAddAirport.setOnAction(event -> {
            String name = txtNewAirportName.getText();
            double xPosition = Double.parseDouble(txtNewAirportXPosition.getText());
            double yPosition = Double.parseDouble(txtNewAirportYPosition.getText());
            airportList.add(new Airport(name, new Point2D.Double(xPosition, yPosition), currentAirportImage));
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

        listViewRunwayList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectRunway(newValue);
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
                            setText(t.getDirection() + "" + t.getDegree());
                        }else
                            setText(null);
                    }
                };
            }
        });

        listViewRunwayList.setCellFactory(new Callback<ListView<Runway>, ListCell<Runway>>(){
            @Override
            public ListCell<Runway> call(ListView<Runway> p) {
                return new ListCell<Runway>(){
                    protected void updateItem(Runway t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getDirection() + "" + t.getDegree());
                        }else
                            setText(null);
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
                selectedAirport.setUKMapPosition(new Point2D.Double(xPosition, yPosition));
                selectedAirport.setImage(currentAirportImage);
                refreshAirportListComboBox();
                ukMapAirportSelection2DVisualization.refresh();
                selectAirport(null);
            }
        });

        btnRemoveSelectedAirport.setOnAction(event -> {
            airportList.remove(selectedAirport);
            selectedAirport = null;
            imageViewAirportImage.setImage(null);
            refreshAirportListComboBox();
            ukMapAirportSelection2DVisualization.refresh();
        });

        btnRemoveSelectedRunway.setOnAction(event -> {
            selectedAirport.getRunwayList().remove(selectedRunway);
            selectedRunway = null;
            refreshRunwayListView();
        });

        imageViewAirportImage.setOnMouseClicked(event -> {
            File file = imageAirportChooser.showOpenDialog(null);
            if (file !=null) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(file);
                    currentAirportImage = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageViewAirportImage.setImage(currentAirportImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnSaveSettings.setOnAction(event -> {
            File file = imageAirportChooser.showSaveDialog(null);
            if (file != null) {
                FileSerializer fileSerializer = new FileSerializer();
                fileSerializer.serializeAirportList(file.toPath(), airportList);
            }
        });

        btnLoadSettings.setOnAction(event -> {
            File file = imageAirportChooser.showOpenDialog(null);
            if (file != null) {
                FileSerializer fileSerializer = new FileSerializer();
                airportList = fileSerializer.deSerializeAirportList(file.toPath());
                ukMapAirportSelection2DVisualization.setAirportList(airportList);
                refreshAirportListComboBox();
                ukMapAirportSelection2DVisualization.refresh();
            }
        });

        btnApplyColorScheme.setOnAction(event -> {
            topViewRunway2DVisualization.setColorScheme((ColorScheme) cboColorScheme.getSelectionModel().getSelectedItem());
            sideViewRunway2DVisualization.setColorScheme((ColorScheme) cboColorScheme.getSelectionModel().getSelectedItem());

            topViewRunway2DVisualization.refresh();
            sideViewRunway2DVisualization.refresh();
        });

        borderPaneLeftWindow.setOnMouseClicked(event -> {
            Node mainNodeContent = borderPaneMainWindow.getCenter();
            Node leftNodeContent = borderPaneLeftWindow.getCenter();
            borderPaneMainWindow.setCenter(leftNodeContent);
            borderPaneLeftWindow.setCenter(mainNodeContent);
        });


        btnAddRunway.setOnAction(event -> {
            Runway runway = new Runway();
            runway.setDirection(txtAddDirection.getText().charAt(0));
            runway.setDegree(Integer.parseInt(txtAddDegree.getText()));
            runway.setStopwayLength(Double.parseDouble(txtAddStopwayLength.getText()));
            runway.setClearwayLength(Double.parseDouble(txtAddClearwayLength.getText()));
            runway.setClearwayWidth(Double.parseDouble(txtAddClearwayWidth.getText()));

            runway.setRunwayStripWidth(Double.parseDouble(txtAddRunwayStripWidth.getText()));
            runway.setRunwayStripLength(Double.parseDouble(txtAddRunwayStripLength.getText()));
            runway.setDisplacedThresholdLength(Double.parseDouble(txtAddDisplacedThresholdLength.getText()));

            //runway.setThresholdStripLength(Double.parseDouble(txtAddThresholdStripLength.getText()));
            //runway.setThresholdStripWidth(Double.parseDouble(txtAddThresholdStripWidth.getText()));

            runway.setStartToThresholdMarkingsLength(Double.parseDouble(txtAddStartToThresholdMarkingsLength.getText()));
            runway.setThresholdMarkingsStripLength(Double.parseDouble(txtAddThresholdMarkingsStripLength.getText()));
            runway.setThresholdMarkingsStripWidth(Double.parseDouble(txtAddThresholdMarkingsStripWidth.getText()));
            runway.setThresholdMarkingsToLetterLength(Double.parseDouble(txtAddThresholdMarkingsToLetterLength.getText()));
            runway.setLetterToNumberLength(Double.parseDouble(txtAddLetterToNumberLength.getText()));
            runway.setNumberToHorizontalStripeLength(Double.parseDouble(txtAddNumberToHorizontalStripeLength.getText()));
            runway.setCharacterLength(Double.parseDouble(txtAddCharacterLength.getText()));
            runway.setCharacterWidth(Double.parseDouble(txtAddCharacterWidth.getText()));


            runway.setHorizontalStripesLength(Double.parseDouble(txtAddHorizontalStripesLength.getText()));
            runway.setHorizontalStripesWidth(Double.parseDouble(txtAddHorizontalStripesWidth.getText()));
            runway.setHorizontalStripesDifference(Double.parseDouble(txtAddHorizontalStripesDifference.getText()));
            runway.setStripEnd(Double.parseDouble(txtAddStripEnd.getText()));
            runway.setSmallVisualStripWidth(Double.parseDouble(txtAddSmallVisualStripWidth.getText()));
            runway.setLargeVisualStripWidth(Double.parseDouble(txtAddLargeVisualStripWidth.getText()));
            runway.setInstrumentStrip(Double.parseDouble(txtAddInstrumentStrip.getText()));
            runway.setBlastProtection(Double.parseDouble(txtAddBlastProtection.getText()));
            runway.setRESA(Double.parseDouble(txtAddRESA.getText()));
            runway.setRunwayToSmallVisualStripLength(Double.parseDouble(txtAddRunwayToSmallVisualStripLength.getText()));
            runway.setRunwayToLargeVisualStripLength(Double.parseDouble(txtAddRunwayToLargeVisualStripLength.getText()));

            runway.setSkyHeight(Double.parseDouble(txtAddSkyHeight.getText()));
            runway.setGroundHeight(Double.parseDouble(txtAddGroundHeight.getText()));
            runway.setAirportHeight(Double.parseDouble(txtAddAirportHeight.getText()));

            selectedAirport.getRunwayList().add(runway);
            selectAirport(selectedAirport);
            refreshRunwayListView();
            refreshAirportListComboBox();
            refreshRunwayListComboBox();
        });

        btnEditRunway.setOnAction(event -> {
            selectedRunway.setDirection(txtEditDirection.getText().charAt(0));
            selectedRunway.setDegree(Integer.parseInt(txtEditDegree.getText()));
            selectedRunway.setTORA(Double.parseDouble(txtEditTORA.getText()));
            selectedRunway.setTODA(Double.parseDouble(txtEditTODA.getText()));
            selectedRunway.setASDA(Double.parseDouble(txtEditASDA.getText()));
            selectedRunway.setLDA(Double.parseDouble(txtEditLDA.getText()));
            selectedRunway.setRunwayStripWidth(Double.parseDouble(txtEditRunwayStripWidth.getText()));

            //selectedRunway.setThresholdStripLength(Double.parseDouble(txtEditThresholdStripLength.getText()));
            //selectedRunway.setThresholdStripWidth(Double.parseDouble(txtEditThresholdStripWidth.getText()));

            selectedRunway.setStartToThresholdMarkingsLength(Double.parseDouble(txtEditStartToThresholdMarkingsLength.getText()));
            selectedRunway.setThresholdMarkingsStripLength(Double.parseDouble(txtEditThresholdMarkingsStripLength.getText()));
            selectedRunway.setThresholdMarkingsStripWidth(Double.parseDouble(txtEditThresholdMarkingsStripWidth.getText()));
            selectedRunway.setThresholdMarkingsToLetterLength(Double.parseDouble(txtEditThresholdMarkingsToLetterLength.getText()));
            selectedRunway.setLetterToNumberLength(Double.parseDouble(txtEditLetterToNumberLength.getText()));
            selectedRunway.setNumberToHorizontalStripeLength(Double.parseDouble(txtEditNumberToHorizontalStripeLength.getText()));
            selectedRunway.setCharacterLength(Double.parseDouble(txtEditCharacterLength.getText()));
            selectedRunway.setCharacterWidth(Double.parseDouble(txtEditCharacterWidth.getText()));

            selectedRunway.setHorizontalStripesLength(Double.parseDouble(txtEditHorizontalStripesLength.getText()));
            selectedRunway.setHorizontalStripesWidth(Double.parseDouble(txtEditHorizontalStripesWidth.getText()));
            selectedRunway.setHorizontalStripesDifference(Double.parseDouble(txtEditHorizontalStripesDifference.getText()));
            selectedRunway.setStripEnd(Double.parseDouble(txtEditStripEnd.getText()));
            selectedRunway.setSmallVisualStripWidth(Double.parseDouble(txtEditVisualStrip.getText()));
            selectedRunway.setLargeVisualStripWidth(Double.parseDouble(txtEditCGA.getText()));
            selectedRunway.setInstrumentStrip(Double.parseDouble(txtEditInstrumentStrip.getText()));
            selectedRunway.setBlastProtection(Double.parseDouble(txtEditBlastProtection.getText()));
            selectedRunway.setBlastProtection(Double.parseDouble(txtEditRESA.getText()));

            selectedRunway.setSkyHeight(Double.parseDouble(txtEditSkyHeight.getText()));
            selectedRunway.setGroundHeight(Double.parseDouble(txtEditGroundHeight.getText()));
            selectedRunway.setAirportHeight(Double.parseDouble(txtEditAirportHeight.getText()));

            selectAirport(selectedAirport);
            refreshRunwayListView();
            refreshAirportListComboBox();
            refreshRunwayListComboBox();
        });

    }

    private void setAllComponentsInGrid(Pane parent, boolean enabled){
        for (Node control : parent.getChildren()) {
            if (control instanceof Pane) {
                setAllComponentsInGrid((Pane) control, enabled);
            } else if (control instanceof TextField) {
                ((TextField) control).clear();
            }else if (control instanceof ComboBox) {
                ((ComboBox) control).getSelectionModel().clearSelection();
            }else if (control instanceof TitledPane) {
                setAllComponentsInGrid((Pane) ((TitledPane) control).getContent(), enabled);
            }
            control.setDisable(!enabled);
        }
    }

    private void saveDefaultValues(Pane parent) {
        for (Node control : parent.getChildren()) {
            if (control instanceof Pane) {
                saveDefaultValues((Pane) control);
            } else if (control instanceof TextField) {
                textFieldTodefaultValue.put((TextField) control, ((TextField) control).getText());
            }else if (control instanceof TitledPane) {
                saveDefaultValues((Pane) ((TitledPane) control).getContent());
            }}
    }

    private void loadDefaultValues() {
        for (TextField textField : textFieldTodefaultValue.keySet()) {
            textField.setText(textFieldTodefaultValue.get(textField));
        }
    }

    private void refreshAirportListComboBox(){
        cboAirportList.getSelectionModel().clearSelection();
        cboAirportList.setItems(null);
        cboAirportList.setItems(FXCollections.observableArrayList(airportList));
    }

    private void refreshRunwayListComboBox(){
        cboRunwayList.getSelectionModel().clearSelection();
        cboRunwayList.setItems(null);
        cboRunwayList.setItems(FXCollections.observableArrayList(selectedAirport.getRunwayList()));
    }

    private void refreshRunwayListView(){
        listViewRunwayList.getSelectionModel().clearSelection();
        runwayListProperty.set(null);
        if(selectedAirport != null)
            runwayListProperty.set(FXCollections.observableArrayList(selectedAirport.getRunwayList()));
    }

    private void selectAirport(Airport airport){
        selectedAirport = airport;
        if(airport != null) {
            setAllComponentsInGrid(gridEditAirport, true);
            setAllComponentsInGrid(gridRemoveAirport, true);
            setAllComponentsInGrid(gridAddRunway, true);
            txtEditedAirportName.setText(airport.getName());
            txtEditedAirportXPosition.setText(String.valueOf(airport.getUKMapPosition().getX()));
            txtEditedAirportYPosition.setText(String.valueOf(airport.getUKMapPosition().getY()));
            imageViewAirportImage.setImage(airport.getImage());
            cboRunwayList.setItems(FXCollections.observableArrayList(airport.getRunwayList()));
            ukMapAirportSelection2DVisualization.selectAirport(airport);
            runwayListProperty.set(FXCollections.observableArrayList(selectedAirport.getRunwayList()));
            txtSelectedAirport.setText(airport.getName());
            loadDefaultValues();
        }else{
            cboAirportList.getSelectionModel().clearSelection();
            imageViewAirportImage.setImage(null);
            txtSelectedAirport.clear();
            setAllComponentsInGrid(gridEditAirport, false);
            setAllComponentsInGrid(gridRemoveAirport, false);

            setAllComponentsInGrid(gridAddRunway, false);
            setAllComponentsInGrid(gridEditRunway, false);
            setAllComponentsInGrid(gridRemoveRunway, false);

            refreshRunwayListView();
        }
    }

    private void selectRunway(Runway runway){
        selectedRunway = runway;
        if(selectedRunway != null) {
            setAllComponentsInGrid(gridEditRunway, true);
            setAllComponentsInGrid(gridRemoveRunway, true);
            txtEditDirection.setText(String.valueOf(runway.getDirection()));
            txtEditDegree.setText(String.valueOf(runway.getDegree()));
            txtEditTORA.setText(String.valueOf(runway.getTORA()));
            txtEditTODA.setText(String.valueOf(runway.getTODA()));
            txtEditASDA.setText(String.valueOf(runway.getASDA()));
            txtEditLDA.setText(String.valueOf(runway.getLDA()));
            txtEditRunwayStripWidth.setText(String.valueOf(runway.getRunwayStripWidth()));

            //txtEditThresholdStripLength.setText(String.valueOf(runway.getThresholdStripLength()));
            //txtEditThresholdStripWidth.setText(String.valueOf(runway.getThresholdStripWidth()));

            txtEditStartToThresholdMarkingsLength.setText(String.valueOf(runway.getStartToThresholdMarkingsLength()));
            txtEditThresholdMarkingsStripLength.setText(String.valueOf(runway.getThresholdMarkingsStripLength()));
            txtEditThresholdMarkingsStripWidth.setText(String.valueOf(runway.getThresholdMarkingsStripWidth()));
            txtEditThresholdMarkingsToLetterLength.setText(String.valueOf(runway.getThresholdMarkingsToLetterLength()));
            txtEditLetterToNumberLength.setText(String.valueOf(runway.getLetterToNumberLength()));
            txtEditNumberToHorizontalStripeLength.setText(String.valueOf(runway.getNumberToHorizontalStripeLength()));
            txtEditCharacterLength.setText(String.valueOf(runway.getCharacterLength()));
            txtEditCharacterWidth.setText(String.valueOf(runway.getCharacterWidth()));

            txtEditHorizontalStripesLength.setText(String.valueOf(runway.getStripesLength()));
            txtEditHorizontalStripesWidth.setText(String.valueOf(runway.getStripesWidth()));
            txtEditHorizontalStripesDifference.setText(String.valueOf(runway.getStripesDifference()));
            txtEditStripEnd.setText(String.valueOf(runway.getStripEnd()));
            txtEditVisualStrip.setText(String.valueOf(runway.getSmallVisualStripWidth()));
            txtEditCGA.setText(String.valueOf(runway.getLargeVisualStripWidth()));
            txtEditInstrumentStrip.setText(String.valueOf(runway.getInstrumentStrip()));
            txtEditBlastProtection.setText(String.valueOf(runway.getBlastProtection()));
            txtEditRESA.setText(String.valueOf(runway.getRESA()));

            txtEditSkyHeight.setText(String.valueOf(runway.getSkyHeight()));
            txtEditGroundHeight.setText(String.valueOf(runway.getGroundHeight()));
            txtEditAirportHeight.setText(String.valueOf(runway.getAirportHeight()));

            topViewRunway2DVisualization.setRunway(runway);
            sideViewRunway2DVisualization.setRunway(runway);

            topViewRunway2DVisualization.refresh();
            sideViewRunway2DVisualization.refresh();
        }else
        {
            setAllComponentsInGrid(gridEditRunway, false);
            setAllComponentsInGrid(gridRemoveRunway, false);
        }
    }
    //endregion
}
