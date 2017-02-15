#!/bin/sh

. ${HOME}/.profile

cd ${HOME}/deploy/processos/manutencaoLogin/bin

#find ../data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

java -jar manutencaoLogin.jar

echo Fim da execucao!