taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean 
call mvn verify -Pgui-tests
cd target
call allure serve