package com.st.modules.admin.vo;

import com.st.modules.deviceData.model.DeviceData;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetDeviceListVo {
    @NotBlank
    private Integer page;

    @NotBlank
    private Integer pageSize;

    @NotBlank
    private Integer total;

    @NotBlank
    private List<DeviceData> list;
}
