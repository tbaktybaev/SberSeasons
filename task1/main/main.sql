SELECT t1.Id 
FROM example t1 
WHERE t1.Id > 1 --во избежания сравнения с первой записью
  AND t1.Timestamp < (SELECT MAX(t2.Timestamp) FROM example t2 WHERE t2.Id < t1.Id) --поиск максимума по Id предыдущих и отбор меньшего timestamp  
