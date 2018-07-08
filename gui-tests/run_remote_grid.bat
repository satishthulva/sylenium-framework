REM local grid is a nonce, dont use it!
Rem here is a 10 node setup, 5xchrome 5xfirefox

cd selenium_grid
start cmd /k Call "start_selenium_hub.bat"
start cmd /k Call "add_chrome_node_01.bat"
start cmd /k Call "add_firefox_node_01.bat"

