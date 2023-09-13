package com.st.modules.toilet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.toilet.dto.AddToiletDto;
import com.st.modules.toilet.dto.DeleteToiletDto;
import com.st.modules.toilet.dto.RefreshAirDto;
import com.st.modules.toilet.dto.UpdateToiletDto;
import com.st.modules.toilet.model.Toilet;

import java.util.List;

public interface ToiletService extends IService<Toilet>  {
    void addToilet(AddToiletDto addToiletDto);
    List<Toilet> getToiletList();
    void deleteToilet(DeleteToiletDto deleteToiletDto);
    void updateToilet(UpdateToiletDto updateToiletDto);
    void refreshAir(RefreshAirDto refreshAirDto);
}
