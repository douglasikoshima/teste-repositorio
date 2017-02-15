#!/bin/bash
############################################################
#  BATCH......: CargaLinhasParceiros
#  Criado por.: Eder Jani Martins (19/02/2015)
#  Funcional..: Marcia Santana Silva
############################################################

function splitArquivo () {
	log ${LOG_PREFIX} "->> splitArquivo"
	arquivo_origem=$1
	log ${LOG_PREFIX} "\tArquivo de entrada [${arquivo_origem}]"
	arquivo_base="${arquivo_origem%.*}"
	log ${LOG_PREFIX} "\tArquivo base [${arquivo_base}]"
	IFS_SALVO="${IFS}"
	quantidade_linhas=0
	IFS=";"
	log ${LOG_PREFIX} "\tRemovendo eventuais arquivos ${ORABATCH_DIR}/data/*.I.csv ${ORABATCH_DIR}/data/*.I.PAR.csv ${ORABATCH_DIR}/data/*.R.csv ${ORABATCH_DIR}/data/*.R.PAR.txt ${arquivo_base}.err"
	rm ${ORABATCH_DIR}/data/*.I.csv ${ORABATCH_DIR}/data/*.I.PAR.csv ${ORABATCH_DIR}/data/*.R.csv ${ORABATCH_DIR}/data/*.R.PAR.csv ${arquivo_base}.err 2> /dev/null
	log ${LOG_PREFIX} "Removendo ^M do arquivo ${arquivo_origem} para ${arquivo_base}.prcm"
	cat -v ${arquivo_origem} | sed 's/\^M//g' >> ${arquivo_base}.prcm
	log ${LOG_PREFIX} "\tIniciando o processamento"
	while read acao nrlinha classificacao parceiro
	do
		ddd=${nrlinha:0:2}
		linha=${nrlinha:2}
		quantidade_linhas=$((quantidade_linhas+1))
		#log ${LOG_PREFIX} "\tacao: [${acao}]"
		#log ${LOG_PREFIX} "\tnrlinha: [${nrlinha}]"
		#log ${LOG_PREFIX} "\tclassificacao: [${classificacao}]"
		#log ${LOG_PREFIX} "\tparceiro: [${parceiro}]"
		case "${acao}" in
			I|i)
				if [ ${#parceiro} -eq 0 ]
				then
					echo "${ddd};${linha};${classificacao}" >> ${ORABATCH_DIR}/${arquivo_base}.I.csv
				else
					echo "${ddd};${linha};${classificacao};${parceiro}" >> ${ORABATCH_DIR}/${arquivo_base}.I.PAR.csv
				fi
				;; 
			R|r)
				if [ ${#parceiro} -eq 0 ]
				then
					echo "${ddd};${linha};${classificacao}" >> ${ORABATCH_DIR}/${arquivo_base}.R.csv
				else
					echo "${ddd};${linha};${classificacao};${parceiro}" >> ${ORABATCH_DIR}/${arquivo_base}.R.PAR.csv
				fi
				;; 
			*)
				echo "${acao};${ddd};${linha};${classificacao};${parceiro};Layout inconsistente" >> ${ORABATCH_DIR}/${arquivo_base}.err
				;; 
		esac
	done < ${ORABATCH_DIR}/${arquivo_base}.prcm
	log ${LOG_PREFIX} "\tRemovendo arquivo temporario ${arquivo_base}.prcm"
	rm ${ORABATCH_DIR}/${arquivo_base}.prcm
	log ${LOG_PREFIX} "\tProcessamento terminado"
	IFS="${IFS_SALVO}"
	# troca a pasta data/ por prc/
	arquivo_destino="${arquivo_base/${PASTA_ARQUIVO_DADOS}/${PASTA_ARQUIVO_PROCESSADO}}.prc"
	#So move se achar arquivos
	if [ -f "${ORABATCH_DIR}/${arquivo_origem}" ]
	then
		log ${LOG_PREFIX} "\tMovendo este arquivo para a pasta de arquivos processados [${ORABATCH_DIR}/${arquivo_origem}]"
		mv ${ORABATCH_DIR}/${arquivo_origem} ${ORABATCH_DIR}/${arquivo_destino}
		log ${LOG_PREFIX} "\tArquivo movido para [${ORABATCH_DIR}/${arquivo_destino}]"
	fi
	if [ -f "${ORABATCH_DIR}/${arquivo_base}.err" ]
	then
		log ${LOG_PREFIX} "\tMovendo este arquivo de erro para a pasta de log [${ORABATCH_DIR}/${arquivo_base}.err]"
    if [ -f "${ORABATCH_DIR_ERR}/${arquivo_base}.err" ]
    then
        cat ${ORABATCH_DIR}/${arquivo_base}.err >> ${ORABATCH_DIR_ERR}/${arquivo_base}.err 
        rm ${ORABATCH_DIR}/${arquivo_base}.err
    else
        mv ${ORABATCH_DIR}/${arquivo_base}.err ${ORABATCH_DIR_ERR}    
    fi		
		log ${LOG_PREFIX} "\tArquivo movido para [${ORABATCH_DIR_ERR}]"    
	fi
	log ${LOG_PREFIX} "\tQuantidade de linhas processadas: [${quantidade_linhas}]"
	log ${LOG_PREFIX} "<<- splitArquivo"
}



