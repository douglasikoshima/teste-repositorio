/******************************************************************************************************************
 ******************************************************************************************************************
 **********  DOCUMENTA��O SOBRE AS CONSULTAS DO RELAT�RIO DE AUDITORIA DO PROCESSOS CRI                  ********** 
 **********                                                                                              **********
 **********     De acordo com a documenta��o analisada, o Relat�rio de Auditoria dos Processos CRI       **********
 **********  tem tr�s campos obrigat�rios:                                                               **********
 **********       - Regional (n�o permitida a escolha m�ltipla);                                         ********** 
 **********       - Grupo (n�o permitida a escolha m�ltipla e vari�vel com o campo Regional);            **********
 **********       - Per�odo (dd/mm/aaaa at� dd/mm/aaaa limitados ao m�s anterior � data da consulta;     **********
 **********  e dois opcionais:                                                                           **********
 **********       - Consultor (n�o permitida a escolha m�ltipla e vari�vel com o campo Grupo);           **********
 **********       - Prioridade (de acordo com regra de atraso)                                           **********
 **********                                                                                              **********
 **********     A abordagem para resolver o problema foi separar a consulta em quatro tipos distintos,   **********
 **********  de acordo com os dados fornecidos:                                                          **********
 **********       - Consulta Tipo 1: Campos obrigat�rios + Consultor                                     **********
 **********       - Consulta Tipo 2: Campos obrigat�rios + Prioridade                                    **********
 **********       - Consulta Tipo 3: Campos obrigat�rios + Consultor + Prioridade                        **********
 **********       - Consulta Tipo 0: Campos obrigat�rios                                                 **********
 **********     E, al�m disso, foram atribu�dos os seguintes valores para os campos Prioridade, de       **********
 **********  acordo com a Regra de Atraso:                                                               **********
 **********       - Prioridade = 0 ou NULL: registro sem alerta nenhum de prioridade;                    **********
 **********       - Prioridade = 1: registro com baixa prioridade;                                       **********
 **********       - Prioridade = 2: registro com m�dia prioridade;                                       **********
 **********       - Prioridade = 3: registro com alta prioridade                                         **********
 **********                                                                                              **********
 ******************************************************************************************************************
 ******************************************************************************************************************
 */

 -- CONSULTA TIPO 1																								   
 -- Verifica o n�mero de atendimentos sobre os quais recaem as condi��es obrigat�rias e de Consultor
	SELECT COUNT(1) AS iNumAtendimento 
	  --INTO :iNumAtendimentos
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc,
		   acesso.usuariogrupo ug
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND gc.IDGRUPO = ug.IDGRUPO
	   AND ug.IDPESSOAUSUARIO = :idRepresentante
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os atendimentos sobre os quais recaem as condi��es obrigat�rias e de Consultor   
	SELECT gc.IDATENDIMENTO
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc,
		   acesso.usuariogrupo ug
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND gc.IDGRUPO = ug.IDGRUPO
	   AND ug.IDPESSOAUSUARIO = :idRepresentante
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os dados sobre os quais recaem as condi��es obrigat�rias e de Consultor	   
	SELECT DECODE(WA.NMCOR,'rowTabelaAlertaBaixo','Baixo',DECODE(WA.NMCOR,'rowTabelaAlertaMedio','Medio',DECODE(WA.NMCOR,'rowTabelaAlertaAlto','Alto','Baixo'))) AS Prioridade,
		   TO_CHAR(A.DTABERTURA), 
		   CT.NMPATH, 
		   '(' || AR.CDAREAREGISTRO || ') ' ||  LB.NRLINHA AS TERMINAL, 
		   P.NMPESSOA AS NOME_CLIENTE, 
		   ACO.NMFALANDOCOM, 
		   PU.NMPESSOA AS NOME_USUARIO, 
		   TO_CHAR(AGA.DTENTRADABKO) AS DTENCAMINHAMENTO, 
		   DECODE( AGA.INCRI, 0, U.NMLOGINUSUARIOCTI, NULL) AS SUPERIORRESPONSAVELATUAL, 
		   DECODE( AGA.INCRI, 0, UNRA.NMLOGINUSUARIOCTI, NULL) AS NOMERESPONSAVELATUAL,
		   G.NMGRUPO,
		   AR.NMAREAREGISTRO AS REGIONAL,
		   ROUND((TO_DATE(TO_CHAR(NVL(AGBKO.DTSAIDA, SYSDATE))) - AGA.DTENTRADABKO)*24) AS TEMPO_TRATAMENTO,
		   NVL(AGBKO.QTINTERCAMBIO,0) AS QTDEINTERCAMBIO,
		   DECODE(A.QTINSISTENCIA, NULL, NULL,A.QTINSISTENCIA) AS QTINSISTENCIA
	  --INTO :Prioridade:i_Prioridade,:dtAbertura:i_dtAbertura, :nmPath:i_nmPath, :terminal:i_terminal, :nmCliente:i_nmCliente, :nmSolicitante:i_nmSolicitante, :nmConsultor:i_nmConsultor, :dtEncaminhamento:i_dtEncaminhamento, :nmSupervisorResponsavelAtual:i_nmSupervisorResponsavelAtual, :nmResponsavelAtual:i_nmResponsavelAtual, :nmGrupoResponsavelAtual:i_nmGrupoResponsavelAtual, :Regional:i_Regional, :TempoTratamento, :QtdeReencaminhamentos, :QtdeInsistencias
	  FROM ATENDIMENTO.GRUPOCRI GC,
				   ATENDIMENTO.ATENDIMENTO A,
		   CONTATOADM.CONTATO CT,
		   CUSTOMER.PESSOALINHA PL,
		   CUSTOMER.PESSOADEPARA PDP,
		   CUSTOMER.PESSOA P,
		   CUSTOMER.PESSOA PU,
		   LINHA.LINHATELEFONICA LTF,
		   LINHA.LINHABASE LB,
		   APOIO.AREAREGISTRO AR,
		   ATENDIMENTO.ATENDIMENTOCONTATO ACO,
		   ACESSO.USUARIO U,
		   ATENDIMENTO.ATENDIMENTOGRUPOATUAL AGA,
		   ACESSO.GRUPO G,
		   ACESSO.USUARIOGRUPO UG,
					   ACESSO.USUARIO UNRA,
		   ATENDIMENTO.ATENDIMENTOUSUARIOATUAL AUA,
		   ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO,
		   WORKFLOW.ALERTA WA,
		   ATENDIMENTO.ATENDIMENTOALERTA AA
	 WHERE GC.IDATENDIMENTO             =    :idAtendimento
	   AND GC.IDATENDIMENTO             =    A.IDATENDIMENTO
	   AND GC.IDATENDIMENTO             =    AGBKO.IDATENDIMENTO (+)
	   AND CT.IDCONTATO                 =    A.IDCONTATO
	   AND GC.IDPESSOALINHAHISTORICO    =    PL.IDPESSOALINHA
	   AND PDP.IDPESSOADEPARA           =    PL.IDPESSOADEPARA
	   AND P.IDPESSOA                   =    PDP.IDPESSOAORIGEM
	   AND PDP.IDPESSOA                 =    PDP.IDPESSOAORIGEM
	   AND PL.IDLINHATELEFONICA         =    LTF.IDLINHATELEFONICA
	   AND LTF.IDLINHABASE              =    LB.IDLINHABASE
	   AND AR.IDAREAREGISTRO            =    LB.IDAREAREGISTRO
	   AND ACO.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND U.IDPESSOAUSUARIO            =    A.IDPESSOAUSUARIOABERTURA
	   AND U.IDPESSOAUSUARIO            =    PU.IDPESSOA
	   AND U.IDUFOPERADORA              =    :idUfOperadora
	   AND A.IDGRUPOABERTURA            =    G.IDGRUPO
	   AND UG.IDGRUPO                   =    AGA.IDGRUPO 
	   AND AGA.IDATENDIMENTO            =    GC.IDATENDIMENTO	
	   AND UG.INSUPERVISOR              =    1
	   AND U.IDPESSOAUSUARIO            =    UG.IDPESSOAUSUARIO
	   AND AUA.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND UNRA.IDPESSOAUSUARIO         =    AUA.IDPESSOAUSUARIO
	   AND AR.CDAREAREGISTRO            =    ACO.CDAREAREGISTRO
	   AND GC.IDATENDIMENTO             =    AA.IDATENDIMENTO
	   AND AA.IDALERTA                  =    WA.IDALERTA;
	   AND ROWNUM < 2
 -- CONSULTA TIPO 2																								   
 -- Verifica o n�mero de atendimentos sobre os quais recaem as condi��es obrigat�rias
	SELECT COUNT(1) AS iNumAtendimento 
	  --INTO :iNumAtendimentos
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os atendimentos sobre os quais recaem as condi��es obrigat�rias e de Prioridade	   
    SELECT gc.IDATENDIMENTO
      FROM apoio.arearegistro ar,
	  	   linha.linhabase lb,
           linha.linhatelefonica lt,
           customer.pessoalinha pl,
           atendimento.grupocri gc
     WHERE ar.IDUFOPERADORA = :idUfOperadora
       AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
       AND lt.IDLINHABASE = lb.IDLINHABASE
       AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
       AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
       AND gc.IDGRUPO = :idGrupo
       AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os dados sobre os quais recaem as condi��es obrigat�rias e de Prioridade
	SELECT DECODE(WA.NMCOR,'rowTabelaAlertaBaixo','Baixo',DECODE(WA.NMCOR,'rowTabelaAlertaMedio','Medio',DECODE(WA.NMCOR,'rowTabelaAlertaAlto','Alto','Baixo'))) AS Prioridade,
		   TO_CHAR(A.DTABERTURA), 
		   CT.NMPATH, 
		   '(' || AR.CDAREAREGISTRO || ') ' ||  LB.NRLINHA AS TERMINAL, 
		   P.NMPESSOA AS NOME_CLIENTE, 
		   ACO.NMFALANDOCOM, 
		   PU.NMPESSOA AS NOME_USUARIO, 
		   TO_CHAR(AGA.DTENTRADABKO) AS DTENCAMINHAMENTO, 
		   DECODE( AGA.INCRI, 0, U.NMLOGINUSUARIOCTI, NULL) AS SUPERIORRESPONSAVELATUAL, 
		   DECODE( AGA.INCRI, 0, UNRA.NMLOGINUSUARIOCTI, NULL) AS NOMERESPONSAVELATUAL,
		   G.NMGRUPO,
		   AR.NMAREAREGISTRO AS REGIONAL,
		   ROUND((TO_DATE(TO_CHAR(NVL(AGBKO.DTSAIDA, SYSDATE))) - AGA.DTENTRADABKO)*24) AS TEMPO_TRATAMENTO,
		   NVL(AGBKO.QTINTERCAMBIO,0) AS QTDEINTERCAMBIO,
		   DECODE(A.QTINSISTENCIA, NULL, NULL,A.QTINSISTENCIA) AS QTINSISTENCIA
	  --INTO :Prioridade:i_Prioridade,:dtAbertura:i_dtAbertura, :nmPath:i_nmPath, :terminal:i_terminal, :nmCliente:i_nmCliente, :nmSolicitante:i_nmSolicitante, :nmConsultor:i_nmConsultor, :dtEncaminhamento:i_dtEncaminhamento, :nmSupervisorResponsavelAtual:i_nmSupervisorResponsavelAtual, :nmResponsavelAtual:i_nmResponsavelAtual, :nmGrupoResponsavelAtual:i_nmGrupoResponsavelAtual, :Regional:i_Regional, :TempoTratamento, :QtdeReencaminhamentos, :QtdeInsistencias
	  FROM ATENDIMENTO.GRUPOCRI GC,
				   ATENDIMENTO.ATENDIMENTO A,
		   CONTATOADM.CONTATO CT,
		   CUSTOMER.PESSOALINHA PL,
		   CUSTOMER.PESSOADEPARA PDP,
		   CUSTOMER.PESSOA P,
		   CUSTOMER.PESSOA PU,
		   LINHA.LINHATELEFONICA LTF,
		   LINHA.LINHABASE LB,
		   APOIO.AREAREGISTRO AR,
		   ATENDIMENTO.ATENDIMENTOCONTATO ACO,
		   ACESSO.USUARIO U,
		   ATENDIMENTO.ATENDIMENTOGRUPOATUAL AGA,
		   ACESSO.GRUPO G,
		   ACESSO.USUARIOGRUPO UG,
		   ACESSO.USUARIO UNRA,
		   ATENDIMENTO.ATENDIMENTOUSUARIOATUAL AUA,
		   ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO,
		   WORKFLOW.ALERTA WA,
		   ATENDIMENTO.ATENDIMENTOALERTA AA
	 WHERE GC.IDATENDIMENTO             =    :idAtendimento
	   AND GC.IDATENDIMENTO             =    A.IDATENDIMENTO
	   AND GC.IDATENDIMENTO             =    AGBKO.IDATENDIMENTO (+)
	   AND CT.IDCONTATO                 =    A.IDCONTATO
	   AND GC.IDPESSOALINHAHISTORICO    =    PL.IDPESSOALINHA
	   AND PDP.IDPESSOADEPARA           =    PL.IDPESSOADEPARA
	   AND P.IDPESSOA                   =    PDP.IDPESSOAORIGEM
	   AND PDP.IDPESSOA                 =    PDP.IDPESSOAORIGEM
	   AND PL.IDLINHATELEFONICA         =    LTF.IDLINHATELEFONICA
	   AND LTF.IDLINHABASE              =    LB.IDLINHABASE
	   AND AR.IDAREAREGISTRO            =    LB.IDAREAREGISTRO
	   AND ACO.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND U.IDPESSOAUSUARIO            =    A.IDPESSOAUSUARIOABERTURA
	   AND U.IDPESSOAUSUARIO            =    PU.IDPESSOA
	   AND U.IDUFOPERADORA              =    :idUfOperadora
	   AND A.IDGRUPOABERTURA            =    G.IDGRUPO
	   AND UG.IDGRUPO                   =    AGA.IDGRUPO 
	   AND AGA.IDATENDIMENTO            =    GC.IDATENDIMENTO	
	   AND UG.INSUPERVISOR              =    1
	   AND U.IDPESSOAUSUARIO            =    UG.IDPESSOAUSUARIO
	   AND AUA.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND UNRA.IDPESSOAUSUARIO         =    AUA.IDPESSOAUSUARIO
	   AND AR.CDAREAREGISTRO            =    ACO.CDAREAREGISTRO
	   AND GC.IDATENDIMENTO             =    AA.IDATENDIMENTO
	   AND AA.IDALERTA                  =    WA.IDALERTA
	   AND wa.NMCOR                     =    DECODE(:idAlerta,0,NULL,DECODE(:idAlerta,1,'rowTabelaAlertaBaixo',DECODE(:idAlerta,2,'rowTabelaAlertaMedio','rowTabelaAlertaAlto')))	   
	   AND ROWNUM < 2
 -- CONSULTA TIPO 3																								   
 -- Verifica o n�mero de atendimentos sobre os quais recaem as condi��es obrigat�rias, de Consultor e de Prioridade
	SELECT COUNT(1) AS iNumAtendimento 
	  --INTO :iNumAtendimentos
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc,
		   acesso.usuariogrupo ug
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND gc.IDGRUPO = ug.IDGRUPO
	   AND ug.IDPESSOAUSUARIO = :idRepresentante
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os atendimentos sobre os quais recaem as condi��es obrigat�rias, de Consultor e de Prioridade
	SELECT gc.IDATENDIMENTO
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc,
		   acesso.usuariogrupo ug
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND gc.IDGRUPO = ug.IDGRUPO
	   AND ug.IDPESSOAUSUARIO = :idRepresentante
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os dados sobre os quais recaem as condi��es obrigat�rias, de Consultor e de Prioridade
	SELECT DECODE(WA.NMCOR,'rowTabelaAlertaBaixo','Baixo',DECODE(WA.NMCOR,'rowTabelaAlertaMedio','Medio',DECODE(WA.NMCOR,'rowTabelaAlertaAlto','Alto','Baixo'))) AS Prioridade,
		   TO_CHAR(A.DTABERTURA), 
		   CT.NMPATH, 
		   '(' || AR.CDAREAREGISTRO || ') ' ||  LB.NRLINHA AS TERMINAL, 
		   P.NMPESSOA AS NOME_CLIENTE, 
		   ACO.NMFALANDOCOM, 
		   PU.NMPESSOA AS NOME_USUARIO, 
		   TO_CHAR(AGA.DTENTRADABKO) AS DTENCAMINHAMENTO, 
		   DECODE( AGA.INCRI, 0, U.NMLOGINUSUARIOCTI, NULL) AS SUPERIORRESPONSAVELATUAL, 
		   DECODE( AGA.INCRI, 0, UNRA.NMLOGINUSUARIOCTI, NULL) AS NOMERESPONSAVELATUAL,
		   G.NMGRUPO,
		   AR.NMAREAREGISTRO AS REGIONAL,
		   ROUND((TO_DATE(TO_CHAR(NVL(AGBKO.DTSAIDA, SYSDATE))) - AGA.DTENTRADABKO)*24) AS TEMPO_TRATAMENTO,
		   NVL(AGBKO.QTINTERCAMBIO,0) AS QTDEINTERCAMBIO,
		   DECODE(A.QTINSISTENCIA, NULL, NULL,A.QTINSISTENCIA) AS QTINSISTENCIA
	  --INTO :Prioridade:i_Prioridade,:dtAbertura:i_dtAbertura, :nmPath:i_nmPath, :terminal:i_terminal, :nmCliente:i_nmCliente, :nmSolicitante:i_nmSolicitante, :nmConsultor:i_nmConsultor, :dtEncaminhamento:i_dtEncaminhamento, :nmSupervisorResponsavelAtual:i_nmSupervisorResponsavelAtual, :nmResponsavelAtual:i_nmResponsavelAtual, :nmGrupoResponsavelAtual:i_nmGrupoResponsavelAtual, :Regional:i_Regional, :TempoTratamento, :QtdeReencaminhamentos, :QtdeInsistencias
	  FROM ATENDIMENTO.GRUPOCRI GC,
				   ATENDIMENTO.ATENDIMENTO A,
		   CONTATOADM.CONTATO CT,
		   CUSTOMER.PESSOALINHA PL,
		   CUSTOMER.PESSOADEPARA PDP,
		   CUSTOMER.PESSOA P,
		   CUSTOMER.PESSOA PU,
		   LINHA.LINHATELEFONICA LTF,
		   LINHA.LINHABASE LB,
		   APOIO.AREAREGISTRO AR,
		   ATENDIMENTO.ATENDIMENTOCONTATO ACO,
		   ACESSO.USUARIO U,
		   ATENDIMENTO.ATENDIMENTOGRUPOATUAL AGA,
		   ACESSO.GRUPO G,
		   ACESSO.USUARIOGRUPO UG,
					   ACESSO.USUARIO UNRA,
		   ATENDIMENTO.ATENDIMENTOUSUARIOATUAL AUA,
		   ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO,
		   WORKFLOW.ALERTA WA,
		   ATENDIMENTO.ATENDIMENTOALERTA AA
	 WHERE GC.IDATENDIMENTO             =    :idAtendimento
	   AND GC.IDATENDIMENTO             =    A.IDATENDIMENTO
	   AND GC.IDATENDIMENTO             =    AGBKO.IDATENDIMENTO (+)
	   AND CT.IDCONTATO                 =    A.IDCONTATO
	   AND GC.IDPESSOALINHAHISTORICO    =    PL.IDPESSOALINHA
	   AND PDP.IDPESSOADEPARA           =    PL.IDPESSOADEPARA
	   AND P.IDPESSOA                   =    PDP.IDPESSOAORIGEM
	   AND PDP.IDPESSOA                 =    PDP.IDPESSOAORIGEM
	   AND PL.IDLINHATELEFONICA         =    LTF.IDLINHATELEFONICA
	   AND LTF.IDLINHABASE              =    LB.IDLINHABASE
	   AND AR.IDAREAREGISTRO            =    LB.IDAREAREGISTRO
	   AND ACO.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND U.IDPESSOAUSUARIO            =    A.IDPESSOAUSUARIOABERTURA
	   AND U.IDPESSOAUSUARIO            =    PU.IDPESSOA
	   AND U.IDUFOPERADORA              =    :idUfOperadora
	   AND A.IDGRUPOABERTURA            =    G.IDGRUPO
	   AND UG.IDGRUPO                   =    AGA.IDGRUPO 
	   AND AGA.IDATENDIMENTO            =    GC.IDATENDIMENTO	
	   AND UG.INSUPERVISOR              =    1
	   AND U.IDPESSOAUSUARIO            =    UG.IDPESSOAUSUARIO
	   AND AUA.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND UNRA.IDPESSOAUSUARIO         =    AUA.IDPESSOAUSUARIO
	   AND AR.CDAREAREGISTRO            =    ACO.CDAREAREGISTRO
	   AND GC.IDATENDIMENTO             =    AA.IDATENDIMENTO
	   AND AA.IDALERTA                  =    WA.IDALERTA
	   AND wa.NMCOR                     =    DECODE(:idAlerta,0,NULL,DECODE(:idAlerta,1,'rowTabelaAlertaBaixo',DECODE(:idAlerta,2,'rowTabelaAlertaMedio','rowTabelaAlertaAlto')));
	   AND ROWNUM < 2
 -- CONSULTA TIPO 0																								   
 -- Verifica o n�mero de atendimentos sobre os quais recaem as condi��es obrigat�rias
	SELECT COUNT(1) AS iNumAtendimento 
	  --INTO :iNumAtendimentos
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os atendimentos sobre os quais recaem as condi��es obrigat�rias
	SELECT gc.IDATENDIMENTO
	  FROM apoio.arearegistro ar,
		   linha.linhabase lb,
		   linha.linhatelefonica lt,
		   customer.pessoalinha pl,
		   atendimento.grupocri gc
	 WHERE ar.IDUFOPERADORA = :idUfOperadora
	   AND lb.IDAREAREGISTRO = ar.IDAREAREGISTRO
	   AND lt.IDLINHABASE = lb.IDLINHABASE
	   AND lt.IDLINHATELEFONICA = pl.IDLINHATELEFONICA
	   AND gc.IDPESSOALINHAHISTORICO = pl.IDPESSOALINHA
	   AND gc.IDGRUPO = :idGrupo
	   AND (gc.dtabertura >= TO_DATE(:stDate, 'DD/MM/YYYY') AND gc.dtabertura <= TO_DATE(:fnDate, 'DD/MM/YYYY'));
 -- Seleciona os dados sobre os quais recaem as condi��es obrigat�rias
	SELECT DECODE(WA.NMCOR,'rowTabelaAlertaBaixo','Baixo',DECODE(WA.NMCOR,'rowTabelaAlertaMedio','Medio',DECODE(WA.NMCOR,'rowTabelaAlertaAlto','Alto','Baixo'))) AS Prioridade,
		   TO_CHAR(A.DTABERTURA), 
		   CT.NMPATH, 
		   '(' || AR.CDAREAREGISTRO || ') ' ||  LB.NRLINHA AS TERMINAL, 
		   P.NMPESSOA AS NOME_CLIENTE, 
		   ACO.NMFALANDOCOM, 
		   PU.NMPESSOA AS NOME_USUARIO, 
		   TO_CHAR(AGA.DTENTRADABKO) AS DTENCAMINHAMENTO, 
		   DECODE( AGA.INCRI, 0, U.NMLOGINUSUARIOCTI, NULL) AS SUPERIORRESPONSAVELATUAL, 
		   DECODE( AGA.INCRI, 0, UNRA.NMLOGINUSUARIOCTI, NULL) AS NOMERESPONSAVELATUAL,
		   G.NMGRUPO,
		   AR.NMAREAREGISTRO AS REGIONAL,
		   ROUND((TO_DATE(TO_CHAR(NVL(AGBKO.DTSAIDA, SYSDATE))) - AGA.DTENTRADABKO)*24) AS TEMPO_TRATAMENTO,
		   NVL(AGBKO.QTINTERCAMBIO,0) AS QTDEINTERCAMBIO,
		   DECODE(A.QTINSISTENCIA, NULL, NULL,A.QTINSISTENCIA) AS QTINSISTENCIA
	  --INTO :Prioridade:i_Prioridade,:dtAbertura:i_dtAbertura, :nmPath:i_nmPath, :terminal:i_terminal, :nmCliente:i_nmCliente, :nmSolicitante:i_nmSolicitante, :nmConsultor:i_nmConsultor, :dtEncaminhamento:i_dtEncaminhamento, :nmSupervisorResponsavelAtual:i_nmSupervisorResponsavelAtual, :nmResponsavelAtual:i_nmResponsavelAtual, :nmGrupoResponsavelAtual:i_nmGrupoResponsavelAtual, :Regional:i_Regional, :TempoTratamento, :QtdeReencaminhamentos, :QtdeInsistencias
	  FROM ATENDIMENTO.GRUPOCRI GC,
		   ATENDIMENTO.ATENDIMENTO A,
		   CONTATOADM.CONTATO CT,
		   CUSTOMER.PESSOALINHA PL,
		   CUSTOMER.PESSOADEPARA PDP,
		   CUSTOMER.PESSOA P,
		   CUSTOMER.PESSOA PU,
		   LINHA.LINHATELEFONICA LTF,
		   LINHA.LINHABASE LB,
		   APOIO.AREAREGISTRO AR,
		   ATENDIMENTO.ATENDIMENTOCONTATO ACO,
		   ACESSO.USUARIO U,
		   ATENDIMENTO.ATENDIMENTOGRUPOATUAL AGA,
		   ACESSO.GRUPO G,
		   ACESSO.USUARIOGRUPO UG,
		   ACESSO.USUARIO UNRA,
		   ATENDIMENTO.ATENDIMENTOUSUARIOATUAL AUA,
		   ATENDIMENTO.ATENDIMENTOGRUPOBKO AGBKO,
		   WORKFLOW.ALERTA WA,
		   ATENDIMENTO.ATENDIMENTOALERTA AA
	 WHERE GC.IDATENDIMENTO             =    :idAtendimento
	   AND GC.IDATENDIMENTO             =    A.IDATENDIMENTO
	   AND GC.IDATENDIMENTO             =    AGBKO.IDATENDIMENTO (+)
	   AND CT.IDCONTATO                 =    A.IDCONTATO
	   AND GC.IDPESSOALINHAHISTORICO    =    PL.IDPESSOALINHA
	   AND PDP.IDPESSOADEPARA           =    PL.IDPESSOADEPARA
	   AND P.IDPESSOA                   =    PDP.IDPESSOAORIGEM
	   AND PDP.IDPESSOA                 =    PDP.IDPESSOAORIGEM
	   AND PL.IDLINHATELEFONICA         =    LTF.IDLINHATELEFONICA
	   AND LTF.IDLINHABASE              =    LB.IDLINHABASE
	   AND AR.IDAREAREGISTRO            =    LB.IDAREAREGISTRO
	   AND ACO.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND U.IDPESSOAUSUARIO            =    A.IDPESSOAUSUARIOABERTURA
	   AND U.IDPESSOAUSUARIO            =    PU.IDPESSOA
	   AND U.IDUFOPERADORA              =    :idUfOperadora
	   AND A.IDGRUPOABERTURA            =    G.IDGRUPO
	   AND UG.IDGRUPO                   =    AGA.IDGRUPO 
	   AND AGA.IDATENDIMENTO            =    GC.IDATENDIMENTO	
	   AND UG.INSUPERVISOR              =    1
	   AND U.IDPESSOAUSUARIO            =    UG.IDPESSOAUSUARIO
	   AND AUA.IDATENDIMENTO            =    A.IDATENDIMENTO
	   AND UNRA.IDPESSOAUSUARIO         =    AUA.IDPESSOAUSUARIO
	   AND AR.CDAREAREGISTRO            =    ACO.CDAREAREGISTRO
	   AND GC.IDATENDIMENTO             =    AA.IDATENDIMENTO
	   AND AA.IDALERTA                  =    WA.IDALERTA
	   AND ROWNUM < 2