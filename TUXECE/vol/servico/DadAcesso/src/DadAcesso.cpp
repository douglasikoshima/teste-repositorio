#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Linha/Linha.hpp>
#include <Menu/Menu.hpp>
#include <Relacionamento/Relacionamento.hpp>
#include <Venda/Venda.hpp>
#include <PessoaAbstract/PessoaAbstract.hpp>
#include <Lojista/Lojista.hpp>
#include <TuxHelperClever/TuxHelperClever.h>


static char *STR_CONSULTARACESSOPORDIA			= "consultarAcessoPorDia";
static char *STR_CONSULTARACESSOPORHORA			= "consultarAcessoPorHora";
static char *STR_CONSULTARADESOES				= "consultarAdesoes";
static char *STR_CONSULTARACESSONEGADO			= "consultarAcessoNegado";
static char *STR_CONSULTARSERVICOSEFETUADOS		= "consultarServicosEfetuados";
static char *STR_CONSULTARSERVICOSDISPONIVEIS	= "consultarServicosDisponiveis";
static char *STR_CONSULTARFINANCEIRO			= "consultarFinanceiro";
static char *STR_CONSULTARLOJAS					= "consultarLojas";
static char *STR_CONSULTARPRIMEIROACESSO		= "consultarPrimeiroAcesso";
static char *STR_CONSULTARACESSOINCIDENCIA		= "consultarAcessoIncidencia";
static char *STR_CONSULTARTEMPOPERMANENCIA		= "consultarTempoPermanencia";
static char *STR_CONSULTARSTATUSVENDA			= "consultarStatusVenda";
static char *STR_CONSULTARSERVICOSEFETUADOSURA  = "consultarServicosEfetuadosURA";
static char *STR_11IOK                   = "11I0000";
static char *STR_11IOKMSG                = "CONSULTA EFETUADA COM SUCESSO";
static char *STR_11IOKALTMSG             = "ALTERACAO EFETUADA COM SUCESSO";
static char *STR_11IOKVALSENMSG          = "SENHA VALIDADA COM SUCESSO";
static char *STR_11IOKREGMSG             = "REGISTRO EFETUADO COM SUCESSO";

static char *STR_11W0001                 = "11W0001";
static char *STR_11W0002                 = "11W0002";
static char *STR_11W0003                 = "11W0003";
static char *STR_11WNOTALTMSG            = "ALTERACAO NAO EFETUADA";
static char *STR_11WNOTCON               = "CONSULTA NAO EFETUADA";

static char *STR_11W9999                 = "11W9999";
static char *STR_11WEXCDESC              = "ERRO DESCONHECIDO";

DECLARE_TUXEDO_SERVICE(DADACESSO);

static void consultarAcessoPorDia(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoRelacionamento =0;
	int		iIdSegmentacao =0;
	int		iIdTipoCarteira =0;

	int		iIdTipoLinha =0;
	int		iIdCanal =0;
	int		iCdAreaRegistro =0;
	int		iNrLinha =0;
	int		iIsAgrupado =0;

	int     iIdTecnologia = 0;

	int		iIdTerminal = 0;
	int		iIdPessoaDePara = 0;
	char	cdLinhaTEMP[12];
	CLojista oLojistaRel;

	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}

//
	pcTagXmlIn = helper.walkTree(dnode,"isAgrupado", 0);
	if (pcTagXmlIn != NULL)
		iIsAgrupado = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);



	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
		if (pcTagXmlIn != NULL){
		
			iIdTerminal =   atoi(pcTagXmlIn);
			if (iIdTerminal != -1){	
		
				oLojistaRel.consultarIdTerminal(pcTagXmlIn);
				iIdTerminal =  oLojistaRel.getIdTerminal();		
			}
	}


	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

		try	
	{
		
		if (iIdCanal == 13)
			oRelacionamento.consultarAcessoPorDiaLoja(lstRelac, 2, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia, iIdPessoaDePara, iIdTerminal);	
		else
			oRelacionamento.consultarAcessoPorDia(lstRelac, 2, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}


	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioAcessoDiarioVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioAcessoDiario" );


		if (iIdCanal == 13) {
	
			xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );	
			xml_g->addItem("nrTerminal", oRelacionamento.getNmTerminal());

		}

		
		xml_g->addItem( "dtRegistroHistorico", oRelacionamento.getDtRelacionamento() );

		
		if (iIdTecnologia == 1) { //cdma
		
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() );
			
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario()) );
		
		}else if(iIdTecnologia == 2) { //gsm
	
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreGsmCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreGsmUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosGsmCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosGsmUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreGsmCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreGsmUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosGsmCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosGsmUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleGsmUsuario() );
			
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario()) );

		
		}else{ //as duas tecnologias

			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreGsmCliente());
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPreGsmUsuario());
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosGsmCliente());
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtVOLPosGsmUsuario());	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreGsmCliente());
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPreGsmUsuario());
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosGsmCliente());
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVPosGsmUsuario());
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() + oRelacionamento.getQtVOLControleGsmUsuario() );	

			int iTotalCdma = (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario());
			int iTotalGsm  = (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario());
			
			xml_g->addItem( "qtTotal", iTotalGsm + iTotalCdma );
		}

		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();



}


