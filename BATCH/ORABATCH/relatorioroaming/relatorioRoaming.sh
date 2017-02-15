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
export RELROAMING_DIR=${DIR_SHELL}
export RELROAMING_CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------
export RELROAMING_SPOOL_PARAM=${ORABATCH_SPOOL_PARAM}
export RELROAMING_OPTIMIZER_QUERY=${ORABATCH_OPTIMIZER_QUERY}
export RELROAMING_OPTIMIZER_DDL=${ORABATCH_OPTIMIZER_DDL}
export RELROAMING_OPTIMIZER_DML=${ORABATCH_OPTIMIZER_DML}
# ----------------------------------------------------------

# IMPORTANDO CONFIGURACOES
. ${RELROAMING_DIR}/relatorioRoaming.cfg

# ARQUIVO 
export RELROAMING_DATA=`date +"%Y%m%d"`
export RELROAMING_HORA=`date +"%H%M%S"`
export RELROAMING_DIR_SAIDA="${ORABATCH_DIR_DATA_OUT}"
export RELROAMING_ARQ="RELATORIOROAMING_${RELROAMING_DATA}_${RELROAMING_HORA}.txt"
export RELROAMING_ARQ_FULL="RELATORIOROAMING_FULL_${RELROAMING_DATA}_${RELROAMING_HORA}.txt"
export RELROAMING_LOG_PREFIX="RELATORIOROAMING"
export RELROAMING_RELATUALIZACAO="${RELROAMING_DIR}/relatorioRoamingAtualizacao.txt"
export RELROAMING_RELATUALIZACAO_CTL="${RELROAMING_DIR}/relatorioRoamingAtualizacao.ctl"

#---------------------------------------------------
# SPOOL segmentado do relatorio
# usado para evitar o limite do file system (1GB)
#---------------------------------------------------
spoolrelatorio () 
{

    arquivo=$1
    linhaInicial=$2
    linhaFinal=$3
    echo "spool ${arquivo}" > arqspool.sql
    echo "SELECT 'NUMERO DA LINHA;DATA DE ATIVACAO;DATA DE EXPIRACAO;TIPO DE ASSINANTE;CATEGORIA DO ASSINANTE' AS REGISTRO FROM DUAL" >> arqspool.sql
    echo "UNION ALL" >> arqspool.sql
    # echo "SELECT REGISTRO FROM CUSTOMER.TMPRELATORIOROAMING;" >> arqspool.sql
    echo "SELECT AR.CDAREAREGISTRO || LB.NRLINHA || ';' || TO_CHAR (LT.DTHABILITACAO, 'YYYYMMDD') || ';' " >> arqspool.sql
    echo "|| TO_CHAR (LT.DTEXPIRACAO, 'YYYYMMDD') || ';' || TL.IDCLASSIFICACAOTIPOLINHA || ';' || TP.SGTIPOPESSOA AS REGISTRO " >> arqspool.sql
    echo "FROM LINHA.LINHATELEFONICA LT JOIN CUSTOMER.TMPATUALIZACAOROAMING RO ON (RO.RID = LT.ROWID) " >> arqspool.sql
    echo "JOIN APOIO.TIPOLINHA TL USING (IDTIPOLINHA) JOIN LINHA.LINHABASE LB USING (IDLINHABASE) " >> arqspool.sql
    echo "JOIN APOIO.AREAREGISTRO AR USING (IDAREAREGISTRO) JOIN CUSTOMER.PESSOALINHA PL USING (IDLINHATELEFONICA) " >> arqspool.sql
    echo "JOIN CUSTOMER.PESSOADEPARA PDP USING (IDPESSOADEPARA) JOIN CUSTOMER.PESSOA P USING (IDPESSOA) " >> arqspool.sql
    echo "JOIN APOIO.TIPOPESSOA TP USING (IDTIPOPESSOA) WHERE PL.IDTIPORELACIONAMENTO = 2 AND LT.DTHABILITACAO IS NOT NULL " >> arqspool.sql
    echo "AND TP.SGTIPOPESSOA IN ('PF', 'PJ') AND TL.IDCLASSIFICACAOTIPOLINHA IN ('POS', 'PRE', 'CTR') " >> arqspool.sql
    echo "spool off" >> arqspool.sql

    log ${RELROAMING_LOG_PREFIX} "Inicio da gravacao do arquivo ${arquivo}"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${RELROAMING_CONNECTION}
${RELROAMING_OPTIMIZER_QUERY};
execute DBMS_APPLICATION_INFO.SET_MODULE('RELROAMING','CRIACAO ARQUIVO ${arquivo}');
${RELROAMING_SPOOL_PARAM}
@@arqspool.sql
exit
EOF

    ret=$?
    if [ $ret -ne 0 ]
    then
        log ${RELROAMING_LOG_PREFIX} "Erro $ret na gravacao do arquivo ${arquivo}"
        exit 1
    fi

    log ${RELROAMING_LOG_PREFIX} "Movendo o arquivo ${arquivo} para o diretorio de saida"

    mv ${arquivo} ${RELROAMING_DIR_SAIDA}/${arquivo}

    log ${RELROAMING_LOG_PREFIX} "Fim da gravacao do arquivo ${arquivo}"

}


