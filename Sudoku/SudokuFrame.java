import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;


public class SudokuFrame {

    private User user;
    private JFrame sudokuFrame;
    private  MenuPanel menuPanel;
    private StatsPanel statsPanel;
    private SelectUserPanel selectUserPanel;
    private  PlayButtonPanel playButtonPanel;
    private  KillerGamePanel killerGamePanel;
    private  ClassicGamePanel classicGamePanel;
    private  DuidokuGamePanel duidokuGamePanel;
    private boolean userNameHasBeenInserted;
    private Initialize initialize;
    private boolean isClassic;
    private boolean isKiller;
    private final int sudoku_frame_size = 650;
    private boolean duidoku;




    public SudokuFrame() {
        sudokuFrame = new JFrame();
        selectUserPanel = new SelectUserPanel();
        setUpSudokuFrame();
        selectUserPanelButtonPressedActions();
        user = selectUserPanel.getUser();
    }

    public void setUpSudokuFrame() {
        sudokuFrame.add(selectUserPanel);
        sudokuFrame.setLocation(300, 80); //I must change the location to null
        sudokuFrame.setSize(sudoku_frame_size, sudoku_frame_size);
        sudokuFrame.setVisible(true);
        sudokuFrame.setResizable(false);
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void selectUserPanelButtonPressedActions(){
        selectUserPanel.getSelectUserButton().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                selectUserPanel.getSelectUserPopUpMenu().show(selectUserPanel.getSelectUserButton(), 0, 50 );
            }

            @Override
            public void focusLost(FocusEvent e) {
                menuPanel = new MenuPanel();
                menuPanelButtonPressedActions();
                selectUserPanel.panelChange(sudokuFrame, menuPanel, selectUserPanel);
            }
        });



        selectUserPanel.getCreateUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    userNameHasBeenInserted = true;
                    selectUserPanel.setVisible(false);
                    sudokuFrame.remove(selectUserPanel);
                    menuPanel = new MenuPanel();
                    menuPanelButtonPressedActions();
                    sudokuFrame.add(menuPanel);
                    menuPanel.setVisible(true);
                    sudokuFrame.revalidate();

            }
        });


    }

    public void menuPanelButtonPressedActions() {
        menuPanel.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    menuPanel.setVisible(false);
                    sudokuFrame.remove(menuPanel);
                    playButtonPanel = new PlayButtonPanel();
                    playButtonPanelButtonPressedActions();
                    sudokuFrame.add(playButtonPanel);
                    playButtonPanel.setVisible(true);
                    sudokuFrame.revalidate();

            }
        });

        menuPanel.getStatsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              menuPanel.setVisible(false);
              sudokuFrame.remove(menuPanel);
              user = selectUserPanel.getUser();
              statsPanel = new StatsPanel(user);
              statsPanelPressedactions();
              sudokuFrame.add(statsPanel);
              statsPanel.setVisible(true);
              sudokuFrame.revalidate();

            }
        });

        menuPanel.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   //Exit the game
                    sudokuFrame.dispose();
                    sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        menuPanel.getMenuPanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                sudokuFrame.remove(menuPanel);
                selectUserPanel = new SelectUserPanel();
                selectUserPanelButtonPressedActions();
                sudokuFrame.add(selectUserPanel);
                selectUserPanel.setVisible(true);
                sudokuFrame.revalidate();
            }
        });


    }

    /**
     * This method acts compared to the button pressed
     * For example:If play button is pressed the sudokuFrame changes its menuPanel to the playButtonPanel
     */
    public void playButtonPanelButtonPressedActions() {
        PlayButtonPanel.classicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayButtonPanel.wordokuOption.show(PlayButtonPanel.classicButton,185,0);
                duidoku = false;
            }
        });



        PlayButtonPanel.killerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show classicBoardPanel
                playButtonPanel.setVisible(false);
                initialize = new Initialize(true, false);
                user = selectUserPanel.getUser();
                killerGamePanel = new KillerGamePanel(selectUserPanel.getUser());
                killerGamePanelButtonPressedActions();
                sudokuFrame.remove(playButtonPanel);
                sudokuFrame.add(killerGamePanel);
                killerGamePanel.setVisible(true);
                sudokuFrame.revalidate();
            }
        });

        PlayButtonPanel.duidokuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayButtonPanel.wordokuOption.show(PlayButtonPanel.duidokuButton,185,0);
                duidoku = true;
            }
        });


        PlayButtonPanel.classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonPanel.setVisible(false);
                sudokuFrame.remove(playButtonPanel);
                sudokuFrame.revalidate();
                if (!duidoku) {
                    user = selectUserPanel.getUser();
                    classicGamePanel = new ClassicGamePanel(user,'9','1',48 );
                    classicGamePanelButtonPressedActions();
                    sudokuFrame.add(classicGamePanel);
                    classicGamePanel.setVisible(true);
                } else {
                    user = selectUserPanel.getUser();
                    duidokuGamePanel = new DuidokuGamePanel(user,'4','1', 48);
                    duidokuGamePanelPressedActions();
                    sudokuFrame.add(duidokuGamePanel);
                    duidokuGamePanel.setVisible(true);
                }
            }
        });

        PlayButtonPanel.wordoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonPanel.setVisible(false);
                sudokuFrame.remove(playButtonPanel);
                sudokuFrame.revalidate();
                if (!duidoku) {
                    user = selectUserPanel.getUser();
                    classicGamePanel = new ClassicGamePanel(user,'I','A',64);
                    classicGamePanelButtonPressedActions();
                    sudokuFrame.add(classicGamePanel);
                    classicGamePanel.setVisible(true);
                } else {
                    user = selectUserPanel.getUser();
                    duidokuGamePanel = new DuidokuGamePanel(user,'D','A',64);
                    duidokuGamePanelPressedActions();
                    sudokuFrame.add(duidokuGamePanel);
                    duidokuGamePanel.setVisible(true);
                }
            }
        });

        PlayButtonPanel.playButtonPanelGoBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    playButtonPanel.setVisible(false);
                    sudokuFrame.remove(playButtonPanel);
                    sudokuFrame.add(menuPanel);
                    menuPanel.setVisible(true);
                    sudokuFrame.validate();
            }
        });
    }




    public void classicGamePanelButtonPressedActions(){
        classicGamePanel.getClassicGamePanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classicGamePanel.setVisible(false);
                sudokuFrame.remove(classicGamePanel);
                sudokuFrame.add(playButtonPanel);
                playButtonPanel.setVisible(true);
                sudokuFrame.validate();
            }
        });
    }

    public void killerGamePanelButtonPressedActions(){
        killerGamePanel.getKillerGamePanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                killerGamePanel.setVisible(false);
                sudokuFrame.remove(killerGamePanel);
                sudokuFrame.add(playButtonPanel);
                playButtonPanel.setVisible(true);
                sudokuFrame.validate();
            }
        });
    }

    public void duidokuGamePanelPressedActions(){
        duidokuGamePanel.getDuidokuGamePanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                duidokuGamePanel.setVisible(false);
                sudokuFrame.remove(duidokuGamePanel);
                sudokuFrame.add(playButtonPanel);
                playButtonPanel.setVisible(true);
                sudokuFrame.validate();
            }
        });
    }

    public void statsPanelPressedactions(){
       statsPanel.getStatsPanelGoBackButton().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               statsPanel.setVisible(false);
               sudokuFrame.remove(statsPanel);
               sudokuFrame.add(menuPanel);
               menuPanel.setVisible(true);
               sudokuFrame.validate();
           }
       });
    }

    public void removePanels(){
        sudokuFrame.remove(menuPanel);
        menuPanel.setVisible(false);
        sudokuFrame.remove(playButtonPanel);
        playButtonPanel.setVisible(false);
        sudokuFrame.remove(classicGamePanel);
        classicGamePanel.setVisible(false);
    }

    public boolean getIfClassic() {
        return isClassic;
    }

    public boolean getIfKiller() {
        return isKiller;
    }




    public static void main(String[] args){
        SudokuFrame frame = new SudokuFrame();

    }
}