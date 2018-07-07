call mvn clean 
call mvn test -Dselenide.browser=chrome -Dthread.count=5
cd target
call allure serve