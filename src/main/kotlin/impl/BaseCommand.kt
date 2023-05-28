package impl

import command.ICommand
import java.io.OutputStream

abstract class BaseCommand : ICommand {
    override abstract fun getCommandName(): String
    override abstract fun execute(args: Array<String>, out: OutputStream)

    fun write(out: OutputStream, message: String) {
        try {
            out.write(message.toByteArray())
            out.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}