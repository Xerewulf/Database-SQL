SET STATISTICS IO OFF
SET STATISTICS TIME OFF

IF EXISTS ( SELECT name FROM sys.tables
			WHERE name = N'TABLEA')
BEGIN
	DROP TABLE TABLEA
END

CREATE TABLE TABLEA
(
	KeyValue INT NOT NULL,
	Value1 INT NOT NULL,
	Value2 INT NOT NULL,
	Value3 INT NOT NULL,
	Value4 INT,
	Value5 NCHAR(50),
	Value6 INT NOT NULL,
	CONSTRAINT [PK_TABLEA] PRIMARY KEY CLUSTERED (KeyValue ASC)
)

DECLARE @KeyValue AS INT, @Value1 INT, @Value2 INT, @Value3 INT, @Value4 INT, @Value5 CHAR(1)
DECLARE @I INT = 0;
DECLARE @MAX_ROW_COUNT INT = 25000;
WHILE (@I < @MAX_ROW_COUNT)
BEGIN
	SET @I = @I + 1;

	SET @KeyValue = ABS(CAST(CAST(NEWID() AS VARBINARY) AS INT)) % 25000 + 1;
	SET @Value1 = ABS(CAST(CAST(NEWID() AS VARBINARY) AS INT)) % 100000 + 1;
	SET @Value2 = ABS(CAST(CAST(NEWID() AS VARBINARY) AS INT)) % 100 + 1;
	SET @Value3 = ABS(CAST(CAST(NEWID() AS VARBINARY) AS INT)) % 10 + 1;
	SET @Value4 = ABS(CAST(CAST(NEWID() AS VARBINARY) AS INT)) % 1000 + 1;
	IF (@Value4 > 100)
	BEGIN
		SET @Value4 = NULL;
	END
	SET @Value5 =
	CHAR(ABS(CAST(CAST(NEWID() AS VARBINARY) AS INT)) % 96 + 32);

	IF NOT EXISTS ( SELECT @KeyValue FROM TABLEA
					WHERE KeyValue =@KeyValue)
	BEGIN
		INSERT INTO TABLEA VALUES
			(@KeyValue, @Value1, @Value2, @Value3, @Value4, @Value5, @Value1 * @Value2)
	END
END


-- Show created data and column statistics
SELECT
	'KeyValue' AS ColumnName
	, COUNT(DISTINCT KeyValue) AS DifferentRecordCount
	, CAST(MIN(KeyValue) AS NCHAR(100)) AS MinimumValue
	, CAST(MAX(KeyValue) AS NCHAR(100)) AS MaximumValue
FROM TABLEA

UNION ALL

SELECT
	'Value1' AS ColumnName
	, COUNT(DISTINCT Value1) 
	, CAST(MIN(Value1) AS NCHAR(100)) 
	, CAST(MAX(Value1) AS NCHAR(100))
FROM TABLEA

UNION ALL

SELECT
	'Value2' AS ColumnName
	, COUNT(DISTINCT Value2) 
	, CAST(MIN(Value2) AS NCHAR(100)) 
	, CAST(MAX(Value2) AS NCHAR(100))
FROM TABLEA

UNION ALL

SELECT
	'Value3' AS ColumnName
	, COUNT(DISTINCT Value3) 
	, CAST(MIN(Value3) AS NCHAR(100)) 
	, CAST(MAX(Value3) AS NCHAR(100))
FROM TABLEA

UNION ALL

SELECT
	'Value4' AS ColumnName
	, COUNT(DISTINCT Value4) 
	, CAST(MIN(Value4) AS NCHAR(100)) 
	, CAST(MAX(Value4) AS NCHAR(100))
FROM TABLEA

UNION ALL

SELECT
	'Value5' AS ColumnName
	, COUNT(DISTINCT Value5) 
	, MIN(Value5) 
	, MAX(Value5) 
FROM TABLEA


SELECT * FROM TABLEA 
