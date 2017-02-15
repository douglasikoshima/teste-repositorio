#!/bin/sh

#
# 156141 - Desligamento Tibco
# Adaptacao para o novo comportamento de consumo da tabela
# Atendimento.FilaSMSProtocolo
#
# Marcelo Nunes   Fev/2016
#
# OBS.: AO PASSAR O NRO. MAXIMO DE REGISTROS A ENVIAR COMO ARGUMENTO NA LINHA DE COMANDO,
#       A PARAMETRIZACAO DO BANCO FICA INVALIDA, VALENDO APENAS O ARGUMENTO PASSADO.
#   EXEMPLO: nohup ./enviarEmailProtocolo 20000   - Indica que serao lidos no maximo,
#            20 mil registros, independente do que estiver parametrizado no banco.
#

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
NOME_SYS=../log/enviarEmailProtocoloSys_"`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/enviarEmailProtocolo.`whoami`.log

if [[ $- = *i* ]]; then
    set -o vi
    stty erase ^H
fi

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/enviarEmailProtocolo/bin
pwd >> ${logfile}

exec 2>&1 >> ${NOME_SYS}

nohup ./enviarEmailProtocolo > ../log/$NOME
retcode=$?

echo Fim execucao: $NOME >> ${logfile}

if [ $retcode == 0 ]
then
	echo "Processo executado com sucesso!"
	exit 0
else
	echo "Processo executado com erro/critica: $retcode"
	exit 1
fi
