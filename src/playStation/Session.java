package playStation;

import java.util.*;

public class Session {

    Scanner scanner = new Scanner(System.in);
    int choice, typeChoice, postNum;
    double amountPaid;
    public String firstname, lastname, duration, startingTime, gameName, console, screen;
    public Client client;

    public Post post;
    int postNumber;
    String[] periodSplit;
    ArrayList<Client> clients = new ArrayList<>();
    Post chosenPost;
    ArrayList<Post> posts = new ArrayList<Post>();
    ArrayList<Object> periodsList = new ArrayList<Object>();
    ArrayList<String> bookedPeriod = new ArrayList<String>();

    String[] postWorksOn;
    ArrayList<GameType> gamesType = new ArrayList<GameType>();
    ArrayList<String> chosenType;
    ArrayList<String> GamesName = new ArrayList<String>();


    public Session() {
        posts.add(new Post("SAMSUNG", "PS5", 1));
        posts.add(new Post("DELL", "XBOX", 2));
        posts.add(new Post("SAMSUNG", "XBOX", 3));
        posts.add(new Post("ASUS", "XBOX", 4));
        posts.add(new Post("HP", "XBOX", 5));
        posts.add(new Post("ASUS", "PS5", 6));
        posts.add(new Post("DELL", "NINTENDO SWITCH", 7));
        posts.add(new Post("ASUS", "NINTENDO SWITCH", 8));
        posts.add(new Post("DELL", "PS5", 9));

        gamesType.add(new GameType(new String[]{"fifa", "PES", "TENNIS"}, "SPORT", new int[]{1, 2, 4, 9, 8}));
        gamesType.add(new GameType(new String[]{"WAR1", "WAR2", "WAR3"}, "WAR", new int[]{1, 2, 3, 5, 6, 7}));

        periodsList = JsonFile.period();

    }

    public void DisplayMainMenu() {
        System.out.println("------------------------------------");
        System.out.println(" 1 - Add a client");
        System.out.println(" 2 - Display posts");
        System.out.println(" 3 - Display the gain the day");
        System.out.println(" 4 - Display the gain the month");
        System.out.println("------------------------------------");
        System.out.print("enter your choice");
        try {
            choice = scanner.nextInt();
            ServiceChoice(choice);
        } catch (Exception e) {
            System.out.println("invalid input 1");
        }
    }

    public void ServiceChoice(int choice) {

        switch (choice) {
            case 1:
                AddClient();
                break;


            default:
                System.out.println("invalid choice");
        }

    }

    private void AddClient() {
        try {

            ClientInfo();
            System.out.println("firstname " + firstname + "lastname " + lastname + "amount " + amountPaid + "game " + gameName + "console " + console + "screen " + screen + "chosen post " + chosenPost);
            if (clients.size() < 17) {

                client = new Client(firstname, lastname, duration, gameName, startingTime, chosenPost.getPostNumber(), amountPaid);
                clients.add(client);
                System.out.println(client.toString() + " size " + clients.size());

            } else {
                System.out.println("Sorry all the places are reserved");
            }
        } catch (Exception e) {
            System.out.println("invalid input 2 ");
        }
    }


    public void ClientInfo() {

        GetPersonalInfo();

        DisplayPosts();

        postNumber = scanner.nextInt();

        DisplayPeriods();

        periodSplit = scanner.next().split("-");

        chosenPost = posts.get(postNumber - 1);

        StoringPeriods(periodSplit, chosenPost);

        DisplayGameTime(chosenPost);

        typeChoice = scanner.nextInt();

        DisplayGameName();

        CalculThePrice(chosenPost);


    }

    public void GetPersonalInfo() {

        System.out.print(" Client firstname : ");
        firstname = scanner.next();

        System.out.print(" Client lastname : ");
        lastname = scanner.next();

    }


    public void DisplayPosts() {
        Post onePost;
        String status;
        System.out.println("----------posts---------------");

        for (int i = 0; i < 9; i++) {
            onePost = posts.get(i);

            if (onePost.getAvailable()) status = " available";

            else status = " not available ,it will be available after " + onePost.getNotAvailableTime();

            System.out.println(onePost.getPostNumber() + "- " + onePost.getScreen() + " - " + onePost.getConsole() + " - " + status);

        }
        System.out.print("enter the number of the chosen post: ");
    }


    public void DisplayPeriods() {

        System.out.print(" Available duration of the chosen post : ");
        for (int i = 0; i < periodsList.size(); i++) {
            System.out.println(i + "- " + periodsList.get(i));
        }
        System.out.print("Enter periods in this format (0-1-2-3) : ");
    }


    public void StoringPeriods(String[] periodSplit, Post chosenPost) {

        startingTime = periodSplit[0].split("-")[0];

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


    public void DisplayGameTime(Post chosenPost) {

        System.out.println("------------------- game's Types ----------------");

        chosenType = new ArrayList<String>();

        int index = 0;

        for (int i = 0; i < gamesType.size(); i++) {

            for (int j = 0; j < gamesType.get(i).getPosts().length; j++) {

                if (gamesType.get(i).getPosts()[j] == chosenPost.getPostNumber()) {

                    chosenType.add(gamesType.get(i).getType());

                    System.out.println(++index + "- " + gamesType.get(i).getType());

                }
            }
        }

    }


    public void DisplayGameName() {
        int index = 0;
        for (GameType gameType : gamesType) {
            if (gameType.getType().equals(chosenType.get(typeChoice - 1))) {
                for (String value : gameType.getGameNames()) {
                    index++;
                    GamesName.add(value);
                    System.out.println(index + "- " + value);
                }
                System.out.print("enter the number of the game :");

            }

        }
        gameName = GamesName.get(scanner.nextInt() - 1);
    }

    public void CalculThePrice(Post chosenPost) {

        console = chosenPost.getConsole();
        screen = chosenPost.getScreen();
        for (String value : periodSplit) {
            System.out.println(value);
            if (value.equals("18")) {
                amountPaid = 65;
                duration = "whole day";
                break;
            } else {
                switch (periodSplit.length) {
                    case 1 -> {
                        amountPaid = 5;
                        duration = "30min";
                    }
                    case 2 -> {
                        amountPaid = 10;
                        duration = "1H";
                    }
                    case 4 -> {
                        amountPaid = 18;
                        duration = "2H";
                    }
                    case 10 -> {
                        amountPaid = 40;
                        duration = "5H";
                    }
                    default -> {
                        amountPaid = 5 * periodSplit.length;
                        duration = Integer.toString(periodSplit.length / 2);
                    }
                }
            }
        }


        if (clients.size() == 0) {
            amountPaid = amountPaid * (1 - 0.02);
        } else if (this.gameName.equals("fifa") && (this.amountPaid >= 10 && amountPaid < 65)) {
            amountPaid = amountPaid * (1 - 0.05);

        } else if (this.console.equals("PS5") && this.screen.equals("SAMSUNG") && (this.amountPaid > 40 && amountPaid < 65)) {
            amountPaid = amountPaid * (1 - 0.10);
        }
        System.out.println("amount paid " + this.amountPaid);
        System.out.println("game " + this.gameName);
        System.out.println("screen " + this.chosenPost.getScreen());
        System.out.println("screen " + this.chosenPost.getConsole());
    }
}





