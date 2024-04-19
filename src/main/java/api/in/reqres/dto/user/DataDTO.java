
package api.in.reqres.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@EqualsAndHashCode(exclude = {"id"})
public class DataDTO {
    private String avatar;
    private String email;
    private String firstName;
    private Long id;
    private String lastName;
}
