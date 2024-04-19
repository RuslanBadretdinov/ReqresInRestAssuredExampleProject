
package api.in.reqres.dto.user.crud;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class UserFormRespDTO {
    private String createdAt;
    private String id;
    private String job;
    private String name;
}
