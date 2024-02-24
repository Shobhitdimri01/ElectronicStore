package ElectronicStore.EStore.Exceptions;

import ElectronicStore.EStore.DataTransferObject.APIResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    //handler for CustomResourceNotFoundException

   @ ExceptionHandler(CustomResourceNotFoundExceptions.class)
    public ResponseEntity<APIResponseMessage> CustomResNotFound(CustomResourceNotFoundExceptions ex){
        ArrayList message = new ArrayList<>();
       message.add("Error : \n");
        message.add(ex.getMessage());
        APIResponseMessage resp = APIResponseMessage.builder().message(message).status(HttpStatus.NOT_FOUND).success(false).build();
        return new ResponseEntity(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object> response = new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String defaultMessage = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field,defaultMessage);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ ExceptionHandler(BadAPIRequest.class)
    public ResponseEntity<APIResponseMessage> HandleBadAPIReq(CustomResourceNotFoundExceptions ex){
        ArrayList message = new ArrayList<>();
        message.add("Error : \n");
        message.add(ex.getMessage());
        APIResponseMessage resp = APIResponseMessage.builder().message(message).status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }

}
