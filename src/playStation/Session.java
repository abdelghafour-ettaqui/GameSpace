package playStation;

import java.util.*;




public class Session {

    Scanner scanner = new Scanner(System.in);
    int choice,typeChoice,postNum;
    String firstname,lastname,duration,startingTime,gameName;
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
    ArrayList <String> chosenType ;
    ArrayList <String> GamesName = new ArrayList <String>();



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
        gamesType.add(new GameType( new String[]{"WAR1","WAR2","WAR3"},"WAR",new int[]{1,2,3,5,6,7} ));

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
    public void serviceChoice(int choice){

        switch (choice){
            case 1:
                addClient();
                break;


            default:
                System.out.println("invalid choice");
        }

    }
     private void addClient(){
        try {

            clientInfo();
            if(clients.size()<17){

                 client = new Client(firstname,lastname, duration , "game", startingTime, postNum);
                 clients.add(client);
                 System.out.println( client.toString() + " size " + clients.size());

            }
            else{
                System.out.println("Sorry all the places are reserved");
            }
        }
        catch(Exception e) {
            System.out.println("invalid input 2 ");
        }
    }


    public void clientInfo(){

        getPersonalInfo();

        displayPosts();

        postNumber=scanner.nextInt();

        displayPeriods();

        periodSplit =scanner.next().split("-");

        Post chosenPost = posts.get(postNumber-1);

        storingPeriods( periodSplit,chosenPost);

        displayGameTime(chosenPost);

        typeChoice=scanner.nextInt();

        DisplayGameName();


    }

    public void getPersonalInfo(){

        System.out.print(" Client firstname : ");
        firstname=scanner.next();

        System.out.print(" Client lastname : ");
        lastname=scanner.next();

    }


    public void displayPosts(){
        Post onePost;
        String status;
        System.out.println("----------posts---------------");

        for(int i = 0 ; i < 9 ; i++){
            onePost=posts.get(i);

            if(onePost.getAvailable())status =" available";

            else status =" not available ,it will be available after "+onePost.getNotAvailableTime();

            System.out.println( onePost.getPostNumber()+"- "+onePost.getScreen()+" - "+onePost.getConsole()+ " - "+status);

        }
        System.out.print("enter the number of the chosen post: ");
    }



    public void displayPeriods(){

        System.out.print(" Available duration of the chosen post : ");
        for (int i=0 ;i<periodsList.size();i++){
            System.out.println(i+"- "+periodsList.get(i));
        }
        System.out.print("Enter periods in this format (0-1-2-3) : ");
    }



    public void storingPeriods(String[] periodSplit, Post chosenPost){
        for (String value : periodSplit) {

            if (chosenPost.getNotAvailableTime().size() == 0) {
                chosenPost.setNotAvailableTime(value);
            } else {
                int size = chosenPost.getNotAvailableTime().size();
                for (int j = 0; j < size; j++) {
                    String s = chosenPost.getNotAvailableTime().get(j);
                    System.out.println(s);
                    System.out.println(value);

                    if (value.equals(s)) {
                        System.out.println("period with number " + s + " is already booked");
                        return;
                    } else {

                        chosenPost.setNotAvailableTime(value);
                        System.out.println(chosenPost.getNotAvailableTime());
                        break;
                    }

                }
            }

        }
    }



    public void displayGameTime(Post chosenPost){
        System.out.println("------------------- game's Types ----------------");

        chosenType = new ArrayList<String>();
        int index=0;
        for (int i=0;i<gamesType.size();i++) {

            for (int j = 0; j < gamesType.get(i).getPosts().length; j++) {

                if (gamesType.get(i).getPosts()[j] == chosenPost.getPostNumber()) {
                    chosenType.add(gamesType.get(i).getType());
                    System.out.println(++index +"- "+ gamesType.get(i).getType());

                }
            }
        }

    }


    public void DisplayGameName(){
        int index=0;
        for (GameType gameType : gamesType) {
            if (gameType.getType().equals(chosenType.get(typeChoice - 1))) {
                for (String value : gameType.getGameNames()) {
                    index++;
                    GamesName.add(value);
                    System.out.println(index+"- "+value);
                }
                System.out.print("enter the number of the game :");

            }

        }
        gameName=GamesName.get(scanner.nextInt()-1);
    }



}
