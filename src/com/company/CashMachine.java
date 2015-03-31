package com.company;

import com.company.command.CommandExecutor;
import com.company.exception.InterruptOperationException;
import java.io.IOException;
import java.util.Locale;

public class CashMachine
{
    public static final String RESOURCE_PATH = "com.company.resources.";
    public static final int MONEY_LENGHT = 3;
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
/*ЗЫ:
        Больше всего не понраилось в коде форматирование. Это просто жесть.
        Посмотри примеры правильного форматирования кода например тут: https://github.com/spring-projects/spring-integration-samples
        И почитай(хотя бы по диаганали) "Чистый код" Роберт Мартина

        Вообще код перегружен всякими нафиг не нужными конструкциями и это делает не пригодным к чтению,
        но я надеюсь, что это потому. что ты просто тренировался на паттернах.

        Еще проект жклательно собирать сборщиком. Вот у меня например нет идеи, и я не смог с наскоку импортировать проект в еклипс
        Поэтому осовой мэйвен или грейдл

        В остальном и целом - норм.*/