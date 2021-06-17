package lamph11.project.common.search;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class StringFilter implements SearchFilter {

    private String equals;
    private String contains;
    private String notEquals;

    @Override
    public <T> Specification<T> buildSpecification(String field) {
        if (equals != null)
            return (root, query, builder) -> builder.equal(
                    root.get(field), equals
            );
        if (notEquals != null)
            return (root, query, builder) -> builder.notEqual(
                    root.get(field), equals
            );
        if (contains != null)
            return (root, query, builder) -> builder.like(
                    root.get(field),
                    contains
            );
        return null;
    }
}
