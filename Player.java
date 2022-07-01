import java.util.ArrayList;
import java.util.Scanner;


public class Player extends User {

    public ArrayList<Request> books = new ArrayList<Request>(5);

    Scanner input = new Scanner(System.in);
    Player(){}
    public Player(User user){
        name = user.name;
        email = user.email;
        password = user.password;
        defaultLocation = user.defaultLocation;
        eWallet = user.eWallet;
    }

    @Override
    public void homeScreen(){
        System.out.println("1. Book a playground \n2. Display last books \n3. Display requests \n4. Manage e-wallet \n5. logout");
        int choice = input.nextInt();
        switch (choice){
            case 1 :{
                bookPlayground();
                break;
            }
            case 2 :{
                showBooks();
                break;
            }
            case 3 :{
                showRequests();
                homeScreen();
                break;
            }
            case 4 :{
                showMyWallet();
                homeScreen();
                break;
            }
            case 5 :{
                WelcomeScreen welcomeScreen = new WelcomeScreen();
                break;
            }
            default:{
                System.out.println("Error: Wrong choice");
                homeScreen();
                break;
            }
        }
    }

    public void bookPlayground(){
        List list = new List();
        System.out.println("Choose playground to book:\nOr enter 0 to return");
        for(int i = 0; i < list.getPlaygroundList().size(); i++){
            System.out.print(i+1 + ".");
            list.getPlaygroundList().get(i).showPlayground();
            list.getPlaygroundList().get(i).available_slots();
        }
        int choice = input.nextInt();
        if(choice < 0 || choice > list.getPlaygroundList().size()){
            System.out.println("Wrong choice!");
            homeScreen();
        }
        else if(choice == 0){
            homeScreen();
        }
        else{
            choice -= 1;
            if(list.getPlaygroundList().get(choice).getHour_cost() < eWallet){
                System.out.println("Sorry, balance is not enough");
                homeScreen();
            }
            else{
                System.out.println("Enter the desired slot or (home) to return home");
                String tms;
                tms = input.next();
                if(tms.equalsIgnoreCase("home")){
                    homeScreen();
                }
                Request request = new Request(tms, list.getPlaygroundList().get(choice));
                list.addPlayerRequest(list.getPlaygroundList().get(choice));
                list.getPlaygroundList().get(choice).getOwner().addToRequests(request);
                addToRequests(request);
            }
        }
        homeScreen();
    }

    public void addToBooks(int ID){
        boolean done = false;
        for (int i = 0; i < requests.size(); i++)
        {
            if(requests.get(i).getID() == ID){
                done = requests.get(i).activate();
                books.add(requests.get(i));
                requests.remove(i);
            }
        }
        if(done){
            System.out.println("Succeeded");
        }
        else{
            System.out.println("Failed");
        }
    }

    public void showBooks(){
        if(!books.isEmpty()){
            for(int i = 0; i < books.size(); i++){
                System.out.println("Your Book Number #" + i+1 + " was");
                books.get(i).show();
            }
            homeScreen();
        }
        else {
            System.out.println("No playground have been booked till now\n");
        }
        homeScreen();
    }
}
