import javafx.scene.control.Alert;
import jdk.internal.util.xml.impl.ReaderUTF16;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.Set;

public class RunwayExporter {

    public Runway importRunway(Path path) {
        Runway runway = new Runway();
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(path.toString());

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("runway");


            for (int i = 0; i < nList.getLength(); i++) {
                org.w3c.dom.Node nNode = nList.item(i);
                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    runway.setDirection(eElement.getElementsByTagName("direction").item(0).getTextContent().charAt(i));
                    runway.setDegree(Integer.parseInt(eElement.getElementsByTagName("degree").item(i).getTextContent()));
                    //runway.setStopwayLength(Double.parseDouble(eElement.getElementsByTagName("stopway").item(i).getTextContent()));
                    //runway.setClearwayLength(Double.parseDouble(eElement.getElementsByTagName("clearwaylength").item(i).getTextContent()));
                   // runway.setClearwayWidth(Double.parseDouble(eElement.getElementsByTagName("clearwaywidth").item(i).getTextContent()));

                    return runway;

                    //selectedAirport.getRunwayList().add(runway);
                    //selectAirport(selectedAirport);
                    //refreshRunwayListView();
                    //refreshAirportListComboBox();
                    //refreshRunwayListComboBox();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return runway;
    }

    public void exportRunway(Runway runway, Path path) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element airport = doc.createElement("airport");
            doc.appendChild(airport);

            Element parentRunway = doc.createElement("runway");
            airport.appendChild(parentRunway);

            for (PropertyDescriptor pd : Introspector.getBeanInfo(Runway.class).getPropertyDescriptors()) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    Element element = doc.createElement(pd.getReadMethod().getName().replaceFirst("get", ""));
                    element.appendChild(doc.createTextNode(String.valueOf(pd.getReadMethod().invoke(runway))));
                    parentRunway.appendChild(element);
                }
            }

            //write content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path.toFile() + ".xml"));
            transformer.transform(source, result);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("File saved");
            alert.setHeaderText("File " + "runway" + path.toFile() + ".xml has been saved.");
            alert.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}