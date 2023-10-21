javac Person.java
rm MyAgent.class
javac MyAgent.java
jar -cmf MANIFEST.MF agent.jar MyAgent.class
rm MyAgentTest.class
javac MyAgentTest.java
java -javaagent:agent.jar -cp . MyAgentTest
