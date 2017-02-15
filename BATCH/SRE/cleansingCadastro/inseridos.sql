spool inseridos.dat
SELECT SGL_UF||'|'||DSC_LOCALIDADE||'|'||DSC_LOCALIDADE_PRINC||'|'||NUM_CEP||'|'||INAREARURAL||'|'||DSC_ABREV_TIPO_LOGRAD||'|'||DSC_TIPO_LOGRAD||'|'||DSC_ABREV_TITULO_LOGRAD||'|'||DSC_TITULO_LOGRAD||'|'||NOM_LOGRADOURO||'|'||NOM_ABREV_LOGRADOURO||'|'||IND_NUMERACAO_ENDERECO||'|'||FLG_COMPLEMENTO||'|'||NOM_BAIRRO||'|'||NOM_ABREV_BAIRRO||'|'||NUM_INIC||'|'||NUM_FINAL||'|'||IND_LADO_NUMERACAO||'|'||DSC_LOCALIDADE_EX||'|'||DSC_LOCALIDADE_PRINC_EX||'|'||INAREARISCO||'|'||DATAEHORA||'|'||MOTIVO
  FROM ADM_PROJECT.LOG_CARGA_PONTUAL C
  WHERE
  (
	   (C.COD_CEP IS NOT NULL)
    OR (C.COD_BAIRRO IS NOT NULL)
    OR (C.COD_TP_LOGRADOURO IS NOT NULL)
	OR (C.COD_TI_LOGRADOURO IS NOT NULL)
	OR (C.COD_LOGRADOURO IS NOT NULL)
	OR (C.COD_RELACAO IS NOT NULL)
  );
spool off