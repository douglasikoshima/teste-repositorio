LOAD DATA
INFILE *
BADFILE '../error/acessosRejeitados.bad'
REPLACE
INTO TABLE LOAD.TMP_ACESSO
FIELDS TERMINATED BY "|"
TRAILING NULLCOLS
(
    SQACESSO    SEQUENCE(MAX, 1),
    OPERACAO    "UPPER(:operacao)",
    LOGIN       "UPPER(:login)",
    NMACESSO    "UPPER(:nmacesso)",
    CDIDENT     "UPPER(:cdident)",
    LOG_ERRO
)