package command

import java.util.*

object CommandMain {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Command Line is Start")
        val manager = CommandManager.getInstance()
        val input = Scanner(System.`in`)
        while (true) {
            val line = input.nextLine()
            if (line.trim { it <= ' ' }.isEmpty()) {
                continue
            }
            val commands = CommandUtil.tokenizerArgs(line)
            val commandName = commands[0]
            val commandArgs: Array<String>?
            commandArgs = if (commands.size > 1) {
                Arrays.copyOfRange(commands, 1, commands.size)
            } else {
                null
            }
            val command = manager.getCommand(commandName)
            command.execute(commandArgs!!, System.out)
            println("")
        }
    }
}