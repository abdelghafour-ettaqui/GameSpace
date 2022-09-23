package playStation;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JsonFile {

   public static  JSONParser parser = new JSONParser();
    public static String  filePath="C:\\java\\playStation\\dataFile.json";
   public static  ArrayList<Object> periodsList = new ArrayList<Object>();




    public static ArrayList<Object> period(){
        try (FileReader reader = new FileReader(filePath))
        {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray periods = (JSONArray) jsonObject.get("periods");

            if (periods != null) {
                for (int i=0;i<periods.size();i++){
                    periodsList.add(periods.get(i));
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return periodsList;
    }

}
