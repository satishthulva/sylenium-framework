taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean 
call mvn test -Dselenide.browser=chrome -Dthread.count=5 -Dremote=https://localhost:4444/wd/hub
cd target
call allure serve