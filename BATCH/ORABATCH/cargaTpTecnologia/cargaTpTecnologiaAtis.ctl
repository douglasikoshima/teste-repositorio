load data
infile 'data/cargaTpTecnologiaAtis.txt'
badfile 'error/cargaTpTecnologiaAtis.bad'
discardfile 'error/cargaTpTecnologiaAtis.dsc'
append into table STA_MIG.TMPTIPOTECNOLOGIA 
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDLINHASISTEMAORIGEM,
 CDTIPOTECNOLOGIA,
 IDSISTEMAORIGEM,
 IDTIPOTECNOLOGIA
)
