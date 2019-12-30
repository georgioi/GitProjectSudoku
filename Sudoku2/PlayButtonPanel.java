import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

public class PlayButtonPanel extends JPanel{
    public static JButton classicButton;
    public static JButton killerButton;
    public static JButton duidokuButton;
    public static JButton playButtonPanelGoBackButton;
    public static JPopupMenu wordokuOption;
    public static JMenuItem wordoku;
    public static JMenuItem classic;
    private JLabel playButtonPanelTitle;
    private Font   font;
    private final int buttonWidth = 180;
    private final int buttonHeight = 35;
    private final int buttonXaxisPos = 220;
    public static int playPanelbuttonPressed;

    public PlayButtonPanel(){
        classicButton = new JButton("Classic");
        killerButton = new JButton("Killer");
        duidokuButton = new JButton("Duidoku");
        playButtonPanelGoBackButton = new JButton("Back");
        wordokuOption = new JPopupMenu();
        classic = new JMenuItem("Classic");
        wordoku = new JMenuItem("Wordoku");
        playButtonPanelTitle = new JLabel();
        font = new Font("Arial", Font.PLAIN, 30);
        setUpPlayButtonPanel();
    }


    public void setUpPlayButtonPanel(){
        //Setting up the background color
        this.setBackground(Color.CYAN);

        //setting up the title name and letter type etc.
        playButtonPanelTitle.setText("Choose type");
        playButtonPanelTitle.setFont(font);
        playButtonPanelTitle.setBounds(225, 40, 500, 40);

        //setting up the position of each button
        classicButton.setBounds(buttonXaxisPos, 100, buttonWidth, buttonHeight);
        classicButton.setBackground(Color.WHITE);
        killerButton.setBounds(buttonXaxisPos, 100 + buttonHeight + 2, buttonWidth, buttonHeight);
        killerButton.setBackground(Color.WHITE);
        duidokuButton.setBounds(buttonXaxisPos, 100 + 2*buttonHeight + 4, buttonWidth, buttonHeight);
        duidokuButton.setBackground(Color.WHITE);
        playButtonPanelGoBackButton.setBounds(61, 585, 70, 20);
        playButtonPanelGoBackButton.setBackground(Color.WHITE);
        classic.setBackground(Color.WHITE);
        wordoku.setBackground(Color.WHITE);
        wordokuOption.add(classic);
        wordokuOption.add(wordoku);


        //Adding everything to the PlayButtonPanel
        this.add(playButtonPanelTitle);
        this.add(classicButton);

        this.add(killerButton);
        this.add(duidokuButton);
        this.add(playButtonPanelGoBackButton);

        //Setting up its layout
        this.setLayout(null);
        this.setVisible(true);
    }

    public static int getButtonPressed(){
        return  PlayButtonPanel.playPanelbuttonPressed;
    }
}