import java.util.ArrayList;

public class ColorArea {
    private ArrayList<Box> ColorAreaBoxes;
    private int sum ;
    private String color;

    public ColorArea(){
        ColorAreaBoxes = new ArrayList<>();
    }

    public void addBox(Box box){

        ColorAreaBoxes.add(box);
    }

    public ArrayList<Box> getColorAreaBoxes() {
        return ColorAreaBoxes;
    }

    public Box getColorAreaBox(int boxPos){
        return ColorAreaBoxes.get(boxPos);
    }

    public void setColorAreas(ArrayList<Box> colorAreas) {
        ColorAreaBoxes = colorAreas;
    }

    public void setColorAreaBoxValue(int boxPosition, int value, Box box){
        box.setBoxValue(value);
        ColorAreaBoxes.set(boxPosition, box);
    }

    public void setColorAreaBoxNotEmptyVariable(int boxPosition, boolean k, Box box){
        box.setNotEmpty(k);
        ColorAreaBoxes.set(boxPosition, box);
    }


    public int getColorAreaSize(){
        return ColorAreaBoxes.size();
    }


    public void setColorAreaSum(int sum) {
        this.sum = sum;
    }

    public int getColorAreaSum(){ return this.sum;}

    public int getSumOfBoxes(){
        int sumOfBoxes = 0;
        for(int i = 0; i < ColorAreaBoxes.size(); i++){
            if(ColorAreaBoxes.get(i).getBoxValue() > 0) {
                sumOfBoxes = sumOfBoxes + ColorAreaBoxes.get(i).getBoxValue();
            }
        }

        return sumOfBoxes;
    }

    /**
     * This method checks of the sum of the values of the boxes is equal to the sum that the colorArea should have
     * If the sum is equal to the this.sum then it returns true, if not it returns false.
     * @return true
     * @return false
     */
    public boolean checkSumOfBoxes(){
        if(this.getSumOfBoxes() == this.sum)
            return true;
        return false;
    }


    public boolean checkIfColorAreaIsFull(){
        boolean full = true;
        for(int i = 0; i < ColorAreaBoxes.size(); i++){
            if(!ColorAreaBoxes.get(i).getNotEmpty()){
                full = false;
                break;
            }
        }
        return full;

    }

    public String getColorAreaColor(){
        return color;
    }

    public void setColorAreaColor(String aColor){
        this.color = aColor;
    }





}
