package Collection;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class with Dragon type definition. It also implements the comparing of the dragon elements
 */

public class Dragon implements Comparable<Dragon> {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически


    private String name; //Поле не может быть null, Строка не может быть пустой


    private Coordinates coordinates; //Поле не может быть null

    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически


    private Long age; //Значение поля должно быть больше 0, Поле не может быть null


    private String description; //Поле может быть null


    private DragonType type; //Поле может быть null


    private DragonCharacter character; //Поле не может быть null

    private Person killer; //Поле может быть null

    public Dragon(
            long id,
            String name,
            Coordinates coordinates,
            java.time.LocalDate creationDate,
            Long age,
            String description,
            DragonType type,
            DragonCharacter character,
            Person killer
    ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.description = description;
        this.type = type;
        this.character = character;
        this.killer = killer;
    }

    public Dragon() {
    }


    @Override
    public int compareTo(Dragon dragon) {
        if (!Objects.equals(this.age, dragon.getAge())) {
            return (int) (this.age - dragon.getAge());
        }

        if (!Objects.equals(this.creationDate, dragon.getCreationDate())) {
            return this.creationDate.compareTo(dragon.getCreationDate());
        }

        return (this.name.length()) - dragon.getName().length();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public Person getKiller() {
        return killer;
    }

    public void setKiller(Person killer) {
        this.killer = killer;
    }

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("name: " + name);
        System.out.println("age " + age);
        System.out.println("description: " + description);

        String chr = character == null? "null" : character.toString();
        System.out.println("dragon character: " + chr);

        String t = type == null? "null" : type.toString();
        System.out.println("dragon type: " + t);

        System.out.println("Coordinates: (" + coordinates.getX() + ", " + coordinates.getY() + ")");
        System.out.println("Creation date: " + creationDate.toString());
        System.out.println("killer name: " + killer.getName());
        System.out.println("killer's location name: " + killer.getLocation().getName());
        System.out.println();
    }
}