package com.ewiderbuy.produce.service;






import com.ewiderbuy.produce.entity.PTypeDto;

import java.util.List;

public interface PTypeService {
    List<PTypeDto> findPTypeByCondi(PTypeDto indto);

    List<PTypeDto> findPTypeByCodenm(String cdnm);
}
