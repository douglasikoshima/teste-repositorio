#!/bin/bash
############################################################
#  BATCH......: cargaDadosCorreiosSRE
#  Criado por.: Denis Hideki 04/03/2016
#  Funcional..: Loiana Moura
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="cargaDadosCorreiosSRE"
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


LOG_HOJE=`date +"%d%m%Y"`
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"
# O arquivo que vem do DW tem esta estrutura: DW_DDMMAAAAHHMISS.txt
ARQUIVO_BASE=
# Variaveis para controle de retorno do SQLPLUS
SEM_RETORNO=0
COM_RETORNO=1
# Arquivo para controle de execucao
ARQUIVO_EM_EXECUCAO=cargaDadosCorreiosSRE.lock


############################################################
# Desviando o SYSLOG para o caso de erro no BATCH
############################################################
exec 1> $SYSLOGFILE
exec 2>> $SYSLOGFILE

log ${LOG_PREFIX} "Limpando Arquivos (UNIX)" 
find ${ORABATCH_DIR_DATA_IN}/${ARQUIVO_BASE}/ -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

find ${ORABATCH_DIR_DATA_IN}/${ARQUIVO_BASE}/ -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \; 


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
	log ${LOG_PREFIX} "====================================="
	log ${LOG_PREFIX} "ERRO: Processo recebeu sinal de cancelamento e esta cancelando..."
	processoEmExecucao "PARAR"
	log ${LOG_PREFIX} "====================================="
	log ${LOG_PREFIX} "PROCESSO FOI INTERROMPIDO COM EXIT -1"
	log ${LOG_PREFIX} "====================================="
	exit -1;
}


############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
retorno_secundario="" #variavel global
source ${ORABATCH_DIR}/cargaDadosCorreiosSRE_db_functions.sh
source ${ORABATCH_DIR}/cargaDadosCorreiosSRE_files_functions.sh



