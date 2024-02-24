package ElectronicStore.EStore.Exceptions;

public class BadAPIRequest extends RuntimeException{
    public BadAPIRequest(String message){
        super(message);
    }

    public BadAPIRequest(){
        super("Bad API or extension not supported");
    }

}
