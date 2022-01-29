package GUI;

import javax.swing.*;
import javax.swing.text.TabableView;


/**
 * This class should Serialize and Deserialize objects from a file and load it <br>
 * into a beautiful representation of a table on the screen
 *
 */
public class myJTable {

    private JTable table;
    public myJTable(){
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", Integer.valueOf(5), Boolean.valueOf(false)},
                {"John", "Doe",
                        "Rowing", Integer.valueOf(3),  Boolean.valueOf(true)},
                {"Sue", "Black",
                        "Knitting", Integer.valueOf(2), Boolean.valueOf(false)},
                {"Jane", "White",
                        "Speed reading", Integer.valueOf(20), Boolean.valueOf(true)},
                {"Joe", "Brown",
                        "Pool", Integer.valueOf(10), Boolean.valueOf(false)}
        };
        table = new JTable(data,columnNames);
    }

    public JTable getTable() {
        return this.table;
    }

    private void DeserializeFromJsonToTable(){



    }
    private void SerializeTable(){



    }

}





