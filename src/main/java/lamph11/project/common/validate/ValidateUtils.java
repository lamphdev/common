package lamph11.project.common.validate;

import lamph11.project.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
@AllArgsConstructor
public class ValidateUtils {

    private final LocalValidatorFactoryBean localValidatorFactoryBean;

    public <T> void validate(T object, Class<?>... group) throws ServiceException {
        Set<ConstraintViolation<T>> violations = localValidatorFactoryBean.validate(object, group);
        if (!violations.isEmpty()) {
            throw ServiceException.fromValidateViolation(violations);
        }
    }
}
