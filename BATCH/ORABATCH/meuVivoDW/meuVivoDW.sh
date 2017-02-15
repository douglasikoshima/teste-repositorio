#!/bin/bash
############################################################
#  BATCH......: meuVivoDW
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="MEUVIVODW"
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
	echo "ERRO: Arquivos de configuracao (${CONFIG_FILE_DEF} ou ${CONFIG_FILE}) nao encontrados!" 
	exit -1
fi
############################################################
# Path e nome do arquivo de LOG para desvia o SYSLOG do SO
############################################################
LOG_HOJE=`date +"%Y%m%d"`
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"
#cria os arquivos na pasta tmp para evitar o spool e tambem 
# para evitar o problema do SPOOL do Oracle so aceita 30 cacacteres, entao geramos com nomes curtos e depois fazemos o rename
ARQUIVO_CLIENTE=datatmp/dados_cliente_
ARQUIVO_PROMOS=datatmp/trans_pcte_promos_
# Para para o arquivo final
ARQUIVO_CLIENTE_LONGO=data/meuvivo_dw_dados_cliente_
ARQUIVO_PROMOS_LONGO=data/meuvivo_dw_listas_trans_pcte_promos_
SEM_RETORNO=0
COM_RETORNO=1

############################################################
# Desviando o SYSLOG para o caso de erro no BATCH
############################################################
exec 1> $SYSLOGFILE
exec 2>> $SYSLOGFILE

# Arquivo de log Sysout
log ${LOG_PREFIX} "==================="
log ${LOG_PREFIX} "INICIANDO"
log ${LOG_PREFIX} "==================="
log ${LOG_PREFIX} "LOG_PREFIX.........: $LOG_PREFIX"
log ${LOG_PREFIX} "DIR_SHELL..........: $DIR_SHELL"
log ${LOG_PREFIX} "BASE_NAME..........: $BASE_NAME"
log ${LOG_PREFIX} "CONFIG_FILE........: $CONFIG_FILE"
log ${LOG_PREFIX} "CONFIG_FILE_DEF....: $CONFIG_FILE_DEF"
log ${LOG_PREFIX} "ORABATCH_DIR.......: $ORABATCH_DIR"
log ${LOG_PREFIX} "ORABATCH_DIR_LOG...: $ORABATCH_DIR_LOG"
log ${LOG_PREFIX} "ORABATCH_CONNECTION: $ORABATCH_CONNECTION"
log ${LOG_PREFIX} "==================="

############################################################
# Se receber algum sinal cancelamento aborta o processo e atualiza o estado
############################################################
trap sinal_cancel INT SIGINT SIGHUP SIGQUIT SIGTERM

