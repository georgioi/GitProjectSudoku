import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private User user;
    private JLabel classicPuzzlesSolvedTitle, killerPuzzlesSolvedTitle, duidokuWinsTitle, duidokuLossesTitle;
    private JLabel classicPuzzlesSolvedData, killerPuzzlesSolvedData,  duidokuWinsData, duidokuLossesData;
    private Font aFont;
    private JButton statsPanelGoBackButton;

    public StatsPanel(User aUser){
        this.user = aUser;
        setUpStatsPanel();
    }


    public void setUpStatsPanel(){
        statsPanelGoBackButton = new JButton("Back");
        aFont = new Font("Arial", Font.PLAIN, 20);
        classicPuzzlesSolvedTitle = new JLabel("Classic Sudoku Puzzles Solved");
        killerPuzzlesSolvedTitle = new JLabel("Killer Sudoku Puzzles Solved");
        duidokuWinsTitle = new JLabel("Duidoku Wins: ");
        duidokuLossesTitle = new JLabel("Duidoku Losses: ");
        classicPuzzlesSolvedData = new JLabel(user.getClassicPPlayed());
        killerPuzzlesSolvedData = new JLabel(user.getKillerPPlayed());
        duidokuWinsData = new JLabel(String.valueOf(user.getDuidokuWins()));
        duidokuLossesData = new JLabel(String.valueOf(user.getDuidokuLosses()));

        classicPuzzlesSolvedTitle.setFont(aFont);
        classicPuzzlesSolvedTitle.setBounds(100, 100, 500, 40);
        classicPuzzlesSolvedTitle.setVisible(true);
        add(classicPuzzlesSolvedTitle);

        classicPuzzlesSolvedData.setBounds(100, 125, 200, 40);
        classicPuzzlesSolvedData.setVisible(true);
        add(classicPuzzlesSolvedData);

        killerPuzzlesSolvedTitle.setFont(aFont);
        killerPuzzlesSolvedTitle.setBounds(100, 160, 500, 40);
        killerPuzzlesSolvedTitle.setVisible(true);
        add(killerPuzzlesSolvedTitle);

        killerPuzzlesSolvedData.setBounds(100, 185, 200, 40);
        killerPuzzlesSolvedData.setVisible(true);
        add(killerPuzzlesSolvedData);


        duidokuWinsTitle.setBounds(100, 230, 150 , 40);
        duidokuWinsTitle.setVisible(true);
        add(duidokuWinsTitle);

        duidokuWinsData.setBounds(190, 230, 70, 40);
        duidokuWinsData.setVisible(true);
        add(duidokuWinsData);


        duidokuLossesTitle.setBounds(100, 273, 150, 40);
        duidokuLossesTitle.setVisible(true);
        add(duidokuLossesTitle);

        duidokuLossesData.setBounds(199, 273, 70, 40);
        duidokuLossesData.setVisible(true);
        add(duidokuLossesData);

        statsPanelGoBackButton.setBounds(61, 585, 70, 20);
        statsPanelGoBackButton.setBackground(Color.WHITE);
        statsPanelGoBackButton.setVisible(true);

        add(statsPanelGoBackButton);



        setBackground(Color.CYAN);
        setLayout(null);
        setVisible(true);
    }

    public JButton getStatsPanelGoBackButton(){
        return statsPanelGoBackButton;
    }



}
