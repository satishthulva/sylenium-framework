taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
call mvn clean
call mvn verify -Dthread.count=10 -Dremote=http://localhost:4444/wd/hub
