package HIT;

import HIT.UI.App;

import javax.swing.*;
import java.awt.*;

public class MessageDialog extends JDialog {
    public JPanel messagePanel=new JPanel();

    public MessageDialog(Frame owner, String title) {
        super(owner, title);
        setSize(320,120);
        messagePanel.setSize(300,100);
    }

    public void showMessage(String s){
        setLocationRelativeTo(App.mainPanelCenter);
        //removeAll();此处不能用removeAll
        messagePanel.removeAll();
        messagePanel.add(new JLabel(s));
        add(messagePanel);
        messagePanel.updateUI();
        setBackground(Color.black);
        System.out.println(s);
        setVisible(true);
    }
}
