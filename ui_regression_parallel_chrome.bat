taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean 
call mvn test -pl gui-tests -Dbrowser=chrome -Dthread.count=5
cd gui-tests/target
call allure serve