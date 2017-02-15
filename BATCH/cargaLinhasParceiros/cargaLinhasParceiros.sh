#!/bin/bash
############################################################
#  BATCH......: CargaLinhasParceiros
#  Criado por.: Eder Jani Martins (19/02/2015)
#  Funcional..: Marcia Santana Silva
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="CARGALINHASPARCEIROS"
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
SEM_RETORNO=0
COM_RETORNO=1
SQLLDR=${ORACLE_HOME}/bin/sqlldr

############################################################
# Nomes gerais utilizados
############################################################
PASTA_ARQUIVO_DADOS=data
PASTA_ARQUIVO_PROCESSADO=prc
CARGA_MASSIVA_ARQUIVO_BASE=${PASTA_ARQUIVO_DADOS}/CARGA_MASSIVA_LINHAS_

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
# Se pressionar Ctrl+C mudo o estado para ERRO
############################################################
trap ctrl_c INT

function ctrl_c() {
	log ${LOG_PREFIX} "ERRO: CTRL+C foi pressionado e este BATCH ira encerrar o processamento..."
	exit -1;
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
source ${ORABATCH_DIR}/cargaLinhasParceiros_Arquivo.sh
source ${ORABATCH_DIR}/cargaLinhasParceiros_Carga_Arquivos.sh
source ${ORABATCH_DIR}/cargaLinhasParceiros_Queries.sh

############################################################
# Iniciando o processo
############################################################
log ${LOG_PREFIX} "================================"
log ${LOG_PREFIX} "Iniciando o processamento dos arquivos [${CARGA_MASSIVA_ARQUIVO_BASE}*.txt]"
log ${LOG_PREFIX} "================================"
for arquivo in ${CARGA_MASSIVA_ARQUIVO_BASE}*.[tT][xX][tT]
do
	############################################################
	# Le o conteudo do arquivo pode gerar 4 arquivos de saida
	# dependendo do primeiro campo (acao) e do ultimo campo (parceiro)
	# os arquivo podem terminar com estas estensoes: 
	#		".I.csv"      Inclusao sem parceiro
	#		".I.PAR.csv"  Inclusao com parceiro
	#		".R.csv"      exclusao sem parceiro
	#		".R.PAR.csv"  exclusao com parceiro
	############################################################
	if [ -f "${arquivo}" ]
	then
		#So processa se achar arquivos
		splitArquivo ${arquivo}
		nome_arquivo=`basename $arquivo .txt`		
		#A funcao acima altera a variavel arquivo_base
		# Salva o nome do arquivo em outra variavel, pois é utilizada em outras funcoes
		nome_arquivo_base="${arquivo_base}"
		arquivo_para_processar="${nome_arquivo_base}.R.csv"
		if [ -f "${arquivo_para_processar}" ]
		then
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tIniciando o processamento do arquivo ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
			# Trunca a tabela que recebera as linhas do arquivo na integra (LOAD.CARGALINHASPARCEIROS_01)
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 01
			# Carrega o arquivo para a tabela trucada
			carregaArquivo ${arquivo_para_processar} ${ORABATCH_DIR_SQL}/CARGALIPAR_CARGA_ARQUIVO.par
			# Trunca a segunda tabela para gerar o consolidado para exclusao (LOAD.CARGALINHASPARCEIROS_02)
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 02
			# Executa a exclusao com os dados consolidados
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_EXCLUSAO ${COM_RETORNO} ${ORABATCH_DIR} ${nome_arquivo}
			
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tRemovendo o arquivo ${arquivo_para_processar}"
			rm ${arquivo_para_processar}
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tArquivo ${arquivo_para_processar} processado com sucesso"
			log ${LOG_PREFIX} "\t--------------------------------"
		else
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tNAO ACHOU O ARQUIVO ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
		fi
		arquivo_para_processar="${nome_arquivo_base}.R.PAR.csv"
		if [ -f "${arquivo_para_processar}" ]
		then
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tIniciando o processamento do arquivo ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
			# Trunca a tabela que recebera as linhas do arquivo na integra (LOAD.CARGALINHASPARCEIROS_01)
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 01
			# Carrega o arquivo para a tabela trucada
			carregaArquivo ${arquivo_para_processar} ${ORABATCH_DIR_SQL}/CARGALIPAR_CARGA_ARQUIVO.par
			# Trunca a segunda tabela para gerar o consolidado para exclusao (LOAD.CARGALINHASPARCEIROS_02)
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 02
			# Executa a exclusao com os dados consolidados
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_EXCLUSAO_PAR ${COM_RETORNO} ${ORABATCH_DIR} ${nome_arquivo}
						
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tRemovendo o arquivo ${arquivo_para_processar}"
			rm ${arquivo_para_processar}
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tArquivo ${arquivo_para_processar} processado com sucesso"
			log ${LOG_PREFIX} "\t--------------------------------"
		else
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tNAO ACHOU O ARQUIVO ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
		fi
		arquivo_para_processar="${nome_arquivo_base}.I.csv"
		if [ -f "${arquivo_para_processar}" ]
		then
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tIniciando o processamento do arquivo ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
			# Trunca a tabela que recebera as linhas do arquivo na integra (LOAD.CARGALINHASPARCEIROS_01)
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 01
			# Carrega o arquivo para a tabela trucada
			carregaArquivo ${arquivo_para_processar} ${ORABATCH_DIR_SQL}/CARGALIPAR_CARGA_ARQUIVO.par
			# Executa a inclusao com os dados consolidados
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_INCLUSAO ${COM_RETORNO}
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tRemovendo o arquivo ${arquivo_para_processar}"
			rm ${arquivo_para_processar}
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tArquivo ${arquivo_para_processar} processado com sucesso"
			log ${LOG_PREFIX} "\t--------------------------------"
		else
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tNAO ACHOU O ARQUIVO ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
		fi
		arquivo_para_processar="${nome_arquivo_base}.I.PAR.csv"
		if [ -f "${arquivo_para_processar}" ]
		then
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tIniciando o processamento do arquivo ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
			# Trunca a tabela que recebera as linhas do arquivo na integra (LOAD.CARGALINHASPARCEIROS_01)
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 01
			# Carrega o arquivo para a tabela trucada
			carregaArquivo ${arquivo_para_processar} ${ORABATCH_DIR_SQL}/CARGALIPAR_CARGA_ARQUIVO.par
			# Executa a inclusao com os dados consolidados
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_INCLUSAO_PAR ${COM_RETORNO}
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tRemovendo o arquivo ${arquivo_para_processar}"
			rm ${arquivo_para_processar}
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tArquivo ${arquivo_para_processar} processado com sucesso"
			log ${LOG_PREFIX} "\t--------------------------------"
		else
			log ${LOG_PREFIX} "\t--------------------------------"
			log ${LOG_PREFIX} "\tNAO ACHOU O ARQUIVO ${arquivo_para_processar}"
			log ${LOG_PREFIX} "\t--------------------------------"
		fi
	else
		log ${LOG_PREFIX} "\t--------------------------------"
		log ${LOG_PREFIX} "\tArquivo nao encontrado [${arquivo}]"
		log ${LOG_PREFIX} "\t--------------------------------"
	fi
done
# Antes de encerrar trunca ambas as tabelas de load
executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 01
executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/CARGALIPAR_TRUNCATABELA ${COM_RETORNO} 02
log ${LOG_PREFIX} "================================"
log ${LOG_PREFIX} "TERMINOU SEM EXIT"
log ${LOG_PREFIX} "================================"