#---------------------------------------------------
# Processo de identificacao de linhas para relatorio
#---------------------------------------------------

cd ${RELROAMING_DIR}

log ${RELROAMING_LOG_PREFIX} "Inicio do processo de geracao de linhas para relatorio"

#---------------------------------------------------
# CRIACAO DE OBJETOS DE BANCO DE DADOS
#---------------------------------------------------
# log  ${RELROAMING_LOG_PREFIX} "Executando script de criacao de tabelas temporarias"
# sqlplus -s /nolog << EOF
# whenever sqlerror exit sql.sqlcode rollback
# connect ${RELROAMING_CONNECTION}
# execute DBMS_APPLICATION_INFO.SET_MODULE('RELROAMING','CRIACAO TABELAS TEMPORARIAS');
# @@relatorioRoamingBanco.sql
# exit
# EOF
#---------------------------------------------------

log ${RELROAMING_LOG_PREFIX} "Carregando o log das ultimas execucoes"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${RELROAMING_CONNECTION}
execute DBMS_APPLICATION_INFO.SET_MODULE('RELROAMING','TRUNCAR_RELATORIO_ATUALIZACAO');
execute CUSTOMER.PKG_RELATORIOROAMING.TRUNCAR_RELATORIO_ATUALIZACAO;
COMMIT;
exit
EOF

log ${RELROAMING_LOG_PREFIX} "sqlldr userid=${RELROAMING_CONNECTION} control=${RELROAMING_RELATUALIZACAO_CTL} bad=${RELROAMING_RELATUALIZACAO_CTL}.bad log=${RELROAMING_RELATUALIZACAO_CTL}.log data=${RELROAMING_RELATUALIZACAO} errors=1000 direct=y"
sqlldr userid=${RELROAMING_CONNECTION} control=${RELROAMING_RELATUALIZACAO_CTL} bad=${RELROAMING_RELATUALIZACAO_CTL}.bad log=${RELROAMING_RELATUALIZACAO_CTL}.log data=${RELROAMING_RELATUALIZACAO} errors=1000 direct=y 

log ${RELROAMING_LOG_PREFIX} "Identificando o modo de execucao"

if [ $RELROAMING_MODOEXECUCAO_FULL -eq 1 ]
then

    log ${RELROAMING_LOG_PREFIX} "Modo de execucao full gerando tabela para relatorio"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${RELROAMING_CONNECTION}
${RELROAMING_OPTIMIZER_QUERY};
${RELROAMING_OPTIMIZER_DDL};
${RELROAMING_OPTIMIZER_DML};
execute DBMS_APPLICATION_INFO.SET_MODULE('RELROAMING','EXTRACAO FULL');
execute CUSTOMER.PKG_RELATORIOROAMING.TRUNCAR_TEMPORARIAS;
execute CUSTOMER.PKG_RELATORIOROAMING.GERA_RELATORIO_FULL;
COMMIT;
exit
EOF
 
    n=$?

    if [ $n -ne 0 ]
    then
      log ${RELROAMING_LOG_PREFIX} "Erro na criacao da tabela do arquivo: ${n}"
      exit 1
    fi

    log ${RELROAMING_LOG_PREFIX} "Fim da criacao de tabela para relatorio"

