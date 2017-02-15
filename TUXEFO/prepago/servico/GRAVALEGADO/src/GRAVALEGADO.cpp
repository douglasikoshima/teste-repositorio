///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase GRAVALEGADO
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:55 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include <classGravaLegado.h>
#include <PrePagoException.h>
#include <Tools.h>
/*
 * Retirado por conflito com o header Global.h */
/* #include <CSafePointer.h> */
#include <Global.h>

DECLARE_TUXEDO_SERVICE(GRAVALEGADO);

void implGRAVALEGADO::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("GRAVALEGADO");

    //Classe que recupera os dados para enviar ao legado
    CGravaLegado clGravaLegado;
	//Classe que recupera ponteiros seguros, que nao precisam de free
	//  Retirado por conflito com o header Global.h
	/* CSafePointer clSafePointer; */

	//Variaveis de uso geral
	char* pszIdPessoa;
	/* char* pszServico = clSafePointer.getTag( dnode, "servico" ); */
	char* pszServico = walkTree( dnode, "servico", 0 );

	/* char* pszDataIni = clSafePointer.getTag( dnode, "dataInicial" ); */
	char* pszDataIni = walkTree( dnode, "dataInicial", 0 );

	/* char* pszDataFim = clSafePointer.getTag( dnode, "dataFinal" ); */
	char* pszDataFim = walkTree( dnode, "dataFinal", 0 );

    char* pszUser    = getUser();
	char* pszPonteiroVazio = "";
	if( STRLENNULL( pszServico ) <= 0 )
	{
		pszServico = pszPonteiroVazio;
		if( STRLENNULL( pszDataIni ) <= 0 )
		{
    		setStatusCode(E_APLICACAO_PREPAGO, "Data inicial não pode ser vazia");
			XMLString::release( &pszDataIni );
			XMLString::release( &pszDataFim );
    		return;
		}
		if( STRLENNULL( pszDataFim ) <= 0 )
		{
    		setStatusCode(E_APLICACAO_PREPAGO, "Data final não pode ser vazia");
			XMLString::release( &pszDataIni );
			XMLString::release( &pszDataFim );
    		return;
		}
	}
	else
	{
		if( !( strcmp( pszServico, "PESSOA" ) == 0 || strcmp( pszServico, "PESSOANOK" ) == 0 ) )
		{
    		setStatusCode(E_APLICACAO_PREPAGO, "TAG <servico> com valor inválido");
			XMLString::release( &pszServico );
			XMLString::release( &pszDataIni );
			XMLString::release( &pszDataFim );
    		return;
		}
	}
	if( STRLENNULL( pszUser ) <= 0 )
	{
    	setStatusCode(E_APLICACAO_PREPAGO, "Usuário no HEADER não pode ser nulo (getUser())");
		XMLString::release( &pszServico );
		XMLString::release( &pszDataIni );
		XMLString::release( &pszDataFim );
    	return;
	}

    try
    {
		if( strcmp( pszServico, "PESSOA" ) == 0 )
		{
			int iCont;
			ULOG("Antes de clGravaLegado.PesquisaPorData(%s)", pszUser);
			for(iCont=0;;iCont++)
			{
				/* pszIdPessoa = clSafePointer.getTag( dnode, "idPessoa", iCont ); */
				pszIdPessoa = walkTree( dnode, "idPessoa", iCont );
				if( STRLENNULL( pszIdPessoa ) > 0 )
				{
					xml_g->createTag( "pessoa" );
					xml_g->addItem( "idPessoa", pszIdPessoa );
					if( clGravaLegado.PesquisaPorIdPessoa( pszIdPessoa
													      ,pszUser ) )
					{
						xml_g->addItem( "status", "OK" );
					}
					else
					{
						xml_g->addItem( "status", "NOK" );
					}
					xml_g->closeTag();//pessoa;
				}
				else
					break;
			}//for(;;)
			ULOG("Depois de clGravaLegado.PesquisaPorData(%s)", pszUser);
		}
		else
		{
			if( strcmp( pszServico, "PESSOANOK" ) == 0 )
			{
				ULOG("Antes de clGravaLegado.PesquisaPorData(%s,%s,%s)", pszDataIni, pszDataFim, pszUser);
				clGravaLegado.PesquisaPorDataNOK( pszDataIni
											     ,pszDataFim
											     ,pszUser );
				ULOG("Depois de clGravaLegado.PesquisaPorData(%s,%s,%s)", pszDataIni, pszDataFim, pszUser);
			}
			else
			{
				ULOG("Antes de clGravaLegado.PesquisaPorData(%s,%s,%s)", pszDataIni, pszDataFim, pszUser);
				clGravaLegado.PesquisaPorData( pszDataIni
											  ,pszDataFim
											  ,pszUser );
				ULOG("Depois de clGravaLegado.PesquisaPorData(%s,%s,%s)", pszDataIni, pszDataFim, pszUser);
			}
		}
	}
	catch(PrePagoException sE)
	{
	    switch(sE.getTipo())
	    {
	        case ERRO_EXECUCAO:
	            setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
	            break;
	        case ERRO_PARAMETRO_NULL:
	            setStatusCode(E_PARAMETRO_PREPAGO, sE.getMsg());
	            break;
	        case ERRO_SEQUENCE:
	            setStatusCode(W_SEQUENCE_PREPAGO, sE.getMsg());
	            break;
	        case ERRO_REGISTRO_NAO_ENCONTRADO:
	            setStatusCode(W_DELETE_PREPAGO, sE.getMsg());
	            break;
	        case ERRO_LEGADO_SET_CLIENTE:
	            setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
	            break;
	        default:
	            setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
	            break;
	    }
	}
    catch(...)
    {
		XMLString::release( &pszServico );
		XMLString::release( &pszDataIni );
		XMLString::release( &pszDataFim );
        ULOGE("Procedimento encerrado com ERRO default");
        ULOG_END("GRAVALEGADO");
        setStatusCode(E_APLICACAO_PREPAGO, "Procedimento encerrado com ERRO.");
        throw;
    }
	XMLString::release( &pszServico );
	XMLString::release( &pszDataIni );
	XMLString::release( &pszDataFim );

    ULOGI("Procedimento encerrado com sucesso");
    ULOG_END("GRAVALEGADO");

    setStatusCode(SUCESSO_EXECUCAO, "Success Execution");
    return;
}
