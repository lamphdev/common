package lamph11.project.common.search;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Data
public class NumberFilter implements SearchFilter {

    private Double equals;
    private Double greaterThan;
    private Double lessThan;
    private Double greaterThanOrEquals;
    private Double lessThanOrEquals;
    private List<Double> in;


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
        if (in != null && !in.isEmpty())
            specification.and((root, query, builder) -> root.get(field).in(in));
        return specification;
    }
}
