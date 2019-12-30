/**
 * This class implements one the 81 boxes of the sudoku field.
 *
 *
 */
public class Box {
    private  int coordinateI ,coordinateJ;//Each box has its own coordinates i,j; that reprsent its position in the
    private int boxValue;                       //field.Als it
    //private boolean valid;
    private boolean notEmpty;//if there is no value in the box then notEmpty = false

    public Box(){
        //
    }

    public Box(int i, int j, int aValue){
        this.coordinateI = i;
        this.coordinateJ = j;
        this.boxValue = aValue;
        this.notEmpty = false;
    }

    public int getBoxCoordinateI() {

        return coordinateI;
    }

    public int getBoxCoordinateJ() {

        return coordinateJ;
    }

    public int getBoxValue() {
        return boxValue;
    }

    public void setBoxValue(int value){
        this.boxValue = value;
    }



    public boolean getNotEmpty(){
        return this.notEmpty;
    }

    public void setNotEmpty(boolean k){
        this.notEmpty = k;

    }


}
