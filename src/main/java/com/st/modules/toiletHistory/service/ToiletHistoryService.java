package com.st.modules.toiletHistory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.toiletHistory.model.ToiletHistory;
import com.st.modules.toiletHistory.vo.GetToiletHistoryVo;

import java.util.List;

public interface ToiletHistoryService extends IService<ToiletHistory> {
    GetToiletHistoryVo getToiletHistory(int toiletId, int page, int pageSize);
}
