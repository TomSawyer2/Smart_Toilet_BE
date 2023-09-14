package com.st.modules.toiletHistory.vo;

import com.st.modules.toiletHistory.model.ToiletHistory;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetToiletHistoryVo {
    @NotBlank
    private Integer page;

    @NotBlank
    private Integer pageSize;

    @NotBlank
    private Integer total;

    private List<ToiletHistory> list;
}
