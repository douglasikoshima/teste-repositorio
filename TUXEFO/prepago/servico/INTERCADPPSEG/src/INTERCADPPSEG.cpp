
#include "tuxfw.h"
#include "Global.h"
#include "Tools.h"
#include "PrePagoException.h"

extern int ProcCadastroPPSeg( char * sPROCEDURE, char * idPessoaDeParaPrm );

DECLARE_TUXEDO_SERVICE( INTERCADPPSEG );

void implINTERCADPPSEG::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START( "INTERCADPPSEG" );

    char      szPROCEDURE[1024];
    char   idPessoaDePara[41];

    try
    {
        memset( szPROCEDURE, 0x0, sizeof(szPROCEDURE) );
        memset( idPessoaDePara, 0x0, sizeof(idPessoaDePara) );

        GETTREE( szPROCEDURE   , dnode, "PROCEDURE", 0, NOBRIGATORIO, "");
        GETTREE( idPessoaDePara, dnode, "idPessoaDePara", 0, NOBRIGATORIO, "");

		int iErro = 0;
        if ( szPROCEDURE[0] != 0x0 )
        {
            iErro = ProcCadastroPPSeg( szPROCEDURE, idPessoaDePara );
			ULOG( "ProcCadastroPPSeg() retornou iErro[%d]." ,iErro);
        }

		if (iErro < 0)
		{
			setStatusCode("10E0001", "Success Execution");			
		}
		else
		{
			setStatusCode(SUCESSO_EXECUCAO, "Success Execution");
		}
		
        ULOG_END("INTERCADPPSEG");
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

        ULOG_END( ">>> INTERCADPPSEG" );
        return;
    }
    catch(...)
    {
        ULOG_END( "INTERCADPPSEG" );
        setStatusCode( E_APLICACAO_PREPAGO, "Procedimento encerrado com ERRO." );
        throw;
    }
}
