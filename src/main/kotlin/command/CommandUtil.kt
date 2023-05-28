package command

object CommandUtil {
    fun tokenizerArgs(args: String): Array<String> {
        val tokens = mutableListOf<String>()
        val charArray = args.toCharArray()

        var contact = ""
        var inText = false
        for (c in charArray) {
            if (c == ' ' && !inText) {
                if (contact.isNotEmpty()) {
                    tokens.add(contact)
                    contact = ""
                }
            } else if (c == '"') {
                if (inText) {
                    tokens.add(contact)
                    contact = ""
                    inText = false
                } else {
                    inText = true
                }
            } else {
                contact += c
            }
        }

        if (contact.trim().isNotEmpty()) {
            tokens.add(contact.trim())
        }

        return tokens.toTypedArray()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val commanda = "file -an c:/dummy/dummy.txt \"Hola mundo tres veces\""
        val tokens = tokenizerArgs(commanda)
        println(tokens.contentToString())
    }
}