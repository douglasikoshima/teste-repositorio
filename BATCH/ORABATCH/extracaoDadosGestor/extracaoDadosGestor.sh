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
export EXTRACAO_DADOS_GESTOR_DIR=${DIR_SHELL}
export EXTRACAO_DADOS_GESTOR_CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------
export EXTRACAO_DADOS_GESTOR_SPOOL_PARAM=${ORABATCH_SPOOL_PARAM}
export EXTRACAO_DADOS_GESTOR_OPTIMIZER_QUERY=${ORABATCH_OPTIMIZER_QUERY}
export EXTRACAO_DADOS_GESTOR_OPTIMIZER_DDL=${ORABATCH_OPTIMIZER_DDL}
export EXTRACAO_DADOS_GESTOR_OPTIMIZER_DML=${ORABATCH_OPTIMIZER_DML}
# ----------------------------------------------------------

# ARQUIVO 
export EXTRACAO_DADOS_GESTOR_DATA=`date +"%d%m%Y"`
export EXTRACAO_DADOS_GESTOR_DIR_SAIDA="${ORABATCH_DIR_DATA_OUT}"

export EXTRACAO_DADOS_GESTOR_ARQ_GC="GC_BaseGestoresVivonet_${EXTRACAO_DADOS_GESTOR_DATA}.txt"
export EXTRACAO_DADOS_GESTOR_ARQ_GM="GM_BaseGestoresVivonet_${EXTRACAO_DADOS_GESTOR_DATA}.txt"

export EXTRACAO_DADOS_GESTOR_SPOOL_GC="${EXTRACAO_DADOS_GESTOR_DIR}/extracaoDadosGestor.sql"
export EXTRACAO_DADOS_GESTOR_SPOOL_GM="${EXTRACAO_DADOS_GESTOR_DIR}/extracaoDadosGestorMaster.sql"

export EXTRACAO_DADOS_GESTOR_LOG_PREFIX="EXTRACAO_DADOS_GESTOR"
export EXTRACAO_DADOS_GESTOR_CTL="${EXTRACAO_DADOS_GESTOR_DIR}/extracaoDadosGestor.ctl"
export EXTRACAO_DADOS_GESTOR_CPF_CNPJ="${EXTRACAO_DADOS_GESTOR_DIR}/cnpj_cpf.txt"


#---------------------------------------------------
# SPOOL segmentado do relatorio
#---------------------------------------------------
spoolArquivo () 
{
    
    arqSpool=$1
    arqSaida=$2
    
    log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "Inicio da gravacao do arquivo ${arqSaida}"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${EXTRACAO_DADOS_GESTOR_CONNECTION}
${EXTRACAO_DADOS_GESTOR_OPTIMIZER_QUERY};
execute DBMS_APPLICATION_INFO.SET_MODULE('EXTRACAO GESTOR','CRIACAO ARQUIVO ${arqSaida}');
${EXTRACAO_DADOS_GESTOR_SPOOL_PARAM}
@@${arqSpool}
exit
EOF

    ret=$?
    if [ $ret -ne 0 ]
    then
        log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "Erro $ret na gravacao do arquivo ${arqSaida}"
        exit 1
    fi

    log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "Movendo o arquivo ${arqSaida} para o diretorio de saida"

    mv spool.txt ${EXTRACAO_DADOS_GESTOR_DIR_SAIDA}/${arqSaida}

    log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "Fim da gravacao do arquivo ${arqSaida}"

}

loader () {

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${EXTRACAO_DADOS_GESTOR_CONNECTION}
execute DBMS_APPLICATION_INFO.SET_MODULE('EXTRACAO GESTOR','TRUNCAR TABELA DE CARGA');
execute LOAD.TRUNC_CARGAGESTORES;
COMMIT;
exit
EOF

cat ${EXTRACAO_DADOS_GESTOR_CPF_CNPJ} | tr -d '\r' > dados.txt
log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "sqlldr userid=${EXTRACAO_DADOS_GESTOR_CONNECTION} control=${EXTRACAO_DADOS_GESTOR_CTL} bad=${EXTRACAO_DADOS_GESTOR_CTL}.bad log=${EXTRACAO_DADOS_GESTOR_CTL}.log data=${EXTRACAO_DADOS_GESTOR_CPF_CNPJ} errors=1000 direct=y"
sqlldr userid=${EXTRACAO_DADOS_GESTOR_CONNECTION} control=${EXTRACAO_DADOS_GESTOR_CTL} bad=${EXTRACAO_DADOS_GESTOR_CTL}.bad log=${EXTRACAO_DADOS_GESTOR_CTL}.log data=dados.txt errors=1000 direct=y 

}


#---------------------------------------------------
# Processo de identificacao de linhas para relatorio
#---------------------------------------------------

cd ${EXTRACAO_DADOS_GESTOR_DIR}

log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "Inicio do processo de geracao de Extracao"


# Carregando o arquivo com CNPJ e CPF
loader

# Spool GC
spoolArquivo ${EXTRACAO_DADOS_GESTOR_SPOOL_GC} ${EXTRACAO_DADOS_GESTOR_ARQ_GC}

# Spool GM
spoolArquivo ${EXTRACAO_DADOS_GESTOR_SPOOL_GM} ${EXTRACAO_DADOS_GESTOR_ARQ_GM}

log ${EXTRACAO_DADOS_GESTOR_LOG_PREFIX} "Fim do processo"
