LOAD DATA
INFILE *
BADFILE '../error/usuariosRejeitados.bad'
REPLACE
INTO TABLE LOAD.TMP_USUARIO
FIELDS TERMINATED BY "|"
TRAILING NULLCOLS
(
    SQUSUARIO    	SEQUENCE(MAX, 1),
    LOGIN       	"UPPER(:login)",
    DESBLOQUEIO    	"UPPER(:desbloqueio)",
    REINICIALIZACAO "UPPER(:reinicializacao)",
    LOG_ERRO
)