#!/bin/bash
############################################################
#  BATCH......: meuVivoDW_Anual
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="MEUVIVODW_ANUAL"
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

#cria os arquivos na pasta tmp para evitar o spool e tambem 
# para evitar o problema do SPOOL do Oracle so aceita 30 cacacteres, entao geramos com nomes curtos e depois fazemos o rename
ARQUIVO_CLIENTE=datatmp/dados_cliente_
ARQUIVO_PROMOS=datatmp/trans_pcte_promos_
ARQUIVO_CLIENTE_LONGO=datatmp/meuvivo_dw_dados_cliente_12meses
ARQUIVO_PROMOS_LONGO=datatmp/meuvivo_dw_listas_trans_pcte_promos_12meses

# Para para o arquivo final
ARQUIVO_CLIENTE_FINAL=data/meuvivo_dw_dados_cliente_12meses
ARQUIVO_PROMOS_FINAL=data/meuvivo_dw_listas_trans_pcte_promos_12meses

#Arquivos temporarios
NOME_ARQUIVO_ATENDIMENTO=dados_atendimentos_
ARQUIVO_ATENDIMENTOS="datatmp/$NOME_ARQUIVO_ATENDIMENTO"
ARQUIVO_ATENDIMENTO_CONSOLIDADO=datatmp/dados_atendimento_consolidado
ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT=datatmp/dados_atendimento_consolidado_split_

#Nome do arquivo de controle
ARQUIVO_DATA_INICIAL=datatmp/arquivo_data_inicial.txt
ARQUIVO_DATA_FINAL=datatmp/arquivo_data_final.txt
ARQUIVO_DATA_PROCESSADA=datatmp/arquivo_data_processada.txt
ARQUIVO_NUMERO_ESTADO=datatmp/arquivo_numero_estado.txt
ARQUIVO_CONTADOR_ARQ=datatmp/arquivo_cont_arquivosCons.txt
ARQUIVO_EM_EXECUCAO=${ORABATCH_DIR}/batche_em_execucao.lock

#Faz a busca por por periodo, a quantidade de dias e determinda por esta variavel
QUANTIDADE_DIAS=5
QUANTIDADE_LINHAS=1000000
QUANTIDADE_DIAS_RETROATIVOS=180

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
# Se receber algum sinal cancelamento aborta o processo e remove o arquivo de lock
############################################################
trap sinal_cancel INT SIGINT

function sinal_cancel() {
	log ${LOG_PREFIX} "ERRO: Processo recebeu sinal de cancelamento e foi cancelado..."
	rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
	exit -1;
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
arquivo="" #variavel global
arquivoInicial="" #variavel global
arquivoProcessado="" #variavel global
arquivoFinal="" #variavel global
dataInicial="" #variavel global
dataProcessada="" #variavel global
dataFinal="" #variavel global
ultimaDataProcessada="" #variavel global
source ${ORABATCH_DIR}/meuVivoDW_Anual_Apoio.sh
source ${ORABATCH_DIR}/meuVivoDW_Anual_Gera_Arquivos.sh
source ${ORABATCH_DIR}/meuVivoDW_Anual_Carga_Arquivos.sh
source ${ORABATCH_DIR}/meuVivoDW_AtualizaParametros.sh

############################################################
# Verifica em que ponto esta entre as datas inicial e final
############################################################
verficaSeEstaEmExecucao ${ARQUIVO_EM_EXECUCAO}
return_code=$?
if [ $return_code != 0 ]
then
	log ${LOG_PREFIX} "======================"
	log ${LOG_PREFIX} "ERRO: ${LOG_PREFIX} esta em execucao.\n\tVerifique se o processo esta no ar, se nao tiver apague o arquivo [$ARQUIVO_EM_EXECUCAO] e execute novamente"
	log ${LOG_PREFIX} "======================"
	exit -1;
fi


############################################################
# Verifica em que ponto esta entre as datas inicial e final
############################################################
verificaDatas
case "$retorno" in
	"SEM_DATA_INICIAL_FINAL")
		criaDataInicialFinal ${ARQUIVO_DATA_INICIAL} ${ARQUIVO_DATA_FINAL} ${QUANTIDADE_DIAS_RETROATIVOS}
		# Se entrou neste CASE entao nunca foi processado e a dataProcessada fica igual a dataInicial
		geraDataProcessada $dataInicial 0 
		dataProcessada=$retorno
		salvaDadoNoArquivo ${ARQUIVO_DATA_PROCESSADA} $dataProcessada
		;;
	"COM_DATA_INICIAL_FINAL")
		log ${LOG_PREFIX} "dataProcessada: [${#dataProcessada}]"
		# Se entrou aqui e nao tem dataProcessada eh porque nao eh o primeiro processamento, logo
		# existe arquivo ARQUIVO_DATA_PROCESSADA
		if [ ${#dataProcessada} -lt 8 ] # vefifica se a data existe (tem 8 caracteres YYYYMMDD)
		then
			buscaDadoNoArquivo ${ARQUIVO_DATA_PROCESSADA}
			$dataProcessada=$retorno
		fi
		#se não entrar no IF e porque ja existe dataProcessada e a funcao verificaDatas achou e retornou
		;;
	*)
		log ${LOG_PREFIX} "Parametro invalido vindo de verificaDatas()"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
		;;
esac

verificaEstadoExiste ${ARQUIVO_NUMERO_ESTADO}

############################################################
#  Faz a pesquisa de 12 meses para tras em partes e vai
#  gerando arquivos, assim consegue retormar de onde parou
############################################################
buscaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO}
estado=$retorno
#So entra aqui se vier do primeiro estado
if [ $estado -eq 1 ]
then
	geraArquivosAtendimentos
	salvaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO} 2
