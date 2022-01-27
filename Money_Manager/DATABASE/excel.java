package DATABASE;


import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.apache.xml.security.Init;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class excel {
    private static final int Table_Width = 31;
    private static final int Table_Height = 5;
    private static final int margin = 2;
    private static File file = new File("C:\\Users\\Alex\\Desktop\\Money_Manager\\workbook.XLS");
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;



    public static void Init_Book(){
        if(!file.exists()){
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet 1");
        }
        else {
            try{
                FileInputStream FIS = new FileInputStream(file);
                workbook = new XSSFWorkbook(FIS);
                sheet = workbook.getSheetAt(0);
            }
            catch (IOException e){
                System.out.println("Cannot read from xls");
            }
        }
    }

    /**
     *
     * This function supposed to create a new table with respect to the existing tables in the sheet
     *
     * @param month
     * @return The new table created
     */

    private static XSSFTable create_Table(int month){
        int TLRow = sheet.getLastRowNum() + margin;
        int TLColumn = sheet.getRow(TLRow).getFirstCellNum();
        CellReference TL = new CellReference(TLRow,TLColumn);
        CellReference BR = new CellReference(TLRow + Table_Height, TLColumn + Table_Width);
        XSSFTable table = sheet.createTable(new AreaReference(TL,BR,workbook.getSpreadsheetVersion()));
        table.setName("" + month);
        XSSFRow topRow =  sheet.getRow(TLRow);
        for (int i = 0; i < Table_Width; i++) {
            table.createColumn("Day " + i);
//           topRow.createCell(i);
//           topRow.getCell(i).setCellValue("Day " + i);
        }
        return table;
    }
    private static XSSFTable get_Table(int month){
        for(XSSFTable table : sheet.getTables().stream().toList()){
            if( table.getName().equals("" + month))
                return table;
        }
        return null;
    }
    /**
    * This function designed to be a black box for the other classes to call and write
     *   into cell without any other processes
    *  */
    public static void Write_Cell(int Year, int Month, int Day, String toWrite){
        if(workbook == null)
            Init_Book();
        XSSFTable thisTable = get_Table(Month);
        if(thisTable == null)
            thisTable = create_Table(Month);
        ArrayList<XSSFTableColumn> columns = (ArrayList<XSSFTableColumn>) thisTable.getColumns();
        int col = thisTable.findColumnIndex("Day "+ Day);
        for (XSSFTableColumn column: columns) {
            if(column.getName().equals("Day " + Day)){

            }

        }

    }


}
