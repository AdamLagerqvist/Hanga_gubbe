import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hanga_gubbe {
    public static void main(String[] args) {
        ArrayList<String> Ord = new ArrayList<String>();
        Ord = from_file("Ord");
        // write_S_array(Ord);
        System.out.println(Ord.get((int) (Math.random() * Ord.size())));
    }
    public static ArrayList<String> from_file(String input_file){
        Scanner in = null;
        {
            try {
                in = new Scanner(new File(input_file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> Ord = new ArrayList<String>();
        while (in.hasNext()) {
            Ord.add(in.nextLine());
        }
        return Ord;
    }
    public static void write_S_array (ArrayList<String> sring_arr){
        int i = 0;
        while (i < sring_arr.size()){
            System.out.println(sring_arr.get(i));
            i++;
        }
    }
}