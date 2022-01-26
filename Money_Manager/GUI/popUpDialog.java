package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class popUpDialog extends JDialog implements ActionListener {
    PanelClass father;
    JTextField text;
    GridBagConstraints constraints = new GridBagConstraints();
    public popUpDialog(PanelClass component, Point appear){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        this.father = component;
        this.setVisible(true);
        this.setLocation(appear);
        this.setSize(200,100);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){
                Adapter.closePopUpDialog();
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        add_stuff();
    }
    private void add_stuff(){
        this.text =new JTextField();
        this.text.setActionCommand("1");
        this.text.setSize(80,40);

        this.text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    Adapter.writeToText(text.getText());
                    father.appendTextArea(text.getText() + "\n");
                    text.setText("");

                }
            }
        });


        //this.text.setFont();
        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.weightx=0.5;
        this.constraints.weighty=0.5;
        this.constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.constraints.fill = GridBagConstraints.BOTH;
        this.add(this.text,this.constraints);
    }
    public void close(){
        this.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        switch(x){
            case 1:
                break;

        }
    }
}
