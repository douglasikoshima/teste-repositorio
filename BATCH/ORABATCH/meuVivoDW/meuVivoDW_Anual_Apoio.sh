#!/bin/bash
############################################################
#  BATCH......: meuVivoDW
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################


############################################################
# Verifica se o arquivo de estado existe, se existe nao faz nada
# se nao existe cria um com estado 1
# Este arquivo e importante para o batche saber onde parou e de onde continuar
############################################################
function verificaEstadoExiste () {
	log ${LOG_PREFIX} "->> verificaEstadoExiste"
	arquivo=$1
	log ${LOG_PREFIX} "\tArquivo: [$arquivo]"
	if [ -f "$arquivo" ]
	then
		log ${LOG_PREFIX} "\tArquivo [$arquivo] encontrado. Nenhuma acao requirida."
	else
		log ${LOG_PREFIX} "\tArquivo [$arquivo] nao encontrado."
		echo 1 > $arquivo
		log ${LOG_PREFIX} "\tArquivo criado com estado [1]."
	fi
	log ${LOG_PREFIX} "<<- verificaEstadoExiste"
}

############################################################
# Salva os dados em um arquivo
############################################################
function salvaDadoNoArquivo () {
	log ${LOG_PREFIX} "->> salvaDadoNoArquivo"
	arquivo=$1
	dados=$2
	log ${LOG_PREFIX} "\tArquivo: [$arquivo]"
	log ${LOG_PREFIX} "\tDados..: [$dados]"
	tamanho=${#dados}
	echo $dados > $arquivo
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel gravar isto [$dados] no arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- salvaDadoNoArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- salvaDadoNoArquivo"
}

############################################################
# Abre o arquivo e retorna TODO o conteudo para uma variavel
############################################################
function buscaDadoNoArquivo() {
	log ${LOG_PREFIX} "->> buscaDadoNoArquivo"
	# Busca o ultimo estado de processamento 
	arquivo=$1
	retorno=`cat $arquivo`
	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel ler o arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- buscaDadoNoArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi
	log ${LOG_PREFIX} "\tDado lido no arquivo [$retorno]"
	log ${LOG_PREFIX} "<<- buscaDadoNoArquivo"
}

############################################################
# Realiza um ZIP no arquivo afim de comprimir e diminuir o 
# tamanho de espaco alocado em disco
############################################################
function comprimeArquivo() {
	log ${LOG_PREFIX} "->> comprimeArquivo"
	nomeArquivo=$1
	arquivo=$2
	dirArquivo=`dirname ${arquivo}`
	
	gzip -f $arquivo

	if [ `find ./$dirArquivo -name  $nomeArquivo.gz` == "./$arquivo.gz" ]
	then
			log ${LOG_PREFIX} "\tArquivo [$nomeArquivo.gz] processado"
			retorno="$arquivo"
			return_code=0
	else
			return_code=1
	fi

	# Verifica se houve erro no processo 
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel comprimir o arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- comprimeArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi
	log ${LOG_PREFIX} "\tArquivo [$retorno] comprimido"
	log ${LOG_PREFIX} "<<- comprimeArquivo"
}

############################################################
# Realiza um UNZIP no arquivo afim de descomprimir um arquivo
# que esteja comprimido
############################################################
function descomprimeArquivo() {
	log ${LOG_PREFIX} "->> descomprimeArquivo"
	nomeArquivo=$1
	arquivo=$2

	gunzip $arquivo.gz

	if [ `find . -name  $nomeArquivo` == "./$arquivo" ]
	then
			log ${LOG_PREFIX} "\tArquivo [$nomeArquivo] processado"
			retorno="$arquivo"
			return_code=0
	else
			return_code=1
	fi

	# Verifica se houve erro no processo 
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel descomprimir o arquivo [$arquivo.gz]"
		log ${LOG_PREFIX} "<<- descomprimeArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi
	log ${LOG_PREFIX} "\tArquivo [$retorno.gz] descomprimido"
	log ${LOG_PREFIX} "<<- descomprimeArquivo"
}

############################################################
# Abre o arquivo e retorna TODO o conteudo para uma variavel
############################################################
function verificaDatas() {
	log ${LOG_PREFIX} "->> verificaDatas"
	retorno="COM_DATA_INICIAL_FINAL" #As 3 datas existem
	dataInicial=`cat ${ARQUIVO_DATA_INICIAL}`
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tNao foi possivel ler o arquivo [${ARQUIVO_DATA_INICIAL}]"
		retorno="SEM_DATA_INICIAL_FINAL" #Data INICIAL e FINAL devem sempre existir
	fi
	dataFinal=`cat ${ARQUIVO_DATA_FINAL}`
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tNao foi possivel ler o arquivo [${ARQUIVO_DATA_FINAL}]"
		retorno="SEM_DATA_INICIAL_FINAL" #Data INICIAL e FINAL devem sempre existir
	fi
	dataProcessada=`cat ${ARQUIVO_DATA_PROCESSADA}`
	return_code=$?
	if [ ${#dataProcessada} -lt 8 ] && [ ${#dataInicial} -ge 8 ]
	then
		log ${LOG_PREFIX} "\tNao foi possivel ler o arquivo [${ARQUIVO_DATA_PROCESSADA}]"
		# Se não existe a data de processamento, entao cria com a data inicial
		dataProcessada=$dataInicial
		salvaDadoNoArquivo ${ARQUIVO_DATA_PROCESSADA} ${dataProcessada}
	fi
	# Verifica se houve erro no sqlplus 
	log ${LOG_PREFIX} "\tData Inicial......:[$dataInicial]"
	log ${LOG_PREFIX} "\tData Processamento:[$dataProcessada]"
	log ${LOG_PREFIX} "\tData Final........:[$dataFinal]"
	log ${LOG_PREFIX} "\tRetorno:[$retorno]"
	log ${LOG_PREFIX} "<<- verificaDatas"
}


############################################################
# Cria um parametro temporario com a data inicial de pesquisa
############################################################
function criaDataInicialFinal () {
	log ${LOG_PREFIX} "->> criaDataInicialFinal"
	arquivoInicial=$1
	arquivoFinal=$2
	diasRetroativos=$3

	log ${LOG_PREFIX} "\tNome do arquivo com Inicial: [$arquivoInicial]"
	log ${LOG_PREFIX} "\tNome do arquivo com Final..: [$arquivoFinal]"
	# Cria a data de inicio do relatorio (12 meses para tras)
	dataInicial=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
		SET PAGESIZE 0
		SET LINESIZE 32766
		SET FEEDBACK OFF
		SET VERIFY OFF
		SET HEADING OFF
		SET ECHO OFF
		SET COLSEP "|"
		SET TAB OFF
		SET PAGESIZE 0
		SET TRIMS ON
		SET WRAP OFF
		SET HEADING OFF
		SET SERVEROUTPUT ON
		WHENEVER SQLERROR EXIT SQL.SQLCODE
		
		SELECT TRIM(TO_CHAR(SYSDATE-$diasRetroativos, 'YYYYMMDD')) AS DATA FROM DUAL;
		
		EXIT;
		EOF`
	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel formatar a data INICIAL no banco de dados [$dataInicial]"
		log ${LOG_PREFIX} "<<- criaDataInicialFinal"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	dataFinal=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
		SET PAGESIZE 0
		SET LINESIZE 32766
		SET FEEDBACK OFF
		SET VERIFY OFF
		SET HEADING OFF
		SET ECHO OFF
		SET COLSEP "|"
		SET TAB OFF
		SET PAGESIZE 0
		SET TRIMS ON
		SET WRAP OFF
		SET HEADING OFF
		SET SERVEROUTPUT ON
		WHENEVER SQLERROR EXIT SQL.SQLCODE
		
		SELECT TRIM(TO_CHAR(SYSDATE, 'YYYYMMDD')) AS DATA FROM DUAL;
		
		EXIT;
		EOF`
	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel formatar a data FINAL no banco de dados [$dataFinal]"
		log ${LOG_PREFIX} "<<- criaDataInicialFinal"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	echo $dataInicial > $arquivoInicial
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel gravar a data INICIAL [$dataInicial] no arquivo [$arquivoInicial]"
		log ${LOG_PREFIX} "<<- criaDataInicialFinal"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	echo $dataFinal > $arquivoFinal
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel gravar a data FINAL [$dataFinal] no arquivo [$arquivoFinal]"
		log ${LOG_PREFIX} "<<- criaDataInicialFinal"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "\tData INICIAL: [$dataInicial] gravada no arquivo [$arquivoInicial] com sucesso!!"
	log ${LOG_PREFIX} "\tData FINAL..: [$dataFinal] gravada no arquivo [$arquivoFinal] com sucesso!!"
	log ${LOG_PREFIX} "<<- criaDataInicialFinal"
}

############################################################
# Cria um parametro temporario com a data inicial de pesquisa
############################################################
function geraDataProcessada () {
	log ${LOG_PREFIX} "->> atualizaDataProcessada"
	dataRecebida=$1
	quantidadeDias=$2
	log ${LOG_PREFIX} "\tQuantidade de dias que serao incrementados: [$quantidadeDias]"
	log ${LOG_PREFIX} "\tData recebida como parametro: [$dataRecebida]"
	# Cria a data de inicio do relatorio (12 meses para tras)
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
		SET PAGESIZE 0
		SET LINESIZE 32766
		SET FEEDBACK OFF
		SET VERIFY OFF
		SET HEADING OFF
		SET ECHO OFF
		SET COLSEP "|"
		SET TAB OFF
		SET PAGESIZE 0
		SET TRIMS ON
		SET WRAP OFF
		SET HEADING OFF
		SET SERVEROUTPUT ON
		WHENEVER SQLERROR EXIT SQL.SQLCODE
		
		SELECT TRIM(TO_CHAR(TO_DATE(${dataRecebida}, 'YYYYMMDD')+${quantidadeDias}, 'YYYYMMDD')) AS DATA FROM DUAL;
		
		EXIT;
		EOF`
	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel incremetar a data atraves do  banco de dados"
		log ${LOG_PREFIX} "\t      Data do ultimo processamento [$dataRecebida]"
		log ${LOG_PREFIX} "\t      Quantidade de dias para incrementar [$quantidadeDias]"
		log ${LOG_PREFIX} "<<- atualizaDataProcessada"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	if [ $dataProcessada -lt $dataFinal ]
	then
		dataProcessada=$dataFinal
	fi
	log ${LOG_PREFIX} "\tData incrementada[$retorno]"
	log ${LOG_PREFIX} "<<- atualizaDataProcessada"
}

function verficaSeEstaEmExecucao {
	log ${LOG_PREFIX} "->> verficaSeEstaEmExecucao"
	#Arquivo recebido como parametro
	arquivo=$1
	# Se não achar o arquivo retorna zero
	retorno=0
	log ${LOG_PREFIX} "\tArquivo: [$arquivo]"
	if [ -f "$arquivo" ]
	then
		log ${LOG_PREFIX} "\t${LOG_PREFIX} EM execucao. \n\tArquivo [$arquivo] encontrado. "
		retorno=1
	else
		echo 1 > $arquivo
		log ${LOG_PREFIX} "\t${LOG_PREFIX} NAO esta em execucao. \n\tArquivo de controle de execucao criado [${arquivo}]."
	fi
	log ${LOG_PREFIX} "<<- verficaSeEstaEmExecucao"
	return $retorno
}
