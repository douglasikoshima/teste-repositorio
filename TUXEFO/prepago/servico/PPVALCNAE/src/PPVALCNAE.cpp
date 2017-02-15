///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPVALCNAE
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:53 $
 **/
///////////////////////////////////////////////////////////////////////////////

//-- FRAMEWORK ----------------------------------------------------------------
#include <tuxfw.h>
//-- ARQUIVOS HERDADOS DE SINCRONISMO (RENATO RUSSO/CADU) ---------------------
#include <Global.h>
#include <PrePagoException.h>
#include <CCnae.h>

DECLARE_TUXEDO_SERVICE(PPVALCNAE);

void implPPVALCNAE::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("PPVALCNAE");

	//Instancia a classe CCnae
	CCnae oCnae;
	//Recupera a TAG para pesquisa
	char* cnrCNAE = walkTree( dnode, "nrCNAE", 0 );

	//Verifica se a TAG esta preenchida
	if( strlennull( cnrCNAE ) <= 0 )
	{
		setStatusCode( "12E0001", "nrCNAE está nulo" );
		return;
	}
	
	//Pesquisa os dados CNAE atravez do codigo
	oCnae.ListNrCNAE( cnrCNAE );

	//Verifica se retornou alguma coisa
	if( oCnae.Quantidade() > 0 )
	{
		//Monta o XML com o resultado
		oCnae.getXml( "CNAEVO", xml_g );
	}
	else
	{
		//Caso nao ache nada, retorna apenas a TAG que recebemos
		xml_g->createTag( "CNAEVO" );
		xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
			xml_g->addItem( "idCNAE" , "" );
			xml_g->addItem( "nrCNAE" , cnrCNAE );
			xml_g->addItem( "dsCNAE" , "" );
			xml_g->addItem( "cdCFOP" , "" );
		xml_g->closeTag();//CNAEVO
	}
	//Libera o texto do wallTree
	XMLString::release(&cnrCNAE);

    ULOG_END("PPVALCNAE");

	//Acerta o status de sucesso
    setStatusCode( "12I0000", "Sucesso" );
}
