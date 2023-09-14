package ElectronicStore.EStore.Helper;

import ElectronicStore.EStore.DataTransferObject.PageableResponse;
import ElectronicStore.EStore.DataTransferObject.UserDTO;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static <U,V> PageableResponse<V> getPageableResponse(Page<U>page,Class<V> type){
        List<U>entity = page.getContent();
        List<V> dtoList = entity.stream().map(object -> new ModelMapper().map(object,type)).collect(Collectors.toList());
        PageableResponse<V> resp = new PageableResponse<>();
        resp.setContent(dtoList);
        resp.setPageSize(page.getSize());
        resp.setPageNumber(page.getNumber());
        resp.setTotalElements(page.getTotalElements());
        resp.setTotalPages(page.getTotalPages());
        resp.setLastPage(page.isLast());
        return resp;
    }
}
