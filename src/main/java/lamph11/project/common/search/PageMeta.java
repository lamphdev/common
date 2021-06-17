package lamph11.project.common.search;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PageMeta {

    protected int pageNumber;
    protected int pageSize;
    protected Map<String, String> sort;

    public Pageable buildPage() {
        if (pageNumber <= 0)
            pageNumber = 1;
        if (pageSize <= 0)
            pageSize = 50;

        if (sort == null || sort.isEmpty())
            return PageRequest.of(pageNumber - 1, pageSize);

        List<Sort.Order> orders = new ArrayList<>(sort.size());
        for (String field : sort.keySet()) {
            String directionString = sort.get(field);
            Sort.Direction direction = Sort.Direction.fromString(directionString);
            orders.add(new Sort.Order(direction, field));
        }
        return PageRequest.of(pageNumber - 1, pageSize, Sort.by(orders));
    }
}
