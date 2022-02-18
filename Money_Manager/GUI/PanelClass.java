package GUI;
import CONSTANTS.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static CONSTANTS.Constants.*;

public class PanelClass extends JPanel  implements ActionListener {
    GridBagConstraints constraints;
    JButton writeDataButton, deleteLastDataButton, viewDataButton;
    JTextArea textArea;
    myJTable myTable = new myJTable();
    popUpDialog popUpDialog_obj;
    public PanelClass(){
        this.constraints = new GridBagConstraints();
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        //setSize(500,400);

        add_stuff_to_Panel();


        repaint();
    }


    /**
     *  ACTION LISTENERS: <br>
     *  INSERT DATA BUTTON = 1 <br>
     *  DELETE DATA BUTTON = 2 <br>
     *  VIEW DATA BUTTON = 3 <br>
     *
     *
     *
     */
    private void add_stuff_to_Panel(){

        //////////////////////// BUTTONS ///////////////////////
        this.writeDataButton = myJButtonFactory.getmyJButton("INSERT DATA",1);
        this.writeDataButton.addActionListener(this);
        this.constraints = myConstraintsFactory.getGridBagConstraints(0,0,0.5f,0.5f,
                GridBagConstraints.FIRST_LINE_START,GridBagConstraints.FIRST_LINE_START);
        this.add(writeDataButton,constraints);


        this.deleteLastDataButton = myJButtonFactory.getmyJButton("DELETE LAST",2);
        this.deleteLastDataButton.addActionListener(this);
        this.constraints = myConstraintsFactory.getGridBagConstraints(0,1,0.5f,0.5f,
                GridBagConstraints.FIRST_LINE_START,GridBagConstraints.FIRST_LINE_START);
        this.add(deleteLastDataButton,constraints);

        this.viewDataButton = myJButtonFactory.getmyJButton("VIEW DATA", 3);
        this.viewDataButton.addActionListener(this);
        this.constraints = myConstraintsFactory.getGridBagConstraints(0,2,0.5f,0.5f,
                GridBagConstraints.FIRST_LINE_START,GridBagConstraints.FIRST_LINE_START);
        this.add(viewDataButton,constraints);

        /////////////////////////////  TEXT AREAS /////////////////////////
        this.textArea = new JTextArea();
        this.textArea.setFont(new Font("areaTextFont",Font.ITALIC,10));
        this.textArea.setPreferredSize(new Dimension(100,150));
        this.textArea.setBackground(Color.black);
        this.textArea.setForeground(Color.ORANGE);

        //this.textArea.setVisible(true);

        this.constraints = myConstraintsFactory.getGridBagConstraints(1,3,0.5f,0.5f,
                GridBagConstraints.LAST_LINE_END,GridBagConstraints.FIRST_LINE_START);
        this.add(textArea,constraints);


        //////////////////////////// Table //////////////////////////
        this.constraints = myConstraintsFactory.getGridBagConstraints(1,2,0.5f, 0.5f,
                GridBagConstraints.FIRST_LINE_START,GridBagConstraints.FIRST_LINE_START);
        this.myTable.setVisible(true);
        this.add(myTable.getTable(),constraints);
    }
    // paint stuff here:
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
            final BufferedImage bgImage = ImageIO.read(new File("Money_Manager\\IMAGES\\wallstreet1.jpg"));
            g.drawImage(bgImage, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);

        }
        catch (IOException e){
            System.out.println("cant read image");
        }
    }
    public void appendTextArea(String str){
        if(Adapter.getWriteCounter() >= 10){
            Adapter.resetCounterWrites();
            this.textArea.setText("");
        }
        Adapter.writeToArea();
        this.textArea.append(str);
    }
    public static void writeText(String string){
        File file = new File("myfile.txt");
        FileWriter writer;
        try{
            writer = new FileWriter(file, true);
            writer.write(string);
            writer.close();
        }
        catch (IOException e){
            System.out.println(e.getCause());
        }
    }
    public void OpenPopUp(){
        int xpos = this.getX() + this.getWidth()/2,
                ypos = this.getY() + this.getHeight()/2;

        Point mypoint = new Point(xpos,ypos);
        this.popUpDialog_obj = new popUpDialog(this,mypoint);
    }

    // disposes all frames created by this object
    public void close(){
        if(Adapter.popUpDialog())
            this.popUpDialog_obj.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        switch (x){
            // popUpButton
            case 1:
                if(!Adapter.popUpDialog()){
                    Adapter.openPopUpDialog();
                    this.OpenPopUp();
                }
                break;
            case 2:
                break;
            case 3:
                if(!Adapter.isTableVisible()){
                    Adapter.showTable();
                    this.myTable.setVisible(true);
                    this.repaint();
                    new popUpTable(this.myTable);
                }
                else if(Adapter.isTableVisible()){
                    Adapter.closeTable();
                    this.myTable.setVisible(false);
                    this.repaint();

                }
        }
    }
}
