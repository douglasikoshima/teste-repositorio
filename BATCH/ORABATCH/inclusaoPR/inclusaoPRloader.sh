#!/bin/bash

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 
# . ../orabatch.cfg
. ./orabatch.cfg
############################################################

############################################################
# DIRETORIO DE EXECUCAO E PARAMETROS DE BANCO 
############################################################
# ----------------------------------------------------------
export DIR_EXEC=${DIR_SHELL}
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------
export SPOOL_PARAM=${ORABATCH_SPOOL_PARAM}
export OPTIMIZER_QUERY=${ORABATCH_OPTIMIZER_QUERY}
export OPTIMIZER_DDL=${ORABATCH_OPTIMIZER_DDL}
export OPTIMIZER_DML=${ORABATCH_OPTIMIZER_DML}
# ----------------------------------------------------------

# ARQUIVO 
export DATA_ATUAL=`date +"%d%m%Y"`
export LOG_PREFIX="INCLUSAO_PR_LOADER"

#---------------------------------------------------
# Processo de inclusao no programa de relacionamento
#---------------------------------------------------

cd ${DIR_EXEC}

log ${LOG_PREFIX} "Inicio do da inclusaode clientes pos e controle"

cat INCLUSAO_PR.dat | tr -d '\r' > INCLUSAO_PR2.dat
rm -f INCLUSAO_PR.dat
mv INCLUSAO_PR2.dat INCLUSAO_PR.dat

sqlldr userid=${CONNECTION} control=inclusaoPRloader.ctl bad=inclusaoPRloader.bad log=inclusaoPRloader.log data=INCLUSAO_PR.dat errors=9999999 direct=n 

log ${LOG_PREFIX} "Fim do processo"

