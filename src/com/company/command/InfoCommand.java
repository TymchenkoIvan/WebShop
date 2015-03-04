package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.CurrencyManipulator;
import com.company.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"info_en");
    @Override
    public void execute()
    {
        boolean money = false;
        ConsoleHelper.writeMessage(res.getString("before"));

        for(CurrencyManipulator cur: CurrencyManipulatorFactory.getAllCurrencyManipulators())
            if(cur.hasMoney())
                money = true;

        if(!money)
            ConsoleHelper.writeMessage(res.getString("no.money"));
        else
            for(CurrencyManipulator cur: CurrencyManipulatorFactory.getAllCurrencyManipulators())
                ConsoleHelper.writeMessage(cur.getCurrencyCode() + " - " + cur.getTotalAmount());
    }
}