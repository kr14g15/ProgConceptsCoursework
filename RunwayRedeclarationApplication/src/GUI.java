import javax.swing.*;
import java.awt.*;

public class GUI {
    //region PrivateVariables
    private JTabbedPane paneUKMap;
    private JPanel jPanelMainPanel;
    private JPanel jPanelDisplay;
    private JPanel jPanelOptions;
    private JComboBox cboRunwayList;
    private JLabel jLabelRunwayList;
    private JTabbedPane paneOptions;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel jPanelUKMap;
    private JComboBox cboAirportList;
    private JLabel lblAirportList;
    private JPanel jPanelMainDisplay;
    private JPanel jPanelLeftSideDisplay;
    private JPanel jPanelRightSideDisplay;
    //endregion

    //region PublicVariables
    //endregion

    //region PrivateMethods
    //endregion

    //region PublicMethods
    //endregion

    //region Events
    //endregion

    //region Main
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double doubleScreenWidth = screenSize.getWidth();
        double doubleScreenHeight = screenSize.getHeight();

        JFrame frame = new JFrame("Runway Re-declaration Tool");
        frame.setSize((int)(doubleScreenWidth * 0.7), (int)(doubleScreenHeight * 0.7));
        frame.setContentPane(new GUI().jPanelMainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //endregion
}
