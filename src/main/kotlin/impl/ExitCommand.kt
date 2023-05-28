package impl

import command.ICommand
import java.io.OutputStream

class ExitCommand : ICommand {
    companion object {
        const val COMMAND_NAME = "exit"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        System.exit(0)
    }
}