static void consultarAcessoPorHora(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char   cTagXmlOut[20];
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoRelacionamento =0;
	int		iIdTipoLinha =0;
	int		iIdSegmentacao =0;
	int		iIdTipoCarteira =0;
	int     iIdTecnologia = 0;
	int		iIdTerminal = 0;
	int		iIdPessoaDePara = 0;
	int		iIdCanal = 0;
	CLojista oLojistaRel;
	char	cdLinhaTEMP[12];
	int		iCdAreaRegistro =0;
	int		iNrLinha =0;
	int		iIsAgrupado =0;


	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);



	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	
	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}


	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}	
	}

	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

	try	
	{
		if ((iIdCanal == 13) && ( iIdTerminal || iIdPessoaDePara))			 
			oRelacionamento.consultarAcessoPorHoraLoja(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia, iIdPessoaDePara, iIdTerminal);
		else
			oRelacionamento.consultarAcessoPorHora(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia);
	
//												  (lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora,  iIdTecnologia);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioAcessoFaixaHorarioVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();



		xml_g->createTag( "VOLTAVRelatorioAcessoFaixaHorario" );
		xml_g->addItem( "hrRegistroHistorico", CUtil::trim(oRelacionamento.getHrRelacionamento() ));
		sprintf(cTagXmlOut, "%f", oRelacionamento.getQtVOL());
		xml_g->addItem( "qtVOL", cTagXmlOut );
		sprintf(cTagXmlOut, "%f", oRelacionamento.getQtTAV());
		xml_g->addItem( "qtTAV", cTagXmlOut );

		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();


}


static void consultarAdesoes(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoRelacionamento =0;
	int		iIdTipoLinha =0;
	int		iIdSegmentacao =0;
	int		iIdTipoCarteira =0;
	int     iIdTecnologia = 0;
	int		iIdTerminal = 0;
	int		iIdPessoaDePara = 0;
	int		iIdCanal = 0 ;
	CLojista oLojistaRel;	



	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");



	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	
	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}
	}



	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);



	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

	try	
	{

		if (iIdCanal == 13) 
			oRelacionamento.consultarAcessoPorDiaLoja(lstRelac, 3, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, 0, 0, 0, iIdTecnologia, iIdPessoaDePara, iIdTerminal);
		else		
			oRelacionamento.consultarAcessoPorDia(lstRelac, 3, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, 0, 0, 0, iIdTecnologia);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioAdesoesVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioAdesoes" );

		xml_g->addItem( "dtRegistroHistorico", oRelacionamento.getDtRelacionamento() );


		if (iIdCanal == 13) {
	
			xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );	
			xml_g->addItem("nrTerminal", oRelacionamento.getNmTerminal());

		}

		if (iIdTecnologia == 1) { //cdma
		
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() );

			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario()) );
		
		}else if(iIdTecnologia == 2) { //gsm
	
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreGsmCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreGsmUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosGsmCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosGsmUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreGsmCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreGsmUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosGsmCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosGsmUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleGsmUsuario() );
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario()) );

		
		}else{ //as duas tecnologias

			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreGsmCliente());
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPreGsmUsuario());
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosGsmCliente());
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtVOLPosGsmUsuario());	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreGsmCliente());
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPreGsmUsuario());
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosGsmCliente());
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVPosGsmUsuario());
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() + oRelacionamento.getQtVOLControleGsmUsuario() );	

			int iTotalCdma = (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario());
			int iTotalGsm  = (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario());
			
			xml_g->addItem( "qtTotal", iTotalGsm + iTotalCdma );
		}


		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();

}


static void consultarAcessoNegado(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoRelacionamento =0;
	int		iIdSegmentacao =0;
	int		iIdTipoCarteira =0;

	int		iIdTipoLinha =0;
	int		iIdCanal = 0;
	int		iCdAreaRegistro = 0;
	int		iNrLinha = 0;
	int		iIsAgrupado = 0;

	int     iIdTecnologia = 0;
	int		iIdTerminal = 0;
	int		iIdPessoaDePara = 0;
	char	cdLinhaTEMP[12];
	CLojista oLojistaRel;

	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);



	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}	
	}


	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}

	//
	pcTagXmlIn = helper.walkTree(dnode,"isAgrupado", 0);
	if (pcTagXmlIn != NULL)
		iIsAgrupado = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

	try	
	{
		
		if (iIdCanal == 13) 
			oRelacionamento.consultarAcessoNegadoLoja(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia, iIdPessoaDePara, iIdTerminal);
		else
			oRelacionamento.consultarAcessoNegado(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia);		


		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioAcessoNegadoVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioAcessoNegado" );

	
		if (iIdPessoaDePara){
	
			xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );	
			xml_g->addItem("nrTerminal", oRelacionamento.getNmTerminal());

		}

		
		xml_g->addItem( "dtRegistroHistorico", oRelacionamento.getDtRelacionamento() );

		
		if (iIdTecnologia == 1) { //cdma
		
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() );
			
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario() ) );
		
		}else if(iIdTecnologia == 2) { //gsm
	
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreGsmCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreGsmUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosGsmCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosGsmUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreGsmCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreGsmUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosGsmCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosGsmUsuario() );
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleGsmUsuario() );
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario()) );

		
		}else{ //as duas tecnologias

			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreGsmCliente());
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPreGsmUsuario());
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosGsmCliente());
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtVOLPosGsmUsuario());	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreGsmCliente());
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPreGsmUsuario());
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosGsmCliente());
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVPosGsmUsuario());
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() + oRelacionamento.getQtVOLControleGsmUsuario() );	

			int iTotalCdma = (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario());
			int iTotalGsm  = (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario());
			
			xml_g->addItem( "qtTotal", iTotalGsm + iTotalCdma );
		}



		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();

}


