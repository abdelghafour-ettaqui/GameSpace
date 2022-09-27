package playStation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Session {

    Scanner scanner = new Scanner(System.in);
    int choice = 1, typeChoice, postNum;
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
        posts.add(new Post("SAMSUNG", "PS5", 1, "available"));
        posts.add(new Post("DELL", "XBOX", 2, "available"));
        posts.add(new Post("SAMSUNG", "XBOX", 3, "available"));
        posts.add(new Post("ASUS", "XBOX", 4, "available"));
        posts.add(new Post("HP", "XBOX", 5, "available"));
        posts.add(new Post("ASUS", "PS5", 6, "available"));
        posts.add(new Post("DELL", "NINTENDO SWITCH", 7, "available"));
        posts.add(new Post("ASUS", "NINTENDO SWITCH", 8, "available"));
        posts.add(new Post("DELL", "PS5", 9, "available"));

        gamesType.add(new GameType(new String[]{"fifa", "PES", "TENNIS"}, "SPORT", new int[]{1, 2, 4, 9, 8}));
        gamesType.add(new GameType(new String[]{"WAR1", "WAR2", "WAR3"}, "WAR", new int[]{1, 2, 3, 5, 6, 7}));

        periodsList = JsonFile.period();

    }


    public void DisplayMainMenu() {
        System.out.println("------------------------------------");
        System.out.println(" 1 - Add a client");
        System.out.println(" 3 - Display the gain the day");
        System.out.println(" 4 - Display the gain the month");
        System.out.println(" 0 - Exit ");
        System.out.println("------------------------------------");
        System.out.print("enter your choice ");
        while (choice != 0) {

            try {
                choice = scanner.nextInt();

                ServiceChoice();


            } catch (Exception e) {
                System.out.println("invalid input 1");
            }
        }
    }


    public void ServiceChoice() {

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
            System.out.println("firstname " + firstname + "lastname " + lastname + "amount " + amountPaid + "game " + gameName + "console " + console + "screen " + screen + "chosen post " + chosenPost + "starting time " + startingTime);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Your input is invalid, please retry to add the client");
            ClientInfo();
        }
    }

    public void storeClient() {
        if (clients.size() < 17) {
            client = new Client(firstname, lastname, duration, gameName, startingTime, chosenPost.getPostNumber(), amountPaid);
            clients.add(client);
            System.out.println(client.toString() + " size " + clients.size());

        } else {
            System.out.println("Sorry all the places are reserved");
        }

    }

    public boolean Reservation_CurrentTime() {

        System.out.println("---------------------------------------");
        System.out.println("1 - Make a reservation");
        System.out.println("2 - client want to play now ");
        choice = scanner.nextInt();
        boolean flag = true;
        switch (choice) {
            case 1 -> {
                flag = true;
                break;
            }
            case 2 -> {
                flag = false;
                break;
            }
        }

        return flag;

    }


    public void ClientInfo() {

        GetPersonalInfo();
        setPostStatus();
        DisplayPosts();
        chosenPost = posts.get(postNumber - 1);
        checkIfAvailable(chosenPost);

        if (Reservation_CurrentTime()) {

            DisplayPeriods(chosenPost);

            periodSplit = scanner.next().split("-");

            if (!filtredSplit(periodSplit, chosenPost)) {
                periodSplit = null;
            }
            StoringPeriods(periodSplit, chosenPost);

            DisplayGameType(chosenPost);

            typeChoice = scanner.nextInt();

            DisplayGameName();

            CalculThePrice(chosenPost);

            storeClient();
            BackToMenu();
        } else {
            getDuration();
            DisplayGameType(chosenPost);
            typeChoice = scanner.nextInt();
            DisplayGameName();
            CalculThePrice(chosenPost);
            storeClient();
            BackToMenu();

        }


    }


    public void GetPersonalInfo() {

        System.out.print(" Client firstname : ");
        firstname = scanner.next();

        System.out.print(" Client lastname : ");
        lastname = scanner.next();

    }

    public void setPostStatus() {
        int index = 0;

        int[] values = new int[3];
        for (int i = 0; i < posts.size(); i++) {

            for (int j = 0; j < posts.get(i).getNotAvailableTime().size(); j++) {

                index = Integer.parseInt(posts.get(i).getNotAvailableTime().get(j));

                values = compareTime(index);


                if (values[0] > 0 && values[1] > 0) {
                    posts.get(i).setAvailable("not available");

                }
            }
        }
    }

    public int[] compareTime(int index) {
        String[] period;
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        int values[] = new int[3];
        LocalTime firstPartTime, secondPartTime, currentTime;

        period = periodsList.get(index).toString().split("-");

        firstPartTime = LocalTime.of(Integer.parseInt(period[0].split(":")[0]), Integer.parseInt(period[0].split(":")[1]), 0);
        secondPartTime = LocalTime.of(Integer.parseInt(period[1].split(":")[0]), Integer.parseInt(period[1].split(":")[1]), 0);
        currentTime = LocalTime.of(Integer.parseInt(time.split(":")[0]), Integer.parseInt(time.split(":")[1]), 0);


        values[0] = currentTime.compareTo(firstPartTime);

        values[1] = secondPartTime.compareTo(currentTime);

        return values;
    }

    public void checkIfAvailable(Post chosenPost) {
        if (chosenPost.getAvailable().equals("not available")) {
            System.out.println("the chosen post is not available for the moment please chose another one");
            DisplayPosts();
        }
    }

    public void DisplayPosts() {

        Post onePost;

        System.out.println("----------posts---------------");

        for (int i = 0; i < 9; i++) {
            onePost = posts.get(i);
            System.out.println(onePost.getPostNumber() + "- " + onePost.getScreen() + " - " + onePost.getConsole() + "- " + onePost.getAvailable());

        }
        System.out.print("enter the number of the chosen post: ");
        postNumber = scanner.nextInt();
    }

    public void DisplayPeriods(Post chosenPost) {

        System.out.println(" Available duration of the chosen post : ");
        for (int i = 0; i < periodsList.size(); i++) {

            if (chosenPost.getNotAvailableTime().size() != 0) {

                if (!chosenPost.getNotAvailableTime().contains(Integer.toString(i))) {

                    System.out.println(i + "- " + periodsList.get(i));
                }

            } else {

                System.out.println(i + "- " + periodsList.get(i));

            }
        }
        System.out.print("Enter periods in this format (0-1-2-3) : ");
    }

    public boolean filtredSplit(String[] periodSplit, Post chosenPost) {
        boolean flag = true;
        for (String value : periodSplit) {
            if (value.matches("\\d+")) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }


    public void StoringPeriods(String[] periodSplit, Post chosenPost) {

        for (String value : periodSplit) {

            if (chosenPost.getNotAvailableTime().size() == 0) {
                chosenPost.setNotAvailableTime(value);
            } else {
                int size = chosenPost.getNotAvailableTime().size();
                for (int j = 0; j < size; j++) {
                    String s = chosenPost.getNotAvailableTime().get(j);
                    if (value.equals(s)) {
                        System.out.println("Period with number " + s + " is already booked, please make sure to select from the periods shown above  ");
                        StoringPeriods(periodSplit, chosenPost);
                    } else {

                        chosenPost.setNotAvailableTime(value);
                        break;
                    }

                }
            }

        }
    }


    public void DisplayGameType(Post chosenPost) {

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
        System.out.print("enter the number of the game type :");

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
//            System.out.println(value);
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
//        System.out.println("game " + this.gameName);
//        System.out.println("screen " + this.chosenPost.getScreen());
//        System.out.println("screen " + this.chosenPost.getConsole());
    }

    public void BackToMenu() {
        System.out.println("-----------------------------");
        System.out.println("1 - Add another client ");
        System.out.println("2 - Back to main menu ");
        System.out.println("3 - Exit");
        System.out.print("Enter your choice : ");
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                ClientInfo();
                break;
            }
            case 2 -> {
                DisplayMainMenu();
                break;
            }
            case 3 -> {
                System.out.println("see you next time ");
                break;
            }
        }
    }


    public void getDuration() {
        System.out.println("enter duration time: ");
        System.out.println("1- 30min");
        System.out.println("2- 1H");
        System.out.println("3- 2H");
        System.out.println("4- 5H");
        System.out.println("5- The whole day");
        System.out.print("enter your choice : ");
        int idOfStartingPeriod = 0, durationChoice = scanner.nextInt();
        System.out.println(durationChoice);
        String startPeriod = "";
        int[] values = new int[3];

        for (int i = 0; i < periodsList.size(); i++) {
            values = compareTime(i);
            if (values[0] > 0 && values[1] > 0) {
                startPeriod = periodsList.get(i).toString();
                idOfStartingPeriod = i;
                System.out.println(startPeriod);
                break;
            }
        }
        handlingChoices(idOfStartingPeriod, durationChoice, startPeriod);
    }

    public void handlingChoices(int idOfStartingPeriod, int durationChoice, String startPeriod) {
        switch (durationChoice) {
            case 1 -> {


                if (chosenPost.getNotAvailableTime().contains(Integer.toString(idOfStartingPeriod + 1))) {
                    System.out.println("first");
                    System.out.println("there are some reservation after the period chosen");
                    break;
                } else {
                    System.out.println("second");
                    chosenPost.setNotAvailableTime(Integer.toString(idOfStartingPeriod));
                    chosenPost.setNotAvailableTime(Integer.toString(idOfStartingPeriod + 1));
                    periodSplit = new String[]{periodsList.get(idOfStartingPeriod + 1).toString()};
                }
            }
            case 2 -> {
                boolean flag = true;
                for (int i = idOfStartingPeriod + 1; i < idOfStartingPeriod + 3; i++) {
                    if (chosenPost.getNotAvailableTime().contains(Integer.toString(i))) {
                        System.out.println("there are some reservation after the period chosen");
                        flag = false;
                        break;
                    }
                }
                if (flag) {

                    for (int i = idOfStartingPeriod; i <= idOfStartingPeriod + 2; i++) {
                        if (i > 18) {
                            System.out.println("we can't provide this duration we will close soon");
                            AddClient();
                            break;
                        }


                        periodSplit = new String[]{periodsList.get(i).toString(),"2"};
                        chosenPost.setNotAvailableTime(Integer.toString(i));
                    }


                }
            }
            case 3 -> {
                boolean flag = true;
                for (int i = idOfStartingPeriod + 1; i < idOfStartingPeriod + 5; i++) {
                    if (chosenPost.getNotAvailableTime().contains(Integer.toString(i))) {
                        System.out.println("there are some reservation after the period chosen");
                        flag = false;
                        break;
                    }
                }
                if (flag) {

                    for (int i = idOfStartingPeriod; i <= idOfStartingPeriod + 4; i++) {
                        if (i > 18) {
                            System.out.println("we can't provide this duration we will close soon");
                            AddClient();
                            break;
                        }

                        periodSplit = new String[]{periodsList.get(i).toString(),"2","2"};
                        chosenPost.setNotAvailableTime(Integer.toString(i));
                    }

                }

            }
            case 4 -> {
                boolean flag = true;

                for (int i = idOfStartingPeriod + 1; i < idOfStartingPeriod + 11; i++) {
                    if (chosenPost.getNotAvailableTime().contains(Integer.toString(i))) {
                        System.out.println("there are some reservation after the period chosen");
                        flag = false;
                        break;
                    }

                }
                if (flag) {

                    System.out.println("hello dear");

                    for (int i = idOfStartingPeriod; i <= idOfStartingPeriod + 9; i++) {
                        if (i > 18) {
                            System.out.println("we can't provide this duration we will close soon");
                            AddClient();
                            break;
                        }
                        periodSplit = new String[]{periodsList.get(i).toString(),"2","2","2"};
                        chosenPost.setNotAvailableTime(periodsList.get(i).toString());


                    }

                }


            }
            case 5 -> {
                if (chosenPost.getNotAvailableTime().size() != 0) {
                    System.out.println("there are other reservations on this post, you can't reserve this post for the whole day");
                    break;
                } else {
                    for (int i = 0; i < periodsList.size(); i++) {
                        chosenPost.setNotAvailableTime(periodsList.get(i).toString());
                        periodSplit =new String[]{periodsList.get(i).toString(),"2","2","2","2"};
                    }
                }
            }
        }
    }











}