function sinal_cancel() {
	log ${LOG_PREFIX} "ERRO: Processo recebeu sinal de cancelamento e esta ancelando..."
	atualizaEstado ERRO
	log ${LOG_PREFIX} "ERRO: ...processo esta cancelado."
	exit -1;
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
retorno_secundario="" #variavel global
source ${ORABATCH_DIR}/meuVivoDW_AtualizaParametros.sh
source ${ORABATCH_DIR}/meuVivoDW_VerificaBatchComErro.sh
############################################################
# Fica em loop ate chegar em SYSDATE-1
############################################################
while true
do
	############################################################
	# Retorna um numerico que indica quantos dias entre hoje e o criterio de pesquisa no meuVivoDW
	# Este valor indica quantos dias faltam processar, o SEGUAUTO processa por dia
	# Desta forma, se o retorno for 10, significa que fazem 10 dias que nao e executado, entao
	# ele sera processado 10 vezes ate encerrar. Lembrando que ele nao pode processar o dia corrente
	# pois executa de madrugada. O dia corrente e retornado como zero.
	############################################################
	#Arquivo que contem a function: meuVivoDW_VerificaBatchComErro
	temPesquisaRetroativa
	quantidadeDias=$?
	
	############################################################
	# Antes de iniciar o processamento devemos verificar se houve erros
	############################################################
	#Recupera o retorno
	#Arquivo que contem a function: meuVivoDW_VerificaBatchComErro
	verificaBatchComErro
	meuVivoDWComErro=$?
	# meuVivoDWComErro = 0 Estado FINALIZADO: terminou bem, entao passar para a proxima data (ate ontem)
	# meuVivoDWComErro = 1 Estado ERRO: Houve erro no processamento, precisa retomar
	# meuVivoDWComErro = 2 Estado PROCESSANDO (nao pode comecar novamente) ou ouve ERRO ao ler o parametro 'MEUVIVODW_ESTADO' em APOIO.PARAMETRO

	# quantidadeDias indica o quanto a data é menor que ontem (D-1)
	# Se for maior ou igual a zero (0) isso indica que nao tem mais data retroativa para processar
	# Menos no caso de erro, pois se estiver processando ontem a variaval quantidadeDias sera zero(0) e meuVivoDWComErro sere dois (2) que indica erro
	# Este caso indica que houve erro ao processar a data de ontem, logo tem que tentar reprocessar novamente
	if [ $quantidadeDias -gt 0 ] || [ $quantidadeDias -eq 0 -a $meuVivoDWComErro -eq 1 ]
	then
		if [ $meuVivoDWComErro -eq 0 ]
		then
			#Atualiza o criterio com +1 dia (apenas para o caso de sucesso)
			atualizaCriterio
			
			# Se meuVivoDWComErro retornar 1, nao vai entrar neste IF, o que indica que o ultimo processamento falhou
			# sendo assim nao atualiza o Criterio, mas continua a processar com a mesma data que esta em APOIO.PARAMETRO
			
			############################################################
			# Atualiza o estado para CHECAGEM, apenas quando a ultima execucao terminou com estado FINALIZADO
			############################################################
			atualizaEtapa CHECAGEM
			
		elif [ $meuVivoDWComErro -eq 2 ]
		then
			log ${LOG_PREFIX} "[$retorno]"
			exit -1
		fi

		#Busca o ultimo criterio de processamento (eh uma data, pois o MEUVIVODW processa por dia)
		buscaCriterioUltimoProcessamento
		criterioParaProcessamento=$retorno
		criterioEmFormatoDDMMYYYY=$retorno_secundario

		############################################################
		# Depois da CHECAGEM, Atualiza o estado para CHECAGEM
		############################################################
		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		log ${LOG_PREFIX} "Ultima Etapa Para Processamento[$ultimaEtapaParaProcessamento]"

		# Atualiza o estado para PROCESSANDO
		atualizaEstado PROCESSANDO
		if [ $ultimaEtapaParaProcessamento == "CHECAGEM" ];
		then
			atualizaEtapa ATENDIMENTOS_PALITOS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "ATENDIMENTOS_PALITOS" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa ATENDIMENTOS_PROTOCOLOS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "ATENDIMENTOS_PROTOCOLOS" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa ATENDIMENTOS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "ATENDIMENTOS" ];
		then
			# A primeira coisa a fazer e apagar a primeira tabela temporaria LOAD.MEUVIVODW_01
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_TRUNCATABELA ${COM_RETORNO} 01
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa CLIENTES
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "CLIENTES" ];
		then
			# A primeira coisa a fazer e apagar a primeira tabela temporaria LOAD.MEUVIVODW_02
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_TRUNCATABELA 1 02
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO}
			atualizaEtapa GERA_ARQUIVO_CLINTE
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "GERA_ARQUIVO_CLINTE" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${SEM_RETORNO} $ARQUIVO_CLIENTE$criterioEmFormatoDDMMYYYY.txt
			mv $ARQUIVO_CLIENTE$criterioEmFormatoDDMMYYYY.txt $ARQUIVO_CLIENTE_LONGO$criterioEmFormatoDDMMYYYY.txt
			atualizaEtapa PALITOS_MARCAS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "PALITOS_MARCAS" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa PALITOS_CONTAS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "PALITOS_CONTAS" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa PALITOS_PESSOAS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "PALITOS_PESSOAS" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa PALITOS_NRTELEFONE
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "PALITOS_NRTELEFONE" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa PALITOS_CONSOLIDADO
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "PALITOS_CONSOLIDADO" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
            atualizaEtapa PALITOS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "PALITOS" ];
		then
			# A primeira coisa a fazer e apagar a primeira tabela temporaria LOAD.MEUVIVODW_02
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_TRUNCATABELA ${COM_RETORNO} 02
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${COM_RETORNO} $criterioParaProcessamento
			atualizaEtapa GERA_ARQUIVO_PROMOS
		fi

		#Busca a ultima etapa, para saber de onde retomar
		buscaEtapaUltimoProcessamento
		ultimaEtapaParaProcessamento=$retorno
		if [ $ultimaEtapaParaProcessamento == "GERA_ARQUIVO_PROMOS" ];
		then
			#Executa a query, se der erro, atualiza para erro e aborta o script
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_$ultimaEtapaParaProcessamento ${SEM_RETORNO} $ARQUIVO_PROMOS$criterioEmFormatoDDMMYYYY.txt
			mv $ARQUIVO_PROMOS$criterioEmFormatoDDMMYYYY.txt $ARQUIVO_PROMOS_LONGO$criterioEmFormatoDDMMYYYY.txt
			# Trunca as tabelas
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_TRUNCATABELA ${COM_RETORNO} 01
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_TRUNCATABELA ${COM_RETORNO} 02

			atualizaEstado FINALIZADO
			log ${LOG_PREFIX} "================================"
			log ${LOG_PREFIX} "FINALIZADO COM SUCESSO"
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
