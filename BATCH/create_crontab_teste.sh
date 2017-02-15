#!/bin/sh

echo Limpando crontab
crontab -r

echo "0,15,30,45 * * * * ${HOME}/deploy/processos/priorizacaoAtendimento/executa.sh"          > ${HOME}/crontab.tmp
echo "0,15,30,45 * * * * ${HOME}/deploy/processos/atualizaStatus/executa.sh"                 >> ${HOME}/crontab.tmp
echo "0,15,30,45 * * * * ${HOME}/deploy/processos/listaCampanha/executa.sh"                  >> ${HOME}/crontab.tmp
echo "0,15,30,45 * * * * ${HOME}/deploy/processos/enviaSMS/executa.sh"                       >> ${HOME}/crontab.tmp
echo "0,15,30,45 * * * * ${HOME}/deploy/processos/resetaIndisponibilidadeUsuario/executa.sh" >> ${HOME}/crontab.tmp
# echo "0,15,30,45 * * * * ${HOME}/deploy/processos/apiLegadoExec/executa.sh"                  >> ${HOME}/crontab.tmp

echo Criando nova crontab ::: HORARIOS DE TESTE
crontab ${HOME}/crontab.tmp
rm ${HOME}/crontab.tmp

echo Listando crontab
crontab -l
