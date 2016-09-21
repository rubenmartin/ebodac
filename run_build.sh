#!/usr/bin/env bash
echo "USE mysql;\nUPDATE user SET password=PASSWORD('$LOCAL_MYSQL_PASSWORD') WHERE user='$LOCAL_MYSQL_USER';\nFLUSH PRIVILEGES;\n" | mysql -u $LOCAL_MYSQL_USER
mvn clean install -PIT -U -X