static void consultarServicosEfetuados(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoRelacionamento =0;
	int		iIdSegmentacao =0;
	int		iIdTipoCarteira =0;
	int		iIdContato =0;

	int		iIdTipoLinha =0;
	int		iIdCanal =0;
	int		iCdAreaRegistro =0;
	int		iNrLinha =0;
	int		iIsAgrupado =0;
	char	cdLinhaTEMP[12];
	CLojista oLojistaRel;
	int     iIdTecnologia = 0;
	int		iIdTerminal = 0;
	int		iIdPessoaDePara = 0;
	int     iIdGrupoOperadoraTerminal = 0;
	int     iIdUFOperadoraTerminal = 0;



	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idContato", 0);
	if (pcTagXmlIn != NULL)
		iIdContato = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}

	//


	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}	
	}


	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadoraTerminal", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadoraTerminal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadoraTerminal", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadoraTerminal = atoi(pcTagXmlIn);



	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}



	pcTagXmlIn = helper.walkTree(dnode,"isAgrupado", 0);
	if (pcTagXmlIn != NULL)
		iIsAgrupado = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);
	oRelacionamento.setIdContato(iIdContato);

	try	
	{
		if (iIdCanal == 13) 		
			oRelacionamento.consultarServicosEfetuadosLoja(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia, iIdGrupoOperadoraTerminal, iIdUFOperadoraTerminal, iIdPessoaDePara, iIdTerminal);
		else
			oRelacionamento.consultarServicosEfetuados(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioServicosEfetuadosVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioServicosEfetuados" );


		if (iIdCanal == 13) {
	
			xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );	
			xml_g->addItem("nrTerminal", oRelacionamento.getNmTerminal());
		}

		xml_g->addItem( "dtRegistro", oRelacionamento.getDtRelacionamento() );
		xml_g->addItem( "nmContato", CUtil::trim(oRelacionamento.getDsOperacao()) );
		xml_g->addItem( "qtVOL", oRelacionamento.getQtVOL() );
		xml_g->addItem( "qtTAV", oRelacionamento.getQtTAV() );

		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();

}




static void consultarServicosEfetuadosURA(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoRelacionamento =0;
	int		iIdSegmentacao =0;
	int		iIdTipoCarteira =0;
	int		iIdContato =0;

	int		iIdTipoLinha =0;
	int		iIdCanal =0;
	int		iCdAreaRegistro =0;
	int		iNrLinha =0;
	int		iIsAgrupado =0;
	char	cdLinhaTEMP[12];
	CLojista oLojistaRel;
	int     iIdTecnologia = 0;
	int		iIdTerminal = 0;
	int		iIdPessoaDePara = 0;
	int     iIdGrupoOperadoraTerminal = 0;
	int     iIdUFOperadoraTerminal = 0;



	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idContato", 0);
	if (pcTagXmlIn != NULL)
		iIdContato = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}

	//


	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}	
	}




	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}



	pcTagXmlIn = helper.walkTree(dnode,"isAgrupado", 0);
	if (pcTagXmlIn != NULL)
		iIsAgrupado = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);
	oRelacionamento.setIdContato(iIdContato);

	try	
	{
		oRelacionamento.consultarServicosEfetuadosURA(lstRelac, iIdTipoCarteira, iIdTipoRelacionamento, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, iIsAgrupado, iIdTecnologia);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioServicosEfetuadosVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioServicosEfetuados" );

		xml_g->addItem( "dtRegistro", oRelacionamento.getDtRelacionamento() );
		xml_g->addItem( "nmContato", CUtil::trim(oRelacionamento.getDsOperacao()) );
		xml_g->addItem( "qtURA", oRelacionamento.getQtURA() );
		

		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();

}






