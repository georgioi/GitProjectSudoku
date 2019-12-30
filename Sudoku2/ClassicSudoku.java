import java.util.ArrayList;
import java.util.Random;

public class ClassicSudoku extends SudokuLogic {


    private ArrayList<Character>[] classicPuzzles = new ArrayList[10];
    private ArrayList<Integer> availableClassicPuzzles;
    private int classicPuzzle;


    public ClassicSudoku() {

        super(9);
        availableClassicPuzzles = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            classicPuzzles[i] = new ArrayList<>();
        }

        for(int i = 1; i <= 10; i++){
            availableClassicPuzzles.add(i);
        }

       // excludeClassicPlayedPuzzles();


    }

    public void chooseRandomClassicPuzzle(){
       // excludeClassicPlayedPuzzles();
        Random r = new Random();
        int kC = r.nextInt(availableClassicPuzzles.size());
        int classicPuzzle = availableClassicPuzzles.get(kC) - 1;


        int row = 0;
        int col = 0;
         for(int i = 0; i < 9; i++) {
             int j = 0;
             for(j = 0; j < 9; j++){
                 if(classicPuzzles[classicPuzzle].get(col) >= '1' && classicPuzzles[classicPuzzle].get(col) <= '9' ){
                     int a= Character.getNumericValue(classicPuzzles[classicPuzzle].get(col));
                     this.setBoard(i, j, a);
                 }else if(classicPuzzles[classicPuzzle].get(col) >= '-'){
                     this.setBoard(i, j, 0);
                 }
                 col++;
             }
             row++;
         }

    }


    public ArrayList<Character> getClassicPuzzle(int i){
        return this.classicPuzzles[i];
    }

   public void addCharacterIntoClassicPuzzle(int i,  char k){
       classicPuzzles[i].add(k);
    }


    public boolean checkWinner(){
        int s = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(getBoard()[i][j] > 0){
                    s++;
                }
            }
        }
        if(s == 81){
            return true;
        }

        return false;
    }

    public boolean checkRightNumber(int row, int col){
        if(isOk(row, col, board[row][col])){
            return true;
        }
        return false;
    }

    public void resetBoardValues(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.setBoard(i, j, 0);
            }
        }
    }

    public int getClassicPuzzle() {
        return classicPuzzle;
    }

    private void excludeClassicPlayedPuzzles(){
        for(int i = 0; i < availableClassicPuzzles.size(); i++){
            if(user.checkIfExistsInClassicPuzzlesPlayed(availableClassicPuzzles.get(i))){
                availableClassicPuzzles.set(i , availableClassicPuzzles.get(i));
            }
        }
    }
}
