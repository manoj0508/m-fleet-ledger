package com.fleet.ledger.app.utils;


import java.util.Collection;

public class Utils {

    public static boolean isNotEmpty(Collection obj){
        return null != obj && !obj.isEmpty();
    }

    public static boolean isNotNUll(Object obj){
        return null != obj;
    }
}
