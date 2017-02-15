
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

extern int ProcCadastro( char * idFila );


DECLARE_TUXEDO_SERVICE( PPPROCCAD );

void implPPPROCCAD::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START( "PPPROCCAD" );

    char      szIdFila[41];
    XMLGen                      *pclXmlGen;

    try
    {

        szIdFila[0] = 0x0;
        
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
        
        szIdFila[0] = 0x0;
        GETTREE( szIdFila, dnode, "idFila", 0, NOBRIGATORIO, "");

        if ( szIdFila[0] != 0x0 )
        {
            ProcCadastro( szIdFila );
        }
        
        ULOG_END("PPPROCCAD");
        setStatusCode(SUCESSO_EXECUCAO, "Success Execution");
        return;

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
            case ERRO_LEGADO_SET_INTERCEPTACAO:
                setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
                break;
            default:
                setStatusCode(E_APLICACAO_PREPAGO, sE.getMsg());
                break;
        }

        ULOGE( "ERRO:Final do processamento de PPPROCCAD.." );
        ULOG_END( ">>> PPPROCCAD" );
        return;
    }
    catch(...)
    {
        ULOGE( ">>>Final do processamento de PPPROCCAD..." );
        ULOG_END( "PPPROCCAD" );
        setStatusCode( E_APLICACAO_PREPAGO, "Procedimento encerrado com ERRO." );
        throw;
    }
}
