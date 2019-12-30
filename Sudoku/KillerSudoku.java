import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class KillerSudoku extends SudokuLogic {




    private ArrayList<ColorArea> ColorAreas;
    private ArrayList<Character>[] killerPuzzles = new ArrayList[10];
    private ArrayList<Integer> availableKillerPuzzles;

    private int killerPuzzle;





    public KillerSudoku(){
        super(9);
        ColorAreas = new ArrayList<>();
        availableKillerPuzzles = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            killerPuzzles[i] = new ArrayList<>();
        }

        for(int i = 1; i <= 10; i++){
            availableKillerPuzzles.add(i);
        }
       // excludeKillerPlayedPuzzles();

    }



    public ArrayList<ColorArea> getColorAreas() {
        return ColorAreas;
    }

    public void addCharacterIntoKillerPuzzle(int i,  char k){
        killerPuzzles[i].add(k);
    }



    public void setKillerColorArea(int row, int col, int value, boolean notEmpty) {
        ColorArea colorArea = ColorAreas.get(checkColorArea(row, col));
            for (int j = 0; j < colorArea.getColorAreaSize(); j++) {
                if (colorArea.getColorAreaBox(j).getBoxCoordinateI() == row && colorArea.getColorAreaBox(j).getBoxCoordinateJ() == col) {
                    colorArea.setColorAreaBoxValue(j, value, colorArea.getColorAreaBox(j));
                    colorArea.setColorAreaBoxNotEmptyVariable(j, notEmpty, colorArea.getColorAreaBox(j));
                }
            }

    }



    public int getColorAreasSize(){
        return ColorAreas.size();
    }
   public int[][] getKillerBoard(){
        return this.board;
   }

    public void chooseRandomKillerPuzzle(){
        Random r = new Random();
        int kK = r.nextInt(availableKillerPuzzles.size());
        killerPuzzle = availableKillerPuzzles.get(kK);


        int j = 0;
        ColorArea aColorArea;
        StringBuilder color;
        String sColor;
       // System.out.println(killerPuzzles[killerPuzzle].size());
        while(j < killerPuzzles[killerPuzzle].size()){
                 aColorArea = new ColorArea();
                 color = new StringBuilder();
                 while(killerPuzzles[killerPuzzle].get(j) != '/'){
                         color.append(killerPuzzles[killerPuzzle].get(j));
                       j++;
                 }
                 j = j + 1;
                 while(killerPuzzles[killerPuzzle].get(j) != '-') {
                         int boxI = Character.getNumericValue(killerPuzzles[killerPuzzle].get(j));
                         int boxJ = Character.getNumericValue(killerPuzzles[killerPuzzle].get(j + 1));
                         Box box = new Box(boxI, boxJ, 0);
                         aColorArea.addBox(box);
                         j = j + 2;
                 }
                 int s = Character.getNumericValue(killerPuzzles[killerPuzzle].get(j + 1)) * 10 + Character.getNumericValue(killerPuzzles[killerPuzzle].get(j + 2));
                         aColorArea.setColorAreaSum(s);
                         j = j + 3;

                     sColor = color.toString();
                     aColorArea.setColorAreaColor(sColor);
                 this.ColorAreas.add(aColorArea);
        }







    }



    public int checkColorArea(int row, int col){
        int colorAreaPos = 0;
        for(int i = 0 ; i < ColorAreas.size(); i ++){
            ColorArea colorArea = ColorAreas.get(i);
            for(int j = 0; j < colorArea.getColorAreaSize(); j++ ){
                if(colorArea.getColorAreaBox(j).getBoxCoordinateI() == row && colorArea.getColorAreaBox(j).getBoxCoordinateJ() == col){
                    colorAreaPos = i;

                }
            }
        }
        return  colorAreaPos;

    }

    public boolean checkIfTheSumIsValid(int colorAreaPos){
        if(ColorAreas.get(colorAreaPos).checkIfColorAreaIsFull()){
            return ColorAreas.get(colorAreaPos).checkSumOfBoxes();
            //In the Graphics class create a method that turns all the boxes of the color area into red color if
            //the value of the ColorAreas.get(colorAreaPos).checkSumOfBoxes() is false.
        }
        return false;
    }



    /**
     * This method sets the value of the box (cell)
     * @param row
     * @param col
     * @param value
     */
    public void setBoxNumber(int row, int col, int value){
        for(int i = 0 ; i < ColorAreas.size(); i ++){

            for(int j = 0; j < ColorAreas.get(i).getColorAreaSize(); j++ ){
                if(ColorAreas.get(i).getColorAreaBox(j).getBoxCoordinateI() == row && ColorAreas.get(i).getColorAreaBox(j).getBoxCoordinateJ() == col){
                    ColorAreas.get(i).setColorAreaBoxValue(j, value, ColorAreas.get(i).getColorAreaBox(j));
                }
            }
        }
    }

    public int getKillerPuzzle() {
        return killerPuzzle;
    }

    public boolean checkWinner(){
        int s = 0;
       for(ColorArea c : ColorAreas){
            if(c.checkSumOfBoxes())
                s++;
        }
        if(s == getColorAreasSize())
            return true;

        return false;
    }

    private void excludeKillerPlayedPuzzles(){
        for(int i = 0; i < availableKillerPuzzles.size(); i++){
            if(user.checkIfExistsInKillerPuzzlesPlayed(availableKillerPuzzles.get(i))){
                availableKillerPuzzles.set(i, availableKillerPuzzles.get(i - 1));
            }
        }
    }

}
