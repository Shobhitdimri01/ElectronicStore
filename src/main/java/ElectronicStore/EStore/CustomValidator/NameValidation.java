package ElectronicStore.EStore.CustomValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameValidation implements ConstraintValidator<NameValidator,String> {

    private Logger Log = LoggerFactory.getLogger(NameValidation.class);
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Log.info("Message from is valid : {} ",s);
        if (s.isBlank()) return false;
        return true;
    }
}
