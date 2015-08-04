MKDIR e:\exc\
cd /D e:\exc\
git clone -b dev https://github.com/k336699k/Exchange.git



cd /d e:\exc\Exchange\
call mvn clean install

cd dao\target\
java -jar dao-0.0.1-SNAPSHOT.jar