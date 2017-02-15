
#include "tuxfw.h"
#include "Global.h"
#include "Tools.h"
#include "PrePagoException.h"

extern int SincronizaPP( char * idCliente, char * idLinhaTelefonica );
extern int SincronizaPPPorLinha( char *idCliente, char * nrLinha);

DECLARE_TUXEDO_SERVICE( SINCVIVONETPP );

void implSINCVIVONETPP::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START( "SINCVIVONETPP" );

	char idCliente[50];
    char idLinhaTelefonica[50];
	char nrLinha[20+1];

    try
    {
		memset(idCliente,0,sizeof(idCliente));
        memset(idLinhaTelefonica,0,sizeof(idLinhaTelefonica));
		memset(nrLinha,0,sizeof(nrLinha));
        GETTREE( idLinhaTelefonica, dnode, "IDLINHATELEFONICA", 0, NOBRIGATORIO, "");
		GETTREE( idCliente, dnode, "IDCLIENTE", 0, NOBRIGATORIO, "");
		GETTREE( nrLinha, dnode, "NRLINHA", 0, NOBRIGATORIO, "");

		int iErro = 0;
        if ( idLinhaTelefonica[0] != 0x0 )
        {
            iErro = SincronizaPP(idCliente, idLinhaTelefonica);
			ULOG( "SincronizaPP() retornou iErro[%d]." ,iErro);
        } else {
			iErro = SincronizaPPPorLinha(idCliente, nrLinha);
			ULOG( "SincronizaPPPorLinha() retornou iErro[%d]." ,iErro);
        }

		if (iErro < 0)
		{
			ULOG( "Erro no retorno da chamda SincronizaPP() iErro[%d]." ,iErro);
			setStatusCode("10E0001", "Erro na execução");			
		}
		else
		{
			ULOG( "Sucesso no retorno da chamda SincronizaPP() iErro[%d]." ,iErro);	
			setStatusCode(SUCESSO_EXECUCAO, "Success Execution");
		}
		
		
        
        ULOG_END("SINCVIVONETPP");
        return;

    }
    catch(PrePagoException sE)
    {

        switch(sE.getTipo())
        {
            case ERRO_EXECUCAO:
                setStatusCode("10E1000", sE.getMsg());
                break;
            case ERRO_PARAMETRO_NULL:
                setStatusCode("10E1000", sE.getMsg());
                break;
            case ERRO_SEQUENCE:
                setStatusCode("10E1000", sE.getMsg());
                break;
            case ERRO_REGISTRO_NAO_ENCONTRADO:
                setStatusCode("10E1000", sE.getMsg());
                break;
            case ERRO_LEGADO_SET_INTERCEPTACAO:
                setStatusCode("10E1000", sE.getMsg());
                break;
            default:
                setStatusCode("10E1000", sE.getMsg());
                break;
        }

        ULOGE( "ERRO:Final do processamento de SINCVIVONETPP.." );
        ULOG_END( ">>> SINCVIVONETPP" );
        return;
    }
    catch(...)
    {
        ULOGE( ">>>Final do processamento de SINCVIVONETPP..." );
        ULOG_END( "SINCVIVONETPP" );
        setStatusCode( E_APLICACAO_PREPAGO, "Procedimento encerrado com ERRO." );
        throw;
    }
}
