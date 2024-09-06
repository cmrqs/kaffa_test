package ex1

internal class CNPJ(private val value: String) {
    fun check() {
        // Check string format, such as length and special symbols position
        val cnpj = value.trim()
        val regNumberOnly = Regex("""\d{14}""")
        val regFormatted = Regex("""\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}""")
        val isNumberOnly = regNumberOnly.matches(cnpj)
        if ((!isNumberOnly) and (!regFormatted.matches(cnpj)))
            throw IllegalArgumentException("Only formats \"00.000.000/0000-00\" and \"00000000000000\" are accepted.")

        // Remove special symbols from the string and check if all digits are equal
        val regSymbols = Regex("""[./-]""")
        val regRepeated = Regex("""([0-9])\1{13}""")
        val cnpjNumberOnly = if (isNumberOnly) cnpj else regSymbols.replace(cnpj, "")
        if (regRepeated.matches(cnpjNumberOnly))
            throw IllegalArgumentException("Repeated numbers are not allowed.")

        // Validate both check digits
        checkFirstDigit(cnpjNumberOnly)
        checkSecondDigit(cnpjNumberOnly)
    }

    private fun checkFirstDigit(cnpj: String) {
        val weights = intArrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val expectedDigit = calcDigit(cnpj, weights)
        if (cnpj[12].digitToInt() != expectedDigit) {
            throw IllegalArgumentException("First digit \"${cnpj[12]}\" mismatch. Expected \"$expectedDigit\".")
        }
    }

    private fun checkSecondDigit(cnpj: String) {
        val weights = intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val expectedDigit = calcDigit(cnpj, weights)
        if (cnpj[13].digitToInt() != expectedDigit) {
            throw IllegalArgumentException("Second digit \"${cnpj[13]}\" mismatch. Expected \"$expectedDigit\".")
        }
    }

    private fun calcDigit(cnpj: String, weights: IntArray): Int {
        val sum = weights.indices.sumOf { cnpj[it].digitToInt() * weights[it] }
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }
}

fun main() {
    try {
        print("CNPJ: ")
        val input = readln()
        CNPJ(input).check()
        println("Valid CNPJ!")
    } catch (e: IllegalArgumentException) {
        println("[!] Invalid CNPJ: ${e.message}")
    }
}