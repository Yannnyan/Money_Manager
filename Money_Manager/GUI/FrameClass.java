package GUI;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameClass {
    JFrame frame;

    public static void main(String[] args){
        new FrameClass();
    }


    public FrameClass(){
        frame = new JFrame();
        frame.setSize(700,500);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        Container container = frame.getContentPane();
        GridBagLayout gridBagLayout = new GridBagLayout();
        container.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        // panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.BOTH;
        frame.setLocationRelativeTo(null);
        final PanelClass panel = new PanelClass();
        container.add(panel, constraints);

       // panel.setVisible(true);

        frame.setVisible(true);


        // dispose all frames
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                panel.close();
                //super.windowClosing(e);
                frame.dispose();
            }
        };
        frame.addWindowListener(windowListener);
    }



}
