load data
infile 'Data/sapoio_histlinhainterceptacao.txt'
badfile 'Error/sapoio_histlinhainterceptacao.bad'
discardfile 'Error/sapoio_histlinhainterceptacao.dsc'
append into table sapoio_ow.historicolinhainterceptacao
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDHISTORICOLINHAINTERCEPTACAO "sapoio_ow.historicolinhainterceptacaosq.nextval",
 IDCLASSIFICACAO POSITION(1),
 CDAREAREGISTRO,
 NRLINHA,
 IDUSUARIOINCLUSAOLINHA "0",
 IDUSUARIOINCLUSAOHISTORICO "0",
 DTINCLUSAO "sysdate"
)
