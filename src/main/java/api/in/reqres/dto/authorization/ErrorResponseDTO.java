
package api.in.reqres.dto.authorization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ErrorResponseDTO {
    private String error;
}
