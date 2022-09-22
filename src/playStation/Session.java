package playStation;

import java.util.*;

import java.io.*;

public class Session {

    Scanner scanner = new Scanner(System.in);
    int choice,typeChoice,postNum;
    String firstname,lastname,duration,gameName="",startingTime;
    //HashMap<String, ArrayList<Integer>> console;
    //HashMap<String, ArrayList<Integer>> screen;
    Console ps5,xbox,ms;
    Screen samsung,dell,hp,asus;
    Console[] consoles;
    Screen[] screens;

    public Session() {
        // = new HashMap<>();
        // screen = new HashMap<>();
         ps5 = new Console("ps5",3);
         xbox = new Console("xbox",4);
         ms = new Console("NINTENDO SWITCH",2);
         samsung = new Screen("samsung",2);
         dell = new Screen("dell",3);
         hp = new Screen ("hp",1);
         asus = new Screen ("asus",3);

         screens = new Screen[]{samsung,dell,hp,asus};
         consoles = new Console[]{ps5,xbox,ms};
    }



    // String[] post={ "PS5-SAMSUNG", "XBOX-DELL", "XBOX-SAMSUNG", "XBOX-ASUS", "XBOX-HP","PS5-ASUS","PS5-DELL","NINTENDO SWITCH-DELL","NINTENDO SWITCH-ASUS"};



    public void displayMainMenu() {


        System.out.println("------------------------------------");
            System.out.println(" 1 - Add a client");
            System.out.println(" 2 - Dispaly consoles and screens");
            System.out.println(" 3 - Display the gain the day");
            System.out.println(" 4 - Display the gain the month");
        System.out.println("------------------------------------");
        System.out.print("enter your choice");
        try {
            choice = scanner.nextInt();
            serviceChoice(choice);
        }
        catch(Exception e) {
            System.out.println("invalid input");
        }
    }
     private void addClient(){
        try {

            clientInfo();
            Client client ;

            ArrayList <Client> clients = new ArrayList<Client>();

            if(clients.size()<17){

                 client = new Client(firstname,lastname, duration , gameName, startingTime, postNum);
                 clients.add(client);
                 System.out.println( client.toString() + " size " + clients.size());

            }
            else{
                System.out.println("Sorry all the places are reserved");
            }


        }
        catch(Exception e) {
            System.out.println("invalid input");
        }
    }

    public void clientInfo(){
        System.out.println("------------------------------------");

        System.out.print(" Client firstname : ");
        firstname=scanner.next();

        System.out.print(" Client lastname : ");
        lastname=scanner.next();

        System.out.print(" Starting time : ");
        startingTime=scanner.next();

        System.out.print(" Duration : ");
        duration=scanner.next();

        System.out.println("----------Consoles---------------");
        int indexConsoles=1;
        for( Console value : consoles ) {
            System.out.println(indexConsoles++ +"- "+ value );
        }
        System.out.print("enter the number of the chosen console: ");
        choice=scanner.nextInt();
        consoleChoice(choice);
        System.out.println("-------------Screens-----------------");
        int indexScreens=1;
        for( Screen value:screens){
            System.out.println(indexScreens++ +"- "+ value);
        }
        System.out.println("enter the number of the chosen screen: ");
        choice=scanner.nextInt();
        screenChoice(choice);

        System.out.println("------------------- type of the game ----------------");
        System.out.println(" 1 - sport");
        System.out.println(" 2 - war");
        typeChoice=scanner.nextInt();
        switch (choice){
            case 1:
                gameName=sport();
                break;

            case 2:
                gameName=war();
                break;
        }
    }
    public String sport(){

        String gameName="";
        System.out.println("------------------- game available for sport type ----------------");
        System.out.println("1 - Fifa");
        System.out.println("2 - Pes");
        try{
            if(scanner.nextInt()==1)gameName="Fifa";
            else gameName="Pes";

        }
        catch(Exception e){
            System.out.println("please enter the correct number");
        }
        return gameName;
    }


    public String war(){
        String gameName="";
        System.out.println("------------------- game available for war type ----------------");
        System.out.println("1 - Counter-Strike");
        System.out.println("2 - Assassin's Creed");
        try{
            if(scanner.nextInt()==1)gameName="Counter-Strike";
            else gameName="Assassin's Creed";
        }
        catch(Exception e){
            System.out.println("please enter the correct number");

        }

        return gameName;
    }

    public void serviceChoice(int choice){

        switch (choice){
            case 1:
                    addClient();
                    break;

            case 2:
                   displayDevices();
                    break;


            case 3:
                // addScreen()();
                break;
            case 4:
                // addScreen()();
                break;
            default:
                System.out.println("invalid choice");
        }

    }
    public void consoleChoice(int choice){

    }

    public void screenChoice(int choice){

    }

    public void displayDevices(){
        int indexConsoles=1;
        int indexScreens=1;
        System.out.println("----------Consoles---------------");
        for( Console value : consoles ) {
            System.out.println(indexConsoles++ +"- "+ value );
        }
        System.out.println("-----------Screens---------------");
        for( Screen value:screens){
            System.out.println(indexScreens++ +"- "+ value);
        }

    }


}