fi

############################################################
#  Como a pesquisa é feito em partes de x dias, e busca palitos
#  gerados pelos clientes, entao podem ocorrer repetencias, que nao sao aceitas
#  neste relatorio, entao os arquivos sao importados para uma tabela
#  onde eh feito um DISTICT e exportado para arquivos novamente (agora sem repetencia)
############################################################
buscaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO}
estado=$retorno
#So entra aqui se os arquivos ja foram gerados
if [ $estado -eq 2 ]
then
	carregaArquivosAtendimentoParaDistinct ${ARQUIVO_ATENDIMENTOS} MEUVIVODW_ANUAL_CARGA_DISTINCT.par
	salvaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO} 3
fi

############################################################
#  As etapas ateriores buscaram os atendimento e seus palitos
#  e consolidaram na tabela para esta funcao gerar arquivos consolidados
############################################################
buscaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO}
estado=$retorno
#So entra aqui se os arquivos ja foram importados para a tabela
if [ $estado -eq 3 ]
then
	geraArquivoAtendimentoConsolidado
	salvaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO} 4
fi

############################################################
#  Cada arquivo consolidado e 'SPLITADO' sera importado para a tabela LOAD.MEUVIVODW_01
#  e os arquivos de cliente e de palitos serao gerados com ela
############################################################
buscaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO}
estado=$retorno
verificaEstadoExiste ${ARQUIVO_CONTADOR_ARQ}

#So entra aqui se os arquivos ja foram importados para a tabela
if [ $estado -eq 4 ]
then
	geraArquivosFinais
	salvaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO} 5
fi

############################################################
#  Cada arquivo consolidado e 'SPLITADO' sera importado para a tabela LOAD.MEUVIVODW_01
#  e um arquivo de cliente e outro de palitos sera gerado com ela
############################################################
buscaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO}
estado=$retorno

#So entra aqui se os arquivos ja foram exportados e processados em arquivos finais menores
if [ $estado -eq 5 ]
then
	#geraArquivosFinaisCons
	salvaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO} 6
fi

############################################################
#  O arquivo consolidado de 'CLIENTE' sera importado para a tabela LOAD.MEUVIVODW_02
#  e um arquivo de cliente sera gerado atraves de DISTINCT
############################################################
buscaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO}
estado=$retorno

#So entra aqui se os arquivos ja foram exportados e processados em arquivos finais, porem efetuaremos um replace no arquivo de clientes
if [ $estado -eq 6 ]
then
	#carregaArquivoClienteParaDistinct ${ARQUIVO_CLIENTE_FINAL}
	geraReplaceArquivoFinaisCliente
	salvaDadoNoArquivo ${ARQUIVO_NUMERO_ESTADO} 7
fi

rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
log ${LOG_PREFIX} "================================"
log ${LOG_PREFIX} "FINALIZADO COM SUCESSO NA ESTADO [$estado]"
log ${LOG_PREFIX} "================================"

