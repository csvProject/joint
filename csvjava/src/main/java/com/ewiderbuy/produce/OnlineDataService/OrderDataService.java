package com.ewiderbuy.produce.OnlineDataService;



import com.ewiderbuy.produce.entity.OrderDto;

import java.util.List;

public interface OrderDataService {

    public void generateOrderDataFromMagento(int limitcount);

    public void testTransactional();

}
