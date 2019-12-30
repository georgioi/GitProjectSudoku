import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class DuidokuGamePanel extends Board{
    private Duidoku duidoku;
    private DocumentListener[][] boxListeners;
    private boolean isOK, user_last_played;
    private int number, asciiCode;
    private JButton duidokuGamePanelGoBackButton, duidokuHintButton, duidokuNewGameButton;
    private JPopupMenu duidokuHintMenu;
    private JMenuItem duidokuAcceptableNumbers;
    private JLabel duidokuTitle, blackBoxExplain1, blackBoxExplain2, winLabel, defeatLabel;
    private Font duidokuTitleFont, winOrDefeatFont;



    public DuidokuGamePanel(User aUser, char upperLimit, char lowerLimit, int anAsciiCode){
        super(4, upperLimit, lowerLimit, 153,100, 80);
        this.asciiCode = anAsciiCode;
        setUpDuidokuGamePanel(aUser);
    }

    public void setUpDuidokuGamePanel(User aUser){
        duidoku = new Duidoku();
        duidoku.user = aUser;
        duidokuGamePanelGoBackButton = new JButton("Back");
        duidokuHintButton = new JButton("Hint");
        duidokuNewGameButton = new JButton("New Game");
        duidokuTitle = new JLabel("Duidoku");
        duidokuTitleFont = new Font("Arial", Font.BOLD, 20);
        duidokuTitle.setFont(duidokuTitleFont);
        blackBoxExplain1 = new JLabel("Black Box: ");
        blackBoxExplain2 = new JLabel("no number can be entered");
        winLabel = new JLabel("You Won!");
        defeatLabel = new JLabel("You Lost");
        winOrDefeatFont = new Font("Arial", Font.BOLD, 30);
        boxListeners = new DocumentListener[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){

                add(getBoxes()[i][j]);

            }
        }
        duidokuTitle.setBounds(271, 60, 500, 40);
        duidokuTitle.setVisible(true);
        add(duidokuTitle);

        duidokuNewGameButton.setBounds(135, 585, 140, 20);
        duidokuNewGameButton.setBackground(Color.WHITE);
        duidokuNewGameButton.setVisible(true);
        add(duidokuNewGameButton);

        duidokuGamePanelGoBackButton.setBounds(61, 585, 70, 20);
        duidokuGamePanelGoBackButton.setBackground(Color.WHITE);
        duidokuGamePanelGoBackButton.setVisible(true);
        add(duidokuGamePanelGoBackButton);
        setBackground(Color.decode("#FFB266"));



        duidokuHintButton.setBounds(500, 585, 70, 20);
        duidokuHintButton.setBackground(Color.WHITE);
        duidokuHintButton.setVisible(true);
        add(duidokuHintButton);

        winLabel.setBounds(247, 450,  200, 40);
        winLabel.setVisible(false);
        winLabel.setFont(winOrDefeatFont);
        add(winLabel);

        defeatLabel.setBounds(247, 450,  200, 40);
        defeatLabel.setVisible(false);
        defeatLabel.setFont(winOrDefeatFont);
        add(defeatLabel);

        blackBoxExplain1.setBounds(150, 430, 70, 20);
        blackBoxExplain1.setVisible(true);
        blackBoxExplain1.setForeground(Color.BLACK);
        blackBoxExplain2.setBounds(225, 430, 160, 20);
        blackBoxExplain2.setVisible(true);

        add(blackBoxExplain1);
        add(blackBoxExplain2);

        setBoxListeners();
        setVisible(true);
        setLayout(null);
    }


    public void setBoxListeners() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final int row = i, col = j;
                getBoxes()[i][j].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        duidokuHintMenu = new JPopupMenu();
                        for(int value = 1; value < 5; value ++){
                            if(duidoku.isOk(row, col, value)){
                                duidokuAcceptableNumbers = new JMenuItem(String.valueOf((char)(value+asciiCode)));
                                duidokuHintMenu.add(duidokuAcceptableNumbers);
                            }
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                       duidokuHintButton.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               duidokuHintMenu.show(duidokuHintButton, duidokuHintButton.getX(), duidokuHintButton.getY()
                               + duidokuHintButton.getHeight());
                           }
                       });
                    }
                });
                boxListeners[i][j] = new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        number = (int)boxes[row][col].getText().charAt(0)-asciiCode;
                        isOK = duidoku.isOk(row, col, number);
                        if (isOK) {
                            duidoku.setDuidokuTablePos(row, col, number);
                            duidoku.setArray(row, col, true);
                            setEditableFalse(row, col);
                            setBoxBlack();
                            user_last_played = true;
                            if (duidoku.com_turn()) {
                                int comRow = duidoku.getComRow();
                                int comCol = duidoku.getComCol();
                                int comNumber = duidoku.getComValue();
                                setComputerValue(comRow, comCol, String.valueOf((char)(comNumber+asciiCode)));
                              //  SwingUtilities.invokeLater(() -> boxes[comRow][comCol].setText(String.valueOf(comNumber)));
                                user_last_played = false;
                                setBoxBlack();
                                setEditableFalse(comRow, comCol);
                            }
                            if(duidoku.getFilledPosOfArray() == 16){
                                if(user_last_played){
                                    winLabel.setVisible(true);
                                    duidoku.user.addWins();
                                    duidoku.user.insertStatisticsInTheFile();
                                }else{
                                    defeatLabel.setVisible(true);
                                    duidoku.user.addLosses();
                                    duidoku.user.insertStatisticsInTheFile();
                                }
                            }


                        } else{
                            SwingUtilities.invokeLater(() -> boxes[row][col].setText(""));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                };
                boxes[i][j].getDocument().addDocumentListener(boxListeners[i][j]);
            }
        }
    }


    public void setBoxBlack() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (duidoku.getBlackColor()[i][j]) {
                    boxes[i][j].setBackground(Color.DARK_GRAY);
                    boxes[i][j].setEditable(false);
                }
            }
        }

    }

    public void setEditableFalse(int row, int col) {
        boxes[row][col].setEditable(false);
        boxes[row][col].setBackground(Color.white);
    }

    public void setComputerValue(int row, int col, String number) {
        boxes[row][col].getDocument().removeDocumentListener(boxListeners[row][col]);
        boxes[row][col].setText(number);
    }

    public JButton getDuidokuGamePanelGoBackButton(){
        return duidokuGamePanelGoBackButton;
    }

    public JButton getDuidokuNewGameButton() {
        return duidokuNewGameButton;
    }
}
