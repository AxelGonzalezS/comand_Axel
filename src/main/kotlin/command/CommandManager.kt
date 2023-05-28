package command

import impl.*
import impl.BatchCommand.Companion.COMMAND_NAME
import java.util.HashMap

class CommandManager private constructor() {
    companion object {
        private var commandManager: CommandManager? = null
        private val COMMANDS = HashMap<String, Class<out ICommand>>()

        init {
            commandManager = CommandManager()
            commandManager!!.registCommand(EchoCommand.COMMAND_NAME, EchoCommand::class.java)
            commandManager!!.registCommand(DirCommand.COMMAND_NAME, DirCommand::class.java)
            commandManager!!.registCommand(DateTimeCommand.COMMAND_NAME, DateTimeCommand::class.java)
            commandManager!!.registCommand(MemoryCommand.COMMAND_NAME, MemoryCommand::class.java)
            commandManager!!.registCommand(FileCommand.COMMAND_NAME, FileCommand::class.java)
            commandManager!!.registCommand(ExitCommand.COMMAND_NAME, ExitCommand::class.java)
            commandManager!!.registCommand(BatchCommand.COMMAND_NAME, BatchCommand::class.java)
            commandManager!!.registCommand(WaitAndSayHello.COMMAND_NAME, WaitAndSayHello::class.java)
        }

        @Synchronized
        fun getInstance(): CommandManager {
            if (commandManager == null) {
                commandManager = CommandManager()
            }

            return commandManager!!
        }
    }

    fun getCommand(commandName: String): ICommand {
        return if (COMMANDS.containsKey(commandName.toUpperCase())) {
            try {
                COMMANDS[commandName.toUpperCase()]!!.newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
                ErrorCommand()
            }
        } else {
            NotFoundCommand()
        }
    }

    fun registCommand(commandName: String, command: Class<out ICommand>) {
        COMMANDS[commandName.toUpperCase()] = command
    }
}
