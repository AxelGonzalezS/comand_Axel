package impl

import java.io.File
import java.io.FileWriter
import java.io.OutputStream
import java.util.Arrays

class FileCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "file"
        private const val WRITE_APPEND = "-WA"
        private const val WRITE_OVERRIDE = "-WO"
        private const val WRITE_NEW = "-WN"
        private const val RENAME_FILE = "-R"
        private const val DELETE_FILE = "-D"
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        if (args.size < 2) {
            write(out, "Parámetros insuficientes")
            return
        }

        val operation = args[0].toUpperCase()

        val reduce = args.copyOfRange(1, args.size)
        when (operation) {
            WRITE_APPEND -> write(out, writeAppend(reduce))
            WRITE_NEW -> write(out, writeNew(reduce))
            WRITE_OVERRIDE -> write(out, writeOverride(reduce))
            RENAME_FILE -> write(out, renameFile(reduce))
            DELETE_FILE -> write(out, deleteFile(reduce))
            else -> write(
                out, "No se encontró la operación {" +
                        "$WRITE_APPEND|$WRITE_NEW|$WRITE_OVERRIDE|$RENAME_FILE|$DELETE_FILE}"
            )
        }
    }

    private fun renameFile(args: Array<String>): String {
        val filePath = args[0]
        val newFileName = args[1]
        return try {
            val file = File(filePath)
            file.renameTo(File(newFileName))
            ""
        } catch (e: Exception) {
            "ERROR: ${e.message}"
        }
    }

    private fun writeOverride(args: Array<String>): String {
        val filePath = args[0]
        val fileContent = args[1]

        return try {
            val file = File(filePath)
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return "ERROR: Error al crear el archivo"
                }
            }
            val fileW = FileWriter(file)
            fileW.write(fileContent.toCharArray())
            fileW.flush()
            fileW.close()
            ""
        } catch (e: Exception) {
            "ERROR: ${e.message}"
        }
    }

    private fun writeAppend(args: Array<String>): String {
        val filePath = args[0]
        val fileContent = args[1]

        return try {
            val file = File(filePath)
            if (!file.exists()) {
                return "ERROR: El archivo no existe"
            }

            val fileW = FileWriter(file, true)
            fileW.append(fileContent)
            fileW.flush()
            fileW.close()
            ""
        } catch (e: Exception) {
            "ERROR: ${e.message}"
        }
    }

    private fun writeNew(args: Array<String>): String {
        val filePath = args[0]
        val fileContent = args[1]

        return try {
            val file = File(filePath)
            if (file.exists()) {
                return "ERROR: El archivo ya existe"
            }
            if (!file.createNewFile()) {
                return "ERROR: No fue posible crear el archivo"
            }

            val fileW = FileWriter(file)
            fileW.write(fileContent.toCharArray())
            fileW.flush()
            fileW.close()
            ""
        } catch (e: Exception) {
            "ERROR: ${e.message}"
        }
    }

    private fun deleteFile(args: Array<String>): String {
        val filePath = args[0]
        val file = File(filePath)
        if (!file.delete()) {
            return "No fue posible eliminar el archivo"
        }
        return ""
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }
}