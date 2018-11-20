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