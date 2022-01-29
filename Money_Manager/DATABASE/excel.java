package DATABASE;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.apache.xml.security.Init;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class excel {
    private static final int Table_Width = 31;
    private static final int Table_Height = 5;
    private static final int margin = 2;
    private static File file = new File("workbook.xlsx");
    private static HashMap<Integer,int[]> columns_len= null;
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;



    public static void Init_Book(){
        columns_len = new HashMap<>();
        if(!file.exists()){
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet 1");

        }
        else {
            try{
                FileInputStream FIS = new FileInputStream(file);
                workbook = new XSSFWorkbook(FIS);
                sheet = workbook.getSheetAt(0);
                Deserialize_json_to_list();
            }
            catch (IOException e){
                System.out.println("Cannot read from xls");
            }
        }
    }

    public static void Deserialize_json_to_list(){
        try {
            File f1 = new File("Money_Manager\\DATABASE\\tables.json");
            FileReader fr = new FileReader(f1);
            Gson gson = new Gson();
            Type typeOb = new TypeToken<HashMap<Integer,int[]>>(){}.getType();
            columns_len = gson.fromJson(fr,typeOb);
            fr.close();
        }
        catch (IOException e){
            System.out.println("Cant read file " + e.getCause());
        }


    }
    public static void Serialize_list_to_json(){
        try{
            File f1 = new File ("Money_Manager\\DATABASE\\tables.json");
            FileWriter fw = new FileWriter(f1);
            JsonWriter jw = new JsonWriter(fw);
            Gson gson = new Gson();
            Type typeOb = new TypeToken<HashMap<Integer,int[]>>(){}.getType();
            gson.toJson(columns_len,typeOb,jw);
            jw.close();
            fw.close();

        }
        catch (IOException e){
            System.out.println("Cant write file " + e.getCause());
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
        int TLRow, TLColumn;
        if(sheet.getLastRowNum() == -1){
            TLRow = margin;
            TLColumn = margin*2;
        }
        else {
            TLRow = sheet.getLastRowNum() + margin;
            TLColumn = sheet.getRow(TLRow).getFirstCellNum();
        }
        CellReference TL = new CellReference(TLRow, TLColumn);
        CellReference BR = new CellReference(TLRow + Table_Height, TLColumn + Table_Width);
        XSSFTable table = sheet.createTable(new AreaReference(TL, BR, workbook.getSpreadsheetVersion()));
        table.setName("" + month);
        XSSFRow currRow;

        int startRow = table.getStartRowIndex(), startCol = table.getStartColIndex();
        for (int i = 0; i < Table_Height; i++) { // create cells and rows for the table
            currRow = sheet.createRow(i);
            for (int j = 0; j < Table_Width; j++) {
                if(i==0){
                    currRow.createCell(i + startCol).setCellValue("Day " + j); // init first row as headers
                }
                else{
                    currRow.createCell(j+startCol).setCellValue("");
                }
            }
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
        XSSFTable table = get_Table(Month);
        if(table == null)
            table = create_Table(Month);
        int startRow = table.getStartRowIndex(), endCol = table.getEndColIndex();
        for (int i = table.getStartColIndex(); i < endCol; i++) {
            if(sheet.getRow(startRow).getCell(i).getStringCellValue().equals("Day "+ Day)){
                int len = columns_len.get(Month)[Day];
                sheet.getRow(len+1).getCell(i).setCellValue(toWrite);
                update_table(table,Month,Day);
                columns_len.get(Month)[Day] +=1;
                break;
            }
        }
        Write_sheet();
    }
    private static void update_table(XSSFTable table,int Month, int Day){
        if(columns_len.get(Month)[Day] +1 >= table.getEndRowIndex() - table.getStartRowIndex()){
            for (int i = 1; i < 6; i++) {
                sheet.createRow(table.getStartRowIndex() + i);
            }
            AreaReference ref = new AreaReference(table.getStartCellReference(), new CellReference(table.getEndColIndex(),table.getEndRowIndex() + 5),workbook.getSpreadsheetVersion());
            table.setArea(ref);
        }
    }
    private static void Write_sheet(){
        if(workbook == null){
            System.out.println("workbook is null, cant write");
            return;
        }
        try{
            if(!file.exists()){
                System.out.println("File doesn't exist! Creating a new One!");
                file.createNewFile();
            }
            FileOutputStream FOS = new FileOutputStream(file);
            workbook.write(FOS);
            Serialize_list_to_json();
            FOS.close();
        }
        catch(IOException e) {
            System.out.println("caught exception trying to write: " + e.getCause());
        }
    }

    // for testing purposes
//    public static void wipe_json(){
//        try{
//            File f1 = new File("Money_Manager\\DATABASE\\tables.json");
//            FileWriter fw = new FileWriter(f1);
//            fw.write("");
//            fw.close();
//        }
//        catch (IOException e){
//            System.out.println(e.getCause());
//        }
//
//    }


    public static HashMap<Integer, int[]> getColumns_len() {
        return columns_len;
    }

    public static void setColumns_len(HashMap<Integer, int[]> columns_len) {
        excel.columns_len = columns_len;
    }

    public static void main(String[] args){





    }

}
