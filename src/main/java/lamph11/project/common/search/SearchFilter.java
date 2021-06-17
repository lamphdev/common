package lamph11.project.common.search;

import org.springframework.data.jpa.domain.Specification;

public interface SearchFilter {

    <T> Specification<T> buildSpecification(String field);
}
