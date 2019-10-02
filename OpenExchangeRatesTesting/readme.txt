1. Тестовые сценарии для endpoints "/latest.json", "/historical/*.json", "/currencies.json" прописаны в файлах:
    - src/test/java/EndpointLatestTest.java
    - src/test/java/EndpointHistoricalTest.java
    - src/test/java/EndpointCurrenciesTest.java
2. Классы EndpointHistoricalResult и EndpointLatestResult описывают ответ сервера соответсвующего endpoint (разбирают JSON в java-объект).
3. Ответ сервера с запроса на endpoint "/currencies.json" хранится в виде HashMap<String, String>.
4. В тестах в константах местами используются ЯКОБЫ корректные данные и ЯКОБЫ некорректные, например:
    - private static final String INVALID_APP_ID = "123";
    - private static final String CORRECT_APP_ID = "correct123";
    - остальные данные приближены к реальности;