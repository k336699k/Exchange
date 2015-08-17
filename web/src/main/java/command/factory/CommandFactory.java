package command.factory;

import command.ActionCommand;
import command.client.CommandEnum;

public class CommandFactory {
    public static ActionCommand getCommand(String paramCommand) {
        CommandEnum currentCommand = CommandEnum.valueOf(paramCommand.toUpperCase());
        return currentCommand.getCurrentCommand();
    }
}