static void consultarServicosDisponiveis(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	int	   iIdCanal = 0;
	char*  pcTagXmlIn = NULL;

	list< CRelacionamento >	lstRelac;


	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
		iIdCanal = atoi(pcTagXmlIn);	


	try	
	{

		if (iIdCanal == 9) //URA
			oRelacionamento.consultarServicosDisponiveisURA(lstRelac);
		else
			oRelacionamento.consultarServicosDisponiveis(lstRelac);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioServicosDisponveisVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioServicosDisponveis" );

		xml_g->addItem( "nuNivel", oRelacionamento.getNuNivel() );
		xml_g->addItem( "idContato", oRelacionamento.getIdContato() );
		xml_g->addItem( "nmContato", CUtil::trim(oRelacionamento.getDsOperacao()) );


		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();

}

static void consultarFinanceiro(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CVenda oVenda;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;

//	int		iIdTipoRelacionamento =0;
	int		iIdTipoLinha =0;
//	int		iIdSegmentacao =0;
//	int		iIdTipoCarteira =0;
	int		iIdPessoaDePara=0;
	int		iIdTipoRecarga=0;
	int		iIdUFOperadora=0;
	int		iIdGrupoOperadora=0;
	int		iIdStatusVenda=0;
	int		iIdTerminal = 0;
	char	cGet[256];

	CLojista oLojistaRel;
	list< CVenda > lstVenda;


	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");
/*
	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);
*/
	//
	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	if (pcTagXmlIn != NULL)
		iIdPessoaDePara = atoi(pcTagXmlIn);

	//idTipoServico = idTipoLinha -> 1-Pagamento de conta 2-Recarga
	pcTagXmlIn = helper.walkTree(dnode,"idTipoServico", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRecarga", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRecarga = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idStatusVenda", 0);
	if (pcTagXmlIn != NULL)
		iIdStatusVenda = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}	
	}



	oVenda.setDataInicial(cDtInicial);
	oVenda.setDataFinal(cDtFinal);


	try	
	{
		oVenda.consultarFinanceiro(lstVenda, iIdPessoaDePara, iIdTipoLinha, iIdTipoRecarga, iIdUFOperadora, iIdGrupoOperadora, iIdStatusVenda, iIdTerminal);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11IOK);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioFinanceiroVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstVenda.size() ) {
		oVenda = lstVenda.front();

		xml_g->createTag( "VOLTAVRelatorioFinanceiro" );

		xml_g->addItem( "dtVenda", oVenda.getDataVenda(cGet));
		xml_g->addItem( "dsLoja",  oVenda.getDsLoja(cGet));
		xml_g->addItem( "dsTipoServico", oVenda.getDsTipoServico(cGet));
		xml_g->addItem( "dsBandeiraCartao", oVenda.getBandeira(cGet));
		xml_g->addItem( "vlCartao", oVenda.getValor(cGet));
		xml_g->addItem( "nrTerminal", oVenda.getNrTerminal(cGet));
		if (oVenda.getCdAreaRegistro() > 0)
			xml_g->addItem( "cdAreaRegistro", oVenda.getCdAreaRegistro() );
		else
			xml_g->addItem( "cdAreaRegistro", "" );				
		if (oVenda.getNrLinha() > 0)
			xml_g->addItem( "nrLinha", oVenda.getNrLinha() );
		else
			xml_g->addItem( "nrLinha", "" );

		xml_g->addItem( "idStatusVenda", oVenda.getInEstadoVenda() );

		//----------------------------------------------------
		const int iArraySize=100;
		char  cStatusVenda[iArraySize][256];
		int   i;

		strcpy(cStatusVenda[0], "Sucesso");
		strcpy(cStatusVenda[1], "Falha SAP-Venda");
		strcpy(cStatusVenda[2], "Falha Sitef-Final");
		strcpy(cStatusVenda[3], "Falha SAP-Estorno");
		strcpy(cStatusVenda[99], "Transação Pendente");

		for (i=0 ; i < iArraySize ; i++){
			if (i == oVenda.getInEstadoVenda()){
				break;
			}
		}

		xml_g->addItem( "dsStatusVenda", cStatusVenda[i] );
		//----------------------------------------------------

		xml_g->closeTag();

		lstVenda.pop_front();
	}

	xml_g->closeTag();

}

static void consultarLojas(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char	cGet[256];

	list< CLojista > lstLojista;
	
	
	CLojista oLojista;
	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);


	

	try	
	{
		oPessoa->AsLojista()->constularLojas(lstLojista);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioLojasVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstLojista.size() ) {
		oLojista = lstLojista.front();

		xml_g->createTag( "VOLTAVRelatorioLojas" );

		xml_g->addItem( "idPessoaDePara", oLojista.getIdPessoaDePara());
		xml_g->addItem( "dsLoja",  oLojista.getNmPessoa(cGet));

		xml_g->closeTag();

		lstLojista.pop_front();
	}

	xml_g->closeTag();

	delete oPessoa;
}

