#!/bin/bash
############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
exit 0
export LOG_PREFIX="SEGAUTO"
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 

BASE_NAME=orabatch
DIR_CFG=cfg
CONFIG_FILE_DEF=${DIR_CFG}/${BASE_NAME}.$(hostname).$(whoami).cfg
CONFIG_FILE=${BASE_NAME}.cfg
if [ -e ${CONFIG_FILE_DEF} ]
then
	# Carrega configuracao para maquina.usuario
	. ./${CONFIG_FILE_DEF}
elif [ -e ${CONFIG_FILE} ]
then
	# Carrega configuracao padrao
	. ./${CONFIG_FILE}
else
	# Arquivo de configuracao nao encontrado
	echo "ERRO: Arquivo de configuracao (${CONFIG_FILE_DEF} ou ${CONFIG_FILE}) nao encontrados!" 
	exit -1
fi
############################################################
# Path e nome do arquivo de LOG para desvia o SYSLOG do SO
############################################################
LOG_HOJE=`date +"%Y%m%d"`
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"

############################################################
# Desviando o SYSLOG para o caso de erro no BATCH
############################################################
exec 1> $SYSLOGFILE
exec 2>> $SYSLOGFILE

# Arquivo de log Sysout
log ${LOG_PREFIX} "==================="
log ${LOG_PREFIX} "SEGAUTO - INICIANDO"
log ${LOG_PREFIX} "==================="
log ${LOG_PREFIX} "DIR_SHELL..........: $DIR_SHELL"
log ${LOG_PREFIX} "BASE_NAME..........: $BASE_NAME"
log ${LOG_PREFIX} "CONFIG_FILE_DEF....: $CONFIG_FILE_DEF"
log ${LOG_PREFIX} "CONFIG_FILE........: $CONFIG_FILE"
log ${LOG_PREFIX} "ORABATCH_DIR_LOG...: $ORABATCH_DIR_LOG"
log ${LOG_PREFIX} "ORABATCH_CONNECTION: $ORABATCH_CONNECTION"
log ${LOG_PREFIX} "==================="

############################################################
# Se pressionar Ctrl+C mudo o estado para ERRO
############################################################
trap ctrl_c INT

