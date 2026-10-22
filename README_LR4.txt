Лабораторная работа №4 — Интеграция Git и Jenkins
Проект: Служба доставки, вариант 10.

ВАЖНО: исходная версия Java 25 сохранена в pom.xml.

Индивидуальное задание:
- добавлен статус CANCELLED;
- добавлен метод DeliveryService.cancelDelivery(Delivery);
- отмена разрешена для статусов CREATED и ASSIGNED;
- после PICKED_UP и далее отмена запрещена;
- добавлены тесты testCancelCreatedDelivery и testCannotCancelDeliveryAfterPickup.

Основные файлы:
- pom.xml — Maven и Java 25;
- Jenkinsfile — Pipeline Build → Test → Package;
- .gitignore — исключает target/ и файлы среды разработки;
- src/main/java — код службы доставки;
- src/test/java/DeliveryServiceTest.java — тесты.

Проверка в корне проекта:
mvn -version
java -version
javac -version
mvn clean compile
mvn test
mvn package

Pipeline рассчитан на Windows-агент Jenkins, поскольку использует bat.
