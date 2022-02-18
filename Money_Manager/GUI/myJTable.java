package GUI;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriter;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class should Serialize and Deserialize objects from a file and load it <br>
 * into a beautiful representation of a table on the screen
 *
 */
public class myJTable {

    private JTable table;
    public myJTable(){
        DeserializeFromCSVToTable();
    }

    public JTable getTable() {
        return this.table;
    }
    public void closeTable(){SerializeTableToCSV();}
    public void setVisible(boolean bool){
        this.table.setVisible(bool);
    }

    private void DeserializeFromCSVToTable(){ // this class constructs the jtable field from a csv file
        String[] title;
        ArrayList<String[]> dataList = new ArrayList<>();
        String[][] data;
        File file = new File("my_TableCSV.csv");
        try{
            CSVReader csvReader = new CSVReader(new FileReader(file));
            Iterator<String[]> iter = csvReader.iterator();
            title = iter.next();
            while(iter.hasNext()){
                dataList.add(iter.next());
            }
            data = new String[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }
            this.table = new JTable(data,title);
            csvReader.close();
        }
        catch (IOException e){
            System.out.println("IOException has occured when trying to read the csv table : " + e.getCause());
        }
    }
    private void SerializeTableToCSV(){ // this class turns the jtable field into a csv file
        File file = new File("my_TableCSV.csv");
        try {
            CSVWriter csvwriter = new CSVWriter(new FileWriter(file));
            String[] temp;
            int width = table.getWidth(),height = table.getHeight();
            for (int i = 0; i < height; i++) {
                temp = new String[width];
                for (int j = 0; j < width; j++) {
                    temp[j] = table.getValueAt(i,j).toString();
                }
                csvwriter.writeNext(temp);
            }
            csvwriter.flush();
        }
        catch (IOException e){
            System.out.println("IOException trying to serialize the table to csv : " + e.getCause());
        }


    }

}





