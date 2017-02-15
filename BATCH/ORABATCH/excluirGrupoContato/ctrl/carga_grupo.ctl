load data
infile 'data/Palitos.csv'
badfile 'error/Palitos.bad'
discardfile 'error/Palitos.dsc'
append into table LOAD.CARGACONTATOGRUPO
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
IDCONTATO,
N1,
TIPO,
NMGRUPO,
N2
)
