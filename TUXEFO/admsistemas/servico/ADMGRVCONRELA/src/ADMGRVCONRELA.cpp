#define ADMGRVCONRELACPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CTabConsRelac.h"
#include "../../../negocio/admatdCmm/include/CTabConsRelacItr.h"

DECLARE_TUXEDO_SERVICE(ADMGRVCONRELA);

void implADMGRVCONRELA::Execute(DOMNode*dnode,XMLGen*xml_g)
{   
   ULOG_START("implADMGRVCONRELA::Execute()");

    CSafePointer oSafePointer;
    CTabConsRelacItr cTabConsRelacItr;
    CTabEmpresaRelacItr cTabEmpresaRelacItr;

    char *ctpOperacao = oSafePointer.getTag( dnode, "tpOperacao", 0 );
    char *cidUser = getUser();
    char *idPessoaUsuario = oSafePointer.getTag( dnode, "idConsultor", 0 );

    ULOG("ctpOperacao=%s",ctpOperacao);
    ULOG("cidUser=%s",cidUser);

    if( strcmp(ctpOperacao,"gravarFormulario")==0 )
    {
        char *nrDocumento;
        char idFormulario[64];
        char vlPeso[64];
        char *idFuncionalidade = oSafePointer.getTag( dnode, "idFuncionalidade", 0 );
        char *idContato = oSafePointer.getTag( dnode, "idContato", 0 );
        char inAtivo[3];
        char InTipoAssociacao[64];
        bool frmMestre = false;
        CTabEmpresaRelac cTabEmpresaRelac;
        
        ULOG( "Vai gravar formulario..." );
        
        DOMNode *subnode;
        int stItem,item=0;

        while ( subnode=walkDOM(dnode,"IDValorClienteVO",item++) )
        {
            nrDocumento = oSafePointer.getTag(subnode, "nrDocumentoFmt", 0 );
            strcpy( idFormulario, oSafePointer.getTag(subnode, "id", 0 ) );
            ULOG( "nrDocumento  [%s]",nrDocumento);
            ULOG( "idFormulario [%s]",idFormulario);
            
            if ( strlen(nrDocumento) == 0 )
            {
                if ( frmMestre == false )
                {
                    strcpy( inAtivo,"1" );
                    frmMestre = true;
                }
                else
                {
                    strcpy( inAtivo,"0" );
                }
            }
            else
            {
                strcpy(inAtivo,"0");
            }
            
            strcpy( vlPeso,oSafePointer.getTag(subnode, "valor", 0 ) );
            ULOG( "vlPeso [%s]",vlPeso );
            if ( idFormulario[0] == 0x0 )
            {
               strcpy( idFormulario,"-11" );
               strcpy( vlPeso,"-11" );
            }
			
            strcpy( InTipoAssociacao,oSafePointer.getTag(subnode, "intipoassociacao", 0 ) );
			ULOG( "Valor obtido de intipoassociacao --------------->>>>> [%s]",InTipoAssociacao );
            ULOG( "intipoassociacao [%s]",InTipoAssociacao );
            if ( InTipoAssociacao[0] == 0x0 )
            {
               strcpy( InTipoAssociacao," " );
            }

            if ( idFormulario && *idFormulario && vlPeso && *vlPeso && *InTipoAssociacao && InTipoAssociacao )
            {
                ULOG( "Inserindo forumalrio pra gravar...");
                cTabEmpresaRelacItr.Add( nrDocumento,idFormulario,vlPeso,inAtivo, InTipoAssociacao );
            }
        }

        if ( cTabEmpresaRelacItr.Quantidade() > 0 )
        {

            cTabEmpresaRelac.GravarAssociacaoEmpresaFrm( xml_g,
                                                         cidUser,
                                                         idContato,
                                                         idFuncionalidade,
                                                         cTabEmpresaRelacItr );

            setStatusCode("14I0000","Sucesso na gravação.");
        }
        else
        {
            setStatusCode("00W0003","Nenhum documento enviado para associação.");
        }
    }
    else if( strcmp(ctpOperacao,"gravar")==0 )
    {
        if ( idPessoaUsuario )
        {
            char *nrDocumento;
            char *idTpRelacionamento;
            CTabConsRelac cTabConsRelac;
            DOMNode *subnode;
            int stItem,item=0;

            while ( subnode=walkDOM(dnode,"IDValorRelacionamentoVO",item++) )
            {
                nrDocumento = oSafePointer.getTag(subnode, "id", 0 );
                idTpRelacionamento = oSafePointer.getTag(subnode, "idRelacionamento", 0 );
                if ( nrDocumento && *nrDocumento && idTpRelacionamento && *idTpRelacionamento )
                {
                    cTabConsRelacItr.Add(nrDocumento,idTpRelacionamento);
                }
            }

            if ( cTabConsRelacItr.Quantidade() > 0 )
            {
                cTabConsRelac.GravarAssociacaoDeClientesConsultor( xml_g,cidUser,idPessoaUsuario,cTabConsRelacItr );

                setStatusCode("14I0000","Sucesso na gravação.");
            }
            else
            {
                setStatusCode("00W0003","Nenhum documento enviado para associação.");
            }
        }
        else
        {
            setStatusCode( "00W0001","nrDocumento não informado.");
        }
    }
    else if ( strcmp(ctpOperacao,"excluir")==0 )
    {
        if ( idPessoaUsuario )
        {
            char *nrDocumento;
            char *idTpRelacionamento;
            CTabConsRelac cTabConsRelac;
            DOMNode *subnode;
            int stItem,item=0;

            while ( subnode=walkDOM(dnode,"IDValorRelacionamentoVO",item++) )
            {
                nrDocumento = oSafePointer.getTag(subnode, "id", 0 );
                idTpRelacionamento = oSafePointer.getTag(subnode, "idRelacionamento", 0 );
                if ( nrDocumento && *nrDocumento && idTpRelacionamento && *idTpRelacionamento )
                {
                    cTabConsRelacItr.Add(nrDocumento,idTpRelacionamento);
                }
            }

            if ( cTabConsRelacItr.Quantidade() > 0 )
            {
                cTabConsRelac.RemoverAssociacaoDeConsultor( cidUser,idPessoaUsuario,cTabConsRelacItr );

                setStatusCode("14I0000","Sucesso na exclusão.");
            }
            else
            {
                setStatusCode("00W0003","Não foram encontrados documentos associados ao consultor selecionado.");
            }
        }
        else
        {
            setStatusCode( "00W0001","Consultor não informado.");
        }
    }
    else if ( strcmp(ctpOperacao,"alterar")==0 )
    {
        if ( idPessoaUsuario )
        {
            char *nrDocumento;
            char *idTpRelacionamento;
            CTabConsRelac cTabConsRelac;
            DOMNode *subnode;
            int stItem,item=0;

            while ( subnode=walkDOM(dnode,"IDValorRelacionamentoVO",item++) )
            {
                nrDocumento = oSafePointer.getTag(subnode, "id", 0 );
                idTpRelacionamento = oSafePointer.getTag(subnode, "idRelacionamento", 0 );
                if ( nrDocumento && *nrDocumento && idTpRelacionamento && *idTpRelacionamento )
                {
                    cTabConsRelacItr.Add(nrDocumento,idTpRelacionamento);
                }
            }

            if ( cTabConsRelacItr.Quantidade() > 0 )
            {
                cTabConsRelac.AlterarAssociacaoDeConsultor( xml_g,cidUser,idPessoaUsuario,cTabConsRelacItr );

                setStatusCode("14I0000","Sucesso na alteração.");
            }
            else
            {
                setStatusCode("00W0003","Não foram encontrados documentos associados ao consultor selecionado.");
            }
        }
        else
        {
            setStatusCode( "00W0001","Consultor não informado.");
        }
    }
    else
    {
        setStatusCode("00W0004", "Faltou informar tag de operação.");
    }
    
    ULOG_END("implADMGRVCONRELA::Execute()");
}
