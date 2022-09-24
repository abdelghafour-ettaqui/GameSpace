package playStation;

import java.util.*;




public class Session {

    Scanner scanner = new Scanner(System.in);
    int choice,typeChoice,postNum;
    String firstname,lastname,duration,gameName="",startingTime;
    Client client ;

    Post post;
    int postNumber;
    String[] periodSplit;
    ArrayList <Client> clients = new ArrayList <Client>();
    ArrayList <Post> posts = new ArrayList <Post>();
    ArrayList <Object> periodsList = new ArrayList <Object>();
    ArrayList <String> bookedPeriod = new ArrayList <String>();

    String[] postWorksOn;
    ArrayList <GameType> gamesType = new ArrayList<GameType>();

    public Session() {
        posts.add(new Post("SAMSUNG","PS5",1));
        posts.add(new Post ("DELL","XBOX",2));
        posts.add(new Post ("SAMSUNG","XBOX",3));
        posts.add(new Post ("ASUS","XBOX",4));
        posts.add(new Post ("HP","XBOX",5));
        posts.add(new Post ("ASUS","PS5",6));
        posts.add(new Post ("DELL","NINTENDO SWITCH",7));
        posts.add(new Post ("ASUS","NINTENDO SWITCH",8));
        posts.add(new Post ("DELL","PS5",9));
        gamesType.add( new GameType( new String[]{"fifa","PES","TENNIS"},"SPORT",new int[]{1,2,4,9,8} ) );
        gamesType.add(new GameType( new String[]{"fifa","PES","TENNIS"},"WAR",new int[]{1,2,3,5,6,7} ));

        periodsList = JsonFile.period();

    }



    // String[] post={ "PS5-SAMSUNG", "XBOX-DELL", "XBOX-SAMSUNG", "XBOX-ASUS", "XBOX-HP","PS5-ASUS","PS5-DELL","NINTENDO SWITCH-DELL","NINTENDO SWITCH-ASUS"};



    public void displayMainMenu() {


        System.out.println("------------------------------------");
            System.out.println(" 1 - Add a client");
            System.out.println(" 2 - Display posts");
            System.out.println(" 3 - Display the gain the day");
            System.out.println(" 4 - Display the gain the month");
        System.out.println("------------------------------------");
        System.out.print("enter your choice");
        try {
            choice = scanner.nextInt();
            serviceChoice(choice);
        }
        catch(Exception e) {
            System.out.println("invalid input 1");
        }
    }
     private void addClient(){
        try {

            clientInfo();


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
            e.printStackTrace();
//            System.out.println("invalid input 2 ");
        }
    }

    public void clientInfo(){
        Post onePost;
        String status;

        System.out.println("------------------------------------");

        System.out.print(" Client firstname : ");
        firstname=scanner.next();

        System.out.print(" Client lastname : ");
        lastname=scanner.next();
        System.out.println("----------posts---------------");

        for(int i = 0 ; i < 9 ; i++){
             onePost=posts.get(i);
    //             if(i%2==0){
    //                onePost.setAvailable(false);
   //             }

            if(onePost.getAvailable())status =" available";

            else status =" not available ,it will be available after "+onePost.getNotAvailableTime();

            System.out.println( onePost.getPostNumber()+"- "+onePost.getScreen()+" - "+onePost.getConsole()+ " - "+status);

        }
        System.out.print("enter the number of the chosen post: ");
        postNumber=scanner.nextInt();


        System.out.print(" Available duration of the chosen post : ");
        for (int i=0 ;i<periodsList.size();i++){
            System.out.println(i+"- "+periodsList.get(i));
        }
        System.out.print("Enter periods in this format (0-1-2-3) : ");

        duration = scanner.next();
        periodSplit = duration.split("-");
        Post chosenPost=posts.get(postNumber-1);


        for (int i = 0 ; i<periodSplit.length ; i++) {

           if(chosenPost.getNotAvailableTime().size()==0){
               chosenPost.setNotAvailableTime(periodSplit[i]);
           }
           else {
               int size = chosenPost.getNotAvailableTime().size();
           for (int j = 0; j < size; j++) {
               String s = chosenPost.getNotAvailableTime().get(j);
               System.out.println(s);
               System.out.println(periodSplit[i]);

               if(periodSplit[i].equals(s)){
                       System.out.println("period with number " + s + " is already booked");
                       return;
                   }
                   else{
                       System.out.println(chosenPost.getNotAvailableTime());
                       chosenPost.setNotAvailableTime(periodSplit[i]);
                   }
               }
           }

        }


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


    }


}
