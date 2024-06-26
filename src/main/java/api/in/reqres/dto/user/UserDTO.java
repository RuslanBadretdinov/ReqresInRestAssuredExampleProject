
package api.in.reqres.dto.user;

import api.in.reqres.dto.SupportDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDTO {
    private DataDTO data;
    private SupportDTO support;
}
