
package api.in.reqres.dto.page;

import api.in.reqres.dto.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data  // Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
@Builder // All constructors
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private List<DatumDTO> mData;
    private Long mPage;
    private Long mPerPage;
    private SupportDTO mSupport;
    private Long mTotal;
    private Long mTotalPages;
}
