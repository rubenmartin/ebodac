#!/usr/bin/env bash

if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
    if [ "$DB" = "mysql" ]; then
        echo "USE mysql;\nUPDATE user SET password=PASSWORD('$LOCAL_MYSQL_PASSWORD') WHERE user='$LOCAL_MYSQL_USER';\nFLUSH PRIVILEGES;\n" | mysql -u root
        mvn clean install -PIT -U
    fi
fi