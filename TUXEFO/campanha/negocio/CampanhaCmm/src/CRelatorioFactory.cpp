//////////////////////////////////////////////////////////////////////
// CRelatorioFactory.cpp: CRelatorioFactory class.
//////////////////////////////////////////////////////////////////////
#include <tuxfw.h>
#include <CRelatorioCampanha.h>
#include <CRelatorioFactory.h>
#include <CRelatorioGerenciamento.h>
#include <CRelatorioEfetividade.h>
#include <CRelatorioOperador.h>
#include <CRelatorioMotivos.h>
#include <CRelatorioRespostas.h>
#include <CRelatorioAgendaAniversario.h>
#include <CRelArqResp.h>
#include <CRelArqResult.h>

// Recupera instância de relatório
CRelatorioCampanha* CRelatorioFactory::getRelatorio(DOMNode* dnode,XMLGen*xml_g, int user) {

	CRelatorioCampanha* poRelatorioFactory = getInstance(dnode); 

	// Recupera os dados do relatório e monta estrutura com dados.
	SRelatorioCampanha* sRelatorioCampanha = setData(dnode); 

	sRelatorioCampanha->pcUser = user; 

	// monta o relatório
	poRelatorioFactory->montaRelatorio(sRelatorioCampanha,xml_g);

	return poRelatorioFactory;
}

//--------------------------------------------------------------------------------------------------------------
// Realiza a checagem dos parametros de entrada..
//--------------------------------------------------------------------------------------------------------------

stErro CRelatorioFactory::VerificaRelatorio(DOMNode* dnode) {

	CRelatorioCampanha* poRelatorioFactory = NULL;

	// Recupera o ID do relatório a ser gerado.
	poRelatorioFactory = getInstance(dnode); 

	// Recupera os dados do relatório e monta estrutura com dados.
	SRelatorioCampanha* sRelatorioCampanha = setData(dnode); 

	// monta o relatório
	return (poRelatorioFactory->CheckRelatorio(sRelatorioCampanha));

}

