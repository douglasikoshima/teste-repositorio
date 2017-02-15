load data
infile 'Data/sapoio_linhaparceiro.txt'
badfile 'Error/sapoio_linhaparceiro.bad'
discardfile 'Error/sapoio_linhaparceiro.dsc'
insert into table sapoio_ow.linhaparceiro
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDLINHAPARCEIRO "sapoio_ow.linhaparceirosq.nextval",
 IDPARCEIRO POSITION(1),
 NRLINHA,
 IDCLASSIFICACAO,
 CDAREAREGISTRO,
 IDUSUARIOALTERACAO "0",
 DTULTIMAALTERACAO "sysdate",
 IDUSUARIOINCLUSAO "0",
 DTINCLUSAO "sysdate"
)
