taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean 
call mvn verify -pl gui-tests -Dbrowser=firefox -Dthread.count=5 -Dis.running.on.travis=false
cd gui-tests/target
call allure serve