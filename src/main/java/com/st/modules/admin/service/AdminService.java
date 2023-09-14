package com.st.modules.admin.service;

import com.st.modules.admin.dto.UpdatePermissionDto;
import com.st.modules.admin.vo.GetDeviceListVo;
import com.st.modules.admin.vo.GetFeedbackListVo;
import com.st.modules.admin.vo.GetUserListVo;

public interface AdminService {
    GetFeedbackListVo getFeedbackList(int page, int pageSize);
    GetDeviceListVo getDeviceList(int page, int pageSize);
    GetUserListVo getUserList(int page, int pageSize);
    void updatePermission(UpdatePermissionDto updatePermissionDto);
}
