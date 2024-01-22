Версия JDK: 17
Система сборки: Maven (Version 3.9.2)

Используемые библиотеки:
Project Lombok (Version 1.18.30) Link: https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30
JCommander (Version 1.82) Link: https://mvnrepository.com/artifact/com.beust/jcommander/1.82
SLF4J API Module (Version 2.0.11) Link: https://mvnrepository.com/artifact/org.slf4j/slf4j-api/2.0.11
Logback Classic Module (Version 1.4.14) Link: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic/1.4.14

Для запуска используйте filter.jar из корнеыой папки приложения
Пример запуска:
java -jar -f filter.jar -p out_ -o result_ in1.txt in2.txt -a

-f, -s - флаги выбора режима статистики. Обязательно должен быть использован один из флагов. Одновременно два флага не могут быть использованы.
-o - задать путь для записи результатов (Несуществующие папки будут созданы)
-p - задать префикс для имен файлов результатов
-a - установить режим дозаписи (По умолчанию используется режим перезаписи)

Имена файлов с результатами записаны в конфигурационном файле paths.properties и могут быть изменены
По умолчанию средние значения округляются до 5 знаков после запятой. Изменить кол-во знаков можно в файле statistics.properties