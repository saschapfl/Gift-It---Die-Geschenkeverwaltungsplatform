package dhbw.se.giftit.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Stateless
public class ValidationBean {
    
    @Resource
    Validator val;
    
    public <T> List<String> validate(T object) {
        List<String> errormessages = new ArrayList<>();
        return this.validate(object, errormessages);
    }

    public <T> List<String> validate(T object, List<String> errormessages) {
        Set<ConstraintViolation<T>> viol = this.val.validate(object);
        
        viol.forEach((ConstraintViolation<T> violat) -> {
            errormessages.add(violat.getMessage());
        });
        
        return errormessages;
    }
}

