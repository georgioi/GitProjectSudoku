import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Initialize {

    //Need to create a Graphics class
    private ClassicSudoku classic;
    private KillerSudoku killer;
    private Random r = new Random();
    private boolean isClassic, isKiller;
    public Initialize(boolean isCl, boolean isKil){
        classic = new ClassicSudoku();
        killer = new KillerSudoku();
        this.isClassic = isCl;
        this.isKiller = isKil;
        readTheDataFromTheFiles();

    }

    public void readTheDataFromTheFiles(){

        if(isClassic) {


            try (BufferedReader in = new BufferedReader(
                    new FileReader("ClassicPuzzles.txt"));
            ){
                String l;
                int i = 0;
                while ((l = in.readLine()) != null) {

                    if(l.isEmpty()){
                        i ++;
                        continue;  //if it doesn't work remove continue;
                    }
                    for (int j=0;j<l.length();j++){
                       classic.addCharacterIntoClassicPuzzle(i, l.charAt(j));
                    }

                }
            }catch(IOException e){
                e.printStackTrace();
            }
          }else if(isKiller){

            try(BufferedReader in = new BufferedReader(new FileReader("KillerPuzzles.txt"));){
                String l;
                int i = 0;
                while ((l = in.readLine()) != null) {
                    if(l.isEmpty()){
                        i ++;
                        continue;  //if it doesn't work remove continue;
                    }
                    for (int j=0;j<l.length();j++){
                      killer.addCharacterIntoKillerPuzzle(i, l.charAt(j));

                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }

    }





    public ClassicSudoku getClassic() {
        return classic;
    }

    public KillerSudoku getKiller() {
        return killer;
    }





}
