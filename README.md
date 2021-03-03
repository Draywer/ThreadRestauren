#### Домашнее задание к занятию 1.2 Работа с синхронизацией. Synchronized блоки. Методы wait, notify, sleep. Интерфейс lock.
#### Задача 3. Ресторан

```text
"C:\Program Files\JetBrains\IntelliJ IDEA 2019.2\jbr\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.2\lib\idea_rt.jar=56549:C:\Program Files\JetBrains\IntelliJ IDEA 2019.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\User\IdeaProjects\ThreadRestauren\target\classes;C:\Users\User\.m2\repository\org\apache\httpcomponents\httpclient\4.5.12\httpclient-4.5.12.jar;C:\Users\User\.m2\repository\org\apache\httpcomponents\httpcore\4.4.13\httpcore-4.4.13.jar;C:\Users\User\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\User\.m2\repository\commons-codec\commons-codec\1.11\commons-codec-1.11.jar;C:\Users\User\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.12.1\jackson-databind-2.12.1.jar;C:\Users\User\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.12.1\jackson-annotations-2.12.1.jar;C:\Users\User\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.12.1\jackson-core-2.12.1.jar;C:\Users\User\.m2\repository\org\hamcrest\hamcrest-all\1.3\hamcrest-all-1.3.jar ru.idcore.Main
Ресторан открыт
Официант-1 в зале...
Официант-0 в зале...
Гость-1 хочет сделать заказ...
Гость-1 ожидает выполнение заказа...
Гость-0 хочет сделать заказ...
Гость-0 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Гость-2 хочет сделать заказ...
Гость-2 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Гость-3 хочет сделать заказ...
Гость-3 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-4 хочет сделать заказ...
Гость-0 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Гость-3 ожидает выполнение заказа...
Гость-4 ожидает выполнение заказа...
Гость-3 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Гость-4 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Официант-0 принимает заказ у Гость-4
Гость-1 ожидает выполнение заказа...
Официант-0 передает заказ на Кухню...
Гость-3 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Гость-4 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Кухня приняла заказ от Официант-0
Официант-0 ожидает исполнение заказа от Кухни...
Ответ Кухни для Официант-0: Заказ еще не готов. Ждите!...
Официант-1 принимает заказ у Гость-3
Гость-3 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Гость-4 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Официант-1 передает заказ на Кухню...
Гость-0 ожидает выполнение заказа...
Кухня приняла заказ от Официант-1
Гость-4 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Официант-1 ожидает исполнение заказа от Кухни...
Ответ Кухни для Официант-1: Заказ еще не готов. Ждите!...
Гость-3 ожидает выполнение заказа...
Кухня выполнила заказ
Гость-1 ожидает выполнение заказа...
Количество выполненных заказов: 1
Гость-3 ожидает выполнение заказа...
Гость-4 получил заказ...
Гость-2 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Официант-0 получил заказ от Кухни для Гость-4
Ответ Кухни для Официант-1: Заказ еще не готов. Ждите!...
Гость-1 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-3 получил заказ...
Гость-4 ушел из ресторана...
Кухня выполнила заказ
Количество выполненных заказов: 2
Гость-0 ожидает выполнение заказа...
Официант-0 принимает заказ у Гость-2
Гость-2 ожидает выполнение заказа...
Официант-0 передает заказ на Кухню...
Гость-3 ушел из ресторана...
Гость-1 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Гость-1 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Кухня приняла заказ от Официант-0
Официант-0 ожидает исполнение заказа от Кухни...
Гость-0 ожидает выполнение заказа...
Официант-0 получил заказ от Кухни для Гость-3
Гость-1 ожидает выполнение заказа...
Гость-2 ожидает выполнение заказа...
Ответ Кухни для Официант-1: Заказ еще не готов. Ждите!...
Кухня выполнила заказ
Количество выполненных заказов: 3
Гость-2 получил заказ...
Гость-1 ожидает выполнение заказа...
Официант-1 получил заказ от Кухни для Гость-2
Гость-0 ожидает выполнение заказа...
Официант-0 принимает заказ у Гость-0
Гость-2 ушел из ресторана...
Гость-1 ожидает выполнение заказа...
Официант-0 передает заказ на Кухню...
Гость-0 ожидает выполнение заказа...
Гость-0 ожидает выполнение заказа...
Кухня приняла заказ от Официант-0
Гость-1 ожидает выполнение заказа...
Официант-0 ожидает исполнение заказа от Кухни...
Ответ Кухни для Официант-0: Заказ еще не готов. Ждите!...
Кухня выполнила заказ
Количество выполненных заказов: 4
Гость-1 ожидает выполнение заказа...
Гость-0 получил заказ...
Официант-1 принимает заказ у Гость-1
Официант-1 передает заказ на Кухню...
Кухня приняла заказ от Официант-1
Гость-1 ожидает выполнение заказа...
Гость-0 ушел из ресторана...
Официант-1 ожидает исполнение заказа от Кухни...
Гость-1 ожидает выполнение заказа...
Официант-1 получил заказ от Кухни для Гость-0
Ответ Кухни для Официант-0: Заказ еще не готов. Ждите!...
Кухня выполнила заказ
Количество выполненных заказов: 5
Кухня выполнила все заказы. Ресторан закрывается...
Официант-0 получил заказ от Кухни для Гость-1
Гость-1 получил заказ...
Официант-1 закончил работу...
Гость-1 ушел из ресторана...
Официант-0 закончил работу...

Process finished with exit code 0
```