#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Pessoa/Pessoa.hpp>
#include <Email/Email.hpp>

static char *STR_CONSULTAREMAILS		 = "consultarEmails";
static char *STR_CONSULTARTIPOSEMAIL	 = "consultarTiposEmail";

static char *STR_11IOK                   = "11I0000";
static char *STR_11IOKMSG                = "Consulta Efetuada com Sucesso.";

static char *STR_11W0099                 = "11W0099";
static char *STR_11W0099MSG				 = "Consulta não efetuada.";

static char *STR_11W0101                 = "11W0101";
static char *STR_11W0101MSG				 = "Parâmetro idPessoa Inválido.";

static char *STR_11W9999                 = "11W9999";
static char *STR_11WEXCDESC              = "ERRO DESCONHECIDO";

DECLARE_TUXEDO_SERVICE( LSTEMAIL );

static void consultarEmails( DOMNode * dnode, XMLGen * xml_g, CStatusCode & statusCode )
{

	CTuxHelperClever helper;


	CPessoa oPessoa;

	CEmail oEmail;

    char *   pcTagXmlIn = NULL; 		
    long     iIdPessoa = 0;

    tuxfw_getlogger()->debug( ">>> consultarEmails()" );
    
    // Navega o XML e recupera as informacoes obrigatorias
    pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
    //ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");
    iIdPessoa = atol(pcTagXmlIn);
    
    tuxfw_getlogger()->debug( "iIdPessoa [%ld]", iIdPessoa );

    if ( iIdPessoa <= 0 )
    {
        statusCode.setCod( STR_11W0101 );
        statusCode.setMsg( STR_11W0101MSG );
    }
    else
    {
        oPessoa.setIdPessoa(iIdPessoa);
        try
        {
            //Consulta os e-mails associados a Pessoa
            oPessoa.consultarEmails();

            // seta mensagem de retorno - header	
            statusCode.setCod(STR_11IOK);
            statusCode.setMsg(STR_11IOKMSG);
        }

        catch(TuxBasicSvcException)
        {
            // seta mensagem de retorno - header
            statusCode.setCod(STR_11W0099);
            statusCode.setMsg(STR_11W0099MSG);
        }
        catch(TuxBasicOraException &e)
        {
            // removendo warning
            e.eCode = 0;

            statusCode.setCod(STR_11W0099);
            statusCode.setMsg(STR_11W0099MSG);
        }
        catch(...)
        {
            statusCode.setCod(STR_11W9999);
            statusCode.setMsg(STR_11WEXCDESC);
        }
    }

    // Monta o XML de saída
    xml_g->createTag( "consultarEmailsVO" );
    xml_g->addProp( "xmlns", "dados.vol.vivo.com.br/vo" );

    char buffer[256];
    memset( buffer, 0x0, sizeof(buffer) );
    while( 0 < oPessoa.getListaEmails().size() ) 
    {
        oEmail = oPessoa.getListaEmails().front();

        sprintf( buffer, "%d", oEmail.getIdEmail() );

        xml_g->createTag( "emails" );
            sprintf( buffer, "%d", oEmail.getIdEmail() );
            xml_g->addItem( "idEmail", buffer );
            sprintf( buffer, "%d", oEmail.getIdTipoEmail() );
            xml_g->addItem( "idTipoEmail", buffer );
            sprintf( buffer, "%s", oEmail.getDsTipoEmail() );
            xml_g->addItem( "dsTipoEmail", buffer );
            sprintf( buffer, "%s", oEmail.getDsEmail() );
            xml_g->addItem( "dsEmail", buffer );
        xml_g->closeTag();		

        oPessoa.getListaEmails().pop_front();
    }
    xml_g->closeTag();
    
    tuxfw_getlogger()->debug( "<<< consultarEmails()" );


}

static void consultarTiposEmail(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CEmail oEmail;
	list<CEmail> lstEmail;

	try	
	{
		//Lista os tipos de e-mail
		oEmail.consultarTiposEmail(lstEmail);

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}

	catch(TuxBasicSvcException)
	{
				
		// seta mensagem de retorno - header
		statusCode.setCod(STR_11W0099);
		statusCode.setMsg(STR_11W0099MSG);
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0099);
		statusCode.setMsg(STR_11W0099MSG);
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}

	// Monta o XML de saída
	xml_g->createTag( "consultarTiposEmailVO" );
	xml_g->addProp( "xmlns", "dados.vol.vivo.com.br/vo" );

    char buffer[256];
    memset( buffer, 0x0, sizeof(buffer) );
	while( 0 < lstEmail.size() ) {
		oEmail = lstEmail.front();

		xml_g->createTag( "tiposEmail" );
            sprintf( buffer, "%d", oEmail.getIdTipoEmail() );
			xml_g->addItem( "idTipoEmail", buffer );
            sprintf( buffer, "%s", oEmail.getDsTipoEmail() );
			xml_g->addItem( "dsTipoEmail", buffer );
		xml_g->closeTag();		

		lstEmail.pop_front();
	}

	xml_g->closeTag();
}


void implLSTEMAIL::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");

	// descobrindo qual operação executar
	if (!strcmp(pcTagXmlIn, STR_CONSULTAREMAILS))
		consultarEmails(dnode, xml_g, statusCode);	
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARTIPOSEMAIL))
		consultarTiposEmail(dnode, xml_g, statusCode);	

	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());

}
