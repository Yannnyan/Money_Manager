package DATABASE;


import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.apache.xml.security.Init;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class excel {
    private static File file = new File("C:\\Users\\Alex\\Desktop\\Money_Manager\\workbook.xls");
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;



    public static void InitWorkbook(){
        try{
            FileInputStream FIS = new FileInputStream(file);
            workbook = new XSSFWorkbook(FIS);
            sheet = workbook.getSheetAt(0);
        }
        catch (IOException e){
            System.out.println("Error cannot create workbook");
        }
    }
    public static void WriteToExcel(int Month, int Day, String str){
        AreaReference areaReference = new AreaReference(new CellReference(0,0), new CellReference(1,1), workbook.getSpreadsheetVersion());
        XSSFTable table = sheet.createTable(areaReference);
        table.setName("Hello my friend");
        WriteToTable(table);
        ArrayList<XSSFTable> tables = (ArrayList<XSSFTable>) sheet.getTables();
        tables.forEach((table1) ->{System.out.println("Table is :" + table1.getName());});
        WriteSheet();
    }
    private static void WriteToTable(XSSFTable table){
        XSSFRow row = null;
        XSSFCell cell = null;
        for (int i = table.getStartRowIndex(); i < table.getEndRowIndex(); i++) {
            if(sheet.getRow(i) == null)
                sheet.createRow(i);
            row = sheet.getRow(i);
            for (int j = table.getStartColIndex(); j < table.getEndColIndex(); j++) {
                if(row.getCell(j) == null)
                    row.createCell(j);
                cell = row.getCell(j);
                cell.setCellValue("cel " + j);
            }
        }
    }
    private static void ReadFromTable(XSSFTable table){
        for (int i=table.getStartRowIndex(); i < table.getEndRowIndex(); i++) {
            for (int j = table.getStartColIndex(); j < table.getEndColIndex(); j++) {
                System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
            }
        }
    }
    private static void WriteSheet(){
        try {
            FileOutputStream FOS = new FileOutputStream(file);
            workbook.write(FOS);
            workbook.close();
        }
        catch (IOException e){
            System.out.println("Cant write sheet");
        }

    }
    public static void main(String[] args){
        InitWorkbook();
        WriteToExcel(10,2,"Hello world");
        Iterator<XSSFTable> iterator = sheet.getTables().iterator();
        while(iterator.hasNext()) {
            ReadFromTable(iterator.next());
        }

    }
}
