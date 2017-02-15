load data
infile 'data/arquivo_contato.csv'
badfile 'error/arquivo_contato.bad'
discardfile 'error/arquivo_contato.dsc'
append into table LOAD.CARGACONTATO
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
CDLEGADON1,
N1,
CDLEGADON2,
N2,
CDLEGADON3,
N3,
CDLEGADON4,
N4,
CDLEGADON5,
N5,
DSHITORICO,
CDCLASIFICACAO,
DSCLASSIFICACAO,
DSSISTEMA,
DSSERVICO,
DSCODIGO,
DSPROCEDENCIA,
SGTIPOCLIENTE,
SEG,
INOSBD,
TIPO
)
