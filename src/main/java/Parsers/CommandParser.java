package Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import App.AppManager;
import App.EBankingApp.Currency;
import Commands.*;
import Entities.User;
import Exceptions.*;

public class CommandParser {
    private final AppManager appManager;
    private final File commandsFile;
    private final Map<String, Command> commandMapping;

    public CommandParser(AppManager appManager, String[] inputFiles) {
        this.appManager = appManager;
        this.commandsFile = new File(inputFiles[2]);

        commandMapping = Map.of(
            "CREATE USER", new CreateUserCommand(),
            "ADD FRIEND", new AddFriendCommand(),
            "ADD ACCOUNT", new AddAccountCommand(),
            "ADD MONEY", new AddMoneyCommand(),
            "EXCHANGE MONEY", new ExchangeMoneyCommand(),
            "TRANSFER MONEY", new TransferMoneyCommand(),
            "BUY STOCKS", new BuyStocksCommand(),
            "LIST USER", new ListUserCommand(),
            "LIST PORTFOLIO", new ListPortfolioCommand(),
            "RECOMMEND STOCKS", new RecommendStocksCommand()
        );
    }

    public void parseCommands() {
        try (Scanner scanner = new Scanner(commandsFile)) {
            while (scanner.hasNextLine()) {
                String commandLine = scanner.nextLine();
                String[] tokens = commandLine.split(" ");

                String commandName = tokens[0] + " " + tokens[1];

                commandMapping.get(commandName).execute(tokens);
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
