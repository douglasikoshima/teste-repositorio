///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPVALLIN
 * @author  Renato Striitzel Russo
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:07 $
 **/
///////////////////////////////////////////////////////////////////////////////

//-- FRAMEWORK ----------------------------------------------------------------
#include <tuxfw.h>
//-- ARQUIVOS HERDADOS DE SINCRONISMO (RENATO RUSSO/CADU) ---------------------
#include <Global.h>
#include <PrePagoException.h>
#include <AreaRegistroBloqueadoPre.h>

//-- ARQUIVOS CRIADOS PELO EDER -----------------------------------------------
#include <CLinhaBase.h>

DECLARE_TUXEDO_SERVICE(PPVALLIN);

void implPPVALLIN::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("PPVALLIN");

//-- Definindo variaveis -----------------------------------------------------------------
	TValidaLinhaXmlIn tValidaLinhaXmlIn;
	CLinhaBase oLinhaBase;
	char ccdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
	char cnrLinha[LEN_NRLINHA + LEN_EOS];
    bool bDDDBloqueado;

//-- Zerando as variaveis ---------------------------------------------------------------------	
	memset( &tValidaLinhaXmlIn, 0, sizeof( tValidaLinhaXmlIn ) );
	memset( ccdAreaRegistro, 0, sizeof( ccdAreaRegistro ) );
	memset( cnrLinha, 0, sizeof( cnrLinha ) );

//-- Instanciandos as classes necessarias ---------------------------------------------------------------------	
	GETXML( tValidaLinhaXmlIn, nrLinha );
	GETXML( tValidaLinhaXmlIn, origemsistema );

//-- Recuperando os dados do XML -------------------------------------------------------
	if( strlennull( tValidaLinhaXmlIn.nrLinha ) <= 0 )
	{
        ULOG_END("PPVALLIN");
		setStatusCode( "12E0001", "nrLinha está nulo" );
		return;
	}
	/*
	if( strlennull( tValidaLinhaXmlIn.origemsistema ) <= 0 )
	{
		setStatusCode( "12E0002", "origemsistema está nulo" );
		return;
	}
	*/
	if( strlennull( tValidaLinhaXmlIn.nrLinha ) < 3 )
	{
        ULOG_END("PPVALLIN");
		setStatusCode( "12E0003", "nrLinha está com tamalho inválido. Dever conter no mínimo 3 dígitos" );
		return;
	}
	//Recupera o DDD
	strncpy( ccdAreaRegistro, &tValidaLinhaXmlIn.nrLinha[0], 2 );
	//Recupera o numero da linha
	strcpy( cnrLinha, &tValidaLinhaXmlIn.nrLinha[2] );
	
    xml_g->createTag("PrePagoRetornoValidaLinha");
    xml_g->addProp("xmlns" ,"cliente.fo.vivo.com.br/vo");

    CAreaRegistroBloqueado *pclAreaRegistroBloqueado = new CAreaRegistroBloqueado;
    pclAreaRegistroBloqueado->setCdAreaRegistro(ccdAreaRegistro);
    bDDDBloqueado = pclAreaRegistroBloqueado->DDDBloqueado();
    delete pclAreaRegistroBloqueado;

    if(bDDDBloqueado == true)
    {
        xml_g->addItem("result", "BLOQ");
        xml_g->addItem("digito", "" );
        xml_g->addItem("idlinhaTelefonica", "" );
        xml_g->addItem("idPessoa", "" );
        xml_g->addItem("idTipoPessoa", "" );
    }
    else
    {

    	switch( oLinhaBase.ValidaLinhaPrePago( ccdAreaRegistro,cnrLinha ))
    	{
    		case -1://Nao Achou nada
    			xml_g->addItem("result", "NOK");
    			xml_g->addItem("digito", "" );
    			xml_g->addItem("idlinhaTelefonica", "" );
    			xml_g->addItem("idPessoa", "" );
    			xml_g->addItem("idTipoPessoa", "" );
    			break;
    
    		case 0://Soh achou linha
    			xml_g->addItem("result", "OK");
    			xml_g->addItem("digito", oLinhaBase.Registro()->cnrDigitoLinha );
    			xml_g->addItem("idlinhaTelefonica", oLinhaBase.Registro()->cidLinhaTelefonica );
    			xml_g->addItem("idPessoa", "" );
    			xml_g->addItem("idTipoPessoa", "" );
    			break;
    
    		case 1://Achou linha e pessoa
    			xml_g->addItem("result", "OK");
    			xml_g->addItem("digito", oLinhaBase.Registro()->cnrDigitoLinha );
    			xml_g->addItem("idlinhaTelefonica", oLinhaBase.Registro()->cidLinhaTelefonica );
    			xml_g->addItem("idPessoa", oLinhaBase.Registro()->cidPessoa );
    			xml_g->addItem("idTipoPessoa", oLinhaBase.Registro()->cidTipoPessoa );
    			break;
    
    		default:
    			setStatusCode( "12I9999", "PPVALLIN: Erro não esperado" );
    			xml_g->addItem("result", "NOK");
    			break;
    	}//switch( oLinhaBase.ValidaLinhaPrePago(...)
    }

    xml_g->closeTag();//PrePagoRetornoValidaLinha

    ULOG_END("PPVALLIN");
    setStatusCode( "12I0000", "Sucesso" );

}
