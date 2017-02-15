LOAD DATA
APPEND
INTO TABLE ADM_PROJECT.LOG_CARGA_PONTUAL
FIELDS TERMINATED BY '|'
TRAILING NULLCOLS
(
	SGL_UF,
    DSC_LOCALIDADE,
    DSC_LOCALIDADE_PRINC,	
    NUM_CEP,
    INAREARURAL,
    DSC_ABREV_TIPO_LOGRAD,	
    DSC_TIPO_LOGRAD,
    DSC_ABREV_TITULO_LOGRAD,
    DSC_TITULO_LOGRAD,
    NOM_LOGRADOURO,
    NOM_ABREV_LOGRADOURO,	
    IND_NUMERACAO_ENDERECO,	
    FLG_COMPLEMENTO,
    NOM_BAIRRO,
    NOM_ABREV_BAIRRO,
    NUM_INIC,
    NUM_FINAL,
    IND_LADO_NUMERACAO,
    DSC_LOCALIDADE_EX,
    DSC_LOCALIDADE_PRINC_EX,
    INAREARISCO
)