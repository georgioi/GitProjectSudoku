/**
 * This SudokuLogic implements the logic laying behind sudoku game
 * @author Velisarios Fafoutis
 */
public class SudokuLogic {



    protected  int[][] board;
    private int sizeOfBoard;
    private double rootOfSizeOfBoard;
    public User user;


    /**
     * Empty Constructor
     */
    public SudokuLogic(int sizeOfBoard){
        this.sizeOfBoard = sizeOfBoard;
        board = new int[sizeOfBoard][sizeOfBoard];
        rootOfSizeOfBoard = Math.sqrt(sizeOfBoard);
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                this.board[i][j] = 0;

            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int i, int j, int value){
        board[i][j] = value;
    }


    /**
     * This method checks if a number can be placed at the specific row of the board
     * @param row  the row to check if the number can be placed
     * @param number the number to be checked if can be placed
     * @return true if the number can be placed at the specific row
     */
    public boolean isInRow(int row, int number){
    for(int i = 0; i < sizeOfBoard; i++){
        if(board[row][i] == number)
            return true;

    }
    return false;
   }

    /**
     * This This method checks if a number can be placed at the specific column of the board
     * @param col  the column to check if the number can be placed
     * @param number the number to be checked if can be placed
     * @return true if the number can be placed at the specific column
     */
   public boolean isInCol(int col, int number){
       for(int i = 0; i < sizeOfBoard; i++){
           if(board[i][col] == number){
               return true;
           }
       }

       return false;
   }

    /**
     * This method checks if the number can be placed at the specific 3x3 area of the board basd on the row and column
     * @param row the row to check if the number can be placed
     * @param col the column to check if the number can be placed
     * @param number the number to be checked if can be placed at the 3x3 area
     * @return true if the number can be placed there
     */
    public boolean isInBox(int row, int col, int number){

        int  r = row - row % (int)rootOfSizeOfBoard;
        int c = col - col % (int)rootOfSizeOfBoard;

       for(int i = r; i <  r + (int)rootOfSizeOfBoard; i++){
           for(int j = c; j < c + (int)rootOfSizeOfBoard; j++){
               if(board[i][j] == number){
                   return true;
               }
           }
       }

       return false;
   }

    /**
     * This method checks if the number obeys in the above constraints which are basically the rules of classic sudoku
     * @param row
     * @param col
     * @param number
     * @return true if the number can be placed at the specific cell based on the rules
     */
   public boolean isOk(int row, int col, int number){
       return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row , col, number);
   }

    public void setNewNumbers(int row, int col, int number){
        if(number == 0){
            board[row][col] = 0;
        }
        else{
            board[row][col] = number;
        }

    }

    public void setKillerNewNumbers(int row, int col, String number){
       if(number == ""){
           board[row][col] = 0;
       }else{
           board[row][col] = Integer.parseInt(number);
       }
    }



}
