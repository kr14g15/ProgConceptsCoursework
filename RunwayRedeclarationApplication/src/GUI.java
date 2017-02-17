import javax.swing.*;

public class GUI {
    //region PrivateVariables
    private JTabbedPane tabbedPaneTabs;
    private JPanel jPanelMainPanel;
    private TopViewRunway2DVisualization topViewRunway2DVisualization1;
    private JPanel jPanelMenu;
    private JPanel jPanel2DVisualization;
    private JPanel jPanel2DTopViewVisualization;
    private JPanel jPanel2DSideViewVisualization;
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
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new GUI().jPanelMainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //endregion
}
