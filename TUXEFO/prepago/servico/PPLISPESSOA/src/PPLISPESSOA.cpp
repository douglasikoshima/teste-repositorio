///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPLISPESSOA
 * @author  Renato Striitzel Russo
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:37 $
 **/
///////////////////////////////////////////////////////////////////////////////

//-- FRAMEWORK ----------------------------------------------------------------
#include <tuxfw.h>

#include <CPesquisaPessoa.h>
#include <Global.h>
#include <PrePagoException.h>

#define FO_CHAR_NOME    "NOME"
#define FO_CHAR_CELULAR "CELULAR"
#define FO_CHAR_CONTA   "CONTA"
#define FO_CHAR_RAZAO   "RAZAO"

DECLARE_TUXEDO_SERVICE(PPLISPESSOA);

void implPPLISPESSOA::Execute(DOMNode *pDnode, XMLGen *xml_g)
{
    ULOG_START("PPLISPESSOA");

	//Classe de negocio
	CPesquisaPessoa oPesquisaPessoa;
	//Variaveis gerais
	int nServico = 0;
	char* cPesquisa = walkTree(pDnode, "pesquisa", 0);
	char* cinTipoPessoa = walkTree(pDnode, "inTipoPessoa", 0);
	char* cnmRazao;
	char* cnmNome;
	char* cnmMeio;
	char* cnmSobreNome;
	char* cDDDFone;
	char* ccdConta;
	char* cnrDocumento;
	char  cDDD[2+1];
	char  cFone[10+1];
	char  cMensagem[255+1];
	char  csgTipoDocumento[255+1];
	//Zerando tudo
	memset( &cDDD, 0, sizeof( cDDD ) );
	memset( &cFone, 0, sizeof( cFone ) );
	memset( &cMensagem, 0, sizeof( cMensagem ) );
	memset( &csgTipoDocumento, 0, sizeof( csgTipoDocumento ) );
	if( strlennull( cPesquisa ) > 0 )
		oPesquisaPessoa.upper(cPesquisa);
	if( strlennull( cinTipoPessoa ) > 0 )
		oPesquisaPessoa.upper(cinTipoPessoa);
	//Inicio do processo
	if( strlennull( cPesquisa ) <= 0 )
	{
		setStatusCode( "12E0001", "A TAG <pesquisa> está nula" );
		return;
	}
	if( strcmp( FO_CHAR_NOME, cPesquisa ) == 0 )
	{
		cnmNome      = walkTree(pDnode, "valor", 0);
		cnmMeio      = walkTree(pDnode, "nmMeio", 0);
		cnmSobreNome = walkTree(pDnode, "nmSobreNome", 0);
		if( strlennull( cnmNome ) <= 0 )
		{
			setStatusCode( "12W0001", "Para asta pesquisa o nome é obrigatório" );
			return;
		}
		if( strlennull( cnmSobreNome ) <= 0 )
		{
			setStatusCode( "12W0000", "Para asta pesquisa o sobrenome é obrigatório" );
			return;
		}
		oPesquisaPessoa.buscarDocPorNome( cnmNome
							            , cnmMeio
							            , cnmSobreNome
							            , cinTipoPessoa );

	}
	else if( strcmp( FO_CHAR_CELULAR, cPesquisa ) == 0 )
	{
		cDDDFone = walkTree(pDnode, "valor", 0);
		if( strlennull( cDDDFone ) <= 0 )
		{
			setStatusCode( "12W0000", "Para asta pesquisa um número de telefone é obrigatório" );
			return;
		}
		strncpy( cDDD, &cDDDFone[0], 2 );
		if( strlennull( &cDDDFone[2] ) <= 10 )
			strcpy( cFone, &cDDDFone[2] );
		else
			strncpy( cFone, &cDDDFone[2], 10 );//Nunca sera maior que oito, mas se expandir, aceita ateh 10

		oPesquisaPessoa.buscarDocPorNrLinha( cDDD
		                                   , cFone
							               , cinTipoPessoa);
	}
	else if( strcmp( FO_CHAR_CONTA, cPesquisa ) == 0 )
	{
		ccdConta = walkTree(pDnode, "valor", 0);
		if( strlennull( ccdConta ) <= 0 )
		{
			setStatusCode( "12W0000", "Para asta pesquisa um número de conta é obrigatório" );
			return;
		}

		oPesquisaPessoa.buscarDocPorNrConta( ccdConta
						                   , cinTipoPessoa );

	}
	else if( strcmp( FO_CHAR_RAZAO, cPesquisa ) == 0 )
	{
		cnmRazao = walkTree(pDnode, "valor", 0);
		if( strlennull( cnmRazao ) <= 0 )
		{
			setStatusCode( "12W0000", "Para asta pesquisa uma razão é necessária" );
			return;
		}

		oPesquisaPessoa.buscarDocPorRazao( cnmRazao
						                 , cinTipoPessoa );

	}
	else //Pesquisa por sigla de documento
	{
		cnrDocumento = walkTree(pDnode, "valor", 0);
		if( strlennull( cnrDocumento ) <= 0 )
		{
			setStatusCode( "12W0000", "Para asta pesquisa um número de documento é obrigatório" );
			return;
		}
		if( oPesquisaPessoa.validaSiglaDocumento( cPesquisa ) <= 0 )
		{
			sprintf( cMensagem, "A sigla de documento passada não é válida: [&s]", cPesquisa );
			setStatusCode( "12W0000", "A sigla de documento passada não é válida." );
			return;
		}
		//Se achou a sigla, recupera tal qual esta escrita no banco
		strcpy( csgTipoDocumento, oPesquisaPessoa.Registro()->csgTipoDocumento );

		oPesquisaPessoa.buscarDocPorTipDocumento( csgTipoDocumento
		                                        , cnrDocumento
							                    , cinTipoPessoa);

	}
    xml_g->createTag("PesquisaTIVO");
    xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");

	if( oPesquisaPessoa.Quantidade() > 100 )
		xml_g->addItem("limite", 1 );//Só envia esta TAG se tiver mais de 100 registros
	else
	{
		for( int x=0; x < oPesquisaPessoa.Quantidade(); x++) 
		{
			xml_g->createTag("ClientesPesquisados");
				xml_g->addItem("idPessoa"       , oPesquisaPessoa.Registro(x)->cidPessoa );
				xml_g->addItem("nmNome"         , oPesquisaPessoa.Registro(x)->cnmPessoa );
				xml_g->addItem("dsTipoDocumento", oPesquisaPessoa.Registro(x)->cdsTipoDocumento );
				xml_g->addItem("nrDocumento"    , oPesquisaPessoa.Registro(x)->cnrDocumento );
				xml_g->addItem("dsTipoPessoa"   , oPesquisaPessoa.Registro(x)->cdsTipoPessoa );//Retorna a sigla, nao a descricao, conforme o nome do campo indica
			xml_g->closeTag();//ClientesPesquisados
		}
	}
    xml_g->closeTag();//PesquisaTIVO

    ULOG_END("PPLISPESSOA");

    setStatusCode( "12I0000", "Sucesso" );
}
