cd /d E:\ENV\apache-tomcat-7.0.55\bin
catalina start

MKDIR e:\exc\
cd /D e:\exc\
git clone -b master https://github.com/k336699k/Exchange.git



cd /d e:\exc\Exchange\
call mvn tomcat7:redeploy

