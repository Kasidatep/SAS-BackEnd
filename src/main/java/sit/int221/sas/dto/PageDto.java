package sit.int221.sas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private List<T> content;
    private boolean first;
    private boolean last;
    private Integer totalPages;
    private Integer totalElements;
    private Integer size;
    @JsonIgnore
    private Integer number;

    public Integer getPage(){
        return number;
    }

}
