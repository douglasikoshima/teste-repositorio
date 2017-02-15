#!/bin/sh

echo Limpando crontab
crontab -r

echo "0    2 * * * ${HOME}/deploy/processos/expurgoAtendimento/executa.sh"                                 > ${HOME}/crontab.tmp
echo "5    3 * * * ${HOME}/deploy/processos/relatorioArvore/executa.sh"                                   >> ${HOME}/crontab.tmp
echo "0    * * * * ${HOME}/deploy/processos/priorizacaoAtendimento/executa.sh"                            >> ${HOME}/crontab.tmp
echo "0,30 * * * * ${HOME}/deploy/processos/priorizacaoAtendimentoPortout/executa.sh"                     >> ${HOME}/crontab.tmp
echo "0    0 * * * ${HOME}/deploy/processos/atualizaStatus/executa.sh"                                    >> ${HOME}/crontab.tmp
echo "0,30 * * * * ${HOME}/deploy/processos/listaCampanha/executa.sh"                                     >> ${HOME}/crontab.tmp
echo "0    * * * * ${HOME}/deploy/processos/enviaSMS/executa.sh"                                          >> ${HOME}/crontab.tmp
echo "0    * * * * ${HOME}/deploy/processos/resetaIndisponibilidadeUsuario/executa.sh"                    >> ${HOME}/crontab.tmp
echo "0    * * * * ${HOME}/deploy/processos/CnpjSemCarteira/executa.sh"                                   >> ${HOME}/crontab.tmp
echo "2,32 * * * * ${HOME}/deploy/processos/ativarAparelho/executa.sh"                                    >> ${HOME}/crontab.tmp
echo "0,30 * * * * ${HOME}/deploy/processos/finalizaCadastroPortIn/executa.sh"                            >> ${HOME}/crontab.tmp
echo "0,30 * * * * ${HOME}/deploy/processos/protocoloNaoSincronizado/executa.sh"                          >> ${HOME}/crontab.tmp
echo "0,5,10,15,20,25,30,35,40,45,50,55 * * * * ${HOME}/deploy/processos/procMatrizesRetencao/executa.sh" >> ${HOME}/crontab.tmp
echo "0,5,10,15,20,25,30,35,40,45,50,55 * * * * ${HOME}/deploy/processos/enviaNGIN2/enviaNGIN2.sh"        >> ${HOME}/crontab.tmp

echo Criando nova crontab
crontab ${HOME}/crontab.tmp
rm ${HOME}/crontab.tmp

echo Listando crontab
crontab -l

#start de processos com execução sem interrupção
echo Startando jobs independentes do cron...
sh -x ${HOME}/deploy/processos/enviaNGIN/enviaNGIN.sh &

echo End.

