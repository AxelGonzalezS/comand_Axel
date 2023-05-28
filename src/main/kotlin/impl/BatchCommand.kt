package impl

import command.CommandManager
import command.CommandUtil
import java.io.File
import java.io.FileInputStream
import java.io.OutputStream

class BatchCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "batch"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        if (args == null || args.size < 1) {
            write(out, "Número de parámetros inválido")
            return
        }

        val manager = CommandManager.getInstance();
        val lines = readLinesFromFile(args[0])
        for (line in lines) {
            val argsCommand = CommandUtil.tokenizerArgs(line)
            val command = manager.getCommand(argsCommand[0])
            val reduce = argsCommand.copyOfRange(1, argsCommand.size)
            command.execute(reduce, out)
            write(out, "\n")
        }
        write(out, "Batch ejecutado")
    }

    private fun readLinesFromFile(filePath: String): Array<String> {
        val file = File(filePath)
        var stream: FileInputStream? = null
        try {
            if (!file.exists()) {
                throw RuntimeException("Archivo no encontrado")
            }

            stream = FileInputStream(file)
            val byteArray = ByteArray(stream.available())
            stream.read(byteArray)

            val commands = String(byteArray)
            return commands.split("\n").toTypedArray()
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        } finally {
            try {
                stream?.close()
            } catch (e2: Exception) {}
        }
    }
}