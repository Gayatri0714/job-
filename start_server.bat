@echo off
echo ==========================================
echo Starting Job Portal Application
echo ==========================================

IF NOT EXIST "apache-maven-3.9.6-bin.zip" (
    echo Downloading Maven...
    curl -o maven.zip https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip
    echo Extracting Maven...
    tar -xf maven.zip
)

echo Starting Spring Boot...
set PATH=%CD%\apache-maven-3.9.6\bin;%PATH%
mvn spring-boot:run

pause
