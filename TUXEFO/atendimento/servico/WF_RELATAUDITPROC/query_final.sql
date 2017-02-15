SELECT DECODE(AP.NMCOR,'rowTabelaAlertaBaixo','Baixo',DECODE(AP.NMCOR,'rowTabelaAlertaMedio','Medio',DECODE(AP.NMCOR,'rowTabelaAlertaAlto','Alto','Baixo'))) AS Prioridade,
	   TO_CHAR(A.DTABERTURA) AS DTABERTURA,
	   (SELECT CT.NMPATH FROM CONTATOADM.CONTATO CT WHERE CT.IDCONTATO = A.IDCONTATO) AS TIPOPROCESSO,	   
	   (SELECT DECODE(plh.nrLinha, NULL, '',
			'(' || TRIM( TO_CHAR(plh.cdAreaRegistro,'000') ) || ')'
			|| SUBSTR(TO_CHAR(LPAD(plh.nrLinha,8),'99999999'),1,5)
			|| '-' || SUBSTR(TO_CHAR(LPAD(plh.nrLinha,8),'99999999'),6,8)) AS TERMINAL
			 FROM atendimento.AtendimentoLinha al,
		     customer.pessoalinhahistorico plh
		WHERE GC.idAtendimento = al.idAtendimento
		AND plh.idPessoaLinhaHistorico = al.idPessoaLinhaHistorico
		) AS TERMINAL,   
	   (SELECT AC1.NMFALANDOCOM FROM ATENDIMENTO.ATENDIMENTOCONTATO AC1 WHERE AC1.IDATENDIMENTO = GC.IDATENDIMENTO) AS FALANDOCOM,
	   (SELECT U2.NMLOGINUSUARIOCTI AS SUPERVISOR
	      FROM ACESSO.USUARIO U2,
		       ATENDIMENTO.ATENDIMENTOCRI ACRI
	     WHERE ACRI.IDATENDIMENTO  = GC.IDATENDIMENTO
		   AND ACRI.IDPESSOAUSUARIO = U2.IDPESSOAUSUARIO
		   AND ROWNUM < 2
	   ) AS  NOME_CONSULTOR,
	   (SELECT ABKO.DTENTRADA FROM ATENDIMENTO.ATENDIMENTOGRUPOBKO ABKO WHERE ABKO.IDATENDIMENTO = GC.IDATENDIMENTO) AS DTENCAMINHAMENTO,
	   (SELECT U2.NMLOGINUSUARIOCTI
	      FROM ACESSO.USUARIO U2,
		       ATENDIMENTO.ATENDIMENTOGRUPOATUAL AGA1,
			   ACESSO.USUARIOGRUPO UG1
	     WHERE AGA1.IDATENDIMENTO  = GC.IDATENDIMENTO
		   AND AGA1.IDGRUPO        = UG1.IDGRUPO
		   AND UG1.IDPESSOAUSUARIO = U2.IDPESSOAUSUARIO
		   AND AGA1.INCRI          = 0
		   AND UG1.INSUPERVISOR    = 1
		   AND ROWNUM < 2
	   ) AS SUPERVISOR_ATUAL,
	   (SELECT U3.NMLOGINUSUARIOCTI
	      FROM ACESSO.USUARIO U3,
		       ATENDIMENTO.ATENDIMENTOUSUARIOATUAL AGA2
	     WHERE AGA2.IDATENDIMENTO  = GC.IDATENDIMENTO
		   AND U3.IDPESSOAUSUARIO = AGA2.IDPESSOAUSUARIO
	   ) AS RESPONSAVEL_ATUAL,
   	   ROUND((TO_DATE(TO_CHAR(NVL(((SELECT MAX(AGBKO.DTSAIDA) AS DTS FROM ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO WHERE AGBKO.IDATENDIMENTO = GC.IDATENDIMENTO)), SYSDATE))) - ((SELECT MIN(AGBKO2.DTENTRADA) FROM ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO2 WHERE AGBKO2.IDATENDIMENTO = GC.IDATENDIMENTO )))*24) AS TEMPO_TRATAMENTO,
	   NVL((SELECT AGBKO.QTINTERCAMBIO FROM ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO WHERE AGBKO.IDATENDIMENTO = GC.IDATENDIMENTO),0) AS QTDEINTERCAMBIO,
	   DECODE(A.QTINSISTENCIA, NULL, NULL,A.QTINSISTENCIA) AS QTINSISTENCIA,
	   GC.IDGRUPO AS IDGRUPO,
	   ACRI.IDPESSOAUSUARIO AS IDCONSULTOR,
	   AR.IDUFOPERADORA AS IDUFOPERADORA
  FROM ATENDIMENTO.GRUPOCRI GC,
  	   ATENDIMENTO.ATENDIMENTOPRIORIZACAO AP,
	   ATENDIMENTO.ATENDIMENTO A,
	   ATENDIMENTO.ATENDIMENTOCRI ACRI,   	   
	   CUSTOMER.PESSOALINHAHISTORICO PLH,
       APOIO.AREAREGISTRO AR   	   
 WHERE GC.IDATENDIMENTO             =    AP.IDATENDIMENTO
   AND GC.IDATENDIMENTO             =    A.IDATENDIMENTO
   AND ACRI.IDPESSOALINHAHISTORICO  =    GC.IDPESSOALINHAHISTORICO
   AND ACRI.IDATENDIMENTO           =    GC.IDATENDIMENTO
   AND AR.CDAREAREGISTRO            =    PLH.CDAREAREGISTRO
   AND PLH.IDPESSOALINHAHISTORICO   =    GC.IDPESSOALINHAHISTORICO

	   		   

	   

