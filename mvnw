#!/bin/bash
# Maven Wrapper Script

# Determine the absolute path to the directory where this script is located
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PROJECT_DIR="$( cd "$SCRIPT_DIR/.." && pwd )"

# Use Maven 3.9.9
MAVEN_VERSION="3.9.9"
MAVEN_BASE_URL="https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries"
MAVEN_ZIP="apache-maven-${MAVEN_VERSION}-bin.zip"
MAVEN_HOME="${PROJECT_DIR}/.mvn/maven-${MAVEN_VERSION}"

if [ ! -d "$MAVEN_HOME" ]; then
    echo "Downloading Maven ${MAVEN_VERSION}..."
    curl -o /tmp/${MAVEN_ZIP} ${MAVEN_BASE_URL}/${MAVEN_ZIP}
    unzip -q /tmp/${MAVEN_ZIP} -d ${PROJECT_DIR}/.mvn/
    mv ${PROJECT_DIR}/.mvn/apache-maven-${MAVEN_VERSION} $MAVEN_HOME
    rm /tmp/${MAVEN_ZIP}
fi

# Execute Maven
$MAVEN_HOME/bin/mvn "$@"

