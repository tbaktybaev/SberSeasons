/**
  *  Есть список из интов. Подсчитать количество перемен знака в последовательности (ноль не перемена)
  *  Для List(0, 0, 1, 0, 0, 1, 0, 1) - 0 перемен знака
  *  Для List(1, 0 , -1)              - 1 перемена
  *  Для List(1, 0 , -1, 0, 0, 0, -1) - тоже 1 перемена
  *  Желательно сделать на Scala, но можно и на другом языке обязательно в функциональном стиле: использовать чистые функции, не использовать циклы, переменные. 
  *  Как вариант можно сделать задание, используя хвостовую рекурсию, но можно и другим способом
  */

object PositiveNegativeCount extends App {
  val in = List(1,-2,3,3,4,-2,-2,-3,1)
  
  // функция для подсчета без рекурсии
  def countSignChanges(lst: List[Int]): Int =
    lst
      .foldLeft((lst.head, 0)) { case ((prev, count), curr) =>
        if (curr == 0) (prev, count) // пропускаем нули
        else if (prev * curr < 0) (curr, count + 1) // нашли перемену знака
        else (prev, count) // продолжаем поиск
      }
      ._2 // возвращаем число перемен знака
  
  // функция для подсчета с хвостовой рекурсией
  def countSignChangesTailRec(lst: List[Int]): Int = {
    @scala.annotation.tailrec
    def loop(prev: Int, lst: List[Int], count: Int): Int = lst match {
      case Nil       => count
      case 0 :: tail => loop(prev, tail, count) // пропускаем нули
      case curr :: tail if prev * curr < 0 =>
        loop(curr, tail, count + 1) // нашли перемену знака
      case curr :: tail => loop(curr, tail, count) // продолжаем поиск
    }

    lst match {
      case Nil          => 0 // обработка пустого списка
      case head :: tail => loop(head, tail, 0) // вызов хвостовой рекурсии
    }
  }

  val lst = List(0, 0, 1, 0, 0, 1, 0, 1)
  val lst1 = List(1, 0, -1)
  val lst2 = List(1, 0, -1, 0, 0, 0, -1)
  val lst3 = List(1, 1, -1, 0, 0, -1, 1, 1, -1)
  println(countSignChangesTailRec(lst)) // 0
  println(countSignChangesTailRec(lst1)) // 1
  println(countSignChangesTailRec(lst2)) // 1 
  println(countSignChangesTailRec(lst3)) // 3
  println(countSignChangesTailRec(in)) // 4
}
