package com.st.modules.admin.vo;

import com.st.modules.user.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetUserListVo {
    @NotBlank
    private Integer page;

    @NotBlank
    private Integer pageSize;

    @NotBlank
    private Integer total;

    @NotBlank
    private List<User> list;
}
