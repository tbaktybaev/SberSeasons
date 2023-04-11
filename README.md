# Домашнее задание 

## Task1

**SQL**

Требовалось написать SQL-запрос, который извлекает значения Id из таблицы где значение Timestamp меньше максимального значения Timestamp среди предыдущих строк на основе их значений Id.

Алгоритм запроса SQL:

1.  `SELECT t1.Id` 
    `FROM example t1`: присвоение алиаса и выбор таблица (в задании не было указано название, условно назвал `example`).
3.  `WHERE t1.Id > 1`: данное условие было дописано для того чтобы избежать сравнения с первой записью
4.  `AND t1.Timestamp < (SELECT MAX(t2.Timestamp) FROM example t2 WHERE t2.Id < t1.Id)`: условие фильтрации, в которых значение Timestamp больше или равно максимальному значению Timestamp среди предыдущих строк на основе их значений Id.  
4. `FROM example t2 WHERE t2.Id < t1.Id)`: поиск максимального Timestamp среди строк, где значение Id меньше значения Id текущей строки t1, из предыдущих строк.

**sparksql Python**
На sparkSQL запрос выглядит примерно также:

1. Импорт модуля SparkSession из библиотеки pyspark.sql и создание объект SparkSession.
2. Считывание данных из CSV-файла, который был перенесен на "src/example.csv", в DataFrame.
3. Создание временного представления из DataFrame df. Как я понял, представление можно использовать для выполнения SQL-запросов к DataFrame.
4. Выполняется SQL-запрос на временном созданном представлении для получения списка по условию. Запрос немного отличается от SQL скрипта. Я решил попробовать другой способ. 
5. Отображение результата запроса - `show()`.

## Task2

Все условия были соблюдены. Как эсперимент попробовал написать как с рекурсией так и без него.

1. Переменой знака считается смена знака между двумя соседними ненулевыми числами. Ноль в данном случае не является переменой знака.
2. В коде две функции: countSignChanges и countSignChangesTailRec. Первая функция выполняет подсчет без использования хвостовой рекурсии, а вторая - с использованием хвостовой рекурсии.
3. Функция countSignChanges использует метод foldLeft, который, как я понял, позволяет пройти по всем элементам списка и последовательно выполнять некоторую операцию с каждым элементом. В данном случае операция заключается в проверке знаков соседних элементов и подсчете перемен знака.
4. Функция countSignChangesTailRec использует хвостовую рекурсию для выполнения той же операции. В данном случае рекурсивная функция loop принимает три аргумента: предыдущий элемент списка, оставшуюся часть списка и текущее число перемен знака. 

PS: Как я понял, хвостовая рекурсия позволяет избежать создания большого количества промежуточных объектов в памяти, что привносит эффективности и экономии ресурсов на вычислениях.

PSPS: старался не использовать циклы и переменные.

## Task4 (3)
Код выполняет следующие действия:

1. Идет создание структуры таблиц с заданной схемой с помощью метода `spark.createDataFrame([], schema)`.
2. Преобразование типов для таблиц calls и dates.
3. В переменную закидываются значения из столбца "date" в таблицу calls и таблице dates.
4. Далее идет подсчет количества записей в таблице calls за каждую дату, присутствующих в таблице dates, а также добавление столбца "stat_date_time" со значением текущей даты и времени с помощью `F.current_timestamp()`.
5. Поиск и определение, которые присутствуют в таблице dates, но отсутствуют в таблице calls.
6. Создание записи для отсутствующих дат со значением "count" равным 0 для того чтобы соблюсти условия задачи.
7. Объединение таблиц
8. Идет подсчет количества записей в таблице calls за каждую дату из таблицы dates для каждого oper_id, а также добавление столбца "stat_date_time".
9. По моим наблюдениям в csv файлам была сортировка таблиц, и поэтому я решил отсортировать таблицы по столбцам "date" и "oper_id" по убыванию.
10. Ну и как итог выводит содержимое таблиц stat_total и stat_oper с помощью метода show().

# Ресурсы и документации

1. https://spark.apache.org/docs/latest/api/python/
2. https://spark.apache.org/docs/2.4.0/api/python/pyspark.sql.html 
3. https://spark.apache.org/docs/3.1.1/api/python/reference/api/pyspark.sql.SparkSession.html
4. https://spark.apache.org/docs/latest/sql-getting-started.html
5. https://spark.apache.org/docs/3.1.3/api/java/org/apache/spark/sql/package-summary.html
6. https://javascopes.com/scala-tail-recursion-by-example-42m-9fb676d5/
7. https://habr.com/ru/articles/464915/
8. https://russianblogs.com/article/98451596829/
9. https://sparkbyexamples.com/pyspark/pyspark-row-using-rdd-dataframe-2/
10. https://spark.apache.org/docs/3.1.3/api/python/reference/api/pyspark.sql.Row.html
11. https://spark.apache.org/docs/2.1.0/api/python/pyspark.sql.html
12. https://sparkbyexamples.com/pyspark/pyspark-sql-functions/

...и много много статей и примеров кодов на stackoverflow, habr, dzen и тд. :) 