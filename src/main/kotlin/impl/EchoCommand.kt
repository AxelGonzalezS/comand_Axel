package impl

import java.io.OutputStream
import java.util.*

class EchoCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "echo"
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        val message = "${getCommandName()} ${Arrays.toString(args)}"
        write(out, message)
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }
}