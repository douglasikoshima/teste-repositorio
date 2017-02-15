/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Pessoa/Pessoa.hpp>
#include <Linha/Linha.hpp>
#include <Processo/Processo.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTPROCESSOS);

void implLSTPROCESSOS::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CPessoa oPessoa;
	CProcesso oProc;
	list< CProcesso >	lstOProc;

	char*  pcDtProcessoFinal = NULL;
	char*  pcDtProcessoInicial = NULL;
	char*  pcIdPessoa = NULL;
	char*  pcQtdProcesso = NULL;
	
	char*  pcTagXmlIn = NULL;

	bool bInsereRegistro;
	int  iIndice = 1;
	int  iIndiceInicial = 0;
	int  iQtLimiteRegistros = 0;


	// Navega o XML e recupera as informacoes obrigatorias
	

	pcDtProcessoFinal = helper.walkTree(dnode, "dtAberturaFinal", 0);
	pcDtProcessoInicial = helper.walkTree(dnode, "dtAberturaInicial", 0);
	pcIdPessoa = helper.walkTree(dnode, "idPessoa", 0);
	pcQtdProcesso = helper.walkTree(dnode, "qtProcessos", 0);

	if(NULL == pcDtProcessoFinal && 
	   NULL == pcDtProcessoInicial &&
	   NULL == pcIdPessoa &&
	   NULL == pcQtdProcesso) 
	{
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}

	if ( '\0' == *pcIdPessoa ) 
	{
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}

	if ( 0 >= atol(pcIdPessoa) )
	{
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	//Setando o id antes de decidir quem eh quem
	oPessoa.setIdPessoa(atol(pcIdPessoa));

	pcTagXmlIn = helper.walkTree(dnode, "indiceInicial", 0);

	if(pcTagXmlIn != NULL)
	{
		iIndiceInicial = atoi(pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "qtLimiteRegistros", 0);

		if(pcTagXmlIn != NULL)
			iQtLimiteRegistros = atoi(pcTagXmlIn);

	}

	if ( strcmp(pcDtProcessoFinal, " ") >= 0 && 
		 strcmp(pcDtProcessoInicial, " ") >= 0 )
	{
		// Consulta pela data Limitada entre as datas
		// Monta o objeto Pessoa
		try
		{
			oPessoa.consultarRangeDateProcessos( lstOProc, pcDtProcessoInicial, pcDtProcessoFinal);
		}
		catch( ... )
		{
			setStatusCode("11W0002", "Consulta não efetuada");
			return;
		}
	}
	else if ( strcmp(pcQtdProcesso, " ") >= 0 )
	{
		// Consulta pela Quantidade de Relacionamentos	
		// Monta o objeto Pessoa
		try
		{
			oPessoa.consultarUltimosProcessos( lstOProc, pcQtdProcesso);
		}
		catch ( ... )
		{
			setStatusCode("11W0002", "Consulta não efetuada");
			return;
		}
	}
	else 
	{
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	xml_g->createTag("LSTPROCESSOSVO");
	xml_g->addProp("xmlns", "acessos.vol.vivo.com.br/vo");

	xml_g->addItem( "qtMaxRegistros", lstOProc.size() );

	REG_CONTATO_PROTOCOLO(iResult,REG_NAO_VALIDA_TAG,protocolo);
	xml_g->addItem("nrProtocolo",protocolo.nrProtocolo);	

	// Monta o XML de saída
	while( 0 < lstOProc.size() ) 
	{			
		if(iIndiceInicial && iQtLimiteRegistros)
		{
			if((iIndice >= iIndiceInicial) && 
			   (iIndice <= (iIndiceInicial + (iQtLimiteRegistros - 1))))
				bInsereRegistro = true;
			else
				bInsereRegistro = false;			
		}
		else
			bInsereRegistro = true;

		if(bInsereRegistro)
		{
			oProc = lstOProc.front();

			// Salvando o ultimo acesso
			xml_g->createTag("PROCESSOS");
			xml_g->addItem( "idAtendimento", oProc.getIdAtendimentoProc() );
			xml_g->addItem( "dsAtendimento", CUtil::trim(oProc.getDsAtendimentoProc()) );
			xml_g->addItem( "dtAbertura", CUtil::trim(oProc.getDtAberturaProc()) );
			xml_g->addItem( "hrAbertura", CUtil::trim(oProc.getHrAberturaProc()) );
			xml_g->addItem( "dtConclusao", CUtil::trim(oProc.getDtConclusaoProc()));
			xml_g->addItem( "hrConclusao", CUtil::trim(oProc.getHrConclusaoProc()));
			xml_g->addItem( "dsStatus", CUtil::trim(oProc.getDsStatusProc()));
			xml_g->closeTag();
		}

		lstOProc.pop_front();
		
		iIndice++;
	}

	xml_g->closeTag();
	
	// registrando contato
	//REG_CONTATO(iRes, REG_VALIDA_TAG);

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Consulta efetuada com sucesso");
	return;
}
