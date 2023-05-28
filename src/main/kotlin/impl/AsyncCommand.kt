package impl

import java.io.OutputStream

abstract class AsyncCommand : BaseCommand() {

    override fun execute(args: Array<String>, out: OutputStream) {
        Thread {
            executeOnBackground(args, out)
        }.start()
    }

    abstract fun executeOnBackground(args: Array<String>, out: OutputStream)
}