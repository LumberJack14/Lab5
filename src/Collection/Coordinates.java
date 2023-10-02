package Collection;

/**
 * Class with Coordinates type definition
 */

public class Coordinates {


    private Double x; //Значение поля должно быть больше -36, Поле не может быть null


    private Float y; //Поле не может быть null

    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }


    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public static boolean isValidX(Double x) {
        return x > -36d;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}