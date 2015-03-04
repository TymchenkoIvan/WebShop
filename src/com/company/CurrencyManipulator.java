package com.company;

import com.company.exception.NotEnoughMoneyException;
import java.util.*;

public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public  void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination)){
            denominations.put(denomination,denominations.get(denomination)+count);
        }else{
            denominations.put(denomination,count);
        }
    }

    public int getTotalAmount(){
        int summ = 0;
        for(Map.Entry<Integer, Integer> entry : denominations.entrySet())
            summ += entry.getKey() * entry.getValue();

        return summ;
    }

    public boolean hasMoney(){
        return denominations.size()!=0;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return getTotalAmount()>=expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        int withdrawMoney = expectedAmount;
        /**
         * создаем 2 "дерева" с сортировкой от больего к меньшему. В одно копируем denominations. Во втором
         * храним номиналы и банкноты отобранные жадным алгоритмом для выдачи денег
         */
        Map<Integer, Integer> moneyOff = new TreeMap<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return (o1 > o2) ? -1 : ((o1.equals(o2)) ? 0 : 1);
            }
        });
        Map<Integer, Integer> banknotesAll = new TreeMap<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return (o1 > o2) ? -1 : ((o1.equals(o2)) ? 0 : 1);
            }
        });
        banknotesAll.putAll(denominations);
        /**
         * реализация жадного алгоритма
         */
        for(Map.Entry<Integer, Integer> entry : banknotesAll.entrySet())
        {
            int par = entry.getKey(); //номинал банкнот
            int amount = entry.getValue(); //количество банкнот

            if(withdrawMoney == 0) //если подобрали банкноты к выдаче в 0, то заканчиваем все итерации
                    break;
            if(amount==0) continue; //если банкнот этого поминала нет, пропускаем итерацию

            int count = withdrawMoney/par; // результат деления денег которые осталось выдать на текущий номинал(245/100 = 2)
            if(count > 0){ // если денег надо выдать больше чем номинал
                if(count<=amount){ // если количестуо банкнот достаточно в наличии
                    moneyOff.put(par, count);
                    withdrawMoney -= par*count;
                }
                else{   // если количестуо банкнот достаточно в наличии, то выдаем все имеющиеся
                     moneyOff.put(par, amount);
                     withdrawMoney -= par*amount;
                    }
                }
        }

        if(withdrawMoney!=0)// если мы не можем выдать необходимую сумму изи-за недостатка банкнот, а не денег
            throw new NotEnoughMoneyException();
        else // если все ок, то удаляем банкноты для выдачи из denominations
            for(Map.Entry<Integer, Integer> entry : moneyOff.entrySet())
                denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());

        return moneyOff;
    }
}
