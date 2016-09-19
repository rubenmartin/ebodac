#!/usr/bin/env bash

echo "USE mysql;\nUPDATE user SET password=PASSWORD('$LOCAL_MYSQL_PASSWORD') WHERE user='$LOCAL_MYSQL_USER';\nFLUSH PRIVILEGES;\n" | mysql -u root
mvn clean install -PIT -U
mvn  install -o -Pchrome-remote  -Dserver.url=$STAGING_SERVER -Dlogin.username=$MOTECH_USER -Dlogin.password=$MOTECH_STAGING_PASS -Dadmin.login=$EBODAC_L1_ADMIN_USER  -Dadmin.password=$EBODAC_L1_ADMIN_PASS -Danalyst.login=$EBODAC_L1_ANALYST_USER -Danalyst.password=$EBODAC_L1_ANALYST_PASS

