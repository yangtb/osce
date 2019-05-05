@echo off
set localdir=%~dp0
cd ..
for %%x in (*.jar) do (
	set JARFILENAME=%%x
	break
)
title %JARFILENAME%
call java -Xmx384m -jar %JARFILENAME%
pause