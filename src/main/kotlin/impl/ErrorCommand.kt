package impl

import java.io.OutputStream

class ErrorCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "ERROR"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        val message = "Error al invocar el comando"
        write(out, message)
    }
}