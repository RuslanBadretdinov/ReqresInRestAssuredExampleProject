
package api.in.reqres.dto.user;

import api.in.reqres.dto.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private DataDTO mData;
    private SupportDTO mSupport;
}
