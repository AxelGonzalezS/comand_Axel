package impl

import java.io.File
import java.io.OutputStream

class DirCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "dir"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        if (args == null || args.size < 2) {
            write(out, "$COMMAND_NAME argumentos insuficientes")
            return
        }

        val operation = args[0]
        when (operation.toUpperCase()) {
            "-D" -> write(out, deleteDir(args[1]))
            "-N" -> write(out, newDir(args[1]))
            else -> write(out, "Se esperaba una operación correcta -d | -n")
        }
    }

    private fun deleteDir(url: String): String {
        try {
            val file = File(url)
            if (!file.exists()) {
                return "El archivo no existe"
            }

            if (!file.canWrite()) {
                return "Privilegios insuficientes"
            }

            file.delete()
            return ""
        } catch (e: Exception) {
            return "ERROR: ${e.message}"
        }
    }

    private fun newDir(url: String): String {
        try {
            val file = File(url)
            if (file.exists()) {
                return "El archivo ya existe"
            }

            file.mkdirs()
            return ""
        } catch (e: Exception) {
            return "ERROR: ${e.message}"
        }
    }
}