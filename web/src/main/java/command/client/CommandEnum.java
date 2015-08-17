package command.client;

import command.ActionCommand;
import command.AddMetalCommand;
import command.AddUserCommand;
import command.LoginCommand;
import command.LogoutCommand;
import command.SaveMetalCommand;
import command.SaveUserCommand;
import command.ViewMetalCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    VIEW_METALS {
        {
            this.command = new ViewMetalCommand();
        }
    },
    ADD_METAL {
        {
            this.command = new AddMetalCommand();
        }
    },
    SAVE_METAL {
        {
            this.command = new SaveMetalCommand();
        }
    },
    ADD_USER {
        {
            this.command = new AddUserCommand();
        }
    },
    SAVE_USER {
        {
            this.command = new SaveUserCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}