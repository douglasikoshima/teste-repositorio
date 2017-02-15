#!/bin/sh

IFS='
'

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/linhaOferta.`whoami`.log

echo Execucao....: $NOME > ${logfile}


#cd ${HOME}/deploy/processos/linhaOferta/data/
#for f in `ls URA_????????_??????.txt 2> /dev/null`
#do
#	perl -i -pe 's/\r\n/\n/g' "$f"
#done


cd ${HOME}/deploy/processos/linhaOferta/bin
pwd >> ${logfile}

NOME_SYS=../log/linhaOfertaSys_"`date +%Y%m%d-%H%M%S`.log"
exec 2>&1 >> ${NOME_SYS}

nohup ./linhaOferta > ../log/$NOME 
retcode=$?

if [ $retcode -ne 0 ];then
  echo "Finalizado com erro"
  exit 1
else
  echo "Finalizado com sucesso"
fi

echo Fim execucao: $NOME >> ${logfile}
exit 0
