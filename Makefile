install:
	mvn clean install -DskipTests

docker:
	mvn clean install docker:build -DskipTests

release:
	rm -f release.properties pom.xml.releaseBackup
	mvn release:prepare -B
	rm -f release.properties pom.xml.releaseBackup
