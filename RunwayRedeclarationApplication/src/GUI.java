import javax.swing.*;
import java.awt.*;

public class GUI {
    //region PrivateVariables
    private JTabbedPane tabbedPaneTabs;
    private JPanel jPanelMainPanel;
    private JPanel jPanelMenu;
    private JPanel jPanel2DVisualization;
    private TopViewRunway2DVisualization topViewRunway2DVisualization;
    private JButton button1;
    private TopViewRunway2DVisualization topViewRunway2DVisualization1;
    private JPanel jPanelSideViewRunway2DVisualization;
    private JPanel jPanelTopViewRunway2DVisualization;
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
