import java.util.Scanner;

/**
 * Represent the admin who control and give permissions to playgrounds
 * Class Admin inherits from class User
 * @author Mahmoud Farid Mohamed
 * Date: 6/8/2021
 */
public class Admin extends User {
    Scanner input = new Scanner(System.in);

    /**
     * The User choice to do
     */
    int choice;

    /**
     * Create admin with given name, email and password
     */
    Admin(){
        name = "Admin";
        email = "admin@gmail.com";
        password = "1234";
    }

    /**
     * Home screen to control th admin functions
     */
    @Override
    public void homeScreen() {
        super.homeScreen();
        boolean check = true;
        while(check){
            System.out.println("1- Show Playgrounds owners requests\n2- log out");
            choice = input.nextInt();
            switch (choice){
                case 1: {
                    List requests = new List();
                    requests.showOwnerRequests();
                    break;
                }
                case 2:{
                    check = false;
                    break;
                }
            }
        }
       WelcomeScreen welcomeScreen = new WelcomeScreen();
    }
}
