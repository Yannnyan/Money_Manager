package GUI;

import javax.swing.*;
import java.awt.*;

public class popUpTable extends JDialog{
    public popUpTable(myJTable table){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComponents(table);
        this.pack();
        this.setVisible(true);
    }
    private void addComponents(myJTable table){
        this.add(new JScrollPane(table.getTable()));
        //this.add(new JTextArea());
    }

    public void updateTable(){



    }
    public void popUp(){



    }


}
