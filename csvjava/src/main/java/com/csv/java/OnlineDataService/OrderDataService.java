package com.csv.java.OnlineDataService;



import com.csv.java.entity.OrderDto;

import java.util.List;

public interface OrderDataService {

    public void generateOrderDataFromMagento(int limitcount);

    public void testTransactional();

}
