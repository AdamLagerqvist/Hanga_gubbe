import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hanga_gubbe{
    String seacretWord;
    int Fail = 0;
    int Right = 0;
    int FailCap = 0;
    ArrayList<Character> printWord = new ArrayList<Character>();
    boolean printWordB = true;

    /*
    Startar min game loop
     */
    Hanga_gubbe() {
        ArrayList<String> Words = new ArrayList<String>();
        Scanner in2 = null;
        in2 = new Scanner(System.in);
        System.out.println("Skriv 1 om du vill använda ett random ord eller skriv 2 om du vill välja ordet själv");
        while(seacretWord == null) {
            try{
                if (Integer.parseInt(in2.nextLine()) == 1){
                    Words = from_file("Ord");
                    seacretWord = (Rand_List(Words));
                } else{
                    System.out.println("Skriv ordet");
                    seacretWord = in2.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Skriv 1 eller 2");
            }
        }
        System.out.println("Hur manga fel tillater du innan spelet ar forlorat 8 ar rekomenderat (kan ej vara 0)");
        while (FailCap == 0) {
            try {
                FailCap = Integer.parseInt(in2.nextLine());
            } catch (Exception e) {
                System.out.println("Skriv en nummer");
            }
        }
        print();
        System.out.println("Spelet kan nu borja skriv en bokstav för att gissa");
        game_loop();
    }

    public void game_loop(){
        ArrayList<String> Gissningar = new ArrayList<String>();
        Scanner in2 = null;
        in2 = new Scanner(System.in);
        while (FailCap > Fail && Right < seacretWord.length()){
            String gissning = in2.nextLine();
            if (!Gissningar.contains(gissning)){
                Gissningar.add(gissning);
                rightOrWrong(gissning.charAt(0));
                System.out.print("gissade bokstäver: ");
                System.out.print(Gissningar);
            }else {
                print();
                System.out.print("gissade bokstäver: ");
                System.out.print(Gissningar);
            }
        }
        if (FailCap < Fail){
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
    }

    /*
    Kollar om en String/gissning finns i ordet 1 eller flera gånger
    startar också Utritnings systemet
    */
    public void rightOrWrong(char S) {
        boolean If_Wrong = true;
        for (int i = 0;i < seacretWord.length();i++) {
            if (Character.toUpperCase(S) == Character.toUpperCase(seacretWord.charAt(i))) {
                printWord.set(i, S);
                Right++;
                If_Wrong = false;
            }
        }
        if (If_Wrong){
            Fail++;
        }
        print();
    }

    /*
    Printar ut ordet + antalet fel du har (fixar gui'n)
    */
    public void print(){
        if(printWordB) {
            for (int i = 0; i < seacretWord.length(); i++) {
                printWord.add('_');
                printWordB = false;
            }
        }
        System.out.println("\n\n\n\n\n\n\n");
        for (int i=0; i < printWord.size(); i++) {
            System.out.print(printWord.get(i));
        }
        System.out.println("\nRätt: " + Right + "\nFel: " + Fail + "/" + FailCap);
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
        ArrayList<String> Words = new ArrayList<String>();
        while (in.hasNext()) {
            Words.add(in.nextLine());
        }
        // skickar ut listan full med ord
        return Words;
    }
}