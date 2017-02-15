#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
PROCNAME="enviaNGIN"
LOGFILE=/tmp/${PROCNAME}.`whoami`.log

echo Execucao....: $NOME > ${LOGFILE}

trap 'echo $pid;kill $pid;exit' HUP INT QUIT ILL ABRT EMT FPE KILL BUS SEGV SYS PIPE ALRM TERM STOP

processo=`ps -ef |grep $USER | grep "$PROCNAME" | grep -v grep | wc -l`

if [ $processo -eq 1 ]; then
	cd ${HOME}/deploy/processos/${PROCNAME}/bin
	pwd >> ${LOGFILE}
	nohup ./$PROCNAME > ../log/$NOME &
	pid=$!
	#echo $pid
	wait ${pid}
else
	echo `date +%d/%m/%Y-%H:%M:%S` "Processo $PROCNAME ja esta no ar" >> ${LOGFILE}
fi

echo Fim execucao: $NOME >> ${LOGFILE}
