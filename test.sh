wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.0.0-M4/junit-platform-console-standalone-1.0.0-M4.jar -O testing.jar

javac -cp .:"/home/curtis/gitkraken/GAME-ENGINE/game-engine/src/lib/*" game-engine/src/engine/cgel/cgelTests.java -Xmaxwarns 1


java -jar testing.jar --class-path game-engine/src/ --scan-class-path --reports-dir .

