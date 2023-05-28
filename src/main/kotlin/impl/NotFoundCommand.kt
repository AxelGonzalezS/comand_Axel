package impl

import java.io.OutputStream

class NotFoundCommand : BaseCommand() {

    companion object {
        private const val COMMAND_NAME = "NOT FOUND"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        write(out, "Comando no encontrado")
    }
}