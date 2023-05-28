package impl

import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class DateTimeCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "date"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        var dateFormater: SimpleDateFormat? = null

        if (args == null) {
            dateFormater = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        } else {
            try {
                dateFormater = SimpleDateFormat(args[0])
            } catch (e: Exception) {
                write(out, "formato inválido")
                return
            }
        }

        val date = dateFormater!!.format(Date())
        write(out, date)
    }
}