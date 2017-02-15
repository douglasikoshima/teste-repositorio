load data
infile 'data/arquivo_grupo.csv'
badfile 'error/arquivo_grupo.bad'
discardfile 'error/arquivo_grupo.dsc'
append into table LOAD.CARGACONTATOGRUPO
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
NMGRUPO,
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
TIPO
)
