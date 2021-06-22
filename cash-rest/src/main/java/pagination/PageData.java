package pagination;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PageData implements Serializable {

    private Integer page;
    private Integer size;
    private Long total;

}
