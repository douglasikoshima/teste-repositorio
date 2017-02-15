Load data
append INTO TABLE customer.programarelacionamentopos
Fields terminated by "|" Optionally enclosed by '"'
TRAILING NULLCOLS
(
  NRDOCUMENTO,
  NRLINHASOLICITANTE "substr(:nrlinhasolicitante,1,10)",
  DTCADASTRO "to_date(:DTCADASTRO,'dd/mm/yy')",
  DTEXCLUSAO,
  DTULTIMAATUALIZACAO,
  IDUSUARIOATUALIZACAO constant  666,
  IDPESSOAENDERECO constant null,
  IDPROGRAMARELACIONAMENTO  constant 1,
  INTIPOINSCRICAO constant 1
)
