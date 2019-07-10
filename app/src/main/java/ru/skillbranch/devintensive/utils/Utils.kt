package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.replace(Regex(" +"), " ")?.split(" ")

        val firstName = parts?.notEmptyOrNullAt(0)
        val lastName = parts?.notEmptyOrNullAt(1)

        return firstName to lastName
    }

    private fun List<String>.notEmptyOrNullAt(index: Int) = getOrNull(index).let {
        if ("" == it) null
        else it
    }

    fun transliteration(payload: String, divider: String? = " "): String? {
        val newString: List<String?> = payload.map { it.toString() }.toList();
        var result: String? = ""
        for (n in newString) {
            when (n) {
                " " -> result += divider
                "а" -> result = result + "a"
                "б" -> result = result + "b"
                "в" -> result = result + "v"
                "г" -> result = result + "g"
                "д" -> result = result + "d"
                "е" -> result = result + "e"
                "ё" -> result = result + "e"
                "ж" -> result = result + "zh"
                "з" -> result = result + "z"
                "и" -> result = result + "i"
                "й" -> result = result + "i"
                "к" -> result = result + "k"
                "л" -> result = result + "l"
                "м" -> result = result + "m"
                "н" -> result = result + "n"
                "о" -> result = result + "o"
                "п" -> result = result + "p"
                "р" -> result = result + "r"
                "с" -> result = result + "s"
                "т" -> result = result + "t"
                "у" -> result = result + "u"
                "ф" -> result = result + "f"
                "х" -> result = result + "h"
                "ц" -> result = result + "c"
                "ч" -> result = result + "ch"
                "ш" -> result = result + "sh"
                "щ" -> result = result + "sh'"
                "ъ" -> result = result + ""
                "ы" -> result = result + "i"
                "ь" -> result = result + ""
                "э" -> result = result + "e"
                "ю" -> result = result + "yu"
                "я" -> result = result + "ya"
                "А" -> result = result + "A"
                "Б" -> result = result + "B"
                "В" -> result = result + "V"
                "Г" -> result = result + "G"
                "Д" -> result = result + "D"
                "Е" -> result = result + "E"
                "Ё" -> result = result + "E"
                "Ж" -> result = result + "ZH"
                "З" -> result = result + "Z"
                "И" -> result = result + "I"
                "Й" -> result = result + "I"
                "К" -> result = result + "K"
                "Л" -> result = result + "L"
                "М" -> result = result + "M"
                "Н" -> result = result + "N"
                "О" -> result = result + "O"
                "П" -> result = result + "P"
                "Р" -> result = result + "R"
                "С" -> result = result + "S"
                "Т" -> result = result + "T"
                "У" -> result = result + "U"
                "Ф" -> result = result + "F"
                "Х" -> result = result + "H"
                "Ц" -> result = result + "C"
                "Ч" -> result = result + "CH"
                "Ш" -> result = result + "SH"
                "Щ" -> result = result + "SH'"
                "Ъ" -> result = result + ""
                "Ы" -> result = result + "I"
                "Ь" -> result = result + ""
                "Э" -> result = result + "E"
                "Ю" -> result = result + "YU"
                "Я" -> result = result + "YA"
                else -> result += n
            }
        }

        return result;
    }

    fun toInitials(firstName: String?, lastName: String?): String? = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName[0].toUpperCase().toString()
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName[0].toUpperCase().toString()
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> firstName[0].toUpperCase() + lastName[0].toUpperCase().toString()
        else -> throw IllegalStateException("Incorrect state in 'when' expression")
    }
}