@echo off
REM
REM $Id: setConfig.bat,v 1.3 2002/02/11 13:35:52 mrumpf Exp $
REM
mkdir tmp\org\openorb\config
copy OpenORB.xml tmp\org\openorb\config\OpenORB.xml
copy default.xml tmp\org\openorb\config\default.xml
for %%i in (..\lib\openorb*.jar) do call add.bat %%i
rmdir /S /Q tmp
