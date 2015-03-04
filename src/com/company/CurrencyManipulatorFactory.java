package com.company;

import java.util.*;


public class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> mapManipulator = new HashMap<>();
    private CurrencyManipulatorFactory(){}
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        if(mapManipulator.containsKey(currencyCode))
            return mapManipulator.get(currencyCode);
        else
        {
            CurrencyManipulator result = new CurrencyManipulator(currencyCode);
            mapManipulator.put(currencyCode, result);
            return result;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return mapManipulator.values();
    }
}
