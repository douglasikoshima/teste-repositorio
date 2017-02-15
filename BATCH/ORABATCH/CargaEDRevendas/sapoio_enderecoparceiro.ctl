load data
infile 'Data/sapoio_parceiro.txt'
badfile 'Error/sapoio_enderecoparceiro.bad'
discardfile 'Error/sapoio_enderecoparceiro.dsc'
insert into table sapoio_ow.enderecoparceiro
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDENDERECOPARCEIRO "sapoio_ow.enderecoparceirosq.nextval",
 IDPARCEIRO POSITION(1) "TRIM(:IDPARCEIRO)",
 NMTIPOLOGRADOURO FILLER,
 NMLOGRADOURO,
 NMBAIRRO,
 DSMUNICIPIO,
 NMUF,
 INSTATUS,
 IDUSUARIOALTERACAO "0",
 DTULTIMAALTERACAO sysdate,
 IDUSUARIOINCLUSAO "0",
 DTINCLUSAO sysdate
) 
