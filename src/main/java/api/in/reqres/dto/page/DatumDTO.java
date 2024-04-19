
package api.in.reqres.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatumDTO {
    private String mColor;
    private Long mId;
    private String mName;
    private String mPantoneValue;
    private Long mYear;
}
