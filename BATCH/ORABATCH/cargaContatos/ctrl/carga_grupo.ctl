load data
infile 'data/arquivo_grupo.csv'
badfile 'error/arquivo_grupo.bad'
discardfile 'error/arquivo_grupo.dsc'
append into table LOAD.CARGACONTATOGRUPO
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
NMGRUPO,
N1,
N2,
N3,
N4,
N5
)
