package ElectronicStore.EStore.Exceptions;

import lombok.Builder;

@Builder
public class CustomResourceNotFoundExceptions extends RuntimeException{

    public CustomResourceNotFoundExceptions(){
        super("Resource is unavailable!!");
    }

    public CustomResourceNotFoundExceptions(String message){
        super(message);
    }
}
