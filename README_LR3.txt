Лабораторная работа №3 — Jenkins Pipeline
Проект: служба доставки (проект из ЛР №2), вариант 10.

Состав:
- pom.xml — настройки Maven и зависимости;
- src/main/java — исходный код;
- src/test/java — тесты JUnit;
- Jenkinsfile — Pipeline Build → Test → Package и индивидуальное задание.

Проверка до Jenkins:
1. Откройте терминал в корне проекта (там, где pom.xml).
2. Выполните по очереди: mvn compile, mvn test, mvn package.
3. Убедитесь, что команды завершаются успешно.

Настройка Jenkins на Windows:
1. Откройте Dashboard → New Item.
2. Назовите задание Lab3-DeliveryService (или другим именем по требованиям преподавателя), выберите Pipeline → OK.
3. Найдите каталог JENKINS_HOME\workspace\Lab3-DeliveryService. JENKINS_HOME можно посмотреть в Manage Jenkins → System Information (переменная/параметр JENKINS_HOME) либо в настройках Jenkins.
4. Скопируйте в каталог workspace именно содержимое папки проекта: pom.xml, Jenkinsfile и папку src. Не копируйте папку lr3_delivery целиком внутрь ещё одной папки.
5. В задании откройте Configure → Pipeline. В Definition выберите “Pipeline script from SCM” только если проект реально размещён в SCM; в данной лабораторной Git не используется, поэтому выберите “Pipeline script” и вставьте содержимое Jenkinsfile из корня проекта в поле Script. Сохраните настройки.
6. Нажмите Build Now. Откройте номер сборки → Console Output. Должны последовательно выполниться Build, Test и Package, а в конце появиться Finished: SUCCESS.
7. В Console Output найдите строку “Предметная область: Служба доставки” — это индивидуальное задание варианта 10. Переменная DELIVERY_SERVICE объявлена в блоке environment и выводится на этапе Build.
8. После успешной сборки проверьте наличие JAR в workspace\Lab3-DeliveryService\target\lr2-1.0-SNAPSHOT.jar (имя следует сверить с artifactId/version в pom.xml).

Проверка обработки ошибки:
1. Временно откройте src/test/java/DeliveryServiceTest.java и измените ожидаемое значение в одном из assertEquals так, чтобы тест не проходил. Например, в testCreateDelivery замените ожидаемый DeliveryStatus.CREATED на DeliveryStatus.DELIVERED.
2. Сохраните файл и снова запустите Build Now.
3. Ожидаемый результат: Build проходит, Test завершается FAILURE, этап Package пропускается, итог сборки — FAILURE. Сделайте скриншот Console Output.
4. Обязательно восстановите исходное значение DeliveryStatus.CREATED и сохраните файл.
5. Запустите Build Now ещё раз и убедитесь, что итог снова Finished: SUCCESS. Не оставляйте намеренно сломанный тест в сдаваемом проекте.

Примечание:
- Этот Pipeline рассчитан на Windows-агент Jenkins, потому что использует bat. Если Jenkins выполняется на Linux, замените bat на sh и используйте команды Maven, доступные в Linux.
- Jenkins должен запускаться от пользователя, у которого есть доступ к JDK 17 и Maven в PATH. Если Maven не доступен как команда mvn, настройте Maven в Manage Jenkins → Tools либо PATH службы Jenkins.
