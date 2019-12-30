/**
 * This class implements a user that plays the Sudoku Game.A User consists of their name, their file that keeps their statistics for the game
 * ,two variables duidokuWins and duidokuLosses that we keep the wins and defeats for the duidoku version.Also there two HashSets in this class
 * classicPuzzlesPlayed and killerPuzzlesPlayed that keep the puzzles that the user has played for the classic and killer Sudoku versions
 * respectively.
 *
 *
 */

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class User {
    private int  duidokuWins, duidokuLosses;
    private HashSet<Integer> classicPuzzlesPlayed, killerPuzzlesPlayed;
    private String name;
    private File userStatistics;

    public User(String aName){
          this.name = aName;
          classicPuzzlesPlayed = new HashSet<>();
          killerPuzzlesPlayed = new HashSet<>();
    }

    public HashSet<Integer> getClassicPuzzlesPlayed() {
        return classicPuzzlesPlayed;
    }

    public HashSet<Integer> getKillerPuzzlesPlayed() {
        return killerPuzzlesPlayed;
    }

    public String getClassicPPlayed(){
        StringBuilder c = new StringBuilder();
        Iterator<Integer> k = classicPuzzlesPlayed.iterator();

        while(k.hasNext()){
            c.append((char)(k.next() + '0'));
            c.append(' ');
        }
        return c.toString();
    }

    public String getKillerPPlayed(){
        StringBuilder c = new StringBuilder();
        Iterator<Integer> k = killerPuzzlesPlayed.iterator();

        while(k.hasNext()){
            c.append((char)(k.next() + '0'));
            c.append(' ');
        }
        return c.toString();
    }



    public void addValueToClassicPuzzlesPlayed(int i){
        classicPuzzlesPlayed.add(i);
    }

    public void addValueToKillerPuzzlesPlayed(int i){
        killerPuzzlesPlayed.add(i);
    }

    public void addWins(){
        this.duidokuWins++;
    }

    public void addLosses(){
        this.duidokuLosses++;
    }


    public boolean checkIfExistsInKillerPuzzlesPlayed(int value){
        return killerPuzzlesPlayed.contains(value);
    }

    public boolean checkIfExistsInClassicPuzzlesPlayed(int value){
        return classicPuzzlesPlayed.contains(value);
    }

    public void insertStatisticsInTheFile(){
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(name, false));
        ){
            Iterator c = classicPuzzlesPlayed.iterator();
            while(c.hasNext()){
                out.write(String.valueOf(c.next()));
            }
            out.write("\n");


            Iterator k = killerPuzzlesPlayed.iterator();
            while(k.hasNext()){
                out.write(String.valueOf(k.next()));
            }
            out.write("\n");

            out.write(String.valueOf(duidokuWins));
            out.write("\n");

            out.write(String.valueOf(duidokuLosses));

        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public void readTheStatisticsFromTheFile(){
        try (BufferedReader in = new BufferedReader(
                    new FileReader(name));
            ) {

                String l;
                int k = 0;
                while ((l = in.readLine()) != null) {
                    if (k == 0) {
                        for (int j = 0; j < l.length(); j++) {
                            classicPuzzlesPlayed.add(Character.getNumericValue(l.charAt(j)));

                        }
                    } else if (k == 1) {
                        for (int j = 0; j < l.length(); j++) {
                            killerPuzzlesPlayed.add(Character.getNumericValue(l.charAt(j)));

                        }
                    } else if (k == 2) {
                        this.duidokuWins = Integer.parseInt(l);

                    } else if (k == 3) {

                       this.duidokuLosses = Integer.parseInt(l);
                    }

                  k++;

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    public void createUserFile(){
         userStatistics = new File(name);
    }

    public String getName() {
        return name;
    }

    public int getDuidokuWins() {
        return duidokuWins;
    }

    public int getDuidokuLosses() {
        return duidokuLosses;
    }


}
