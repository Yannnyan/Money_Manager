package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class popUpDialog extends JDialog implements ActionListener {
    Component father;
    JTextField text;
    GridBagConstraints constraints = new GridBagConstraints();
    File file = new File("myfile.txt");
    FileWriter writer;
    public popUpDialog(Component component, Point appear){
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
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        try {
            writer = new FileWriter(file, true);
        }
        catch (IOException e){
            System.out.println(e.getCause());
            this.dispose();
        }
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
                    try {
                        writer.write(text.getText());
                    }
                    catch (IOException e1){
                        System.out.println(e1.getCause());
                        dispose();
                    }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        switch(x){
            case 1:
                break;

        }
    }
}
