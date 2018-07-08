call mvn clean 
call mvn test -Dselenide.browser=chrome -Dthread.count=4
cd target
call allure serve