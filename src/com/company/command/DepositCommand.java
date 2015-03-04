package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.CurrencyManipulator;
import com.company.CurrencyManipulatorFactory;
import com.company.exception.InterruptOperationException;

import java.util.ResourceBundle;


class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();

            try {
                String[] values = ConsoleHelper.getValidTwoDigits(code);
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
                int dom = Integer.valueOf(values[0]);
                int count = Integer.valueOf(values[1]);
                manipulator.addAmount(dom, count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), (dom * count), code));
            } catch (NumberFormatException ex) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }

    }
}