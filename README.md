Sample Code Usage
------------------

    String inputFile = "src/test/resources/test.csv";

    CSVParser parser = new CSVParserImpl();

    Iterator<CSVRecord> records = parser.parse(inputFile);
