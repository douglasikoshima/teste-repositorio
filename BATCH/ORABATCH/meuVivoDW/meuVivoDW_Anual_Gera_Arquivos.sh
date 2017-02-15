#!/bin/bash
############################################################
#  BATCH......: meuVivoDW_Anual
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################

############################################################
#  Esta funcao faz uma pesquisa anual a partir de uma data e vai gerando arquivos
#  em disco, isto eh assim porque sera executado em D-1, que cai todos os dias
#  entM-co precisa ir gerando ao poucos e ter a capacidade de retormar onde parou
############################################################

function geraArquivosAtendimentos () {
	log ${LOG_PREFIX} "->> geraArquivosAtendimentos"

	log ${LOG_PREFIX} "\t==============================================="
	log ${LOG_PREFIX} "\tIniciando a geracao de arquivos"
	log ${LOG_PREFIX} "\t==============================================="
	log ${LOG_PREFIX} "\tData inicial.........: [$dataInicial]"
	log ${LOG_PREFIX} "\tData de processamento: [$dataProcessada]"
	log ${LOG_PREFIX} "\tData final...........: [$dataFinal]"
	log ${LOG_PREFIX} "\t==============================================="

	while true
	do
			if [ $dataProcessada -le $dataFinal ]
			then
					log ${LOG_PREFIX} "\tIniciando processamento com a data: [$dataProcessada]"
					executaQuerySemGravarEstado ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_GERA_ARQUIVO_ATENDIMENTOS ${SEM_RETORNO} ${ARQUIVO_ATENDIMENTOS}${dataProcessada}.txt ${dataProcessada} ${QUANTIDADE_DIAS} ${dataFinal}
					#Comprime arquivo para alocar menos espaco
					comprimeArquivo ${NOME_ARQUIVO_ATENDIMENTO}${dataProcessada}.txt ${ARQUIVO_ATENDIMENTOS}${dataProcessada}.txt
					# Depois de processado e o arquivo de saida gerado, faz um update na data processada
					log ${LOG_PREFIX} "\tData: [$dataProcessada], arquivo gerado: [${ARQUIVO_ATENDIMENTOS}${dataProcessada}.txt]"
					geraDataProcessada $dataProcessada ${QUANTIDADE_DIAS}
					dataProcessada=$retorno
					log ${LOG_PREFIX} "\tData processada atualizada: [$dataProcessada]"
					salvaDadoNoArquivo ${ARQUIVO_DATA_PROCESSADA} $dataProcessada
			else
					log ${LOG_PREFIX} "\t================================"
					log ${LOG_PREFIX} "\tSAIU DO LOOP SEM PROCESSAMENTO NESTA ITERACAO"
					log ${LOG_PREFIX} "\tdataInicial...:[$dataInicial]"
					log ${LOG_PREFIX} "\tdataProcessada:[$dataProcessada]"
					log ${LOG_PREFIX} "\tdataFinal.....:[$dataFinal]"
					log ${LOG_PREFIX} "\t================================"
					break
			fi #if [ dataProcessada < dataFinal ]
	done
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "\tGeracao de arquivo terminada"
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "<<- geraArquivosAtendimentos"
}

function geraArquivoAtendimentoConsolidado () {
	log ${LOG_PREFIX} "->> geraArquivoAtendimentoConsolidado"
	executaQuerySemGravarEstado ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_GERA_ARQUIVO_CONSOLIDADO ${SEM_RETORNO} ${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt
	return_code=$?
	if [ $return_code != 0 ]
	then
			log ${LOG_PREFIX} "\tERRO: Nao foi possivel exportar a tabela LOAD.MEUVIVODW_01 para o arquivo [${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt]"
			rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
			exit -1;
	fi
	log ${LOG_PREFIX} "\tArquivo gerado: [${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt]"
	log ${LOG_PREFIX} "\tIniciando o split do arquivo [${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt] em varios arquivos de ${QUANTIDADE_LINHAS} linhas"
	split -l ${QUANTIDADE_LINHAS} ${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}
	return_code=$?
	if [ $return_code != 0 ]
	then
			log ${LOG_PREFIX} "\tERRO: split do arquivo [${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt] falhou"
			rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
			exit -1;
	fi
	for arquivo in ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}*
	do
			ret="ls -l ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}a*"
			$ret
			if [ $? != 0 ]
			then
				log ${LOG_PREFIX} "\tNao existem dados, assumindo ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}aa.dat"
				cp -f ${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}aa.dat

				#comprime o arquivo
				nomeArquivo=`basename ${arquivo}.dat`
				comprimeArquivo ${nomeArquivo} ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}aa.dat
			else
				log ${LOG_PREFIX} "\tRenomeando [mv ${arquivo} ${arquivo}.dat]"
				mv ${arquivo} ${arquivo}.dat

				#comprime o arquivo
				nomeArquivo=`basename ${arquivo}.dat`
				comprimeArquivo ${nomeArquivo} ${arquivo}.dat
			fi
	done
	log ${LOG_PREFIX} "\tSplit terminado com sucesso"
	log ${LOG_PREFIX} "\tApagando o arquivo inteiro: [rm ${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt]"
	rm ${ARQUIVO_ATENDIMENTO_CONSOLIDADO}.txt
	log ${LOG_PREFIX} "\tArquivo apagado"
	log ${LOG_PREFIX} "<<- geraArquivoAtendimentoConsolidado"
}

