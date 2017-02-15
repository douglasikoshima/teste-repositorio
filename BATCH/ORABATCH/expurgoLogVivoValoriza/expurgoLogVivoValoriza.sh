#!/bin/bash

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 

. ./orabatch.cfg
############################################################

############################################################
# DIRETORIO DE EXECUCAO E PARAMETROS DE BANCO 
############################################################
# ----------------------------------------------------------
export DIR_EXEC=`pwd`
export DIR_PENDENTE=${DIR_EXEC}/pendente
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------

# ARQUIVO 
export DATA_ATUAL=`date +"%d%m%Y"`
export LOG_PREFIX="expurgoLogVivoValoriza"
export LOG_HOJE=`date +"%Y%m%d"`
export LOG_ARQUIVO="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.log"
export LOG_SYSOUT="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.sysout.log"

# HORA LIMITE EXECUCAO (0 desabilita)
export HORA_LIMITE=0

#---------------------------------------------------
# LOG do processo
#---------------------------------------------------
# @param  $1 Nome do arquivo de log sem extensao
# @param  $2 Texto a ser incluido no arquivo
#---------------------------------------------------
log ()
{
    
    LOG_AGORA=`date +"%H:%M:%S"`
    echo "${LOG_AGORA} - ${1}" >> "${LOG_ARQUIVO}"
}

#---------------------------------------------------
# Processo de inclusao no programa de relacionamento
#---------------------------------------------------

exec 1> ${LOG_SYSOUT}
exec 2>> ${LOG_SYSOUT}

cd ${DIR_EXEC}

log "Inicio do processo para expurgo"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${CONNECTION}
DECLARE

	TYPE TB_IDLOGERRO IS TABLE OF NUMBER;
	ARR_IDLOGERRO TB_IDLOGERRO;

	CURSOR C_LOG(P_EXPURGO_DIAS NUMBER) IS
		SELECT IDLOGERRO FROM VIVOVALORIZA.LOGERRO WHERE DTOCORRENCIA < TRUNC(SYSDATE-P_EXPURGO_DIAS);

	V_DIAS_RETROATIVOS 	NUMBER;
	V_TOTAL_ERRO		NUMBER := 0;   
	V_COUNT 			NUMBER := 0;
	V_LIMIT 			NUMBER := 150;
	
BEGIN

	SELECT TO_NUMBER(DSVALORPARAMETRO) INTO V_DIAS_RETROATIVOS FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'EXPURGO_LOG_VV';

	SELECT COUNT(1) INTO V_TOTAL_ERRO FROM VIVOVALORIZA.LOGERRO WHERE DTOCORRENCIA < TRUNC(SYSDATE-V_DIAS_RETROATIVOS);

	DBMS_APPLICATION_INFO.SET_MODULE('EXPURGO LOG VV DIAS: ' || V_DIAS_RETROATIVOS || ', TOTAL:  '  || V_TOTAL_ERRO,'EXPURGO');

	OPEN C_LOG(V_DIAS_RETROATIVOS);
	LOOP
		FETCH C_LOG BULK COLLECT INTO ARR_IDLOGERRO LIMIT V_LIMIT;
		
		V_COUNT := V_COUNT+V_LIMIT;

		DBMS_APPLICATION_INFO.SET_ACTION( 'PROCESSO - PROC: ' || V_COUNT);
		FORALL I IN 1..ARR_IDLOGERRO.COUNT
			DELETE
			  FROM VIVOVALORIZA.LOGERROPROCESSO
 			 WHERE IDLOGERRO = ARR_IDLOGERRO(I);
 			 COMMIT;

 		DBMS_APPLICATION_INFO.SET_ACTION( 'INDEFINIDO - PROC: ' || V_COUNT);
		FORALL J IN 1..ARR_IDLOGERRO.COUNT
			DELETE
			  FROM VIVOVALORIZA.LOGERROINDEFINIDO
 			 WHERE IDLOGERRO = ARR_IDLOGERRO(J);
 			 COMMIT;

 		DBMS_APPLICATION_INFO.SET_ACTION( 'ERRO - PROC: ' || V_COUNT);
		FORALL K IN 1..ARR_IDLOGERRO.COUNT
			DELETE
			  FROM VIVOVALORIZA.LOGERRO
 			 WHERE IDLOGERRO = ARR_IDLOGERRO(K);
 			 COMMIT;

 		EXIT WHEN ARR_IDLOGERRO.COUNT < V_LIMIT;

 	END LOOP;

 	CLOSE C_LOG;

END;
/
exit
EOF

n=$?
if [ $n -ne 0 ]
then
	log "Erro no  expurgo ${n}"
fi

exit $n

