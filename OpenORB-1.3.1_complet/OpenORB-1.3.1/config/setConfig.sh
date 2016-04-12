#!/bin/sh
#
# $Id: setConfig.sh,v 1.3 2002/02/11 13:35:52 mrumpf Exp $
#
mkdir -p tmp/org/openorb/config
cp OpenORB.xml tmp/org/openorb/config/OpenORB.xml
cp default.xml tmp/org/openorb/config/default.xml
JARS=`echo ../lib/*.jar | tr ' ' ':'`
IFS=:
for i in $JARS; do
    jar -uvf $i -C tmp org/openorb/config/OpenORB.xml -C tmp org/openorb/config/default.xml
done
rm -rf tmp
