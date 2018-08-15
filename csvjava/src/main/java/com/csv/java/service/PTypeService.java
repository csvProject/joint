package com.csv.java.service;






import com.csv.java.entity.PTypeDto;

import java.util.List;

public interface PTypeService {
    List<PTypeDto> findPTypeByCondi(PTypeDto indto);

    List<PTypeDto> findPTypeByCodenm(String cdnm);
}
