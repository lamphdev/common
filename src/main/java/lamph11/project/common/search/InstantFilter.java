package lamph11.project.common.search;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

@Data
public class InstantFilter implements SearchFilter {

    private Instant equals;
    private Instant greaterThan;
    private Instant lessThan;
    private Instant greaterThanOrEquals;
    private Instant lessThanOrEquals;

    @Override
    public <T> Specification<T> buildSpecification(String field) {
        Specification<T> specification = Specification.where(null);
        if (equals != null)
            specification = specification.and((root, query, builder) -> builder.equal(
                    root.get(field), equals
            ));
        if (greaterThan != null)
            specification = specification.and((root, query, builder) -> builder.greaterThan(
                    root.get(field), greaterThan
            ));
        if (lessThan != null)
            specification = specification.and((root, query, builder) -> builder.lessThan(
                    root.get(field), lessThan
            ));
        if (greaterThanOrEquals != null)
            specification = specification.and((root, query, builder) -> builder.greaterThanOrEqualTo(
                    root.get(field), greaterThanOrEquals
            ));
        if (lessThanOrEquals != null)
            specification = specification.and((root, query, builder) -> builder.lessThanOrEqualTo(
                    root.get(field), lessThanOrEquals
            ));
        return specification;
    }
}
