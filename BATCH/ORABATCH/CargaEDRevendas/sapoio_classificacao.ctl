load data
infile 'Data/sapoio_classificacao.txt'
badfile 'Error/sapoio_classificacao.bad'
discardfile 'Error/sapoio_classificacao.dsc'
insert into table sapoio_ow.classificacao
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 idclassificacao,
 dsclassificacao,
 cdvdn,
 instatus "1",
 inlinhaparceiro "to_char('PA')",
 idusuarioalteracao "0",
 dtultimaalteracao "sysdate",
 idusuarioinclusao "0",
 dtinclusao "sysdate"
)