package com.csv.java.service;



import com.csv.java.entity.CsvCustmerFieldDto;

import java.util.List;

public interface CsvCustmerFieldService {
    List<CsvCustmerFieldDto> findCsvCustmerField(int csvtempId);

}