#    log ${RELROAMING_LOG_PREFIX} "Inicio Criacao de arquivo FULL"
#    spoolrelatorio ${RELROAMING_ARQ_FULL} ${RELROAMING_MODOEXECUCAO_FULL}
#    log ${RELROAMING_LOG_PREFIX} "Fim Criacao de arquivo FULL"

   log ${RELROAMING_LOG_PREFIX} "Excluindo registro de execucoes"
   rm ${RELROAMING_RELATUALIZACAO}
   touch ${RELROAMING_RELATUALIZACAO}

else
    
    log ${RELROAMING_LOG_PREFIX} "Modo de execucao parcial gerando tabela para relatorio dias retroativos de ${RELROAMING_DIA_RETROATIVO_DE} ate ${RELROAMING_DIA_RETROATIVO_ATE}"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${RELROAMING_CONNECTION}
${RELROAMING_OPTIMIZER_QUERY};
${RELROAMING_OPTIMIZER_DDL};
${RELROAMING_OPTIMIZER_DML};
execute DBMS_APPLICATION_INFO.SET_MODULE('RELROAMING','EXTRACAO PARCIAL');
execute CUSTOMER.PKG_RELATORIOROAMING.TRUNCAR_TEMPORARIAS;
execute CUSTOMER.PKG_RELATORIOROAMING.IDENTIFICA_ATUALIZACOES(${RELROAMING_DIA_RETROATIVO_DE},${RELROAMING_DIA_RETROATIVO_ATE},${RELROAMING_MIN_ATUALIZACOES},${RELROAMING_MAX_REPROCESSAMENTO});
COMMIT;
#execute CUSTOMER.PKG_RELATORIOROAMING.GERA_RELATORIO_ATUALIZACOES;
COMMIT;
exit
EOF
    n=$?

    if [ $n -ne 0 ]
    then
      log ${RELROAMING_LOG_PREFIX} "Erro na criacao da tabela do arquivo: ${n}"
      exit 1
    fi

    log ${RELROAMING_LOG_PREFIX} "Fim da criacao de tabela para relatorio"

    log ${RELROAMING_LOG_PREFIX} "Inicio Criacao de arquivo PARCIAL"
    spoolrelatorio ${RELROAMING_ARQ} ${RELROAMING_MODOEXECUCAO_FULL}
    log ${RELROAMING_LOG_PREFIX} "Fim Criacao de arquivo PARCIAL"
    
fi


# Gravando a quantidade de registros processados

echo "spool registrosProcessadosTmp.txt" > arqprocessado.sql
echo "SELECT TO_CHAR(SYSDATE,'YYYYMMDD') || ';' || COUNT(1) AS LINHA FROM CUSTOMER.TMPRELATORIOROAMING;" >> arqprocessado.sql
echo "spool off" >> arqprocessado.sql

log ${RELROAMING_LOG_PREFIX} "Inicio da identificacao da quantidade de registros processados"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${RELROAMING_CONNECTION}
${RELROAMING_OPTIMIZER_QUERY};
execute DBMS_APPLICATION_INFO.SET_MODULE('RELROAMING','QUANTIDADE REGISTROS PROCESSADOS');
${RELROAMING_SPOOL_PARAM}
@@arqprocessado.sql
exit
EOF

    ret=$?
    if [ $ret -ne 0 ]
    then
        log ${RELROAMING_LOG_PREFIX} "Erro $ret na identificacao da quantidade de registros processados"
        exit 1
    fi

cat registrosProcessadosTmp.txt >> ${RELROAMING_RELATUALIZACAO}

log ${RELROAMING_LOG_PREFIX} "Fim do processo"