function ctrl_c() {
	log ${LOG_PREFIX} "ERRO: CTRL+C foi pressionado e este BATCH ira encerrar o processamento..."
	atualizaEstado ERRO
	log ${LOG_PREFIX} "ERRO: CTRL+C foi pressionado e este BATCH encerrou com sucesso, gravando estado de ERRO em APOIO.PARAMETRO(SEGAUTO_ESTADO)"
	exit -1;
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #varialve global
source ${ORABATCH_DIR}/segauto_VerificaBatchComErro.sh
source ${ORABATCH_DIR}/segauto_AtualizaParametros.sh

############################################################
# Fica em loop ate chegar em SYSDATE-1
############################################################
while true
do
	############################################################
	# Retorna um numerico que indica quantos dias entre hoje e o criterio de pesquisa no segauto
	# Este valor indica quantos dias faltam processar, o SEGUAUTO processa por dia
	# Desta forma, se o retorno for 10, significa que fazem 10 dias que nao e executado, entao
	# ele sera processado 10 vezes ate encerrar. Lembrando que ele não pode processar o dia corrente
	# pois executa de madrugada. O dia corrente e retornado como zero.
	############################################################
	temPesquisaRetroativa
	quantidadeDias=$?
	# Só processa numeros maiores que zero (pois zero eh o dia corrente)
	if [ $quantidadeDias -gt 0 ]
	then
		############################################################
		# Antes de iniciar o processamento devemos verificar se houve erros
		############################################################
		verificaBatchComErro
		segautoComErro=$?
		if [ $segautoComErro -eq 0 ]
		then
			############################################################
			# O ultimo processou ocorreu com sucesso entao atualiza o criterio para a retomada
			############################################################
			
			#Atualiza o criterio com +1 dia (apenas para o caso de sucesso)
			atualizaCriterio
			
			# Se segautoComErro retornar 1, nao vai entrar neste IF, o que indica que o ultimo processamento falhou
			# sendo assim nao atualiza o Criterio, mas continua a processar com a mesma data que esta em APOIO.PARAMETRO
			
			############################################################
			# Atualiza o estado para CHECAGEM, apenas quando a ultima execucao terminou com estado FINALIZADO
			############################################################
			atualizaEtapa CHECAGEM
			
		elif [ $segautoComErro -eq 2 ]
		then
			log ${LOG_PREFIX} $retorno
			exit -1
		fi

		#Busca o ultimo criterio de processamento (eh uma data, pois o SEGAUTO processa por dia)
		buscaCriterioUltimoProcessamento
		criterioParaProcessamento=$retorno

		############################################################
		# Depois da CHECAGEM, Atualiza o estado para CHECAGEM
		############################################################
		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		log ${LOG_PREFIX} "Ultima Etapa Para Processamento[$ultimaEtapaParaProcessamento]"

		# Atualiza o estado
		atualizaEstado PROCESSANDO
		if [ $ultimaEtapaParaProcessamento == "CHECAGEM" ];
		then
			atualizaEtapa INCLUSAO_POS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "INCLUSAO_POS" ];
		then
			log ${LOG_PREFIX} "Iniciando a execucao de segauto_inclusaoPos.sql: [`date`]"
			retorno=`sqlplus -s ${ORABATCH_CONNECTION} @${ORABATCH_DIR}/segauto_inclusaoPos.sql $criterioParaProcessamento`
			sql_return_code=$?
			log ${LOG_PREFIX} "Retorno de segauto_inclusaoPos.sql: [$retorno]"
			log ${LOG_PREFIX} "Execucao de segauto_inclusaoPos.sql finalizada: [`date`]"
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[INCLUSAO_POS]: Falha ao executar o arquivo segauto_inclusaoPos.sql[$sql_return_code]"
				atualizaEstado ERRO
				exit -1;
			fi
			atualizaEtapa EXCLUSAO_POS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "EXCLUSAO_POS" ];
		then
			log ${LOG_PREFIX} "Iniciando a execucao de segauto_exclusaoPos.sql: [`date`]"
			retorno=`sqlplus -s ${ORABATCH_CONNECTION} @${ORABATCH_DIR}/segauto_exclusaoPos.sql $criterioParaProcessamento`
			sql_return_code=$?
			log ${LOG_PREFIX} "Retorno de segauto_exclusaoPos.sql: [$retorno]"
			log ${LOG_PREFIX} "Execucao de segauto_exclusaoPos.sql finalizada: [`date`]"
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[EXCLUSAO_POS]: Falha ao executar o arquivo segauto_exclusaoPos.sql[$sql_return_code]"
				atualizaEstado ERRO
				exit -1;
			fi
			atualizaEtapa INCLUSAO_PRE
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "INCLUSAO_PRE" ];
		then
			log ${LOG_PREFIX} "Iniciando a execucao de segauto_inclusaoPre.sql: [`date`]"
			retorno=`sqlplus -s ${ORABATCH_CONNECTION} @${ORABATCH_DIR}/segauto_inclusaoPre.sql $criterioParaProcessamento`
			sql_return_code=$?
			log ${LOG_PREFIX} "Retorno de segauto_inclusaoPre.sql: [$retorno]"
			log ${LOG_PREFIX} "Execucao de segauto_inclusaoPre.sql finalizada: [`date`]"
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[INCLUSAO_PRE]: Falha ao executar o arquivo segauto_inclusaoPre.sql"
				atualizaEstado ERRO
				exit -1;
			fi
			atualizaEtapa EXCLUSAO_PRE
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "EXCLUSAO_PRE" ];
		then
			log ${LOG_PREFIX} "Iniciando a execucao de segauto_exclusaoPre.sql: [`date`]"
			retorno=`sqlplus -s ${ORABATCH_CONNECTION} @${ORABATCH_DIR}/segauto_exclusaoPre.sql $criterioParaProcessamento`
			sql_return_code=$?
			log ${LOG_PREFIX} "Retorno de segauto_exclusaoPre.sql: [$retorno]"
			log ${LOG_PREFIX} "Execucao de segauto_exclusaoPre.sql finalizada: [`date`]"
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[EXCLUSAO_PRE]: Falha ao executar o arquivo segauto_exclusaoPre.sql"
				atualizaEstado ERRO
				exit -1;
			fi
			atualizaEstado FINALIZADO
			log ${LOG_PREFIX} "================================"
			log ${LOG_PREFIX} "SEGAUTO - FINALIZADO COM SUCESSO"
			log ${LOG_PREFIX} "================================"
		fi
	else
		log ${LOG_PREFIX} "================================"
		log ${LOG_PREFIX} "SAIU DO LOOP SEM PROCESSAMENTO NESTA ITERACAO [$quantidadeDias]"
		log ${LOG_PREFIX} "================================"
		break
	fi #if [ $quantidadeDias -gt 0 ]
done
log ${LOG_PREFIX} "================================"
log ${LOG_PREFIX} "TERMINOU SEM EXIT"
log ${LOG_PREFIX} "================================"
