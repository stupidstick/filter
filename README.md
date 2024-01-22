<h2>Информация о проекте</h2>
<b>Версия JDK:</b> 17 <br>
<b>Система сборки:</b> Maven (Version 3.9.2) <br>
<h3>Используемые библиотеки:</h3>
<i><b>Project Lombok</b> (Version 1.18.30)</i> Link: https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30 <br>
<b>JCommander</b> (Version 1.82) Link: https://mvnrepository.com/artifact/com.beust/jcommander/1.82 <br>
<b>SLF4J API Module</b> (Version 2.0.11) Link: https://mvnrepository.com/artifact/org.slf4j/slf4j-api/2.0.11 <br>
<b>Logback Classic Module</b> (Version 1.4.14) Link: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic/1.4.14 <br>

<h2>Запуск приложения</h2>
Для запуска используйте filter.jar из корнеыой папки приложения <br>
<b>Пример запуска:</b> <br>
<i>java -jar -f filter.jar -p out_ -o result_ in1.txt in2.txt -a</i>
<br>
<b>-f, -s</b> - флаги выбора режима статистики. Обязательно должен быть использован один из флагов. Одновременно два флага не могут быть использованы. <br>
<b>-o</b> - задать путь для записи результатов (Несуществующие папки будут созданы) <br>
<b>-p</b> - задать префикс для имен файлов результатов <br>
<b>-a</b> - установить режим дозаписи (По умолчанию используется режим перезаписи) <br>
<br>
Имена файлов с результатами записаны в конфигурационном файле <b><i>paths.properties</b></i> и могут быть изменены<br>
По умолчанию средние значения округляются до <b>5</b> знаков после запятой. Изменить кол-во знаков можно в файле <b><i>statistics.properties</i></b> 
