package impl

import java.io.OutputStream

class MemoryCommand : BaseCommand() {

    companion object {
        const val COMMAND_NAME = "memory"
    }

    override fun getCommandName(): String {
        return COMMAND_NAME
    }

    override fun execute(args: Array<String>, out: OutputStream) {
        val heap = Runtime.getRuntime().totalMemory() / 1000000.0
        val heapMax = Runtime.getRuntime().maxMemory() / 1000000.0
        val heapFree = Runtime.getRuntime().freeMemory() / 1000000.0

        val salida = "Heap: $heap\nMax Heap: $heapMax\nFree Heap: $heapFree"
        write(out, salida)
    }
}