@echo off

set TAGDIR=C:\TreeTagger

set BIN=%TAGDIR%\bin
set CMD=%TAGDIR%\cmd
set LIB=%TAGDIR%\lib
set TAGOPT=%LIB%\dutch.par -token -lemma -sgml -no-unknown

if "%2"=="" goto label1
perl %CMD%\utf8-tokenize.perl -a %LIB%\dutch-abbreviations "%~1" | %BIN%\tree-tagger %TAGOPT% > "%~2"
goto end

:label1
if "%1"=="" goto label2
perl %CMD%\utf8-tokenize.perl -a %LIB%\dutch-abbreviations "%~1" | %BIN%\tree-tagger %TAGOPT% 
goto end

:label2
echo.
echo Usage: tag-dutch file {file}
echo.

:end
