package com.company.command;

import com.company.Operation;
import com.company.exception.InterruptOperationException;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandExecutor
{
    public static Map<Operation, Command> executeMap = new HashMap<>();
    static {
        executeMap.put(Operation.LOGIN, new LoginCommand());
        executeMap.put(Operation.INFO, new InfoCommand());
        executeMap.put(Operation.DEPOSIT, new DepositCommand());
        executeMap.put(Operation.WITHDRAW, new WithdrawCommand());
        executeMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        executeMap.get(operation).execute();
    }
}