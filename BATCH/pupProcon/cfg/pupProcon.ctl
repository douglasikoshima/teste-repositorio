LOAD DATA 
APPEND
INTO TABLE A5019660.PROCON
FIELDS TERMINATED BY ';'
 OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
   NRLINHA ,
   DTCADASTRO "to_date(:DTCADASTRO,'dd/mm/yyyy')",
   ESTADO ,
   DTINICIO "to_date(:DTINICIO,'dd/mm/yyyy')"
)
