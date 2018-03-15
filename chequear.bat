mkdir doccheck
javadoc -doclet com.sun.tools.doclets.doccheck.DocCheck ^
-encoding UTF-8 ^
-docletpath .\lib\doccheck.jar ^
-sourcepath .\src ^
-subpackages \juego ^
-d doccheck ^
-cp ./lib/*
pause 