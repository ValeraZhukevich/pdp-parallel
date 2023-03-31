## How to run API Tests

```
mvn clean test -Dsuite-xml=src/test/resources/api.xml
```

## How to run GUI Tests

```
mvn clean test -Dsuite-xml=src/test/resources/gui.xml
```

## How to generate Allure Report

```
mvn allure:report
```

## How to see Allure Report

```
mvn allure:serve
```
