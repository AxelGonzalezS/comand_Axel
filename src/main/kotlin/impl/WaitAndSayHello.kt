package impl

import java.io.OutputStream

class WaitAndSayHello : AsyncCommand() {

    companion object {
        const val COMMAND_NAME = "waithello"
    }

    override fun executeOnBackground(args: Array<String>, out: OutputStream) {
        if (args == null || args.size < 1) {
            write(out, "Parámetros insuficientes")
            return
        }

        var time: Long? = null
        try {
            time = args[0].toLong()
        } catch (e: Exception) {
            write(out, "Tiempo inválido")
            return
        }

        try {
            Thread.sleep(time)
            write(out, "Hello!!")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }
}