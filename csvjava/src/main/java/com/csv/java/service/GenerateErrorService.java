package com.csv.java.service;


import com.csv.java.entity.GenerateErrorDto;

import java.util.List;

public interface GenerateErrorService {

    public void addGenerateError(String orderNumber,String err);

    public void adde(int orderNumber,String err);

    List<GenerateErrorDto> findErrOrderNo(GenerateErrorDto indto);

    public void updDelFlag(int generateErrorId);

}
