@REM Maven Wrapper for Windows
@echo off

setlocal enabledelayedexpansion

set SCRIPT_DIR=%~dp0
set PROJECT_DIR=%SCRIPT_DIR%..

set MAVEN_VERSION=3.9.9
set MAVEN_BASE_URL=https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries
set MAVEN_ZIP=apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_HOME=%PROJECT_DIR%\.mvn\maven-%MAVEN_VERSION%

if not exist "%MAVEN_HOME%" (
    echo Downloading Maven %MAVEN_VERSION%...
    powershell -Command "(New-Object System.Net.WebClient).DownloadFile('%MAVEN_BASE_URL%/%MAVEN_ZIP%', '%TEMP%\%MAVEN_ZIP%')"
    powershell -Command "Expand-Archive -Path '%TEMP%\%MAVEN_ZIP%' -DestinationPath '%PROJECT_DIR%\.mvn\'"
    move "%PROJECT_DIR%\.mvn\apache-maven-%MAVEN_VERSION%" "%MAVEN_HOME%"
    del "%TEMP%\%MAVEN_ZIP%"
)

call "%MAVEN_HOME%\bin\mvn.cmd" %*

endlocal

