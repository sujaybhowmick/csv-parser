Sample Code Usage
------------------
```java

    String inputFile = "src/test/resources/test.csv";

    CSVParser parser = new CSVParserImpl(inputFile);

    // To get Iterator to iterate over records parsed
    Iterator<CSVRecord> records = parser.iterator();

    // To get List of records parsed
    List<CSVRecord> list = parser.getRecords();

    // To get number of records
    int size = parser.size();
```