static void consultarPrimeiroAcesso(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*   pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoCarteira =0;
	int		iIdSegmentacao =0;

	int		iIdCanal =0;
	int		iIdTipoLinha =0;
	int		iCdAreaRegistro =0;
	int		iNrLinha =0;
	char	cdLinhaTEMP[12];

	int		iIdTecnologia = 0;
	int		iIdPessoaDePara = 0;
	int		iIdTerminal = 0;

	long	lQtTotalClientesVOL=0;
	long	lQtTotalClientesTAV=0;

	list< CRelacionamento >	lstRelac;
	CLojista oLojistaRel;

	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
	{	
		memset( cdLinhaTEMP, 0, sizeof( cdLinhaTEMP ));
		//
		strncpy( cdLinhaTEMP, pcTagXmlIn, 2 );
		iCdAreaRegistro = atoi(cdLinhaTEMP);
		//
		sprintf( cdLinhaTEMP, "%s", (char*)&pcTagXmlIn[2] );
		iNrLinha = atoi(cdLinhaTEMP);
	}

	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);



	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
		if (pcTagXmlIn != NULL){
		
			iIdTerminal =   atoi(pcTagXmlIn);
			if (iIdTerminal != -1){	
		
				oLojistaRel.consultarIdTerminal(pcTagXmlIn);
				iIdTerminal =  oLojistaRel.getIdTerminal();		
			}
	}


	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);



	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

	try	
	{
		if (iIdCanal == 13) 
			oRelacionamento.consultarAcessoPorDiaLoja(lstRelac, 14, iIdTipoCarteira, 0, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, 0, iIdTecnologia, iIdPessoaDePara, iIdTerminal);
		else
			oRelacionamento.consultarAcessoPorDia(lstRelac, 14, iIdTipoCarteira, 0, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iCdAreaRegistro, iNrLinha, 0, iIdTecnologia );

		oRelacionamento.consultarTotalClientes(ID_CANAL_VOL);
		lQtTotalClientesVOL = oRelacionamento.getQtTotalClientes();

		oRelacionamento.consultarTotalClientes(ID_CANAL_TAV);
		lQtTotalClientesTAV = oRelacionamento.getQtTotalClientes();

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioPrimeiroAcessoVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstRelac.size() ) {
		oRelacionamento = lstRelac.front();

		xml_g->createTag( "VOLTAVRelatorioPrimeiroAcesso" );


		if (iIdCanal == 13) {
	
			xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );	
			xml_g->addItem("nrTerminal", oRelacionamento.getNmTerminal());

		}

		xml_g->addItem( "dtPrimeiroAcesso", oRelacionamento.getDtRelacionamento() );
			
		if (iIdTecnologia == 1) { //cdma
		
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() );
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario()) );
		
		}else if(iIdTecnologia == 2) { //gsm
	
			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreGsmCliente() );
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreGsmUsuario() );
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosGsmCliente() );
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosGsmUsuario() );	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreGsmCliente() );
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreGsmUsuario() );
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosGsmCliente() );
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosGsmUsuario() );
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleGsmUsuario() );
			xml_g->addItem( "qtTotal", (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario()) );

		
		}else{ //as duas tecnologias

			xml_g->addItem( "qtVOLPreCliente", oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreGsmCliente());
			xml_g->addItem( "qtVOLPreUsuario", oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPreGsmUsuario());
			xml_g->addItem( "qtVOLPosCliente", oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosGsmCliente());
			xml_g->addItem( "qtVOLPosUsuario", oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtVOLPosGsmUsuario());	
			xml_g->addItem( "qtTAVPreCliente", oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreGsmCliente());
			xml_g->addItem( "qtTAVPreUsuario", oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPreGsmUsuario());
			xml_g->addItem( "qtTAVPosCliente", oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosGsmCliente());
			xml_g->addItem( "qtTAVPosUsuario", oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVPosGsmUsuario());
			// linha controle
			xml_g->addItem( "qtTAVConCliente", oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleGsmCliente() );
			xml_g->addItem( "qtTAVConUsuario", oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtTAVControleGsmUsuario() );
			xml_g->addItem( "qtVOLConCliente", oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleGsmCliente() );
			xml_g->addItem( "qtVOLConUsuario", oRelacionamento.getQtVOLControleUsuario() + oRelacionamento.getQtVOLControleGsmUsuario() );	

			int iTotalCdma = (oRelacionamento.getQtVOLPreCliente() + oRelacionamento.getQtVOLPreUsuario() + oRelacionamento.getQtVOLPosCliente() + oRelacionamento.getQtVOLPosUsuario() + oRelacionamento.getQtTAVPreCliente() + oRelacionamento.getQtTAVPreUsuario() + oRelacionamento.getQtTAVPosCliente() + oRelacionamento.getQtTAVPosUsuario() + oRelacionamento.getQtTAVControleCliente() + oRelacionamento.getQtTAVControleUsuario() + oRelacionamento.getQtVOLControleCliente() + oRelacionamento.getQtVOLControleUsuario());
			int iTotalGsm  = (oRelacionamento.getQtVOLPreGsmCliente() + oRelacionamento.getQtVOLPreGsmUsuario() + oRelacionamento.getQtVOLPosGsmCliente() + oRelacionamento.getQtVOLPosGsmUsuario() + oRelacionamento.getQtTAVPreGsmCliente() + oRelacionamento.getQtTAVPreGsmUsuario() + oRelacionamento.getQtTAVPosGsmCliente() + oRelacionamento.getQtTAVPosGsmUsuario() + oRelacionamento.getQtTAVControleGsmCliente() + oRelacionamento.getQtTAVControleGsmUsuario() + oRelacionamento.getQtVOLControleGsmCliente() + oRelacionamento.getQtVOLControleGsmUsuario());
			
			xml_g->addItem( "qtTotal", iTotalGsm + iTotalCdma );
		}
		
		
		xml_g->addItem( "qtTotalClientesAcumuladosVOL", lQtTotalClientesVOL );
		xml_g->addItem( "qtTotalClientesAcumuladosTAV", lQtTotalClientesTAV );

		xml_g->closeTag();

		lstRelac.pop_front();
	}

	xml_g->closeTag();
}


