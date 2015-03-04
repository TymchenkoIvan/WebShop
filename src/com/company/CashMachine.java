package com.company;

import com.company.command.CommandExecutor;
import com.company.exception.InterruptOperationException;
import java.io.IOException;
import java.util.Locale;

public class CashMachine
{
    public static final String RESOURCE_PATH = "com.company.resources.";
    public static void main(String[] args) throws IOException
    {
        Locale.setDefault(Locale.ENGLISH);
        try{
            CommandExecutor.execute(Operation.LOGIN);

            Operation operation;
            do{
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        }catch (InterruptOperationException e){
        ConsoleHelper.printExitMessage();
        }
    }
}
