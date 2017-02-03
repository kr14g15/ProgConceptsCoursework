import javax.swing.*;

public class guiTest {
    private JPanel jPanelTest;
    private JButton button1;
    private JButton button2;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new guiTest().jPanelTest);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
