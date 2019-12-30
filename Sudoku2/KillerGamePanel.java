import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class KillerGamePanel extends Board{
    private Initialize initialize;
    private KillerSudoku killerSudoku;
    private boolean isOK, sumIsOk;
    private JLabel[] sums;
    private JLabel killerTitle, redColorExplain1, redColorExplain2, blackColorExplain1, blackColorExplain2, winLabel;
    private Font killerTitleFont, winLabelFont;
    private  JMenuItem killerAcceptableNumbers;
    private JPopupMenu killerHintMenu;
    private JButton killerHintButton, killerGamePanelGoBackButton, killerNewGameButton;
    private int coordinateI, coordinateJ, number;

    public KillerGamePanel(User aUser) {
        super(9,'9', '1', 70, 40, 55);
        setUpKillerGamePanel(aUser);
    }




    public void setUpKillerGamePanel(User aUser){
        killerSudoku = new KillerSudoku();
        killerSudoku.user = aUser;
        killerTitle = new JLabel("Killer Sudoku");
        killerTitleFont = new Font("Arial", Font.BOLD, 20);
        winLabelFont = new Font("Arial", Font.BOLD, 25);
        killerGamePanelGoBackButton = new JButton("back");
        killerHintButton = new JButton("Hint");
        killerNewGameButton = new JButton("New Game");
        redColorExplain1 = new JLabel("Red Color :");
        redColorExplain2 = new JLabel("Wrong number position");
        blackColorExplain1 = new JLabel("Black Color :");
        blackColorExplain2 = new JLabel("Right number position");
        winLabel = new JLabel("Congratulations, you solved the puzzle!");
        initialize = new Initialize(false, true);
        initTheKillerColorAreas();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                add(getBoxes()[i][j]);
            }
        }


        killerTitle.setFont(killerTitleFont);
        killerTitle.setBounds(253, 10, 500, 40);
        killerTitle.setVisible(true);
        add(killerTitle);

        killerGamePanelGoBackButton.setBounds(61, 585, 70, 20);
        killerGamePanelGoBackButton.setBackground(Color.WHITE);
        killerGamePanelGoBackButton.setVisible(true);
        add(killerGamePanelGoBackButton);

        killerNewGameButton.setBounds(135, 585, 140, 20);
        killerNewGameButton.setBackground(Color.WHITE);
        killerNewGameButton.setVisible(true);
        add(killerNewGameButton);

        winLabel.setBounds(85, 538, 500, 40);
        winLabel.setFont(winLabelFont);
        winLabel.setVisible(false);
        add(winLabel);

        redColorExplain1.setBounds(77, 538, 70, 20);
        redColorExplain1.setVisible(true);
        redColorExplain1.setForeground(Color.RED);
        redColorExplain2.setBounds(153, 538, 150, 20);
        redColorExplain2.setVisible(true);
        blackColorExplain1.setBounds(77, 555, 80, 20);
        blackColorExplain1.setVisible(true);
        blackColorExplain1.setForeground(Color.BLACK);
        blackColorExplain2.setBounds(152, 555, 150, 20);

        add(redColorExplain1);
        add(redColorExplain2);
        add(blackColorExplain1);
        add(blackColorExplain2);
        setBackground(Color.decode("#FFFFFF"));
        setVisible(true);
        setLayout(null);


        killerHintButton.setBounds(500, 585, 70, 20);
        killerHintButton.setBackground(Color.WHITE);
        killerHintButton.setVisible(true);
        add(killerHintButton);

        setBoxListeners();

        // setColorAreasColors();
        // this.setLayout(null);
        // this.setVisible(true);

    }

    public void setBoxListeners() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i, col = j;
                boxes[i][j].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        killerHintMenu = new JPopupMenu();
                        for(int value = 1; value < 10; value ++){
                            if(killerSudoku.isOk(row, col, value)){
                                killerAcceptableNumbers = new JMenuItem(String.valueOf(value));
                                killerHintMenu.add(killerAcceptableNumbers);
                            }
                        }


                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                        killerHintButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                               killerHintMenu.setLocation(50, 60);
                            }
                        });


                    }
                });
                boxes[i][j].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        number = (int)boxes[row][col].getText().charAt(0)-48;
                        isOK = killerSudoku.isOk(row,col,number);
                        if (!isOK){
                            number = number * (-1);
                        }
                        killerSudoku.setKillerColorArea(row, col, number, true);
                        killerSudoku.setNewNumbers(row, col, number);

                        setNumberColor(isOK, row, col);
                        sumIsOk = killerSudoku.checkIfTheSumIsValid(killerSudoku.checkColorArea(row, col));
                        coordinateI = killerSudoku.getColorAreas().get(killerSudoku.checkColorArea(row, col)).getColorAreaBoxes().get(0).getBoxCoordinateI();
                        coordinateJ = killerSudoku.getColorAreas().get(killerSudoku.checkColorArea(row, col)).getColorAreaBoxes().get(0).getBoxCoordinateJ();
                        if(!sumIsOk){
                            getBoxLabel(coordinateI,coordinateJ).setForeground(Color.RED);
                        }else {
                            getBoxLabel(coordinateI, coordinateJ).setForeground(Color.BLACK);
                        }
                        if(killerSudoku.checkWinner()){
                            for(int i = 0; i < 9; i++){
                                for(int j = 0; j < 9; j++){
                                    boxes[i][j].setEditable(false);

                                }
                            }
                            redColorExplain1.setVisible(false);
                            redColorExplain2.setVisible(false);
                            blackColorExplain1.setVisible(false);
                            blackColorExplain2.setVisible(false);
                            winLabel.setVisible(true);
                            killerSudoku.user.addValueToKillerPuzzlesPlayed(killerSudoku.getKillerPuzzle() + 1);
                            killerSudoku.user.insertStatisticsInTheFile();
                        }

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        number = 0;
                        killerSudoku.setNewNumbers(row,col, number);
                        resetNumbersColor();
                        killerSudoku.setKillerColorArea(row, col, 0, false);
                        getBoxLabel(coordinateI,coordinateJ).setForeground(Color.RED);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });


            }
        }
    }


    private void setColorAreaColor(String aColor, ColorArea aColorArea){

        for(int i = 0; i < aColorArea.getColorAreaSize(); i++){
            int coordinateI = aColorArea.getColorAreaBox(i).getBoxCoordinateI();
            int coordinateJ = aColorArea.getColorAreaBox(i).getBoxCoordinateJ();
            boxes[coordinateI][coordinateJ].setBackground(Color.decode(aColor));
        }
    }

    public void setColorAreasColors(){
        for(int i = 0; i < killerSudoku.getColorAreasSize(); i++){
            setColorAreaColor(killerSudoku.getColorAreas().get(i).getColorAreaColor(), killerSudoku.getColorAreas().get(i));
        }
    }

    public void initTheKillerColorAreas(){
        killerSudoku = initialize.getKiller();
        killerSudoku.chooseRandomKillerPuzzle();
        sums = new JLabel[killerSudoku.getColorAreasSize()];
        setColorAreasColors();
        for (int i = 0; i<sums.length;i++) {
            ColorArea colorArea  = killerSudoku.getColorAreas().get(i);
            sums[i] = new JLabel(String.valueOf(colorArea.getColorAreaSum()));
            sums[i].setForeground(Color.RED);

            int x = colorArea.getColorAreaBoxes().get(0).getBoxCoordinateI();
            int y = colorArea.getColorAreaBoxes().get(0).getBoxCoordinateJ();
            boxes[x][y].add(sums[i]);
            boxes[x][y].setLayout(new FlowLayout(FlowLayout.LEFT));

        }
    }



    public void setNumberColor(boolean isOK, int row, int col) {
        if (isOK) {
            boxes[row][col].setForeground(Color.BLACK);
        } else {
            boxes[row][col].setForeground(Color.RED);
        }
    }

    public JLabel getBoxLabel(int row, int col){
        return (JLabel) boxes[row][col].getComponent(0);
    }

    public void resetNumbersColor() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(killerSudoku.isOk(i, j, killerSudoku.getBoard()[i][j]*(-1)) && killerSudoku.getBoard()[i][j] < 0){
                    boxes[i][j].setForeground(Color.BLACK);
                    killerSudoku.setBoard(i, j, killerSudoku.getBoard()[i][j]*(-1));
                    killerSudoku.setKillerColorArea(i, j,killerSudoku.getBoard()[i][j]*(-1) , true);

                }

            }
        }

    }

    public JButton getKillerGamePanelGoBackButton(){
        return killerGamePanelGoBackButton;
    }

    public JButton getKillerNewGameButton() {
        return killerNewGameButton;
    }
}
