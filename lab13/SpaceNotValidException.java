package lab13;

public class SpaceNotValidException extends IllegalArgumentException{
    public SpaceNotValidException() {
        super();
    }

    public SpaceNotValidException(String s){
        super(s);
    }

    public SpaceNotValidException(String s, Throwable cause) {
        super(s, cause);
    }

    public SpaceNotValidException(Throwable cause) {
        super(cause);
    }
}