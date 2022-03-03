SET STATISTICS IO ON

CREATE NONCLUSTERED INDEX indexr ON TABLEA(TABLEA  ASC)

		

SELECT
	KeyValue,
	Value1,
	Value2,
	Value3,
	Value4,
	Value5,
	Value6
FROM TABLEA
WHERE Value3 IN (SELECT TOP 1
				Value3
				FROM TABLEA
				GROUP BY Value3
				ORDER BY COUNT(*)
				 )
AND Value1 - 1000 < 0 
--AND KeyValue BETWEEN 2000 and 23000 (I dont know thiss is walid or not but its reduce the page size)


SET STATISTICS IO OFF


