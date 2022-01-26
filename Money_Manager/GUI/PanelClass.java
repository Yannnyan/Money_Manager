package GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PanelClass extends JPanel  implements ActionListener {
    GridBagConstraints constraints;
    JButton popUpButton;
    JTextArea textArea;
    popUpDialog popUpDialog_obj;
    public PanelClass(){
        this.constraints = new GridBagConstraints();
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        //setSize(500,400);

        add_stuff_to_Panel();


        repaint();
    }



    private void add_stuff_to_Panel(){
        this.popUpButton = new JButton();
        Font font = new Font("insert",Font.ITALIC,10);
        this.popUpButton.setFont(font);
        this.popUpButton.setBackground(Color.BLACK);
        this.popUpButton.setForeground(Color.GREEN);
        this.popUpButton.setText("INSERT DATA");
        this.popUpButton.addActionListener(this);
        this.popUpButton.setActionCommand("1");
        this.popUpButton.setSize(100,50);

        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.weightx = 0.5;
        this.constraints.weighty = 0.5;
        this.constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.constraints.fill = GridBagConstraints.FIRST_LINE_START;
        this.add(popUpButton,constraints);

        this.textArea = new JTextArea();
        this.textArea.setFont(font);
        this.textArea.setPreferredSize(new Dimension(100,50));
        this.textArea.setBackground(Color.black);
        this.textArea.setLocation(100,0);
        this.textArea.setForeground(Color.ORANGE);

        //this.textArea.setVisible(true);

        this.constraints.gridx=1;
        this.constraints.gridy=0;
        this.constraints.weightx = 0.5;
        this.constraints.weighty = 0.5;
        this.constraints.anchor = GridBagConstraints.LAST_LINE_END;
        this.constraints.fill = GridBagConstraints.FIRST_LINE_START;

        add(textArea,constraints);

    }
    // paint stuff here:
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("Hello world", 0, 100);
        try {
            final BufferedImage bgImage = ImageIO.read(new File("C:\\Users\\Alex\\Desktop\\Money_Manager\\Money_Manager\\IMAGES\\wallstreet1.jpg"));
            g.drawImage(bgImage, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);

        }
        catch (IOException e){
            System.out.println("cant read image");
        }
    }
    public void appendTextArea(String str){
        if(Adapter.getWriteCounter() >= 3){
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
        }
    }
}
