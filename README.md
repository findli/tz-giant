test case from giant

stack: java; spring; graphql; react; apollo.

configuration for Unix*:

resources/application.yaml:

`app:`

 ` giantCliPath: /home/user/giant-1.2.2.1-linux64`

`  giantConfFilePath: /home/user/.giant/giant.conf`


1. run -to-giant/giatd
2. run in debug mode: `mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transpo
rt=dt_socket,server=y,suspend=y,address=8001" -X`
3. cd yarn; yarn install
4. yarn run start-dev


Burtovoy Ian.
skype: yanchik366


Нужно написать приложение на Spring Boot, которое интегрируется с консольной утилитой giant-cli (https://github.com/GiantPay/GiantCore/releases/tag/1.2.2.1 выбор ОС на Ваше усмотрение). Приложение должно отобразить на web странице (http://localhost:8080) номер и сложность последнего блока блокчейна Giant. Страница должна обновляться без перезагрузки раз в 5 минут и показывать актуальные значения.
	Документация по утилите giant-cli:
Метод для получения последнего номера блока giant-cli getblockcount
Метод для получения хеша блока по номеру giant-cli getblockhash :height
Метод для получения данных блока по хешу giant-cli getblock :hash
Для реализации используйте в первую очередь следующие инструменты и библиотеки: Spring, Spring Boot.
