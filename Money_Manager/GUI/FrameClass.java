package GUI;
import javax.swing.JFrame;
import java.awt.*;

public class FrameClass {

    public static void main(String[] args){
        new FrameClass();
    }


    public FrameClass(){
        JFrame frame = new JFrame();
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        PanelClass panel = new PanelClass();
        container.add(panel, constraints);

       // panel.setVisible(true);

        frame.setVisible(true);

    }



}
