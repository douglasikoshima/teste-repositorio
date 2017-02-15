#! /bin/sh

export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

echo java -jar LDAPUser.jar $1 $2 $3 $4
java -jar LDAPUser.jar $1 $2 $3 $4
exit $?
