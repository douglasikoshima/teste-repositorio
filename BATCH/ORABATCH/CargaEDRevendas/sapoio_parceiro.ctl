load data
infile 'Data/sapoio_parceiro.txt'
badfile 'Error/sapoio_parceiro.bad'
discardfile 'Error/sapoio_parceiro.dsc'
append into table sapoio_ow.parceiro
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDPARCEIRO "TRIM(:IDPARCEIRO)",
 NMPARCEIRO,
 endereco FILLER,
 bairro FILLER,
 cidade FILLER,
 uf FILLER,
 INSTATUS,
 IDCLASSIFICACAO,
 CDADABAS,
 IDCANALATENDIMENTO,
 CDDOMINIOGERIDO,
 IDUSUARIOINCLUSAO "0",
 DTINCLUSAO "sysdate",
 IDUSUARIOALTERACAO "0",
 DTULTIMAALTERACAO "sysdate"
)
