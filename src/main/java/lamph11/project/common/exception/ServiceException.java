package lamph11.project.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Set<ConstraintViolation> violations;

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(ConstraintViolation... violations) {
        super("VALIDATE_EXCEPTION");
        this.violations = new HashSet<>(Arrays.asList(violations));
    }

    public static <T> ServiceException fromValidateViolation(
            Set<javax.validation.ConstraintViolation<T>> violations
    ) {
        ConstraintViolation[] constraintViolations = violations.stream().map(
                item -> new ConstraintViolation(
                        item.getPropertyPath().toString(),
                        item.getConstraintDescriptor().getAnnotation().getClass().getSimpleName(),
                        item.getConstraintDescriptor().getAttributes(),
                        item.getInvalidValue()
                )
        ).collect(Collectors.toList()).toArray(new ConstraintViolation[violations.size()]);
        return new ServiceException(constraintViolations);
    }


}
