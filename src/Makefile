run:	compile
	java GameManager
compile:
	javac *.java
solve:
	javac GameSolverTester.java TreeNode.java Map.java
	java GameSolverTester
jar:
	javac *.java
	jar cvfe RushHour.jar GameManager *.class img storage
	rm *.class
clean:
	rm *.class RushHour.jar
