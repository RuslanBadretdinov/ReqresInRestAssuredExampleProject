
package api.in.reqres.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {
    private String mAvatar;
    private String mEmail;
    private String mFirstName;
    private Long mId;
    private String mLastName;
}
