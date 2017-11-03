@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson4.task1.abs
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {
    age in 11..14 || age in 111..114 ||
            age % 10 in 5..9 || age % 10 == 0 -> "$age лет"
    age % 10 == 1 -> "$age год"
    else -> "$age года"
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val halfS = (s1 + s2 + s3) / 2
    return when {
        halfS <= s1 -> halfS / v1
        halfS in s1..(s1 + s2) -> (halfS - s1) / v2 + t1
        else -> (halfS - s1 - s2) / v3 + t1 + t2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val rook1Attacks = (kingX == rookX1) || (kingY == rookY1)
    val rook2Attacks = (kingX == rookX2) || (kingY == rookY2)
    return when {
        !rook1Attacks && !rook2Attacks -> 0
        rook1Attacks && !rook2Attacks -> 1
        !rook1Attacks && rook2Attacks -> 2
       else -> 3
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */

fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val bishopAttacks = abs(kingX - bishopX) == abs(kingY - bishopY)
    val rookAttacks = kingX == rookX || kingY == rookY
    return when {
        bishopAttacks && rookAttacks -> 3
        bishopAttacks && !rookAttacks -> 2
        !bishopAttacks && rookAttacks -> 1
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (a + b < c || a + c < b || b + c < a)
        return -1
    else {
        val maxSide: Double = maxOf(a, b, c)
        val minSide: Double = minOf(a, b, c)
        val midSide: Double = a + b + c - maxSide - minSide
        val tCos: Double =
                (sqr(midSide) + sqr(minSide) - sqr(maxSide)) /
                        (2 * midSide * minSide)
      return when {
          tCos == 0.0 -> 1
          tCos < 0.0 -> 2
          else -> 0
      }
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    (b in a..c && c in b..d && b == c) ||
            (a in d..b && d in c..a && d == a) -> 0  // A...B(C)...D или C...D(A)...B
    c in a..b && b in a..d -> b - c                  // A...C|||B...D (||| - область пересечания)
    a in c..d && d in a..b -> d - a                  // C...A|||D...B
    a in c..b && b in a..d -> b - a                  // C...A|||B...D
    c in a..d && d in c..b -> d - c                  // A...C|||D...B
    else -> -1                                       // A...B C...D или C...D A...B
}
