package kr.co.sloop.study.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryRegionDTO {
    private int categoryRegionTier;
    private String categoryRegionName;
    private String categoryRegionCode;
    private String categoryRegionParent;
}
