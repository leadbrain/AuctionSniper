package auctionsniper.ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.leadbrain.ex.ApplicationRunner;
import com.leadbrain.ex.Main;

public class MainWindow extends JFrame {
    private final JLabel sniperStatus = createLabel(ApplicationRunner.STATUS_JOINING);

    public MainWindow() {
        super("Auction Sniper");
        setName(Main.MAIN_WINDOW_NAME);
        add(sniperStatus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showStatus(String status) {
        sniperStatus.setText(status);
    }

    private static JLabel createLabel(String initialText) {
        JLabel result = new JLabel(initialText);
        result.setName(Main.SNIPER_STATUS_NAME);
        result.setBorder(new LineBorder(Color.BLACK));
        return result;
    }

}
