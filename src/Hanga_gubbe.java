import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hanga_gubbe{
    String Hang_ord;
    int Fel = 0;
    int Rätt = 0;
    int tillåtna_fel = 0;
    ArrayList<Character> U = new ArrayList<Character>();
    boolean Ub = true;

    /*
    Startar min game loop
     */
    Hanga_gubbe() {
        ArrayList<String> Ord = new ArrayList<String>();
        Scanner in2 = null;
        in2 = new Scanner(System.in);
        System.out.println("Skriv 1 om du vill använda ett random ord eller skriv 2 om du vill välja ordet själv");
        if (Integer.parseInt(in2.nextLine()) == 1){
            Ord = from_file("Ord");
            Hang_ord = (Rand_List(Ord));
        } else{
                Hang_ord = in2.nextLine();
        }
        System.out.println("Hur manga fel tillater du innan spelet ar forlorat 8 ar rekomenderat");
        tillåtna_fel = in2.nextInt();
        print();
        System.out.println("Spelet kan nu borja skriv en bokstav för att gissa");
        game_loop();
        // write_S_ArrayList(Ord);
    }

    public void game_loop(){
        ArrayList<String> Gissningar = new ArrayList<String>();
        Scanner in2 = null;
        in2 = new Scanner(System.in);
        while (tillåtna_fel > Fel && Rätt < Hang_ord.length()){
            String gissning = in2.nextLine();
            if (!Gissningar.contains(gissning)){
                Gissningar.add(gissning);
                Rätt_Eller_Fel(gissning.charAt(0));
            }else {
                print();
            }
        }
        if (tillåtna_fel < Fel){
            print();
            System.out.println("You lost");
        } else{
            print();
            System.out.println("Du vann yey");
        }
        return;
    }

    public static void main(String[] args){
        Hanga_gubbe instans = new Hanga_gubbe();
        //instans.Rätt_Eller_Fel('O');
        //instans.Rätt_Eller_Fel('q');
    }

    /*
    Kollar om en String/gissning finns i ordet 1 eller flera gånger
    startar också Utritnings systemet
    */
    public void Rätt_Eller_Fel(char S) {
        boolean Om_Fel = true;
        for (int i = 0;i < Hang_ord.length();i++) {
            if (Character.toUpperCase(S) == Character.toUpperCase(Hang_ord.charAt(i))) {
                U.set(i, S);
                Rätt++;
                Om_Fel = false;
            }
        }
        if (Om_Fel){
            Fel++;
        }
        print();
    }

    /*
    Printar ut ordet + antalet fel du har (fixar gui'n)
    */
    public void print(){
        if(Ub) {
            for (int i = 0; i < Hang_ord.length(); i++) {
                U.add('_');
                Ub = false;
            }
        }
        System.out.println("\n\n\n\n\n\n\n");
        for (int i=0; i < U.size(); i++) {
            System.out.print(U.get(i));
        }
        System.out.println("\nRätt: " + Rätt + "\nFel: " + Fel + "/" + tillåtna_fel);
    }

    /*
    Funktionen ger ett svar för ett random index från en in skickad ArrayList
     */
    public String Rand_List(ArrayList<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    /*
    Funktion för att skriva ord till en ArrayList från en fill där inputten är filnamnet
     */
    public ArrayList<String> from_file(String input_file){
        Scanner in = null;
        // Letar efter filen med filnamnet
        // Om den ite hittar filen avbryts programmet soch meddelandet "Filen Hittades inte" skickas
        {
            try {
                in = new Scanner(new File(input_file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Filen Hittades inte");
                System.exit(0);
            }
        }
        // loop för inmatningen av ord i ArrayListen
        ArrayList<String> Ord = new ArrayList<String>();
        while (in.hasNext()) {
            Ord.add(in.nextLine());
        }
        // skickar ut listan full med ord
        return Ord;
    }

    /*
    Funktion för utskrift av ArrayLists
     */
    public void write_S_ArrayList (ArrayList<String> sring_arr){
        for (int i = 0; i < sring_arr.size(); i++){
            System.out.println(sring_arr.get(i));
        }
    }
}
