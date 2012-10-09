mvn install:install-file -Dfile=./amserver.jar -DgroupId=org.forgerock.openam -DartifactId=amserver -Dversion=10.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=./opensso-sharedlib.jar -DgroupId=org.forgerock.openam -DartifactId=opensso-sharedlib -Dversion=10.0.0 -Dpackaging=jar
