package DATABASE;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static DATABASE.excel.*;

import static org.junit.jupiter.api.Assertions.*;

class excelTest {

    @org.junit.jupiter.api.Test
    void write_Cell() {
        excel.Write_Cell(2022,1,28,"9999");


    }

    @Test
    void test_Deserialize_json_to_list(){
        Deserialize_json_to_list();
        printMonths();


    }
    private static void printMonths(){
        for (int[] month: getColumns_len().values()) {
            for (int i = 0; i < 31; i++) {
                System.out.print(month[i] + " ");
            }
            System.out.println();
        }
    }

    @Test
    void test_Serialize_list_to_json(){
        int[] month1 = new int[31];
        for (int i = 0; i < 31; i++) {
            month1[i] = i;
        }
        HashMap<Integer,int[]> map = new HashMap<>();
        map.put(Integer.valueOf(1),month1);
        setColumns_len(map);
        Serialize_list_to_json();

    }
    @Test
    void test_wipe_json(){
       // wipe_json();

    }
}