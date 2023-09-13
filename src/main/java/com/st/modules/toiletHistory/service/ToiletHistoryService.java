package com.st.modules.toiletHistory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.toiletHistory.model.ToiletHistory;

import java.util.List;

public interface ToiletHistoryService extends IService<ToiletHistory> {
    List<ToiletHistory> getToiletHistory(int toiletId);
}
