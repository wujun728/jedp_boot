 @echo off
   :config
  start config.bat
   echo wait...
   pause
    :discovery
    start discovery.bat
   echo wait...
   pause
    :books
    start books.bat
   echo wait...
   pause
    :api
    start api.bat
   echo wait...
   pause
   :consumer
    start consumer.bat
   echo wait...
     pause
       :monitor
    start monitor.bat
   echo wait...
   pause
    :aggregator
    start aggregator.bat
   echo wait...
   exit