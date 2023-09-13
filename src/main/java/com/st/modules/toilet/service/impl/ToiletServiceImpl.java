package com.st.modules.toilet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.exception.Asserts;
import com.st.modules.toilet.dto.AddToiletDto;
import com.st.modules.toilet.dto.DeleteToiletDto;
import com.st.modules.toilet.dto.RefreshAirDto;
import com.st.modules.toilet.dto.UpdateToiletDto;
import com.st.modules.toilet.mapper.ToiletMapper;
import com.st.modules.toilet.model.Toilet;
import com.st.modules.toilet.service.ToiletService;
import com.st.modules.toiletHistory.mapper.ToiletHistoryMapper;
import com.st.modules.toiletHistory.model.ToiletHistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToiletServiceImpl extends ServiceImpl<ToiletMapper, Toilet> implements ToiletService {
    @Autowired
    ToiletMapper toiletMapper;

    @Autowired
    ToiletHistoryMapper toiletHistoryMapper;

    @Override
    public void addToilet(AddToiletDto addToiletDto) {
        Toilet toilet = new Toilet();
        BeanUtils.copyProperties(addToiletDto, toilet);
        toilet.setUpdateTime(new Date());
        toiletMapper.insert(toilet);
    }

    @Override
    public List<Toilet> getToiletList() {
        return toiletMapper.selectList(null);
    }

    @Override
    public void deleteToilet(DeleteToiletDto deleteToiletDto) {
        // 首先判断该厕所是否存在
        Toilet toilet = toiletMapper.selectById(deleteToiletDto.getToiletId());
        if (toilet == null) {
            Asserts.fail(ResultCode.TOILET_NOT_EXIST);
        } else {
            toiletMapper.deleteById(deleteToiletDto.getToiletId());
        }
    }

    @Override
    public void updateToilet(UpdateToiletDto updateToiletDto) {
        // 首先判断该厕所是否存在
        Toilet toiletDB = toiletMapper.selectById(updateToiletDto.getId());
        if (toiletDB == null) {
            Asserts.fail(ResultCode.TOILET_NOT_EXIST);
        } else {
            // 1. 如果有厕所，先将之前的信息保存一份到toilet_history中，如果temperature、humidity、airStatus不为null，才保存
            if (updateToiletDto.getTemperature() == null && updateToiletDto.getHumidity() == null && updateToiletDto.getAirStatus() == null) {
                // 如果没有变化，不保存
            } else {
                ToiletHistory toiletHistory = new ToiletHistory();
                toiletHistory.setToiletId(updateToiletDto.getId());
                toiletHistory.setUpdateTime(new Date());
                toiletHistory.setHumidity(updateToiletDto.getHumidity());
                toiletHistory.setTemperature(updateToiletDto.getTemperature());
                toiletHistory.setAirStatus(updateToiletDto.getAirStatus());
                toiletHistory.setName(updateToiletDto.getName());
                toiletHistoryMapper.insert(toiletHistory);
            }
            // 2. 再更新toilet表
            Toilet toilet = new Toilet();
            BeanUtils.copyProperties(updateToiletDto, toilet);
            toilet.setUpdateTime(new Date());
            toiletMapper.updateById(toilet);
        }
    }

    @Override
    public void refreshAir(RefreshAirDto refreshAirDto) {
        // 首先判断该厕所是否存在
        Toilet toiletDB = toiletMapper.selectById(refreshAirDto.getToiletId());
        if (toiletDB == null) {
            Asserts.fail(ResultCode.TOILET_NOT_EXIST);
        } else {
            // todo: 换气
        }
    }
}
