package com.test.amz;

public class WarehouseCountPossibleSegments {
    /**
     * k=3
     * weights=[1,3,6]
     * output = 5
     * (0,0)->max(weight[0])-min(weight[0]) = max(1)-min(1)=0
     * (0,1)->max(1,3)-min(1,3) = 3-1=2
     * (0,2)->max(1,3,6)-min(1,3,6) = 6-1=5>3  error
     * (1,1)...
     * (1,2)...
     * (2,2)...
     */
}
