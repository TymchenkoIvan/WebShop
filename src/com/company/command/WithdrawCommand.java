package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.CurrencyManipulator;
import com.company.CurrencyManipulatorFactory;
import com.company.exception.InterruptOperationException;
import com.company.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        Map<Integer, Integer> moneyOff;
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String withdrawSumm;
        int withdrawSummInt;

        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            withdrawSumm = ConsoleHelper.readString();
            if(withdrawSumm == null || Integer.parseInt(withdrawSumm) <= 0){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            withdrawSummInt =  Integer.parseInt(withdrawSumm);

            if(!currencyManipulator.isAmountAvailable(withdrawSummInt))
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            else
                break;
        }

        try{
            moneyOff = currencyManipulator.withdrawAmount(withdrawSummInt);
            for(Map.Entry<Integer, Integer> entry : moneyOff.entrySet())
                ConsoleHelper.writeMessage("\t"+entry.getKey()+" - "+entry.getValue());
        } catch (NotEnoughMoneyException e){
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }

        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), withdrawSummInt, currencyCode));
    }
}