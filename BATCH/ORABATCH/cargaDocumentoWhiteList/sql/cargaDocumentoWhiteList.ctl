
LOAD DATA 
CHARACTERSET WE8ISO8859P1
INFILE *
INTO TABLE CUSTOMER.DOCUMENTOWHITELIST
APPEND
FIELDS TERMINATED BY '|'
TRAILING NULLCOLS
(
	IDTIPODOCUMENTO 		INTEGER EXTERNAL
	,NRDOCUMENTO 			CHAR "TRIM(:NRDOCUMENTO)"
	,IDDOCUMENTOWHITELIST 	"CUSTOMER.DOCUMENTOWHITELISTSQ.NEXTVAL"
	,IDUSUARIOALTERACAO 	"135963"
	,DTULTIMAALTERACAO 		SYSDATE
)