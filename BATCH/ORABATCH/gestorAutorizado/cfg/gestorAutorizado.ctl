LOAD DATA 
APPEND
INTO TABLE CUSTOMER.AUTORIZADOGESTORPJ
FIELDS TERMINATED BY ';'
 OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
  NRDOCUMENTO     "rtrim(:NRDOCUMENTO,chr(13))",
  NRDOCUMENTOEMPRESA "rtrim(:NRDOCUMENTOEMPRESA,chr(13))",
  INATIVO              CONSTANT 1 ,
  IDUSUARIOALTERACAO  CONSTANT 1 ,
  DTULTIMAATUALIZACAO   SYSDATE
)