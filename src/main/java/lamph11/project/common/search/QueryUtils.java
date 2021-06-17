package lamph11.project.common.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class QueryUtils {

    public static <T> Specification<T> buildSpecification(Object filter) throws IllegalAccessException {
        Class<?> filterClass = filter.getClass();
        Field[] filterFields = filterClass.getDeclaredFields();

        List<Specification<T>> specifications = new LinkedList<>();
        for (Field field : filterFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(filter);
            if (!(fieldValue instanceof SearchFilter))
                continue;
            SearchFilter searchFilter = (SearchFilter) fieldValue;
            Optional.ofNullable(searchFilter.<T>buildSpecification(field.getName()))
                    .ifPresent(specifications::add);
        }
        return specifications.stream().reduce(Specification::and).orElse(
                (root, query, builder) -> builder.conjunction()
        );
    }

//    public static <T> Specification<T> buildSpecification(String queryString) {
//        if (queryString == null || queryString.isEmpty())
//            return Specification.where(
//                    (root, query, builder) -> builder.conjunction()
//            );
//        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?),");
//        Matcher matcher = pattern.matcher(queryString + ",");
//
//        Specification<T> specification = Specification.where(null);
//        while (matcher.find()) {
//            specification = specification.and(
//                    buildSpecification(matcher.group(1), matcher.group(2), matcher.group(3))
//            );
//        }
//        return specification;
//    }

    private static <T> Specification<T> buildSpecification(final String field, final String operation, final String value) {
        assert field != null && !field.isEmpty();
        assert operation != null && !operation.isEmpty();
        assert value != null && !value.isEmpty();
        return (root, query, builder) -> {
            switch (operation) {
                case ":": {
                    return builder.equal(root.get(field), value);
                }
                case ">": {
                    return builder.greaterThan(root.get(field), value);
                }
                case "<": {
                    return builder.lessThan(root.get(field), value);
                }
                default: {
                    return builder.conjunction();
                }
            }
        };
    }
}
