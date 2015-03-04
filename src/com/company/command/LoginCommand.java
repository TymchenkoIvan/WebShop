package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login_en");
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String userCreditCardNumber = ConsoleHelper.readString().trim();
            if (userCreditCardNumber == null || userCreditCardNumber.length() != 12){
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            String userPin = ConsoleHelper.readString().trim();
            if (userPin == null || userPin.length() != 4){
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            if (validCreditCards.containsKey(userCreditCardNumber) && validCreditCards.getString(userCreditCardNumber).equals(userPin)){
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), userCreditCardNumber));
                break;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCreditCardNumber));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
