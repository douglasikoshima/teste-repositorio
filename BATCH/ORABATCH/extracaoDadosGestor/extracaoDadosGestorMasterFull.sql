spool spool.txt
SELECT
    /*+ parallel(t1,8) parallel(t3,8) parallel(c,8) */
    T1.NMGESTOR ||'|'|| 
    T1.NMSOBRENOMEGESTOR ||'|'||
    T1.NMPESSOAGESTOR ||'|'||
    'GM' ||'|'|| -- AS sigla
    T1.NRDOCUMENTO ||'|'||
    T1.NRTELEFONECELULARVIVO ||'|'||
    T1.NRTELEFONEFIXO ||'|'||
    T3.NRDOCUMENTOEMPRESA ||'|'|| -- AS nrdocumentoempresa
    T1.EMAIL ||'|'||
    '' ||'|'|| -- AS idconta
    T1.IDPESSOASISTEMAORIGEM ||'|'||
    TO_CHAR(T1.DTULTIMAALTERACAO,'DD/MM/YYYY HH24:MI:SS') ||'|'||
    TO_CHAR(T3.DTULTIMAALTERACAO,'DD/MM/YYYY HH24:MI:SS')
FROM
    CUSTOMER.PESSOAGESTOR T1,
    CUSTOMER.PESSOAGESTORMASTER T3
WHERE 
    T1.NRDOCUMENTO = T3.NRDOCUMENTOGESTOR
    ;
spool off;