function geraArquivosFinais () {
	log ${LOG_PREFIX} "->> geraArquvosFinais"
	buscaDadoNoArquivo ${ARQUIVO_CONTADOR_ARQ}
	contador=$retorno

	for arquivos in ${ARQUIVO_ATENDIMENTO_CONSOLIDADO_SPLIT}*.dat.gz
	do
			# Trunca a tabela LOAD.MEUVIVODW_01
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 01

			#descomprime o arquivo
			nomeArquivo=`basename ${arquivos}`
			arquivo=${arquivos%.*}
			descomprimeArquivo ${nomeArquivo%.*} ${arquivo}

			log ${LOG_PREFIX} "\tSQL*Loader - Iniciando carga do arquivo consolidado: [$arquivo]"
			${SQLLDR} userid=${ORABATCH_CONNECTION} parfile=${ORABATCH_DIR}/sql/MEUVIVODW_ANUAL_CARGA_DISTINCT.par data=${ORABATCH_DIR}/${arquivo} log=${ORABATCH_DIR}/${arquivo}.log bad=${ORABATCH_DIR}/${arquivo}.bad discard=${ORABATCH_DIR}/${arquivo}.dsc
			RETCODE=$?
			case "${RETCODE}" in
			0)
					log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_OK"
					# A primeira coisa a fazer e apagar a primeira tabela temporaria LOAD.MEUVIVODW_02
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 02
					#Executa a query, se der erro, atualiza para erro e aborta o script
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_CLIENTES ${COM_RETORNO}
					#Executa a query, se der erro, atualiza para erro e aborta o script
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_GERA_ARQUIVO_CLINTE ${SEM_RETORNO} ${ARQUIVO_CLIENTE}${contador}.txt
					log ${LOG_PREFIX} "\tmv ${ARQUIVO_CLIENTE}${contador}.txt ${ARQUIVO_CLIENTE_LONGO}_${contador}.txt"
					mv ${ARQUIVO_CLIENTE}${contador}.txt ${ARQUIVO_CLIENTE_LONGO}_${contador}.txt
					# Trunca a tabela LOAD.MEUVIVODW_02, pois serM-a utilizada novamente para a segunda fase
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 02
					#Executa a query, se der erro, atualiza para erro e aborta o script
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_PALITOS_MARCAS
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_PALITOS_CONTAS
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_PALITOS_PESSOAS
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_PALITOS_NRTELEFONE
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_PALITOS_CONSOLIDADO
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_PALITOS ${COM_RETORNO}
					#Executa a query, se der erro, atualiza para erro e aborta o script
					executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_GERA_ARQUIVO_PROMOS ${SEM_RETORNO} ${ARQUIVO_PROMOS}${contador}.txt
					log ${LOG_PREFIX} "\tmv ${ARQUIVO_PROMOS}${contador}.txt ${ARQUIVO_PROMOS_LONGO}_${contador}.txt"
					mv ${ARQUIVO_PROMOS}${contador}.txt ${ARQUIVO_PROMOS_LONGO}_${contador}.txt
					log ${LOG_PREFIX} "\trm ${ORABATCH_DIR}/${arquivo}"
					rm ${ORABATCH_DIR}/${arquivo}
					# Se sucesso apaga o arquivo de log, nao sera necessario
					rm -f ${ORABATCH_DIR}/${arquivo}.log 2> /dev/null

					contador=$((contador+1))
					salvaDadoNoArquivo ${ARQUIVO_CONTADOR_ARQ} $contador
					;;
			1)
					log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_FAIL, verifique logfile"
					;;
			2)
					log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_WARN"
					;;
			3)
					log ${LOG_PREFIX} "\tSQL*Loader - ERRO fatal na execucao"
					;;
			*)
					log ${LOG_PREFIX} "\tSQL*Loader - ERRO desconhecido"
					;;
			esac

			if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
			then
					log ${LOG_PREFIX} "<<- geraArquvosFinais"
					rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
					exit ${RETCODE}
			fi
			log ${LOG_PREFIX} "\tArquivo processado com sucesso!!"
	done
	
	# Terminou, trunca as tabelas
	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 01
	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 02

	log ${LOG_PREFIX} "<<- geraArquvosFinais"
}

