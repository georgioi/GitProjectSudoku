import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class Board extends  JPanel{

    private Font font;
    protected JTextField[][] boxes;



    public Board(int size, char upperLimit, char lowerLimit, int xAxisShow, int yAxisShow, int boxesWidthHeight) {

        font = new Font("SansSerif", Font.PLAIN, 20);
        boxes = new JTextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[] borders = new int[]{1, 1, 1, 1};
                boxes[i][j] = new JTextField();
                boxes[i][j].setDocument(new TextFieldLimit(1, upperLimit, lowerLimit));
                boxes[i][j].setBounds((xAxisShow + j * boxesWidthHeight), (yAxisShow + i * boxesWidthHeight), boxesWidthHeight, boxesWidthHeight);

                if (i % Math.sqrt(size) == Math.sqrt(size) - 1) {
                    borders[2] = 5;
                }
                if (j % Math.sqrt(size) == Math.sqrt(size) - 1) {
                    borders[3] = 5;
                }
                if (i == 0) {
                    borders[0] = 5;
                }
                if (j == 0) {
                    borders[1] = 5;
                }
                boxes[i][j].setBorder(BorderFactory.createMatteBorder(borders[0], borders[1], borders[2], borders[3], Color.black));
                boxes[i][j].setHorizontalAlignment(JTextField.CENTER);
                boxes[i][j].setFont(font);
            }
        }


    }

    public JTextField[][] getBoxes(){
        return boxes;
    }

    class TextFieldLimit extends PlainDocument {
        private int limit;
        private char upperLimit;
        private char lowerLimit;
        TextFieldLimit(int limit, char upperLimit, char lowerLimit) {
            super();
            this.upperLimit = upperLimit;
            this.limit = limit;
            this.lowerLimit = lowerLimit;
        }
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= limit && str.charAt(0)>=this.lowerLimit && str.charAt(0)<=this.upperLimit) {
                super.insertString(offset, str, attr);

            }
        }
    }

}