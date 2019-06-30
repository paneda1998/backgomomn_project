import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BoardFrame extends JFrame {

    JPanel contentPanel;
    MainPanel mainPanel;
    public BoardFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(960,800));
        setResizable(false);
        contentPanel=(JPanel) this.getContentPane();
        mainPanel = new MainPanel();
        contentPanel.add(mainPanel);
        //contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

    }
}