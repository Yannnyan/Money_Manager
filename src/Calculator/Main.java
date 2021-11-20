package Calculator;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame f;

    public static void main(String[] args){
        f = new JFrame("Calculator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Calculator_Panel cp = new Calculator_Panel();
        f.add(cp, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }


}
