package GUI;

import javax.swing.*;
import java.awt.*;

import static CONSTANTS.Constants.BUTTON_HEIGHT;
import static CONSTANTS.Constants.BUTTON_WIDTH;

public class myJButtonFactory {

    public static JButton getmyJButton(String name, int actionCommand){
        JButton myButton = new JButton();
        myButton.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
        Font buttonFont = new Font("buttonFont",Font.ITALIC,10);
        myButton.setFont(buttonFont);
        myButton.setBackground(Color.BLACK);
        myButton.setForeground(Color.GREEN);
        myButton.setText(name);
        myButton.setActionCommand("" + actionCommand);

        return myButton;
    }






}