############################################################
# Antes de iniciar o processamento verifica se ja esta em execucao
# O valor de retorno vai para a variavel $retorno
############################################################
processoEmExecucao "ESTADO"
if [ ${retorno} = "PARADO" ]
then
	# Cria o arquivo de lock, que indica a execucao para evitar dupla execucao
	processoEmExecucao "INICIAR"
	# Le os arquivos para carga, enviados pelo DW
	log ${LOG_PREFIX} "LN110"
	# Limpa tabela temporarias
	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/truncateTempTables ${SEM_RETORNO} ${LOG_PREFIX}_TRUNCTEMP_${LOG_HOJE}.log

	for arquivo in ${ORABATCH_DIR_DATA_IN}/${ARQUIVO_BASE}*.[Tt][Xx][Tt]
	do

		log ${LOG_PREFIX} "LN113"
		# Verifica se o arquivo existe, isso ocorre porque se o for..in quando não achar retorna um arquivo invalido
		if [ -f ${arquivo} ]
		then

			varArquivo=`basename ${arquivo}`
			dirArquivo=`dirname ${arquivo}`
			log ${LOG_PREFIX} "LN120: varArquivo = ${varArquivo}" 

			if [ $varArquivo = "DNE_GU_BAIRROS.TXT" ]
			then

				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${varArquivo}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosBairro.par ${arquivo}

			elif [ $varArquivo = "DNE_GU_LOCALIDADES.TXT" ] 
			then
			
				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${varArquivo}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosLocalidade.par ${arquivo}

			elif [ $varArquivo = "DNE_GU_TIPOS_LOGRADOURO.TXT" ] 
			then

				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${varArquivo}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosTipoLogradouro.par ${arquivo}

			elif [ $varArquivo = "DNE_GU_TITULOS_PATENTES.TXT" ] 
			then

				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${varArquivo}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosTituloLogradouro.par ${arquivo}

			elif [ $varArquivo = "DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT" ]
			then

				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${varArquivo}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosCaixaPostalComunitaria.par ${arquivo}

			elif [ $varArquivo = "DNE_GU_UNIDADES_OPERACIONAIS.TXT" ]
			then
				firstRow=0
				cont=0
				row_par=''

				#Tratamento do arquivo para o SQL Loader
				arquivo_old=$dirArquivo"/DNE_GU_UNIDADES_OPERACIONAIS_OLD.TXT"
				mv $arquivo $arquivo_old
				arquivo_new=$dirArquivo"/DNE_GU_UNIDADES_OPERACIONAIS.TXT"

				IFS=''
				cat $arquivo_old | while read row; 
				do
					if [ $firstRow == 0 ]
					then
					 	echo "${row}" > "${arquivo_new}"
						firstRow=1
					else
						if [ $cont == 0 ]
						then
							row_par=$row
							cont=1
						else
							row_par=$row_par$row
							echo "${row_par}" >> "${arquivo_new}"
							cont=0
						fi
					fi
				done
				
				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${arquivo_new}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosCentroDistribuicao.par ${arquivo_new}

				#Remove o arquivo original
				rm -f $arquivo_old
				
			elif [ $varArquivo = "DNE_GU_GRANDES_USUARIOS.TXT" ]
			then
				firstRow=0
				cont=0
				row=''

				#Tratamento do arquivo para o SQL Loader
				arquivo_old=$dirArquivo"/DNE_GU_GRANDES_USUARIOS_OLD.TXT"
				mv $arquivo $arquivo_old
				arquivo_new=$dirArquivo"/DNE_GU_GRANDES_USUARIOS.TXT"

				IFS=''
				cat $arquivo_old | while read row; 
				do
					if [ $firstRow == 0 ]
					then
					 	echo "${row}" > "${arquivo_new}"
						firstRow=1
					else
						if [ $cont == 0 ]
						then
							row_par=$row
							cont=1
						else
							row_par=$row_par$row
							echo "${row_par}" >> "${arquivo_new}"
							cont=0
						fi
					fi
				done

				log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${arquivo_new}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosGrandesUsuarios.par ${arquivo_new}
				
				#Remove o arquivo original
				rm -f $arquivo_old
				
			elif [ ${varArquivo:0:7}${varArquivo:10} = "DNE_GU_LOGRADOUROS.TXT" ] 
			then
				
                log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${varArquivo}"

				# Faz a carga do arquivo e processa
				cargaArquivos ${ORABATCH_DIR_SQL}/cargaDadosLogradouros.par ${arquivo}

				#done
		
			fi

			############################################################
			# Fim do relatorio
			############################################################
			log ${LOG_PREFIX} "================================"
			log ${LOG_PREFIX} "Arquivo processado: ${arquivo}"
			log ${LOG_PREFIX} "================================"
		else
			log ${LOG_PREFIX} "======================================="
			log ${LOG_PREFIX} "ARQUIVO NAO ENCONTRADO PARA PROCESSAR"
			log ${LOG_PREFIX} "======================================="
		fi
	done


	##################################
	# EXECUTA PACKAGE CARGA CORREIOS
	##################################
	log ${LOG_PREFIX} "========================================="
	log ${LOG_PREFIX} "INICIO EXECUTA PACKAGE CARGA CORREIOS"
	log ${LOG_PREFIX} "========================================="

	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/cargaDadosCorreiosSRE ${SEM_RETORNO} ${LOG_PREFIX}_PKG_${LOG_HOJE}.log

	#${ORABATCH_DIR_LOG}/${arquivo_sem_extensao}-NAO-ENCONTADO.log
	log ${LOG_PREFIX} "========================================="
	log ${LOG_PREFIX} "FIM EXECUTA PACKAGE CARGA CORREIOS"
	log ${LOG_PREFIX} "========================================="



	##################################
	# EXECUTA RELATORIOS CARGA
	##################################

	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Inicio relCargaCdDNECep"
	log ${LOG_PREFIX} "================================"

	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/relCargaCdDNECep ${SEM_RETORNO}
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Fim relCargaCdDNECep"
	log ${LOG_PREFIX} "================================"



	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Inicio relCargaCdDNELimpeza"
	log ${LOG_PREFIX} "================================"

	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/relCargaCdDNELimpeza ${SEM_RETORNO}
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Fim relCargaCdDNELimpeza"
	log ${LOG_PREFIX} "================================"



	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Inicio relCargaCdDNETotal"
	log ${LOG_PREFIX} "================================"

	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/relCargaCdDNETotal ${SEM_RETORNO}
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Fim relCargaCdDNETotal"
	log ${LOG_PREFIX} "================================"
	

	# Apaga o arquivo de lock, que indica a execucao
	processoEmExecucao "PARAR"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "TERMINOU COM SUCESSO"
	log ${LOG_PREFIX} "================================"
else
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "PROCESSO EM EXECUCAO"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "	Verifique se o processo esta em execucao: ps -ef | grep cargaDadosCorreiosSRE"
	log ${LOG_PREFIX} "	Se não estiver em execucao apague o arquivo [${ARQUIVO_EM_EXECUCAO}] e dispare o BATCH novamente"
	log ${LOG_PREFIX} "================================"
fi
