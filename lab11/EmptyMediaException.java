package lab11;

public class EmptyMediaException extends IllegalArgumentException{
    public EmptyMediaException() {
        super();
    }
    public EmptyMediaException(String s){
        super(s);
    }    
}