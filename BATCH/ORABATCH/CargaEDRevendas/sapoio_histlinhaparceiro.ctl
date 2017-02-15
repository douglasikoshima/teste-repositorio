load data
infile 'Data/sapoio_histlinhaparceiro.txt'
badfile 'Error/sapoio_histlinhaparceiro.bad'
discardfile 'Error/sapoio_histlinhaparceiro.dsc'
append into table sapoio_ow.historicolinhaparceiro
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDHISTORICOLINHAPARCEIRO "sapoio_ow.historicolinhaparceirosq.nextval",
 IDPARCEIRO POSITION(1),
 IDCLASSIFICACAO,
 CDAREAREGISTRO,
 NRLINHA,
 IDUSUARIOINCLUSAOLINHA "0",
 IDUSUARIOINCLUSAOHISTORICO "0",
 DTINCLUSAO "sysdate"
)
