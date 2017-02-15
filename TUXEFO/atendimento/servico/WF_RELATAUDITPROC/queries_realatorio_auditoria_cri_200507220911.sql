/******************************************************************************************************************
 ******************************************************************************************************************
 **********  DOCUMENTAÇÃO SOBRE AS CONSULTAS DO RELATÓRIO DE AUDITORIA DO PROCESSOS CRI                  ********** 
 **********                                                                                              **********
 **********     De acordo com a documentação analisada, o Relatório de Auditoria dos Processos CRI       **********
 **********  tem três campos obrigatórios:                                                               **********
 **********       - Regional (não permitida a escolha múltipla);                                         ********** 
 **********       - Grupo (não permitida a escolha múltipla e variável com o campo Regional);            **********
 **********       - Período (dd/mm/aaaa até dd/mm/aaaa limitados ao mês anterior à data da consulta;     **********
 **********  e dois opcionais:                                                                           **********
 **********       - Consultor (não permitida a escolha múltipla e variável com o campo Grupo);           **********
 **********       - Prioridade (de acordo com regra de atraso)                                           **********
 **********                                                                                              **********
 **********     A abordagem para resolver o problema foi separar a consulta em quatro tipos distintos,   **********
 **********  de acordo com os dados fornecidos:                                                          **********
 **********       - Consulta Tipo 1: Campos obrigatórios + Consultor                                     **********
 **********       - Consulta Tipo 2: Campos obrigatórios + Prioridade                                    **********
 **********       - Consulta Tipo 3: Campos obrigatórios + Consultor + Prioridade                        **********
 **********       - Consulta Tipo 0: Campos obrigatórios                                                 **********
 **********     E, além disso, foram atribuídos os seguintes valores para os campos Prioridade, de       **********
 **********  acordo com a Regra de Atraso:                                                               **********
 **********       - Prioridade = 0 ou NULL: registro sem alerta nenhum de prioridade;                    **********
 **********       - Prioridade = 1: registro com baixa prioridade;                                       **********
 **********       - Prioridade = 2: registro com média prioridade;                                       **********
 **********       - Prioridade = 3: registro com alta prioridade                                         **********
 **********                                                                                              **********
 ******************************************************************************************************************
 ******************************************************************************************************************
 */

 -- CONSULTA TIPO 1																								   
 -- Verifica o número de atendimentos sobre os quais recaem as condições obrigatórias e de Consultor
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
 -- Seleciona os atendimentos sobre os quais recaem as condições obrigatórias e de Consultor   
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
 -- Seleciona os dados sobre os quais recaem as condições obrigatórias e de Consultor	   
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
 -- Verifica o número de atendimentos sobre os quais recaem as condições obrigatórias
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
 -- Seleciona os atendimentos sobre os quais recaem as condições obrigatórias e de Prioridade	   
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
 -- Seleciona os dados sobre os quais recaem as condições obrigatórias e de Prioridade
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
 -- Verifica o número de atendimentos sobre os quais recaem as condições obrigatórias, de Consultor e de Prioridade
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
 -- Seleciona os atendimentos sobre os quais recaem as condições obrigatórias, de Consultor e de Prioridade
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
 -- Seleciona os dados sobre os quais recaem as condições obrigatórias, de Consultor e de Prioridade
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
 -- Verifica o número de atendimentos sobre os quais recaem as condições obrigatórias
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
 -- Seleciona os atendimentos sobre os quais recaem as condições obrigatórias
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
 -- Seleciona os dados sobre os quais recaem as condições obrigatórias
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