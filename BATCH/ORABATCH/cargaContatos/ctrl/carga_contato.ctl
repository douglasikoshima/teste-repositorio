load data
infile 'data/arquivo_contato.csv'
badfile 'error/arquivo_contato.bad'
discardfile 'error/arquivo_contato.dsc'
append into table LOAD.CARGACONTATO
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
SGTIPOCLIENTE,
DSCODIGO,
TIPO,
DSHITORICO,
DSCLASSIFICACAO,
N1,
N2,
N3,
N4,
N5
)
