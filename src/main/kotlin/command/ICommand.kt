package command

import java.io.OutputStream

interface ICommand {
    fun getCommandName(): String
    fun execute(args: Array<String>, out: OutputStream)
}