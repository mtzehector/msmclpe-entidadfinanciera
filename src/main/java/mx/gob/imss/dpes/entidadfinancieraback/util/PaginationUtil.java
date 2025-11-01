package mx.gob.imss.dpes.entidadfinancieraback.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public final class PaginationUtil {
    private PaginationUtil(){}
    public static <T> Page<T> paginateList(final Pageable pageable, List<T> list) {
        int first = pageable.getPageNumber() * pageable.getPageSize();
        int last = first + pageable.getPageSize();
        if (last >= list.size()) {
            last = list.size();
        } else if (pageable.getPageSize() > list.size() && pageable.getPageNumber() > 1) {
            last = list.size();
        }
        return new PageImpl<>(list.subList(first, last), pageable, list.size());
    }
}