Итеграция токена:
Переименовать token_example.properties в token.properties и вставить свой токен в получившийся файл.

Перед запуском native тестов на облачных устройствах, на них необходимо установить соответсвующее приложение.

Запуск тестов
Local Android Native test:
mvn -Pnative clean test

Loacal Android Web test:
mvn -Pweb clean test

Native Android Cloud test:
mvn -PnativeAndroidCloud clean test

Web Android Cloud test:
mvn -PwebAndroidCloud clean test

Native iOS Cloud test:
mvn -PnativeAndroidCloud clean test

Web iOS Cloud test:
mvn -PwebAndroidCloud clean test