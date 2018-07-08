taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean 
call mvn test -Dselenide.browser=chrome -Dthread.count=4
cd target
call allure serve