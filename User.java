import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class User represent the users data
 * User can be Player, Playground owner or Admin
 * @author Mahmoud Farid Mohamed
 * Date: 6/8/2021
 */
public class User {
    /**
     * The user name
     */
    protected String name;
    /**
     * User Email
     */
    protected String email;
    /**
     * User password
     */
    protected String password;
    /**
     * User default Location
     */
    protected String defaultLocation;
    /**
     * User type Player, Playground owner or Admin
     */
    protected int type;
    /**
     * User EWallet represent the account balance
     */
    protected double eWallet;
    /**
     * List to store user`s requests
     */
    protected ArrayList<Request> requests = new ArrayList<Request>(5);

    /**
     * Default Constructor for class user
     */
    User(){}

    /**
     * Get the email of user
     * @return email
     */
    String getEmail(){
        return email;
    }

    /**
     * Get user`s password
     * @return password
     */
    String getPassword(){
        return password;
    }

    /**
     * Create new User with given name, email, password, default location, type and balance
     * @param name: User`s name
     * @param email:User`s email
     * @param password:User`s password
     * @param defaultLocation: User`s default location
     * @param type: User`s type
     * @param balance: User`s balance
     */
    public User(String name, String email, String password ,String defaultLocation, int type, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.defaultLocation = defaultLocation;
        this.type = type;
        this.eWallet = balance;
    }

    /**
     * Abstract home screen for user
     */
    public void homeScreen(){
        System.out.println("Welcome to GoFo!");
        System.out.println(name);
        System.out.println(email);
        System.out.println("What do you want to do?");
    }

    /**
     * Allow user to withdraw from his own wallet
     * @param amount: the amount of money that the user want to withdraw
     * @return boolean
     */
    public boolean withdraw(double amount){
        if (eWallet >= amount){
            eWallet -= amount;
            return true;
        }else {
            System.out.println("Sorry, there is no enough balance");
            return false;
        }
    }

    /**
     * Allow user to deposit to his own wallet
     * @param amount: the amount of money that the user want to deposit
     * @return boolean
     */
    public void deposit(double amount){
        eWallet += amount;
    }

    public void showMyWallet(){
        System.out.println("eWallet");
        System.out.println("Your balance = " + eWallet);
        System.out.println("What do you want to do?\n 1- Withdraw\n 2- Deposit");
        Scanner input = new Scanner(System.in);
        boolean success;
        int answer;
        answer = input.nextInt();
        switch (answer){
            case 1 : {
                double amount;
                System.out.print("Enter the amount: ");
                amount = input.nextDouble();
                if (amount < 0){
                    System.out.println("Error: Wrong value");
                    this.homeScreen();
                }
                success = withdraw(amount);
                break;
            }
            case 2 : {
                double amount;
                System.out.print("Enter the amount: ");
                amount = input.nextDouble();
                if (amount < 0){
                    System.out.println("Error: Wrong value");
                    this.homeScreen();
                }
                deposit(amount);
                break;
            }
            default : {
                System.out.println("Error: Wrong choice");
                homeScreen();
                break;
            }
        }
    }

    /**
     * Add the user request to the list of his requests
     * @param request: the request that user want to make
     */
    public void addToRequests(Request request){
        requests.add(request);
    }

    /**
     * Display the user`s requests
     */
    public void showRequests(){
        if(!requests.isEmpty()){
            for(int i = 0; i < requests.size(); i++){
                System.out.println("Your Request Number #" + i+1 + " was");
                requests.get(i).show();
            }
        }
        else {
            System.out.println("No playground have been requested till now\n");
        }
    }
}
