import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClassicGamePanel extends Board {

    private static ClassicSudoku classicSudoku;
    private Initialize initialize;
    private  JMenuItem classicAcceptableNumbers;
    private JPopupMenu classicHintMenu;
    private JButton classicHintButton, classicGamePanelGoBackButton;

    //private String sNumber;
    private int sNumber, asciiCode;
    private boolean isOk ;
    private JLabel classicTitle,redColorExplain1, redColorExplain2, blueColorExplain1, blueColorExplain2;
    private Font classicTitleFont;



    public ClassicGamePanel(User aUser, char upperLimit, char lowerLimit, int anAsciiCode){
        super(9, upperLimit, lowerLimit, 70, 40, 55);
        this.asciiCode = anAsciiCode;
        setUpClassicGamePanel(aUser);

    }



    public void setUpClassicGamePanel(User aUser) {
        classicTitle = new JLabel("Classic Sudoku");
        classicTitleFont = new Font("Arial", Font.BOLD, 20);
        initialize = new Initialize(true, false);
        classicSudoku = new ClassicSudoku();
        classicSudoku.user = aUser;
        initTheClassicGamePanelValues();
        classicHintButton = new JButton("Hint");
        classicGamePanelGoBackButton = new JButton("back");
        redColorExplain1 = new JLabel("Red Color :");
        redColorExplain2 = new JLabel("Wrong number position");
        blueColorExplain1 = new JLabel("Blue Color :");
        blueColorExplain2 = new JLabel("Right number position");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (classicSudoku.getBoard()[i][j] != 0) {
                   // boxes[i][j].setText(String.valueOf(classicSudoku.getBoard()[i][j]));
                    boxes[i][j].setText(String.valueOf((char)(classicSudoku.getBoard()[i][j]+asciiCode)));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setForeground(Color.BLACK);
                    boxes[i][j].setBackground(Color.decode("#C0C0C0"));
                }
            }
        }

           for(int i = 0; i < 9; i++){
             for(int j = 0; j < 9; j++){
                add(getBoxes()[i][j]);
            }



            setBoxListeners();
            classicTitle.setFont(classicTitleFont);
            classicTitle.setBounds(240, 10, 500, 40);
            classicTitle.setVisible(true);
            add(classicTitle);
            classicGamePanelGoBackButton.setBounds(61, 585, 70, 20);
            classicGamePanelGoBackButton.setBackground(Color.WHITE);
            classicGamePanelGoBackButton.setVisible(true);
            add(classicGamePanelGoBackButton);

            classicHintButton.setBackground(Color.WHITE);
            classicHintButton.setVisible(true);
            classicHintButton.setBounds(500, 585, 70, 20);
            add(classicHintButton);

            redColorExplain1.setBounds(77, 538, 70, 20);
            redColorExplain1.setVisible(true);
            redColorExplain1.setForeground(Color.RED);
            redColorExplain2.setBounds(153, 538, 150, 20);
            redColorExplain2.setVisible(true);
            blueColorExplain1.setBounds(77, 555, 70, 20);
            blueColorExplain1.setVisible(true);
            blueColorExplain1.setForeground(Color.BLUE);
            blueColorExplain2.setBounds(152, 555, 150, 20);

            add(redColorExplain1);
            add(redColorExplain2);
            add(blueColorExplain1);
            add(blueColorExplain2);

            setBackground(Color.decode("#66FF66"));
            setVisible(true);
            setLayout(null);
        }
    }


    public void setBoxListeners(){

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final int row = i, col = j;
                    boxes[i][j].addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            classicHintMenu = new JPopupMenu();
                            for(int value = 1; value < 10; value ++){
                                if(classicSudoku.isOk(row, col, value)){
                                    classicAcceptableNumbers = new JMenuItem(String.valueOf((char)(value+asciiCode)));
                                    final int v = value;
                                   /* classicAcceptableNumbers.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            classicSudoku.setNewNumbers(row,col,String.valueOf(v));
                                            boxes[row][col].setText(String.valueOf(v));
                                            boxes[row][col].setForeground(Color.BLUE);

                                        }
                                    });*/
                                    classicHintMenu.add(classicAcceptableNumbers);
                                }
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            classicHintButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    classicHintMenu.show(classicHintButton,72,  -50);
                                }
                            });


                        }
                    });


                    boxes[i][j].getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                           // sNumber = boxes[row][col].getText();
                            sNumber = (int)boxes[row][col].getText().charAt(0)-asciiCode;
                            isOk = classicSudoku.isOk(row, col, sNumber);
                            if (!isOk){
                                sNumber = sNumber*(-1);
                            }
                            setNumberColor(isOk, row, col);
                            classicSudoku.setNewNumbers(row,col,sNumber);
                            if(classicSudoku.checkWinner()){
                                for(int i = 0; i < 9; i++){
                                    for(int j = 0; j < 9; j++){
                                        boxes[i][j].setEditable(false);

                                    }
                                }
                                classicSudoku.user.addValueToClassicPuzzlesPlayed(classicSudoku.getClassicPuzzle());
                                classicSudoku.user.insertStatisticsInTheFile();
                            }

                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            sNumber = 0;
                            classicSudoku.setNewNumbers(row,col,sNumber);
                            resetNumbersColor();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {

                        }
                    });
                }
            }
        }


    public void initTheClassicGamePanelValues(){
        classicSudoku = initialize.getClassic();
        classicSudoku.chooseRandomClassicPuzzle();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                classicSudoku.setBoard(i, j, initialize.getClassic().getBoard()[i][j]);
            }
        }
    }


    public void resetNumbersColor() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(classicSudoku.isOk(i, j, classicSudoku.getBoard()[i][j]*(-1)) && classicSudoku.getBoard()[i][j] < 0){
                    boxes[i][j].setForeground(Color.BLUE);
                    classicSudoku.setBoard(i, j, classicSudoku.getBoard()[i][j]*(-1));
                } }
        }

    }

    public void setNumberColor(boolean isOK, int row, int col) {
        if (isOK) {
            boxes[row][col].setForeground(Color.BLUE);
        } else {
            boxes[row][col].setForeground(Color.RED);
        }
    }

    public JButton getClassicGamePanelGoBackButton(){
        return classicGamePanelGoBackButton;
    }


}
