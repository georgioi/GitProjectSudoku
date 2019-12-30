import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class SelectUserPanel extends JPanel {

    private JLabel createUserLabel, noUserAddedLabel, userAlreadyAddedLabel;
    private JButton selectUserButton, createUserButton;
    private JTextField createUserTextField;
    private JPopupMenu selectUserPopUpMenu;
    private JMenuItem userΝameMenuItem;
    private ArrayList<JMenuItem> userNamesMenuItems;
    private ArrayList<String> userNamesList;
    private ArrayList<User> userObjects;
    private User user, userClicked;
    private String nameClicked = " ", nameInserted = " ";
    private int nameClickedPos;
    private Font font;
    private boolean userNameExists = false;
    private File userNamesFile;



    public SelectUserPanel() {
        selectUserButton = new JButton("Select User");
        createUserButton = new JButton("Create User");
        createUserLabel = new JLabel("Insert new Username");
        noUserAddedLabel = new JLabel("There is no user added, please create a new user!");
        userAlreadyAddedLabel = new JLabel("This user has already been added.");
        font = new Font("Arial", Font.PLAIN, 15);
        createUserTextField = new JTextField();
        userObjects = new ArrayList<>();
        userNamesList = new ArrayList<>();
        setUpSelectUserPanel();


    }

    public void setUpSelectUserPanel(){
        noUserAddedLabel.setFont(font);
        noUserAddedLabel.setBounds(200, 282, 400, 40);
        noUserAddedLabel.setVisible(false);
        add(noUserAddedLabel);

        userAlreadyAddedLabel.setFont(font);
        userAlreadyAddedLabel.setBounds(200,282,400,40);
        userAlreadyAddedLabel.setVisible(false);
        add(userAlreadyAddedLabel);

        createUserButton.setBounds(200,200,200,40);
        createUserButton.setBackground(Color.WHITE);
        add(createUserButton);

        selectUserButton.setBounds(200, 242, 200, 40);
        selectUserButton.setBackground(Color.WHITE);
        add(selectUserButton);

       // createUserLabel.setFont(font);
        //createUserLabel.setLocation(200, 100);
        createUserLabel.setFont(font);
        createUserLabel.setBounds(200,125,200,40);
        createUserLabel.setVisible(true);
        createUserTextField.setBounds(200,158 , 200, 40);
        add(createUserLabel);

        add(createUserTextField);
        initThePopUpMenuNames();
        componentsListeners();
        setBackground(Color.CYAN);
        setVisible(true);
        setLayout(null);
    }

    public void componentsListeners(){
        selectUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userObjects.size() == 0){
                    noUserAddedLabel.setVisible(true);
                }
            }
        });

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noUserAddedLabel.setVisible(false);
                remove(noUserAddedLabel);
                nameInserted = createUserTextField.getText();
                user = new User(nameInserted);
                user.createUserFile();
                user.insertStatisticsInTheFile();
                writeTheNewUserNames(user.getName());
            }
        });
    }

    public void readTheNamesFromTheFile(){
        try (BufferedReader in = new BufferedReader(
                new FileReader("UsersNames.txt"));
        ) {
           /* if (in.readLine() == null) {
                in.close();
            } else {*/
            String l;
            while ((l = in.readLine()) != null) {
                StringBuilder n = new StringBuilder();

                for (int j = 0; j < l.length(); j++) {
                    n.append(l.charAt(j));
                }
                userNamesList.add(n.toString());
            }
        //}
            }catch(IOException e){
                e.printStackTrace();
            }

    }

    public void writeTheNewUserNames(String aName){
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter("UsersNames.txt",true));
        ){
                out.write(aName + "\n");

        }catch(IOException e){
            e.printStackTrace();
        }
    }




    public String getNameClicked(){
        return nameClicked;
    }

    public String getNameInserted(){
        return nameInserted;
    }
    public JButton getSelectUserButton(){
        return selectUserButton;
    }

    public JButton getCreateUserButton(){
        return createUserButton;
    }

    public User getUser() {
        return user;
    }

    public void initThePopUpMenuNames(){
        readTheNamesFromTheFile();
        createUserObjects();
        userNamesMenuItems = new ArrayList<>();
        selectUserPopUpMenu = new JPopupMenu();
        selectUserPopUpMenu.setBackground(Color.WHITE);
        if(userObjects.size() != 0) {
            for (User aUser : userObjects) {
                userΝameMenuItem = new JMenuItem();
                userΝameMenuItem.setText(aUser.getName());
                userΝameMenuItem.setBackground(Color.WHITE);
                userΝameMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(User aUser : userObjects){
                            if(e.getActionCommand().equals(aUser.getName())){
                                user = aUser;
                            }
                        }
                    }
                });
                selectUserPopUpMenu.add(userΝameMenuItem);
                userNamesMenuItems.add(userΝameMenuItem);
            }
        }
    }

    public ArrayList<JMenuItem> getUserNamesMenuItems() {
        return userNamesMenuItems;
    }


    public boolean getUserNameExists() {
        return userNameExists;
    }

    public JLabel getUserAlreadyAddedLabel(){
        return userAlreadyAddedLabel;
    }

    public void panelChange(JFrame frame, MenuPanel panel, SelectUserPanel panel2){

        for (JMenuItem item : userNamesMenuItems) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(User aUser : userObjects){
                        if(item.getText().equals(aUser.getName())){
                            user = aUser;
                        }
                    }
                    panel2.setVisible(false);
                    frame.remove(panel2);
                    frame.add(panel);
                    panel.setVisible(true);
                    frame.revalidate();

                }
            });

        }

    }

    public JPopupMenu getSelectUserPopUpMenu() {
        return selectUserPopUpMenu;
    }

    public void createUserObjects(){
        for(String userName : userNamesList) {
            user = new User(userName);
            user.readTheStatisticsFromTheFile();
            userObjects.add(user);
        }

    }

}