load data
infile 'Data/sapoio_linhainterceptacao.txt'
badfile 'Error/sapoio_linhainterceptacao.bad'
discardfile 'Error/sapoio_linhainterceptacao.dsc'
insert into table sapoio_ow.linhainterceptacao
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDLINHAINTERCEPTACAO "sapoio_ow.linhainterceptacaosq.nextval",
 NRLINHA POSITION(1),
 IDCLASSIFICACAO,
 CDAREAREGISTRO,
 IDUSUARIOALTERACAO "0",
 DTULTIMAALTERACAO "sysdate",
 IDUSUARIOINCLUSAO "0",
 DTINCLUSAO "sysdate"
)
