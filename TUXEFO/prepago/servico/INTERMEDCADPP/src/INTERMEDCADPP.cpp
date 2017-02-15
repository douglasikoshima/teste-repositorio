
#include "tuxfw.h"
#include "Global.h"
#include "Tools.h"
#include "PrePagoException.h"

/*
#include "Documento.h"
#include "Pessoa.h"
#include "PessoaComunicacao.h"
#include "PessoaDocumento.h"
#include "PessoaEndereco.h"
#include "PessoaFisica.h"
#include "PessoaJuridica.h"
#include "PessoaLinha.h"
#include "PessoaConta.h"
#include "PessoaLinhaPre.h"
#include "ContaEndereco.h"
#include "LinhaConta.h"
#include "SistemaOrigem.h"
#include "PessoaValorPossivel.h"
#include "PessoaSegmentacaoHistorico.h"
#include "PessoaSegmentacao.h"
#include "TipoEnderecoCobranca.h"
#include "TipoRelacionamento.h"
#include "PessoaLinhaHistorico.h"
#include "TipoConta.h"
#include "TipoDocumento.h"
#include "FilaSetClientInfo.h"
#include "Conta.h"
#include "UF.h"
#include "Parametro.h"
#include "RegistraContato.h"
*/

//extern int ProcCadastroPP( char * idFila );
extern int ProcCadastroPP( char * szIDOPERADORA, char * szNRLINHA, char * szNRDOCUMENTO, char * szNMPESSOA, char * szSGPLANO, char * szPROCEDURE, char * szIDLINHATELEFONICA, char * szINQTDTENTATIVA );

DECLARE_TUXEDO_SERVICE( INTERMEDCADPP );

void implINTERMEDCADPP::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START( "INTERMEDCADPP" );

	char      szIDLINHATELEFONICA[8192];
    char      szIDOPERADORA[8192];
    char      szNRLINHA[8192];
    char      szNRDOCUMENTO[8192];
    char      szNMPESSOA[8192];
	char      szSGPLANO[8192];
    char      szPROCEDURE[8192];
	char      szINQTDTENTATIVA[8192];
    //XMLGen    *pclXmlGen;

    try
    {

        szPROCEDURE[0] = 0x0;
        
        // Obtem informacoes do header XML
        /*
        pUser = getUser();
        if(pUser != NULL)
        {
            if(strlen(pUser) > 0)
                strcpy(szUser, pUser);
            else
                throw PrePagoException(ERRO_EXECUCAO, "Tag user vazia");
        }
        else
            throw PrePagoException(ERRO_EXECUCAO, "Tag user inexistente");

        ULOG("szUser[%s]", szUser);
        */
        

        //GET_HEADER(szSubSystem, getSubSystem(), NOBRIGATORIO); ULOG("szSubSystem[%s]", szSubSystem);
        

        szIDLINHATELEFONICA[0] = 0x0;
        GETTREE( szIDLINHATELEFONICA, dnode, "IDLINHATELEFONICA", 0, NOBRIGATORIO, "");


        szIDOPERADORA[0] = 0x0;
        GETTREE( szIDOPERADORA, dnode, "IDOPERADORA", 0, NOBRIGATORIO, "");
        szNRLINHA[0] = 0x0;
        GETTREE( szNRLINHA, dnode, "NRLINHA", 0, NOBRIGATORIO, "");
        szNRDOCUMENTO[0] = 0x0;
        GETTREE( szNRDOCUMENTO, dnode, "NRDOCUMENTO", 0, NOBRIGATORIO, "");
        szNMPESSOA[0] = 0x0;
        GETTREE( szNMPESSOA, dnode, "NMPESSOA", 0, NOBRIGATORIO, "");
        szSGPLANO[0] = 0x0;
        GETTREE( szSGPLANO, dnode, "SGPLANO", 0, NOBRIGATORIO, "");
        szPROCEDURE[0] = 0x0;
        GETTREE( szPROCEDURE, dnode, "PROCEDURE", 0, NOBRIGATORIO, "");
		
        szINQTDTENTATIVA[0] = 0x0;
        GETTREE( szINQTDTENTATIVA, dnode, "INQTDTENTATIVA", 0, NOBRIGATORIO, "");
		

		int iErro = 0;
        if ( szPROCEDURE[0] != 0x0 )
        {
            iErro = ProcCadastroPP( szIDOPERADORA, szNRLINHA, szNRDOCUMENTO, szNMPESSOA, szSGPLANO, szPROCEDURE, szIDLINHATELEFONICA, szINQTDTENTATIVA );
			ULOG( "ProcCadastroPP() retornou iErro[%d]." ,iErro);
        }

		if (iErro < 0)
		{
			ULOG( "Erro no retorno da chamda ProcCadastroPP() iErro[%d]." ,iErro);
			setStatusCode("10E0001", "Success Execution");			
		}
		else
		{
			ULOG( "Sucesso no retorno da chamda ProcCadastroPP() iErro[%d]." ,iErro);	
			setStatusCode(SUCESSO_EXECUCAO, "Success Execution");
		}
		
		
        
        ULOG_END("INTERMEDCADPP");
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

        ULOGE( "ERRO:Final do processamento de INTERMEDCADPP.." );
        ULOG_END( ">>> INTERMEDCADPP" );
        return;
    }
    catch(...)
    {
        ULOGE( ">>>Final do processamento de INTERMEDCADPP..." );
        ULOG_END( "INTERMEDCADPP" );
        setStatusCode( E_APLICACAO_PREPAGO, "Procedimento encerrado com ERRO." );
        throw;
    }
}
