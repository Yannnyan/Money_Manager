package Control_Panel;
import Calculator.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control_Panel extends JPanel
                                    implements ActionListener {
    JButton calculatorButton;
    public Control_Panel(){
        Font calcfont = new Font("Calculator", Font.BOLD, 15);
        this.calculatorButton = new JButton("Calculator");
        this.calculatorButton.setFont(calcfont);
        this.calculatorButton.setBorder(BorderFactory.createLineBorder(Color.black,1));
        this.calculatorButton.setBounds(50,50,100,40);
        this.calculatorButton.setActionCommand("Call_Calculator");
        this.calculatorButton.setToolTipText("Call_Calculator");
        this.calculatorButton.addActionListener(this);
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setPreferredSize(new Dimension(600,560));
        Border b = BorderFactory.createLineBorder(Color.WHITE,10,true);
        Font font = new Font("xd", Font.ITALIC,20);
        Border b1 = BorderFactory.createTitledBorder(b,"Money Manager", TitledBorder.CENTER,TitledBorder.TOP,font, Color.BLACK);
        label.setBorder(b1);
        label.setBackground(Color.WHITE);
        label.add(this.calculatorButton);
        this.add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Call_Calculator")){
            Main.closeFrame();
            String[] a = {};
            Calculator.Main.main(a);
        }

    }
}
