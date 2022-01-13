package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelClass extends JPanel  implements ActionListener {
    GridBagConstraints constraints;
    JButton popUpButton;
    JTextArea textArea;
    public PanelClass(){
        this.constraints = new GridBagConstraints();
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setBackground(new Color(0x494949));
        setSize(500,400);

        add_stuff_to_Panel();


        repaint();
    }



    private void add_stuff_to_Panel(){
        this.popUpButton = new JButton();
        Font font = new Font("insert",Font.ITALIC,10);
        this.popUpButton.setFont(font);
        this.popUpButton.setText("INSERT DATA");
        this.popUpButton.addActionListener(this);
        this.popUpButton.setActionCommand("1");
        this.popUpButton.setSize(100,50);
        this.popUpButton.setLocation(0,0);

        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.weightx = 0.5;
        this.constraints.weighty = 0.5;
        this.constraints.anchor = GridBagConstraints.CENTER;
        this.constraints.fill = GridBagConstraints.BOTH;
        this.add(popUpButton,constraints);

        this.textArea = new JTextArea();
        this.textArea.setFont(font);
        this.textArea.setSize(100,50);
        this.textArea.setBackground(Color.black);
        this.textArea.setLocation(100,0);
        this.textArea.setForeground(Color.ORANGE);
        //this.textArea.setVisible(true);

        this.constraints.gridx=1;
        this.constraints.gridy=0;
        this.constraints.weightx = 0.5;
        this.constraints.weighty = 0.5;
        this.constraints.fill = GridBagConstraints.BOTH;

        add(textArea,constraints);

    }
    // paint stuff here:
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("Hello world", 0, 100);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());

        switch (x){
            // popUpButton
            case 1:
                int xpos = this.getX() + this.getWidth()/2,
                        ypos = this.getY() + this.getHeight()/2;

                Point mypoint = new Point(xpos,ypos);
                popUpDialog popUp = new popUpDialog(this,mypoint);

                break;


        }




    }



}
