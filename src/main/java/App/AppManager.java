package App;

import EntityLister.EntityLister;
import Parsers.CommandParser;
import PersonalActions.PersonalAction;
import PersonalActions.PersonalActionProxy;
import PersonalActions.PersonalActionsInterface;
import Transactions.Transaction;
import Transactions.TransactionInterface;
import Transactions.TransactionProxy;

/** GUI class, works with available services through interfaces.
 * We can safely pass a proxy object and benefit from its functionalities.
 */
public class AppManager {
    private static AppManager appManager;
    private final TransactionInterface transactionService;
    private final PersonalActionsInterface personalActionService;
    private final EntityLister entityLister;

    private AppManager() {
        // Initialize services
        TransactionInterface transactionService = new Transaction();
        TransactionInterface transactionProxy = new TransactionProxy(transactionService);

        PersonalActionsInterface personalActionService = new PersonalAction();
        PersonalActionsInterface personalActionProxy = new PersonalActionProxy(personalActionService);

        // Pass proxies to the app manager
        this.transactionService = transactionProxy;
        this.personalActionService = personalActionProxy;

        this.entityLister = new EntityLister();
    }

    public static AppManager getInstance() {
        if (appManager == null)
            appManager = new AppManager();
        return appManager;
    }
    
    /** Reads input files and solves requests.
     *
     */
    public void solveRequests() {
        CommandParser commandParser = new CommandParser(this, EBankingApp.getInstance().getInputFiles());

        commandParser.parseCommands();
    }

    public TransactionInterface getTransactionService() {
        return transactionService;
    }
    public PersonalActionsInterface getPersonalActionService() {
        return personalActionService;
    }
    public EntityLister getEntityLister() {
        return entityLister;
    }
}
