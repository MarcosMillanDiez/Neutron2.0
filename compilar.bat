mkdir bin
set CLASSPATH=.\bin;.\lib\juego2048-gui-lib-1.0.jar;.\lib\log4j-1.2.17.jar;.\lib\slf4j-api-1.7.1.jar;.\lib\slf4j-log4j12-1.7.1.jar
javac -d .\bin ^
.\src\juego\modelo\*.java ^
.\src\juego\control\*.java ^
.\src\juego\util\*.java ^
.\src\juego\textui\*.java
