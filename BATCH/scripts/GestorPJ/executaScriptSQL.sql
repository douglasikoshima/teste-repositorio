set lin 200
set pages 0
set define off 
set timi off
set echo off

spool resultadoConsultaSQL.txt

SELECT 
C.CDCONTA ||'|'|| -- AS NumeroConta
PG.IDPESSOASISTEMAORIGEM ||'|'|| --AS CNTCT_ID
PG.NMGESTOR ||'|'|| --AS PrimeiroNome
' ' ||'|'|| --AS NomeMeio
PG.NMSOBRENOMEGESTOR ||'|'|| --AS Sobrenome
DECODE(PG.NMMEIOGESTOR,NULL,PG.NRDOCUMENTO,PG.NMMEIOGESTOR) ||'|'|| --AS CPF
PG.NRTELEFONECELULARVIVO ||'|'|| --AS Celular
PG.Email ||'|'|| --as Email
PG.nrtelefonefixo ||'|'|| --as TelefoneFixo
PE.Nmlogradouro ||'|'|| --as NomeRua 
PE.Nrendereco ||'|'|| --as NumeroCasa
PE.Dsenderecocomplemento ||'|'|| --as Complemento
PE.Cdcaixapostal ||'|'|| --as CaixaPostal
PE.Nrcep ||'|'|| --as CEP
PE.Nmmunicipio ||'|'|| --as Municipio
uf.sguf ||'|'|| --as Estado
PE.NMBairro --as Bairro
/* * */
FROM
CUSTOMER.PESSOAGESTOR PG,
CUSTOMER.PESSOAGESTORCONTA PGC,
CUSTOMER.CONTA C,
CUSTOMER.CONTAENDERECO CE,
CUSTOMER.PESSOAENDERECO PE,
APOIO.UF UF
WHERE
PG.NRDOCUMENTO = PGC.NRDOCUMENTO AND
PGC.IDCONTA = C.IDCONTA AND
PGC.IDCONTA = CE.IDCONTA AND
CE.IDPESSOAENDERECO = PE.IDPESSOAENDERECO AND
PE.IDUF = UF.IDUF
order by ce.idconta;

spool off
exit;