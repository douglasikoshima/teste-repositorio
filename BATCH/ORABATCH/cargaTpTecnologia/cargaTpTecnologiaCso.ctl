load data
infile 'data/cargaTpTecnologiaCso.txt'
badfile 'error/cargaTpTecnologiaCso.bad'
discardfile 'error/cargaTpTecnologiaCso.dsc'
append into table STA_MIG.TMPTIPOTECNOLOGIA 
fields terminated by ";" optionally enclosed by '"'
TRAILING NULLCOLS
(
 IDLINHASISTEMAORIGEM,
 CDTIPOTECNOLOGIA,
 IDSISTEMAORIGEM,
 IDTIPOTECNOLOGIA
)
