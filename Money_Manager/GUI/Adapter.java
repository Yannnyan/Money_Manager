package GUI;

public class Adapter {
    private static boolean popupOpen = false;
    private static int counter_Writes = 0;
    private static boolean TableVisible = true;

    public static void resetCounterWrites(){
        counter_Writes = 0;
    }
    public static void writeToArea(){
        counter_Writes++;
    }
    public static int getWriteCounter(){
        return counter_Writes;
    }
    public static boolean popUpDialog(){
        return popupOpen;
    }
    public static void closePopUpDialog(){
        popupOpen = false;
    }
    public static void openPopUpDialog(){
        popupOpen = true;
    }
    public static void writeToText(String str){
        PanelClass.writeText(str);
    }
    public static void showTable(){TableVisible = true;}
    public static void closeTable(){TableVisible = false;}
    public static boolean isTableVisible(){return TableVisible;}

}
