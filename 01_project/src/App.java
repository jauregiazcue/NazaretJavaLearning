import java.awt.event.*;
import javax.swing.*;

class MyListener implements ActionListener {
    boolean changePannel = false;

    public void actionPerformed(ActionEvent e) {
        this.changePannel = true;
    }
}

public class App {
    public static void main(String[] args) {
        // Creating instance of JFrame
        JFrame frame = new JFrame();

        // Creating instance of JButton
        JButton button = new JButton("Scream for me");
        button.setBounds(150, 200, 220, 50);
        MyListener ml = new MyListener();
        button.addActionListener(ml);
        frame.add(button);

        frame.setSize(500, 600);
        // using no layout managers
        frame.setLayout(null);
        frame.setVisible(true);


    }
}
