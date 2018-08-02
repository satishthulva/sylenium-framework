taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean 
call mvn verify -DskipUTs=true -pl gui-tests -Dbrowser=chrome -Dthread.count=5 -Dis.running.on.travis=false
cd gui-tests/target
call allure serve