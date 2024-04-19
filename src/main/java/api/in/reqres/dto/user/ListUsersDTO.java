
package api.in.reqres.dto.user;

import api.in.reqres.dto.SupportDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ListUsersDTO {
    private List<DataDTO> data;
    private Long page;
    private Long perPage;
    private SupportDTO supportDTO;
    private Long total;
    private Long totalPages;
}