static void consultarAcessoIncidencia(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*   pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoCarteira =0;
	int		iIdSegmentacao =0;

	int		iIdCanal =0;
	int		iIdTipoLinha =0;

	int		iIdTecnologia = 0;
	int		iIdPessoaDePara = 0;
	int		iIdTerminal = 0;


	long	lQtTotalAcessos2=0;
	long	lQtTotalAcessos5=0;
	long	lQtTotalAcessos9=0;
	long	lQtTotalAcessos10=0;

	CLojista oLojistaRel;
	
	list< CRelacionamento >	lstRelac;


	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);



	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}
	}


	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL)
		 iIdPessoaDePara = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

	try	
	{	
		if (iIdCanal != 13) {

			lstRelac.clear();

			oRelacionamento.consultarAcessoIncidencia(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal,iIdTecnologia, 1, 2);
			lQtTotalAcessos2 = oRelacionamento.getQtTotalClientes();

			//Clientes com até 5 acessos (intervalo de 3 a 5)
			oRelacionamento.consultarAcessoIncidencia(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iIdTecnologia,  3, 5);
			lQtTotalAcessos5 = oRelacionamento.getQtTotalClientes();

			//Clientes com até 9 acessos (intervalo de 6 a 9)
			oRelacionamento.consultarAcessoIncidencia(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iIdTecnologia,  6, 9);
			lQtTotalAcessos9 = oRelacionamento.getQtTotalClientes();

			//Clientes acima 10 acessos (intervalo de 10 a 9999)
			oRelacionamento.consultarAcessoIncidencia(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal,iIdTecnologia,  10, 9999);
			lQtTotalAcessos10 = oRelacionamento.getQtTotalClientes();
		
		}
		
		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}



	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioAcessosIncidenciaVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );


		if (iIdCanal == 13){

			oRelacionamento.consultarAcessoIncidenciaLoja(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal,iIdTecnologia, iIdPessoaDePara, iIdTerminal, 1, 2);

			while( 0 < lstRelac.size() ) {
				
				oRelacionamento = lstRelac.front();

				xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
					xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );
					xml_g->addItem( "nrTerminal", oRelacionamento.getNmTerminal());
					xml_g->addItem( "dsIncidencia", "Até" );
					xml_g->addItem( "qtIncidencia", "2" );
					xml_g->addItem( "qtTotalClientes", oRelacionamento.getQtTotalClientes() );
				xml_g->closeTag();

  			

				lstRelac.pop_front();

			}
			oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
			oRelacionamento.setDtRelacionamentoFinal(cDtFinal);
			oRelacionamento.consultarAcessoIncidenciaLoja(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iIdTecnologia, iIdPessoaDePara, iIdTerminal, 3, 5);
			while( 0 < lstRelac.size() ) {
				
				oRelacionamento = lstRelac.front();
				xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
					xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );
					xml_g->addItem( "nrTerminal", oRelacionamento.getNmTerminal());
					xml_g->addItem( "dsIncidencia", "Até" );
					xml_g->addItem( "qtIncidencia", "5" );
					xml_g->addItem( "qtTotalClientes", oRelacionamento.getQtTotalClientes() );
				xml_g->closeTag();

  				

				lstRelac.pop_front();

			}

			oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
			oRelacionamento.setDtRelacionamentoFinal(cDtFinal);
			oRelacionamento.consultarAcessoIncidenciaLoja(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iIdTecnologia, iIdPessoaDePara, iIdTerminal, 6, 9);
			while( 0 < lstRelac.size() ) {
				
				oRelacionamento = lstRelac.front();
				xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
					xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );
					xml_g->addItem( "nrTerminal", oRelacionamento.getNmTerminal());
					xml_g->addItem( "dsIncidencia", "Até" );
					xml_g->addItem( "qtIncidencia", "9" );
					xml_g->addItem( "qtTotalClientes", oRelacionamento.getQtTotalClientes() );
				xml_g->closeTag();
				
				lstRelac.pop_front();
			}
			
			oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
			oRelacionamento.setDtRelacionamentoFinal(cDtFinal);
			oRelacionamento.consultarAcessoIncidenciaLoja(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal,iIdTecnologia, iIdPessoaDePara, iIdTerminal, 10, 9999);
			while( 0 < lstRelac.size() ) {
				
				oRelacionamento = lstRelac.front();
			
				xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
					xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );
					xml_g->addItem( "nrTerminal", oRelacionamento.getNmTerminal());
					xml_g->addItem( "dsIncidencia", "Acima" );
					xml_g->addItem( "qtIncidencia", "10" );
					xml_g->addItem( "qtTotalClientes", oRelacionamento.getQtTotalClientes() );
				xml_g->closeTag();
				
				lstRelac.pop_front();
			}

		}else{
			xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
				xml_g->addItem( "dsIncidencia", "Até" );
				xml_g->addItem( "qtIncidencia", "2" );
				xml_g->addItem( "qtTotalClientes", lQtTotalAcessos2 );
			xml_g->closeTag();

			xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
				xml_g->addItem( "dsIncidencia", "Até" );
				xml_g->addItem( "qtIncidencia", "5" );
				xml_g->addItem( "qtTotalClientes", lQtTotalAcessos5 );
			xml_g->closeTag();

			xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
				xml_g->addItem( "dsIncidencia", "Até" );
				xml_g->addItem( "qtIncidencia", "9" );
				xml_g->addItem( "qtTotalClientes", lQtTotalAcessos9 );
			xml_g->closeTag();

			xml_g->createTag( "VOLTAVRelatorioAcessosIncidencia" );
				xml_g->addItem( "dsIncidencia", "Acima" );
				xml_g->addItem( "qtIncidencia", "10" );
				xml_g->addItem( "qtTotalClientes", lQtTotalAcessos10 );
			xml_g->closeTag();

		}

	xml_g->closeTag();
}

