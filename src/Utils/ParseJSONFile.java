package Utils;

import Collection.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.time.LocalDate;
import java.util.Vector;

/**
 * Class that reads JSON representation of the collection and puts in into Vector collection
 */

public class ParseJSONFile {
    public static Vector<Dragon> parse(String path, File file) {
        Vector<Dragon> collection = new Vector<>();

        if (file.length() == 0) {
            return collection;
        }

        JSONParser parser = new JSONParser();

        try {
            FileReader inputStreamReader = new FileReader(path);
            Object obj = parser.parse(inputStreamReader);
            JSONObject jsonObject = (JSONObject) obj;


            JSONArray JSONDragons = (JSONArray) jsonObject.get("dragons");
            for (Object dragon : JSONDragons) {
                JSONObject jsonDragon = (JSONObject) dragon;

                JSONObject jsonCoordinates = (JSONObject) jsonDragon.get("coordinates");
                Double x = ((Number)jsonCoordinates.get("x")).doubleValue();
                Float y = ((Number)jsonCoordinates.get("y")).floatValue();

                JSONObject jsonKiller = (JSONObject) jsonDragon.get("killer");
                JSONObject jsonKillerLoc = (JSONObject) jsonKiller.get("location");

                Location killerLoc = new Location(
                        ((Number)jsonKillerLoc.get("x")).intValue(),
                        ((Number)jsonKillerLoc.get("y")).longValue(),
                        ((Number)jsonKillerLoc.get("z")).floatValue(),
                        (String) jsonKillerLoc.get("name")
                );

                Person killa = new Person(
                        (String) jsonKiller.get("name"),
                        ((Number)jsonKiller.get("weight")).intValue(),
                        (String) jsonKiller.get("passportID"),
                        killerLoc
                );

                DragonType dt = jsonDragon.get("type") == ""? null : DragonType.valueOf((String) jsonDragon.get("type"));

                Dragon res = new Dragon(
                        ((Number)jsonDragon.get("id")).longValue(),
                        (String) jsonDragon.get("name"),
                        new Coordinates(x, y),
                        LocalDate.parse((String) jsonDragon.get("creationDate")),
                        ((Number)jsonDragon.get("age")).longValue(),
                        (String) jsonDragon.get("description"),
                        dt,
                        DragonCharacter.valueOf((String) jsonDragon.get("character")),
                        killa
                );

                collection.add(res);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while parsing json: \n" + e.getMessage());
            e.printStackTrace();
        }

        return collection;
    }

}
