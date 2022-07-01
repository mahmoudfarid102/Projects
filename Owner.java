import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Owner to represent the Playground owner
 * Class Owner inherits from class User
 * @author Mahmoud Farid Mohamed
 * Date: 6/8/2021
 */
public class Owner extends User{
    /**
     * Count the number of requests that the owner had made
     */
    static int count = 0;
    /**
     * Represent the list of owner`s playgrounds
     */
    private ArrayList<Playground> Playgrounds = new ArrayList<Playground>(5);
    /**
     * Represent the list of owner`s playground requests
     */
    private ArrayList<Playground> PlaygroundRequests = new ArrayList<Playground>(5);

    Scanner input = new Scanner(System.in);
    /**
     * The User choice to do
     */
    int choice;
    /**
     * List to access owner requests
     */
    List ownerRequest = new List();
    /**
     *List to access the players requests to book playground
     */
    List list = new List();

    /**
     * Default constructor to create new owner
     */
    Owner(){}

    /**
     * Create new playground owner with new user data
     * @param user: user data
     */
    public Owner(User user){
        name = user.name;
        email = user.email;
        password = user.password;
        defaultLocation = user.defaultLocation;
        eWallet = user.eWallet;
    }

    /**
     * Home screen to control th owner functions
     */
    @Override
    public void homeScreen() {
        super.homeScreen();
        System.out.println("1- Create new Playground\n2- Display requests\n3- Show my requests\n4- Manage my eWallet\n5- Log out");
        choice = input.nextInt();
        switch (choice){
            case 1:
                createPlayground();;
                homeScreen();
                break;
            case 2:
                for(int i=0 ; i <requests.size(); i ++){
                    requests.get(i).show();
                    System.out.println("1.Accept\n2.Refuse");
                    int ans = input.nextInt();
                    if(ans == 1){
                        requests.get(i).activate();
                        requests.remove(i);
                    }
                    else {
                        requests.remove(i);
                    }
                }

                homeScreen();
                break;
            case 3:
                showMyRequests();
                homeScreen();
                break;
            case 4:
                showMyWallet();
                homeScreen();
                break;
            case 5:
                WelcomeScreen welcomeScreen = new WelcomeScreen();
                break;
            default:
                System.out.println("Error: Wrong choice");
                this.homeScreen();
                break;
        }
    }

    /**
     * Creates new Playground and send the request to the admin
     * Add new time slots to the playground
     * Save the Owner`s requests to list
     */
    public void createPlayground() {
        System.out.println("Enter your Playground data");
        String name, location, time;
        int capacity;
        double hourCost;
        System.out.print("Enter your Playground name: ");
        name = input.next();
        System.out.print("Enter your Playground capacity: ");
        capacity = input.nextInt();
        System.out.print("Enter your Playground location: ");
        location = input.next();
        System.out.print("Enter your Playground cost per hour: ");
        hourCost = input.nextDouble();
        Playground playground = new Playground(name, capacity, location, hourCost, this);
        Request request = new Request(playground);
        PlaygroundRequests.add(playground);
        ownerRequest.addOwnerRequest(playground);// add to owner Playgrounds requests
        count++;
        TimeSlot timeSlot = new TimeSlot("");
        boolean yes = true;
        while (yes) {
            System.out.println("Do you want to add time slot?");
            System.out.println("1- Yes\t2- No");
            choice = input.nextInt();
            switch (choice) {
                case 1:{
                    System.out.print("Enter time: ");
                    time = input.next();
                    timeSlot.setTime(time);
                    playground.addSlot(timeSlot);
                    break;
                }
                case 2: {
                    yes = false;
                    break;
                }
                default:
                    System.out.println("Error: wrong choice");
                    this.homeScreen();
                    break;
            }
        }
        System.out.println("Your Playground created successfully wait for admin permission");
    }

    /**
     * Access the booking requests
     * Accept or reject players requests
     */
    public void income(){
        for(int i=0;i < requests.size(); i++)
        {
            requests.get(i).show();
            System.out.println("Accept?\n1-Yes\n2-No");
            int ans;
            ans= input.nextInt();
            if(ans == 1)
                requests.get(i).activate();
            else
                continue;
        }
    }

    /**
     * Display the owner`s Playgrounds that have got permission from the Admin
     */
    public void MyPlaygrounds(){
        for (int i = 0; i < List.playerRequestsCount; i++){
            for (int j = 0; j < count; j++){
                if (PlaygroundRequests.get(j).getName() == List.allowed.get(j).getName()){
                    Playgrounds.add(List.allowed.get(j));
                    PlaygroundRequests.remove(j);
                }
            }
        }
    }

    /**
     * Display the requests for playground
     */
    public void showMyRequests() {
        if (count == 0){
            System.out.println("No Requests for now");
        }
        for (int i = 0; i < count; i++){
            System.out.println("=============================");
            PlaygroundRequests.get(i).showPlayground();
            PlaygroundRequests.get(i).available_slots();
            System.out.println("=============================");
        }
    }
}
