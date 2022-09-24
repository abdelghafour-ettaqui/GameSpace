package playStation;

import java.util.ArrayList;

public class GameType {

    private String type;

    private String[] GameNames;
    private int[] Posts ;


    public GameType(String[] gameNames,String type,int[] Posts){

        this.GameNames=gameNames;

        this.type=type;

        this.Posts=Posts;


    }

    public int[] getPosts() {
        return Posts;
    }

    public void setPosts(int[] posts) {
        Posts = posts;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String[] getGameNames() {
        return GameNames;
    }

    public void setGameNames(String[] gameNames) {
        GameNames = gameNames;
    }


//    @Override
//    public String toString() {
//        return "firstname: " + this.firstname() + " | lastname: " + this.lastname();
//    }



}
