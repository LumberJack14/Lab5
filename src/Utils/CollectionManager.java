package Utils;

import Collection.*;
import Exceptions.IncorrectDataException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

public class CollectionManager {
    private static volatile CollectionManager instance;
    private Vector<Dragon> collection;
    private String path;

    private CollectionManager() {

    }

    public static CollectionManager getInstance() {
        CollectionManager result = instance;
        if (result == null) {
            synchronized (CollectionManager.class) {
                result = instance;
                if (result == null) {
                    instance = result = new CollectionManager();
                }
            }
        }

        return result;
    }

    public void initializeCollection(String path, File file) {
        Vector<Dragon> collection = ParseJSONFile.parse(path, file);
        System.out.println("Collection initialized.");
        setCollection(collection);
        this.path = path;
    }

    public Vector<Dragon> getCollection() {
        return collection;
    }

    public void setCollection(Vector<Dragon> collection) {
        this.collection = collection;
    }

    public Dragon generateNewDragon() {
        CollectionManager collectionManager = CollectionManager.getInstance();

        Vector<Dragon> collection = collectionManager.getCollection();

        String dragonName = (String) InputHandler.validate(ValidationType.DRAGON_NAME);
        Long dragonAge = (Long) InputHandler.validate(ValidationType.DRAGON_AGE);
        String dragonDesc = (String) InputHandler.validate(ValidationType.DRAGON_DESC);

        DragonType dragonType;
        Object temp = InputHandler.validate(ValidationType.DRAGON_TYPE);
        if (temp == null) {
            dragonType = null;
        } else {
            dragonType = DragonType.valueOf((String) temp);
        }
        DragonCharacter dragonCharacter = DragonCharacter.valueOf((String) InputHandler.validate(ValidationType.DRAGON_CHARACTER));
        Double dragonX = (Double) InputHandler.validate(ValidationType.DRAGON_COORDINATE_X);
        Float dragonY = (Float) InputHandler.validate(ValidationType.DRAGON_COORDINATE_Y);


        String killerName = (String) InputHandler.validate(ValidationType.KILLER_NAME);
        String killerPassportID = (String) InputHandler.validate(ValidationType.KILLER_PASSPORT_ID);
        Integer killerWeight = (Integer) InputHandler.validate(ValidationType.KILLER_WEIGHT);
        Integer killerX = (Integer) InputHandler.validate(ValidationType.KILLER_LOCATION_X);
        Long killerY = (Long) InputHandler.validate(ValidationType.KILLER_LOCATION_Y);
        Float killerZ = (Float) InputHandler.validate(ValidationType.KILLER_LOCATION_Z);
        String killerLocationName = (String) InputHandler.validate(ValidationType.KILLER_LOCATION_NAME);

        Coordinates coordinates = new Coordinates(dragonX, dragonY);
        Location killerLocation = new Location(killerX, killerY, killerZ, killerLocationName);
        Person killer = new Person(killerName, killerWeight, killerPassportID, killerLocation);

        long id = collection.size();
        LocalDate creationDate = LocalDate.now();

        Dragon dragon = new Dragon(id, dragonName, coordinates, creationDate, dragonAge, dragonDesc, dragonType, dragonCharacter, killer);

        return dragon;
    }


    public Dragon customGenerateNewDragon(BufferedReader bufferedReader) {
        CollectionManager collectionManager = CollectionManager.getInstance();

        Vector<Dragon> collection = collectionManager.getCollection();
        Dragon dragon = null;

        try {

            String dragonName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_NAME);
            Long dragonAge = (Long) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_AGE);
            String dragonDesc = (String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_DESC);

            DragonType dragonType;
            Object temp = InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_TYPE);
            if (temp == null) {
                dragonType = null;
            } else {
                dragonType = DragonType.valueOf((String) temp);
            }
            DragonCharacter dragonCharacter = DragonCharacter.valueOf((String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_CHARACTER));
            Double dragonX = (Double) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_COORDINATE_X);
            Float dragonY = (Float) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_COORDINATE_Y);


            String killerName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_NAME);
            String killerPassportID = (String) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_PASSPORT_ID);
            Integer killerWeight = (Integer) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_WEIGHT);
            Integer killerX = (Integer) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_X);
            Long killerY = (Long) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_Y);
            Float killerZ = (Float) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_Z);
            String killerLocationName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_NAME);

            Coordinates coordinates = new Coordinates(dragonX, dragonY);
            Location killerLocation = new Location(killerX, killerY, killerZ, killerLocationName);
            Person killer = new Person(killerName, killerWeight, killerPassportID, killerLocation);

            long id = collection.size();
            LocalDate creationDate = LocalDate.now();

            dragon = new Dragon(id, dragonName, coordinates, creationDate, dragonAge, dragonDesc, dragonType, dragonCharacter, killer);
        } catch (IncorrectDataException exception) {
            System.out.println("Entry data in the script doesn't fit the requirements. Raised Error: \n" + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("Error: exception while reading script (IOException):\n" + exception.getMessage());
        }

        return dragon;
    }

    public String getPath() {
        return path;
    }

}
