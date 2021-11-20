package Control_Panel;

import javax.swing.*;
import java.awt.*;

public class Main {
    // this function runs the jframe
    private static JFrame f;

    public static void main(String[] args){
        f = new JFrame("Money_Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Control_Panel cp = new Control_Panel();
        f.getContentPane().add(cp, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);

    }

    public static void closeFrame(){
        f.dispose();
    }
}
