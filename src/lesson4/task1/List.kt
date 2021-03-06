@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import java.lang.Math.pow
import java.lang.Math.sqrt
import java.lang.StringBuilder

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */

fun abs(v: List<Double>): Double {
    var result = 0.0
    for (i in v)
        result += sqr(i)
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty())
        return list
    val avg = list.sum() / list.size
    for (i in 0 until list.size)
        list[i] -= avg
    return list
}



/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if (a.size != b.size)
        return error("Error")
    var scalMult = 0.0
    for (i in 0 until a.size)
        scalMult += a[i] * b[i]
    return scalMult
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var pX = 0.0
    var xInPow = 1.0
    for (i in p) {
        pX += i * xInPow
        xInPow *= x
    }
    return pX
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty())
        return list
    var tmp = 0.0
    var toAdd = 0.0
    for (i in 0 until list.size) {
        tmp = list[i]
        list[i] += toAdd
        toAdd += tmp
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var div = 2
    var num = n
    val listOfSimpleMultipliers = mutableListOf<Int>()
    while (num > 1) {
        if (num % div == 0) {
            num /= div
            listOfSimpleMultipliers += div
        } else
            div++
    }
    return listOfSimpleMultipliers
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    val resultInList = factorize(n)
    val stringOfSimpleMultipliers = StringBuilder()
    for (i in 0 until resultInList.size ) {
        stringOfSimpleMultipliers.append(resultInList[i])
        if (i != resultInList.size - 1)
            stringOfSimpleMultipliers.append("*")
    }
    return stringOfSimpleMultipliers.toString()
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var num = n
    val result = mutableListOf<Int>()
    do {
        result.add(num % base)
        num /= base
    } while (num != 0)
    return result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val numberInList = convert(n, base)
    var resultString = StringBuilder()
    for (i in numberInList) {
        if (i <= 9)
            resultString.append('0' + i)
        else
            resultString.append('a' + i - 10)
    }
    return resultString.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var numberInDecimal = 0
    for (i in (digits.size - 1) downTo 0) {
        numberInDecimal += digits[i] * pow(base.toDouble(),
                (digits.lastIndex - i).toDouble()).toInt()
    }
    return numberInDecimal
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val result  = mutableListOf<Int>()
    for (i in str) {
        if (i - '0' <= 9)
            result.add(i - '0')
        else
            result.add(i - 'a' + 10)
    }
    return decimal(result, base)

}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String  {
    val conformity = mapOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
            100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X", 9 to "IX",
            5 to "V", 4 to "IV", 1 to "I")
    var number = n
    var numInRoman = ""
    for (i in conformity) {
        while (number - i.key >= 0) {
            numInRoman += i.value
            number -= i.key
        }
    }
    return numInRoman
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

val hundreds = listOf("", "сто", "двести", "триста", "четыреста",
        "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
val tensToHundred = listOf("", "", "двадцать", "тридцать", "сорок",
        "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
val tensToTwenty = listOf("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
val unitsIfFirst = listOf("", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
val unitsIfLast = listOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")

fun makePart(num: Int, isFirst: Boolean): String {
    var number = num
    val result = StringBuilder("")
    if (number / 10 != 0) {
        if (number % 100 in 10..19) {
            result.insert(0, tensToTwenty[number % 10])
            number /= 100
            if (number == 0) return result.toString()
            result.insert(0, hundreds[number] + " ")
            return result.toString()
        }
        else {
            if (isFirst)
                result.insert(0, unitsIfFirst[number % 10])

            else
                result.insert(0, unitsIfLast[number % 10])
            number /= 10
            if (number == 0) return result.toString()
            result.insert(0, tensToHundred[number % 10] + " ")
            number /= 10
            if (number == 0) return result.toString()
            result.insert(0, hundreds[number] + " ")
        }
    }
    else {
        return if (isFirst) unitsIfFirst[number % 10] else
        unitsIfLast[number % 10]
    }
    return result.toString()
}

fun separate(num: Int): List<Int> {
    var parts = listOf<Int>()
    parts += num / 1000
    parts += num % 1000
    return parts
}

fun deleteAddinitionDelays(str: StringBuilder): StringBuilder {
    var i = 0
    while(i < str.length) {
        if (i + 1 < str.length && str[i].toString() == " " && str[i + 1].toString() == " ") {
            str.deleteCharAt(i + 1)
            i--
        }
        i++
    }
    return str
}

fun russian(n: Int): String {
    var num = listOf(n)
    var inRussian = StringBuilder("")
    var isFirst = true
    if (digitNumber(n) > 3)
        num = separate(n)
    for (i in num) {
        if (num.size == 1) {
            inRussian.append(makePart(i, false))
        }
        else inRussian.append(makePart(i, isFirst))
        if (isFirst && num.size > 1)
            if (i % 100 in 11..14) {
                inRussian.append(" тысяч ")
            }
        else when (i % 10) {
                1 -> inRussian.append(" тысяча ")
                in 5..9 -> inRussian.append(" тысяч ")
                0 -> inRussian.append(" тысяч ")
                else -> inRussian.append(" тысячи ")
            }

        isFirst = false
    }
    inRussian = deleteAddinitionDelays(inRussian)
    return inRussian.toString().trim()
}