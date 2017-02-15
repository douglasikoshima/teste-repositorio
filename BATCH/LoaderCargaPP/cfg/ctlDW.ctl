Load Data
APPEND
INTO TABLE customer.cargaprogramarelacionamento
Fields terminated by "|" Optionally enclosed by '"'
TRAILING NULLCOLS
(nrdocumento,
nrtelefone "substr(:nrtelefone,1,10)",
dtcadastropr "to_date(:DTCADASTROPR,'dd/mm/yy')",
inpr,
dtatualizacaopr ,
incadastromanual,
nmloginatualizacaopr,
idsistemaorigempr,
nrlinhacontato,
nmloginatualizacaolc ,
idsistemaatualizacaolc )

