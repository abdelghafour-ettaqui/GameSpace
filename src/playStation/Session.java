package playStation;

import java.util.*;

import java.io.*;

public class Session {
    Scanner scanner = new Scanner(System.in);
    int choice,typeChoice;
    String firstname,lastname,duration,gameName="",startingTime;
    int postNum;
   // String[] console={ "PS5-SAMSUNG", "XBOX-DELL", "XBOX-SAMSUNG", "XBOX-ASUS", "XBOX-HP","PS5-ASUS","PS5-DELL","NINTENDO SWITCH-DELL","NINTENDO SWITCH-ASUS"};



    public void displayMainMenu() {

        System.out.println("------------------------------------");
            System.out.println(" 1 - Add a client");
            System.out.println(" 2 - Add a console");
            System.out.println(" 2 - Add a screen");
            System.out.println(" 2-  Create a post");
            System.out.println(" 2 - Display the gain the day");
            System.out.println(" 3 - Display the gain the month");
        System.out.println("------------------------------------");
        System.out.print("     enter your choice     ");
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
        System.out.println("------------------- post ----------------");
        int index=1;

        /*  for( String value : console ) {
            System.out.println(index++ +"- "+ value );

        }*/
        choice=scanner.nextInt();
        consoleChoice(choice);
        System.out.print(" N° of the empty post : ");
        postNum=scanner.nextInt();
        System.out.print(" Starting time : ");
        startingTime=scanner.next();
        System.out.print(" Duration : ");
        duration=scanner.next();
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
                   //Gain.displayDay();
                    break;

            case 3:
                   // Gain.displayMonth();
                    break;

            default:
                System.out.println("invalid choice");
        }

    }
    public void consoleChoice(int choice){



    }


}
