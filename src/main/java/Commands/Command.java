package Commands;

import App.AppManager;

public abstract class Command {
    protected AppManager appManager;
    protected String[] tokens;

    public Command() {
        this.appManager = AppManager.getInstance();
    }
    public Command(String[] tokens) {
        this.appManager = AppManager.getInstance();
        this.tokens = tokens;
    }

    public abstract void execute(String[] tokens);
}
