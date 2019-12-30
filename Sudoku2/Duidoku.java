/**
 * This class implements one of the sudoku versions called duidoku
 *
 */

import java.util.Random;

public class Duidoku extends SudokuLogic {

    private boolean[][] array;//if a pos is false it means that the same pos at the duidokuTable is empty
    private boolean[][] blackColor;//if a pos is true it means that no number can be entered in the same pos at the
    //duidokuTable
    private  int comRow, comCol, comValue;


    public Duidoku() {
        super(4);
        this.array = new boolean[4][4];
        this.blackColor = new boolean[4][4];
    }

    public int[][] getDuiBoard() {
        return this.board;
    }


    public void setDuidokuTablePos(int row, int col, int value) {
        this.board[row][col] = value;
        this.array[row][col] = true;
        this.checkAllCells();
    }


    public boolean com_turn() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!this.array[i][j]) {
                    for (int k = 1; k < 5; k++) {
                        if (checkIfValCanBeEntered(i, j, k)) {
                            this.setDuidokuTablePos(i, j, k);
                            comCol = j;
                            comValue = k;
                            comRow = i;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public int getComRow() {
        return comRow;
    }
    public int getComCol() {
        return comCol;
    }
    public int getComValue() {
        return comValue;
    }
    public boolean checkIfValCanBeEntered(int row, int col, int value){
        if(isOk(row, col, value))
            return true;
        return false;
    }


    public boolean checkIfAcceptable(int row, int col){
        for(int i = 1; i < 5; i++){
            if(isOk(row, col, i)){
                return true;
            }
        }
        return false;
    }

    public void checkAllCells(){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(!this.array[i][j]) {
                    if(!checkIfAcceptable(i,j)){
                        this.blackColor[i][j] = true;
                        this.array[i][j] = true;
                    }
                }

            }
        }

    }

    public boolean[][] getBlackColor(){
        return blackColor;
    }

    public void setArray(int row, int col, boolean value){
        this.array[row][col] = value;
    }

    public int getFilledPosOfArray(){
        int s = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.array[i][j])
                    s++;
            }
        }
        return s;
    }
}
