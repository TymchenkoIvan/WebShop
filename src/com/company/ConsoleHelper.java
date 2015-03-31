package com.company;

import com.company.exception.InterruptOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static void printExitMessage()
    {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException
    {
        String line = null;
        try{
            line = reader.readLine();
            if ("EXIT".equalsIgnoreCase(line))
                throw new InterruptOperationException();
        }
        catch (IOException e){
            writeMessage(e.getMessage());
        }
        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
        String console = readString();
        while(console.length() != CashMachine.MONEY_LENGHT) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
            console = readString();
        }
        console = console.toUpperCase();
        return console;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String console = readString().trim();
        String[] consBuff = console.split(" ");
        while(Integer.parseInt(consBuff[0]) <= 0 && Integer.parseInt(consBuff[1]) <= 0) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
            console = readString().trim();
            consBuff = console.split(" ");
        }
        return consBuff;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        ConsoleHelper.writeMessage(res.getString("operation.INFO"));
        ConsoleHelper.writeMessage(res.getString("operation.DEPOSIT"));
        ConsoleHelper.writeMessage(res.getString("operation.WITHDRAW"));
        ConsoleHelper.writeMessage(res.getString("operation.EXIT"));
        Operation operation = null;

        while (operation == null) {
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                operation = null;
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
        return operation;
    }
}
