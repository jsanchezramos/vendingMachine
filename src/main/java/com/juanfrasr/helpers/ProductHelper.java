package com.juanfrasr.helpers;


public class ProductHelper {

    private static ProductHelper instance;

    public static synchronized ProductHelper getInstance(){
        if(instance == null){
            instance = new ProductHelper();
        }
        return instance;
    }


}