function geraArquivosFinaisCons () {
	log ${LOG_PREFIX} "->> geraArquvosFinaisCons"
	log ${LOG_PREFIX} "\tJuntando os arquivos [${ARQUIVO_CLIENTE_LONGO}*] para o arquivo final ${ARQUIVO_CLIENTE_LONGO}.txt"
	cat ${ARQUIVO_CLIENTE_LONGO}* > ${ARQUIVO_CLIENTE_LONGO}.txt
	log ${LOG_PREFIX} "\tMovendo ${ARQUIVO_CLIENTE_LONGO}.txt para ${ARQUIVO_CLIENTE_FINAL}.txt"
	mv ${ARQUIVO_CLIENTE_LONGO}.txt ${ARQUIVO_CLIENTE_FINAL}.txt
	log ${LOG_PREFIX} "\tApagando arquivos [${ARQUIVO_CLIENTE_LONGO}*]"
	rm -f ${ARQUIVO_CLIENTE_LONGO}*

	#comprime o arquivo
	nomeArquivo=`basename ${ARQUIVO_CLIENTE_FINAL}.txt`
	comprimeArquivo ${nomeArquivo} ${ARQUIVO_CLIENTE_FINAL}.txt

	log ${LOG_PREFIX} "\tJuntando os arquivos [${ARQUIVO_PROMOS_LONGO}*] para o arquivo final ${ARQUIVO_PROMOS_LONGO}.txt"
	cat ${ARQUIVO_PROMOS_LONGO}* > ${ARQUIVO_PROMOS_LONGO}.txt
	log ${LOG_PREFIX} "\tMovendo ${ARQUIVO_PROMOS_LONGO}.txt para ${ARQUIVO_PROMOS_FINAL}.txt"
	mv ${ARQUIVO_PROMOS_LONGO}.txt ${ARQUIVO_PROMOS_FINAL}.txt
	log ${LOG_PREFIX} "\tApagando arquivos [${ARQUIVO_PROMOS_LONGO}*]"
	rm -f ${ARQUIVO_PROMOS_LONGO}*
	
	#comprime o arquivo
	nomeArquivo=`basename ${ARQUIVO_PROMOS_FINAL}.txt`
	comprimeArquivo ${nomeArquivo} ${ARQUIVO_PROMOS_FINAL}.txt

	log ${LOG_PREFIX} "<<- geraArquvosFinaisCons"
}

function geraReplaceArquivoFinaisCliente () {
	log ${LOG_PREFIX} "->> geraReplaceArquivoFinaisCliente"
	log ${LOG_PREFIX} "\tReplace com Distinct para o arquivo final ${ARQUIVO_CLIENTE_FINAL}.txt"
	
	# A primeira coisa a fazer e apagar a primeira tabela temporaria LOAD.MEUVIVODW_02
	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 02
	
	for arquivos in ${ARQUIVO_CLIENTE_LONGO}*.txt
	do
		#nomeArquivo=`basename $arquivos`
		carregaArquivoClienteParaDistinct ${arquivos}
	done

	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_GERA_ARQUIVO_CLINTE_FINAL ${SEM_RETORNO} ${ARQUIVO_CLIENTE_FINAL}.txt

	#comprime o arquivo
	nomeArquivo=`basename ${ARQUIVO_CLIENTE_FINAL}.txt`
	comprimeArquivo ${nomeArquivo} ${ARQUIVO_CLIENTE_FINAL}.txt

	log ${LOG_PREFIX} "<<- geraReplaceArquivoFinaisCliente"
}
