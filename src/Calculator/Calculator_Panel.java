package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator_Panel extends JPanel
                                    implements ActionListener {
    JButton[] buttons;
    JLabel label1,label2;

    GridLayout gl; FlowLayout fl;
    JTextField tf;
    public Calculator_Panel(){
        this.setSize(600,560);
        this.setFlowLayout();
        this.setTextPane();
        this.setLabel2();

        this.setGridLayout();
        this.setButtons();
        this.setLabel1();

        this.label2.setVisible(true);
        this.label1.setVisible(true);
        this.add(this.label1);
        this.add(this.label2);
    }
    private void setGridLayout(){
        gl = new GridLayout(4,3);
    }
    private void setFlowLayout(){
        fl = new FlowLayout(FlowLayout.LEFT);
    }
    private void setTextPane(){
        this.tf = new JTextField();
        this.tf.setText("Your input will be updated here!");
        this.tf.setOpaque(true);
        this.tf.setVisible(true);
    }
    private void setLabel1(){
        this.label1 = new JLabel();
        this.label1.setPreferredSize(new Dimension(600,560));
        this.label1.setBackground(Color.blue);
        this.label1.setLayout(this.gl);
        for(JButton l : this.buttons){
            this.label1.add(l);
        }
    }
    private void setLabel2(){
        this.label2 = new JLabel();
        this.label2.setPreferredSize(new Dimension(600,40));
        this.label2.setBackground(Color.cyan);
        this.label2.setLayout(fl);
        this.label2.add(this.tf);
        this.setOpaque(true);
        this.setVisible(true);

    }
    private void setButtons(){
        this.buttons = new JButton[12];
        for (int i=0; i< 11; i++){
            if (i == 10){
               JButton l = new JButton("=");
               JButton l1 = new JButton("+");
               this.buttons[i] = l;
               this.buttons[i+1] = l1;
            }
            else{
                JButton l = new JButton("" + i);
                this.buttons[i] = l;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