//--------------------------------------------------------------------------------------------------------------
// Carrega os dados de Campanha..
//--------------------------------------------------------------------------------------------------------------
SRelatorioCampanha* CRelatorioFactory::setData(DOMNode* dnode) 
{
	TuxHelper tuxHelper;
	SRelatorioCampanha *sRelatorioCampanha = NULL;
	sRelatorioCampanha = (SRelatorioCampanha*)realloc((void*)sRelatorioCampanha,sizeof(SRelatorioCampanha));

	if( strlennull( tuxHelper.walkTree(dnode, "idCampanha", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidCampanha, tuxHelper.walkTree(dnode, "idCampanha", 0) );
	else
		strcpy( sRelatorioCampanha->pcidCampanha, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idSubCampanha", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidSubCampanha, tuxHelper.walkTree(dnode, "idSubCampanha", 0) );
	else
		strcpy( sRelatorioCampanha->pcidSubCampanha, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idCanalCampanha", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidCanalCampanha, tuxHelper.walkTree(dnode, "idCanalCampanha", 0) );
	else
		strcpy( sRelatorioCampanha->pcidCanalCampanha, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idGrupo", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidGrupo, tuxHelper.walkTree(dnode, "idGrupo", 0) );
	else
		strcpy( sRelatorioCampanha->pcidGrupo, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idRegional", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidRegional, tuxHelper.walkTree(dnode, "idRegional", 0) );
	else
		strcpy( sRelatorioCampanha->pcidRegional, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "dtInicio", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcdtInicio, tuxHelper.walkTree(dnode, "dtInicio", 0) );
	else
		strcpy( sRelatorioCampanha->pcdtInicio, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "dtFim", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcdtFim, tuxHelper.walkTree(dnode, "dtFim", 0) );
	else
		strcpy( sRelatorioCampanha->pcdtFim, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idMotivo", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidMotivo, tuxHelper.walkTree(dnode, "idMotivo", 0) );
	else
		strcpy( sRelatorioCampanha->pcidMotivo, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idPergunta", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidPergunta, tuxHelper.walkTree(dnode, "idPergunta", 0) );
	else
		strcpy( sRelatorioCampanha->pcidPergunta, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idOperador", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidOperador, tuxHelper.walkTree(dnode, "idOperador", 0) );
	else
		strcpy( sRelatorioCampanha->pcidOperador, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "sgOperador", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcsgOperador, tuxHelper.walkTree(dnode, "sgOperador", 0) );
	else
		strcpy( sRelatorioCampanha->pcsgOperador, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "idAreaRegistro", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidAreaRegistro, tuxHelper.walkTree(dnode, "idAreaRegistro", 0) );
	else
		strcpy( sRelatorioCampanha->pcidAreaRegistro, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "dataAniversario", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcdtAniversario, tuxHelper.walkTree(dnode, "dataAniversario", 0) );
	else
		strcpy( sRelatorioCampanha->pcdtAniversario, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "linha", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcnrLinha, tuxHelper.walkTree(dnode, "linha", 0) );
	else
		strcpy( sRelatorioCampanha->pcnrLinha, "-1" );

	if( strlennull( tuxHelper.walkTree(dnode, "inDistincao", 0) ) > 0 )
		strcpy( sRelatorioCampanha->pcidDistincao, tuxHelper.walkTree(dnode, "inDistincao", 0) );
	else
		strcpy( sRelatorioCampanha->pcidDistincao, "" );

	// adicionamos a TAG nmOperador
	// Pega o nome do operador, caso tenha sido passado
	char* pszValorTmp = NULL;
	pszValorTmp = tuxHelper.walkTree(dnode, "nmOperador", 0);
	
	if( pszValorTmp != NULL )
		strcpy(sRelatorioCampanha->pcnmOperador, pszValorTmp);
	else
		strcpy(sRelatorioCampanha->pcnmOperador, "");

	// Desaloca o valor temporario
	delete pszValorTmp; pszValorTmp = NULL;

	ULOGI("  --->login SetData()::sRelatorioCampanha->pcnmOperador [%s]", sRelatorioCampanha->pcnmOperador); 


	return sRelatorioCampanha;
}

//--------------------------------------------------------------------------------------------------------------
// Carrega os dados de Campanha..
//--------------------------------------------------------------------------------------------------------------
CRelatorioCampanha* CRelatorioFactory::getInstance(DOMNode* dnode) 
{
	TuxHelper tuxHelper;
	CRelatorioCampanha* poRelatorioFactory = NULL;
	char *pcIdRelatorioFactory = NULL; 
	int iIdRelatorioFactory = 0;

	pcIdRelatorioFactory = tuxHelper.walkTree(dnode, "idRelatorio", 0);

	if (pcIdRelatorioFactory)
		iIdRelatorioFactory = atoi(pcIdRelatorioFactory);

	// Instancia objeto do relatório
	switch(iIdRelatorioFactory) {
		case REL_GERENCIAMENTO: //1
			poRelatorioFactory = new CRelatorioGerenciamento();
			break;
		case REL_EFETIVIDADE: //2
			poRelatorioFactory = new CRelatorioEfetividade();
			break;
		case REL_OPERADOR: //3
			poRelatorioFactory = new CRelatorioOperador();
			break;
		case REL_MOTIVOS: //4
			poRelatorioFactory = new CRelatorioMotivos();
			break;
		case REL_RESPOSTAS: //5
			poRelatorioFactory = new CRelatorioRespostas();
			break;
		case REL_AGANIVERSARIO: //6
			poRelatorioFactory = new CRelatorioAgAni();
			break;
		case REL_ARQRESP: //7
			poRelatorioFactory = new CRelArqResp();
			break; 
		case REL_ARQRESULT: //8
			poRelatorioFactory = new CRelArqResult();
			break; 

		default:
			return NULL;
	}

	return poRelatorioFactory; 
}

//--------------------------------------------------------------------------------------------------------------