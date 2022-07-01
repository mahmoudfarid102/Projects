import java.util.ArrayList;
import java.util.Scanner;

public class List {
    static public ArrayList<Player> playerList = new ArrayList<Player>(5);
    static public ArrayList<Owner> ownerList = new ArrayList<Owner>(5);
    static public ArrayList<Playground> playgroundList = new ArrayList<Playground>(5);
    static public ArrayList<Admin> adminList = new ArrayList<Admin>(5);
    static public ArrayList<Playground> playerRequests = new ArrayList<Playground>(5);
    static public ArrayList<Playground> ownerRequests = new ArrayList<Playground>(10);
    static public ArrayList<Playground> allowed = new ArrayList<Playground>(10);
    static public ArrayList<Playground> booked = new ArrayList<Playground>(5);
    static public int requestCount = 0;
    static public int playerRequestsCount = 0;

    public void createAdmin(){
        Admin admin = new Admin();
        adminList.add(admin);
    }

    List(){
        createAdmin();
    }
    ArrayList<Player> getPlayerList(){
        return playerList;
    }

    ArrayList<Owner> getOwnerList(){
        return ownerList;
    }

    ArrayList<Playground> getPlaygroundList(){
        return playgroundList;
    }

    ArrayList<Admin> getAdminList(){
        return adminList;
    }

    public void signIn(String email, String password){
        if(findByEmail(email)){
            for(int i = 0; i < playerList.size(); i++){
                if(playerList.get(i).getEmail().equals(email) && playerList.get(i).getPassword().equals(password)){
                    playerList.get(i).homeScreen();
                }
            }
            for(int i = 0; i < ownerList.size(); i++){
                if(ownerList.get(i).getEmail().equals(email) && ownerList.get(i).getPassword().equals(password)){
                    ownerList.get(i).homeScreen();
                }
            }
            for(int i = 0; i < adminList.size(); i++){
                if(adminList.get(i).getEmail().equals(email) && adminList.get(i).getPassword().equals(password)){
                    adminList.get(i).homeScreen();
                }
            }
            System.out.println("Password is invalid!");
            WelcomeScreen welcomeScreen = new WelcomeScreen();
        }
        else{
            System.out.println("Email is invalid!");
            WelcomeScreen welcomeScreen = new WelcomeScreen();
        }

    }

    public void addUser(int type, User newUser){
        if(type == 1){
            addPlayer(newUser);
        }
        else if(type == 2){
            addOwner(newUser);
        }
        else {
            System.out.println("Error: Wrong value");
            System.exit(0);
        }
    }

    public void addPlayer(User newUser){
        Player newPlayer = new Player(newUser);
        playerList.add(newPlayer);
        System.out.println("GoFo.User created successfully");
        newPlayer.homeScreen();
    }

    public void addOwner(User newUser){
        Owner newOwner = new Owner(newUser);
        ownerList.add(newOwner);
        System.out.println("GoFo.User created successfully");
        newOwner.homeScreen();
    }

    public boolean findByEmail(String email){
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        for(int i = 0; i < ownerList.size(); i++){
            if(ownerList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        for(int i = 0; i < adminList.size(); i++){
            if(adminList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public void addPlayerRequest(Playground pg){
        playerRequestsCount++;
        playerRequests.add(pg);
    }

    public void  showPlayerRequests(){
        int i = 0;
        for (i = 0; i < playerRequestsCount; i++){
            playerRequests.get(i).showPlayground();
            playerRequests.get(i).available_slots();
            System.out.println("Do you want to accept this Playground booking request\n1- Yes\n2- No");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    booked.add(playerRequests.get(i));
                    playerRequests.remove(i);
                    playerRequestsCount--;
                    break;
                case 2:
                    playerRequests.remove(i);
                    playerRequestsCount--;
                    break;
                default:
                    System.out.println("Error: Wrong choice");
                    break;
            }
        }
    }


    public void addOwnerRequest(Playground pg){
        requestCount++;
        ownerRequests.add(pg);
    }

    public void showOwnerRequests(){
        int i = 0;
        for (i = 0; i < requestCount; i++){
            ownerRequests.get(i).showPlayground();
            ownerRequests.get(i).available_slots();
            System.out.println("Do you want to give permission to this Playground\n1- Yes\n2- No");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    ownerRequests.get(i).setPermission(true);
                    allowed.add(ownerRequests.get(i));
                    playgroundList.add(ownerRequests.get(i));
                    ownerRequests.remove(i);
                    requestCount--;

                    break;
                case 2:
                    ownerRequests.get(i).setPermission(false);
                    ownerRequests.remove(i);
                    requestCount--;
                    break;
                default:
                    System.out.println("Error: Wrong choice");
                    ownerRequests.get(i).setPermission(false);
                    break;
            }
        }
    }
}
