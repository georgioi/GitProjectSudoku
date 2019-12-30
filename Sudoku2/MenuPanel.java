import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class MenuPanel extends JPanel{

    private JButton playButton, statsButton, exitButton , menuPanelGoBackButton;


    private JLabel menuTitle;
    private Font  menuFont;
    private final int buttonWidth = 180;
    private final int buttonHeight = 35;
    private final int buttonXaxisPos = 220;
    public static int menuButtonPressed;
    public static int playPanelbuttonPressed;




    public MenuPanel(){


        playButton = new JButton("Play");
        statsButton = new JButton("Stats");
        exitButton = new JButton("Exit");
        menuPanelGoBackButton = new JButton("Back");


        menuTitle = new JLabel();
        setUpMenuPanel();
    }

    public void setUpMenuPanel(){

        //Setting up the background color
        this.setBackground(Color.CYAN);

        //setting up the title name and letter type etc.
        menuTitle.setText("Welcome to Sudoku");
        menuFont = new Font("Arial", Font.BOLD, 35);
        menuTitle.setFont(menuFont);
        menuTitle.setBounds(150, 25, 500, 40);

        //setting up the position of each button
        playButton.setBounds(buttonXaxisPos, 100, buttonWidth, buttonHeight);
        playButton.setBackground(Color.WHITE);
        statsButton.setBounds(buttonXaxisPos, 100 + buttonHeight + 2, buttonWidth, buttonHeight);
        statsButton.setBackground(Color.WHITE);
        exitButton.setBounds(buttonXaxisPos, 100 + 2*buttonHeight + 4, buttonWidth, buttonHeight);
        exitButton.setBackground(Color.WHITE);

        menuPanelGoBackButton.setBounds(61, 585, 70, 20);
        menuPanelGoBackButton.setBackground(Color.WHITE);
        menuPanelGoBackButton.setVisible(true);
        add(menuPanelGoBackButton);



        //Adding everything to the MenuPanel

        this.add(menuTitle);
        this.add(playButton);
        this.add(statsButton);
        this.add(exitButton);


        //Setting up its layout

        this.setLayout(null);
        this.setVisible(true);
    }



    public void addComponent(Component k){
        this.add(k);
    }

    public static int getButtonPressed(){
        return  MenuPanel.menuButtonPressed;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getStatsButton() {
        return statsButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getMenuPanelGoBackButton() {
        return menuPanelGoBackButton;
    }
}