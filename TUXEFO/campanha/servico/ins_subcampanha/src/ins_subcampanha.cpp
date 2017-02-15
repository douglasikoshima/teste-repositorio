///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  campanha
 * @usecase INSSUBCAMPANHA
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:48 $
 **/
///////////////////////////////////////////////////////////////////////////////

//-- FRAMEWORK ----------------------------------------------------------------
#include <tuxfw.h>

#include "../../negocio/CampanhaCmm/include/CSubCampanhaHistorico.h"
#include "../../negocio/CampanhaCmm/include/CSubCampanhaFixa.h"
#include "../../negocio/CampanhaCmm/include/CCanalCampanha.h"
#include "../../negocio/CampanhaCmm/include/CSubCampanhaGrupoUsuario.h"

DECLARE_TUXEDO_SERVICE(INSSUBCAMPANHA);

void implINSSUBCAMPANHA::Execute(DOMNode *pDnode, XMLGen *xml_g)
{
	//Classe de negocio
	CSubCampanhaHistorico oSubCampanhaHistorico;
	CSubCampanhaFixa oSubCampanhaFixa;
	CCanalCampanha oCanalCampanha;
	CSubCampanhaGrupoUsuario oSubCampanhaGrupoUsuario;
	//Variaveis gerais
	char* cNaoDisponivel = "0";
	DOMNode* dnNode;

	//Recuperas dados do XML
	char* cidCanalUFOperadora;
	char* csqApresentacao;
	char* cidGrupo;
	char* ciAgendaMaxDefault = "0";
	int   iCont;
	
	char* cidCampanha = walkTree(pDnode, "idCampanha", 0);
	char* cinAtivo = walkTree(pDnode, "inAtivo", 0);
	char* cidTipoCampanha = walkTree(pDnode, "idTipoCampanha", 0);
	char* cdsSubCampanha = walkTree(pDnode, "dsSubCampanha", 0);
	char* cinCliente = walkTree(pDnode, "inCliente", 0);
	char* ciAgendaMax = walkTree(pDnode, "iAgendaMax", 0);
	char* cdtInicio = walkTree(pDnode, "dtInicio", 0);
	char* cdtTermino = walkTree(pDnode, "dtTermino", 0);
	char* csqVersao = walkTree(pDnode, "sqVersao", 0);
	char* ciRecontato = walkTree(pDnode, "iRecontato", 0);
	char* cnmSubCampanha = walkTree(pDnode, "nmSubCampanha", 0);
	char* cinDisponibilidade = walkTree(pDnode, "inDisponibilidade", 0);
	char* cinFidelizacao = walkTree(pDnode, "inFidelizacao", 0);
	char* cidUsuario = getUser();
	
	//Verifica as tags obrigatorias
	if( strlennull( cidCampanha ) <= 0 )
	{
    	setStatusCode( "12E0001", "idCampanha está nulo" );
		return;
	}
	if( strlennull( cinAtivo ) <= 0 )
	{
    	setStatusCode( "12E0002", "inAtivo está nulo" );
		return;
	}
	if( strlennull( cidTipoCampanha ) <= 0 )
	{
    	setStatusCode( "12E0003", "idTipoCampanha está nulo" );
		return;
	}
	if( strlennull( cdsSubCampanha ) <= 0 )
	{
    	setStatusCode( "12E0004", "dsSubCampanha está nulo" );
		return;
	}
	if( strlennull( cinCliente ) <= 0 )
	{
    	setStatusCode( "12E0005", "inCliente está nulo" );
		return;
	}
	if( strlennull( cdtInicio ) <= 0 )
	{
    	setStatusCode( "12E0007", "dtInicio está nulo" );
		return;
	}
	if( strlennull( cdtTermino ) <= 0 )
	{
    	setStatusCode( "12E0008", "dtTermino está nulo" );
		return;
	}
	if( strlennull( csqVersao ) <= 0 )
	{
    	setStatusCode( "12E0009", "sqVersao está nulo" );
		return;
	}
	if( strlennull( ciRecontato ) <= 0 )
	{
    	setStatusCode( "12E0010", "iRecontato está nulo" );
		return;
	}
	if( strlennull( cnmSubCampanha ) <= 0 )
	{
    	setStatusCode( "12E0011", "nmSubCampanha está nulo" );
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
		cinDisponibilidade = cNaoDisponivel;

	if( strcmp( ciRecontato, "1" ) == 0 )
	{
		if( strlennull( ciAgendaMax ) <= 0 )
		{
    		setStatusCode( "12E0006", "iAgendaMax está nulo" );
			return;
		}
	}
	else
		ciAgendaMax = ciAgendaMaxDefault;

	if( oSubCampanhaFixa.ProcuraNome( cnmSubCampanha,cidCampanha ) > 0 )
	{
    	setStatusCode( "12W0001", "Já existe uma subcampanhafixa com o mesmo nome" );
    	return;
	}
	if( strlennull( cinFidelizacao ) <= 0 )
		cinFidelizacao = cNaoDisponivel;

	//Insere a campanha fixa antes de inserir a subcampanhahistorico
	oSubCampanhaFixa.Insert( cnmSubCampanha,
	                         cidCampanha,
                             cinAtivo,
                             cinDisponibilidade );

	//Insere a subcampanhahistorico utilizando o idSubCampanhaFixa 
	oSubCampanhaHistorico.Insert( oSubCampanhaFixa.Registro(0)->cidSubCampanhaFixa,
	                              cdsSubCampanha,
	                              cinCliente,
	                              ciAgendaMax,
	                              cdtInicio,
	                              cdtTermino,
								  "1",//Versao, sempre cria a primeira como 1
	                              ciRecontato,
	                              cidTipoCampanha,
	                              "",//Se nulo, entao verifica a tabela e recupera o maior + 1
	                              cidUsuario,
	                              cnmSubCampanha,
	                              cinDisponibilidade );

	//Insere em SubCampanhaFidelizacao
	if( strcmp( cinFidelizacao, "1" ) == 0 )
	{
		oSubCampanhaHistorico.AtualizaSubCampanhaFidelizacao( oSubCampanhaHistorico.Registro(0)->cidSubCampanhaHistorico,
										                      cidUsuario );
	}

	//Insere a campanha recem criada em SubCampanhaAtual
	oSubCampanhaHistorico.AtualizaSubCampanhaAtual( oSubCampanhaFixa.Registro(0)->cidSubCampanhaFixa,
                                                    oSubCampanhaHistorico.Registro(0)->cidSubCampanhaHistorico,
													cidUsuario );

	//Cadastra os canais
	for( iCont = 0;; iCont++ )
	{
		dnNode = walkDOM( pDnode, "ApoioCanalVO", iCont );
		if( dnNode == NULL )
			break;
		cidCanalUFOperadora = walkTree(dnNode, "idCanalUFOperadora", 0);
		csqApresentacao = walkTree(dnNode, "sqApresentacao", 0);

		oCanalCampanha.Insert( 	oSubCampanhaHistorico.Registro(0)->cidSubCampanhaHistorico,
				                cidCanalUFOperadora,
				                csqApresentacao,
				                cinDisponibilidade,
				                cinAtivo,
				                cidUsuario );

		XMLString::release( &cidCanalUFOperadora );
		XMLString::release( &csqApresentacao );
	}

	XMLString::release( &cinAtivo );

	//Cadastra os perfis
	for( iCont = 0;; iCont++ )
	{
		dnNode = walkDOM( pDnode, "PerfilUtil", iCont );
		if( dnNode == NULL )
			break;
		cidGrupo = walkTree(dnNode, "idGrupo", 0);
		cinAtivo = walkTree(dnNode, "ginAtivo", 0);

		oSubCampanhaGrupoUsuario.Insert( oSubCampanhaFixa.Registro(0)->cidSubCampanhaFixa,
		                                 cidGrupo,
		                                 cinAtivo,
		                                 cidUsuario );

		XMLString::release( &cidGrupo );
		XMLString::release( &cinAtivo );
	}



	xml_g->createTag( "tns:RetornoCampanhaVO " );
	xml_g->addProp("xmlns:tns", "retornotux.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" );
	xml_g->addItem("idSubCampanhaFixa", oSubCampanhaHistorico.Registro(0)->cidSubCampanhaFixa );
	xml_g->addItem("idSubCampanhaHistorico", oSubCampanhaHistorico.Registro(0)->cidSubCampanhaHistorico);
	xml_g->closeTag();//tns:retornoVO

//	xml_g->addItem("valor", oSubCampanhaHistorico.Registro(0)->cidSubCampanhaHistorico );
    setStatusCode( "12I0000", "Sucesso" );


	//Libera a memoria
	XMLString::release( &cidCampanha );
	XMLString::release( &cinAtivo );
	XMLString::release( &cidTipoCampanha );
	XMLString::release( &cdsSubCampanha );
	XMLString::release( &cinCliente );
	XMLString::release( &ciAgendaMax );
	XMLString::release( &cdtInicio );
	XMLString::release( &cdtTermino );
	XMLString::release( &csqVersao );
	XMLString::release( &ciRecontato );
	XMLString::release( &cnmSubCampanha );
	XMLString::release( &cinDisponibilidade );
}