static void consultarTempoPermanencia(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	tuxfw_getlogger()->information("consultarTempoPermanencia"); 

	char*   pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	int		iIdTipoCarteira =0;
	int		iIdSegmentacao =0;

	int		iIdTecnologia = 0;
	int		iIdPessoaDePara = 0;
	int		iIdTerminal = 0;

	int		iIdCanal =0;
	int		iIdTipoLinha =0;
	CLojista oLojistaRel;

	list< CRelacionamento >	lstRelac;

	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoCarteira", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoCarteira = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idSegmentacao", 0);
	if (pcTagXmlIn != NULL)
		iIdSegmentacao = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idTecnologia", 0);
	if (pcTagXmlIn != NULL)
		 iIdTecnologia = atoi(pcTagXmlIn);

	tuxfw_getlogger()->information("tentativa 1"); 

	pcTagXmlIn = helper.walkTree(dnode,"nrTerminal", 0);
	if (pcTagXmlIn != NULL){
		
		iIdTerminal =   atoi(pcTagXmlIn);
		if (iIdTerminal != -1){	
		
			oLojistaRel.consultarIdTerminal(pcTagXmlIn);
			iIdTerminal =  oLojistaRel.getIdTerminal();		
		}
	}

	pcTagXmlIn = helper.walkTree(dnode,"idLoja", 0);
	if (pcTagXmlIn != NULL && strcmp(pcTagXmlIn,"null") != 0){		 
		 iIdPessoaDePara = atoi(pcTagXmlIn);
	}

	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);

	try	
	{
		//
		if (iIdCanal == 13)
			oRelacionamento.consultarTempoSessaoLoja(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal, iIdTecnologia, iIdPessoaDePara, iIdTerminal, 10, 9999);
		else
			oRelacionamento.consultarTempoSessao(lstRelac, iIdTipoCarteira, iIdTipoLinha, iIdUFOperadora, iIdSegmentacao, iIdGrupoOperadora, iIdCanal,iIdTecnologia, 10, 9999);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException &e)
	{
		if(!strcmp(e.getCode(), "11E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}

	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioTempoPermanenciaVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );


	while( 0 < lstRelac.size() ) {
				
		oRelacionamento = lstRelac.front();
	
		xml_g->createTag( "VOLTAVRelatorioTempoPermanencia" );


		if (iIdCanal == 13) {
			xml_g->addItem( "dsLoja", oRelacionamento.getNmLoja() );	
			xml_g->addItem("nrTerminal", oRelacionamento.getNmTerminal());
		}
		xml_g->addItem( "tmVOLPreCliente", strcmp(CUtil::trim(oRelacionamento.getTmVOLPreCliente()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmVOLPreCliente()) );
		xml_g->addItem( "tmVOLPreUsuario", strcmp(CUtil::trim(oRelacionamento.getTmVOLPreUsuario()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmVOLPreUsuario()) );
		xml_g->addItem( "tmVOLPosCliente", strcmp(CUtil::trim(oRelacionamento.getTmVOLPosCliente()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmVOLPosCliente()) );
		xml_g->addItem( "tmVOLPosUsuario", strcmp(CUtil::trim(oRelacionamento.getTmVOLPosUsuario()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmVOLPosUsuario()) );
		xml_g->addItem( "tmTAVPreCliente", strcmp(CUtil::trim(oRelacionamento.getTmTAVPreCliente()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTAVPreCliente()) );
		xml_g->addItem( "tmTAVPreUsuario", strcmp(CUtil::trim(oRelacionamento.getTmTAVPreUsuario()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTAVPreUsuario()) );
		xml_g->addItem( "tmTAVPosCliente", strcmp(CUtil::trim(oRelacionamento.getTmTAVPosCliente()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTAVPosCliente()) );
		xml_g->addItem( "tmTAVPosUsuario", strcmp(CUtil::trim(oRelacionamento.getTmTAVPosUsuario()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTAVPosUsuario()) );

		// controle
		xml_g->addItem( "tmTAVConCliente", strcmp(CUtil::trim(oRelacionamento.getTmTAVControleCliente()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTAVControleCliente()) );
		xml_g->addItem( "tmTAVConUsuario", strcmp(CUtil::trim(oRelacionamento.getTmTAVControleUsuario()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTAVControleUsuario()) );
		xml_g->addItem( "tmVOLConCliente", strcmp(CUtil::trim(oRelacionamento.getTmVOLControleCliente()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmVOLControleCliente()) );
		xml_g->addItem( "tmVOLConUsuario", strcmp(CUtil::trim(oRelacionamento.getTmVOLControleUsuario()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmVOLControleUsuario()) );

		xml_g->addItem( "qtTotalAcessos", oRelacionamento.getQtTotalClientes() );
		xml_g->addItem( "tmTotalUtilizacao", strcmp(CUtil::trim(oRelacionamento.getTmTotalUtilizacao()),"Min  Seg")==0 ? "":CUtil::trim(oRelacionamento.getTmTotalUtilizacao()) );
		xml_g->closeTag();
			
		lstRelac.pop_front();
	}
	
	xml_g->closeTag();
}

static void consultarStatusVenda(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	const int iArraySize=5;
	char cStatusVenda[iArraySize][256];

	strcpy(cStatusVenda[0], "Sucesso");
	strcpy(cStatusVenda[1], "Falha SAP-Venda");
	strcpy(cStatusVenda[2], "Falha Sitef-Final");
	strcpy(cStatusVenda[3], "Falha SAP-Estorno");
	strcpy(cStatusVenda[4], "Transação Pendente");

	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatorioStatusVendaVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	for (int i=0 ; i < iArraySize ; i++)
	{
		xml_g->createTag( "VOLTAVRelatorioStatusVenda" );
			xml_g->addItem( "idStatusVenda", i == 4 ? 99 : i );
			xml_g->addItem( "dsStatusVenda", cStatusVenda[i] );
		xml_g->closeTag();
	}

	xml_g->closeTag();

	// seta mensagem de retorno - header	
	statusCode.setCod(STR_11IOK);
	statusCode.setMsg(STR_11IOKMSG);
}

static void consultarLojasRelatorio(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	list< CLojista > lstLojistaRel;
	CLojista oLojistaRel;
	char	cGet[256];
	char*   pcTagXmlIn = NULL;
	int		iIdUFOperadora =0;


	CPessoaAbstract *oPessoaRel = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	try	
	{
		oPessoaRel->AsLojista()->consultarLojasRel(lstLojistaRel, iIdUFOperadora);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException )
	{	
		// removendo warning
	//	e.eCode = 0;

		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}
	
	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatoriosFiltrosVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstLojistaRel.size() ) {
		oLojistaRel = lstLojistaRel.front();

		xml_g->createTag( "VOLTAVRelatoriosFiltroLojaVO" );

		xml_g->addItem( "idPessoaDePara", oLojistaRel.getIdPessoaDePara());
		xml_g->addItem( "dsLoja",  oLojistaRel.getNmPessoa(cGet));

		xml_g->closeTag();

		lstLojistaRel.pop_front();
	}

	xml_g->closeTag();

	delete oPessoaRel;



}

void consultarTerminais(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	list< CLojista > lstLojistaRel;
	CLojista oLojistaRel;
	char	 cGet[256];
	char*    pcTagXmlIn = NULL;
	int		 iIdPessoaDePara =0;

	CPessoaAbstract *oPessoaRel = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);
	
	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	if (pcTagXmlIn != NULL)
		iIdPessoaDePara = atoi(pcTagXmlIn);

	try	
	{
		oPessoaRel->AsLojista()->consultarTerminais(lstLojistaRel, iIdPessoaDePara);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch( ... ){}


	// Monta o XML de saída
	xml_g->createTag( "VOLTAVRelatoriosFiltrosVO" );
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	while( 0 < lstLojistaRel.size() ) {
		oLojistaRel = lstLojistaRel.front();

		xml_g->createTag( "VOLTAVRelatoriosFiltroTerminalVO" );

		xml_g->addItem( "NrTerminal", oLojistaRel.getNrTerminal(cGet));
		xml_g->addItem( "NrIpTerminal",  oLojistaRel.getNrIpTerminal(cGet));

		xml_g->closeTag();

		lstLojistaRel.pop_front();
	}

}

static void consultarCampanhaVip(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*  pcTagXmlIn = NULL;
	char*	cDtInicial = NULL;
	char*	cDtFinal = NULL;
	list< CRelacionamento >	lstRelac;
	int		iIdUFOperadora =0;
	int		iIdGrupoOperadora =0;
	
	int		iIdCanal =0;
	int		iCdAreaRegistro =0;
	int		iNrLinha =0;
	char	cdLinhaTEMP[12];
	int     inAtivadoEnvioMail = 0;


	// Navega o XML e recupera as informacoes obrigatorias
	cDtInicial = helper.walkTree(dnode,"dtInicial", 0);
	ASSERT_STR(cDtInicial, "dtInicial");

	//
	cDtFinal = helper.walkTree(dnode,"dtFinal", 0);
	ASSERT_STR(cDtFinal, "dtFinal");

	//
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"inAtivadoEnvioMail", 0);
	if (pcTagXmlIn != NULL)
		inAtivadoEnvioMail = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
		iNrLinha = atoi(pcTagXmlIn);
	
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
		iCdAreaRegistro = atoi(pcTagXmlIn);


	oRelacionamento.setDtRelacionamentoInicio(cDtInicial);
	oRelacionamento.setDtRelacionamentoFinal(cDtFinal);


	try	
	{
		oRelacionamento.consultarCampanhaVip(iIdUFOperadora, iIdGrupoOperadora, iCdAreaRegistro, iNrLinha, inAtivadoEnvioMail, xml_g);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException)
	{
		
		statusCode.setCod(STR_11W0002);
		statusCode.setMsg("INFORMACAO NAO EXISTENTE");
	}

}

void implDADACESSO::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	//ASSERT_STR(pcTagXmlIn, "operacao");

	// descobrindo qual operação executar
	if (!strcmp(pcTagXmlIn, STR_CONSULTARACESSOPORDIA))
		consultarAcessoPorDia(dnode, xml_g, statusCode);		
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARACESSOPORHORA))
		consultarAcessoPorHora(dnode, xml_g, statusCode);							
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARADESOES))
		consultarAdesoes(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARACESSONEGADO))
		consultarAcessoNegado(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARSERVICOSEFETUADOS))
		consultarServicosEfetuados(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARSERVICOSEFETUADOSURA))
		consultarServicosEfetuadosURA(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARSERVICOSDISPONIVEIS))
		consultarServicosDisponiveis(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARFINANCEIRO))
		consultarFinanceiro(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARLOJAS))
		consultarLojas(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARPRIMEIROACESSO))
		consultarPrimeiroAcesso(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARACESSOINCIDENCIA))
		consultarAcessoIncidencia(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARTEMPOPERMANENCIA))
		consultarTempoPermanencia(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARSTATUSVENDA))
		consultarStatusVenda(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, "consultarLojasRelatorio"))
		consultarLojasRelatorio(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, "consultarTerminais"))
		consultarTerminais(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, "consultarCampanhaVip"))
		consultarCampanhaVip(dnode, xml_g, statusCode);	


	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());
}