/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:05 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../include/cWF_POACDEVFILAPC.h"
using namespace std;

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POACDEVFILA);

void implPOACDEVFILA::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOACDEVFILA::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POACDEVFILA Concluida";

    cWF_POACDEVFILA rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;

        delete ex;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        sCode = ex->getCode();
        mCode = ex->getMessage();

        delete ex;
    }
    catch (char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0097";
        mCode = errMsg;
    }
    catch (const char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0098";
        mCode = errMsg;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        sCode = "04E9999";
        mCode = "erro desconhecido";
    }

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());
    
    ULOG_END("implPOACDEVFILA::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POACDEVFILA::cWF_POACDEVFILA(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Devolver processo de portout para a fila
//------------------------------------------------------------------------------
void cWF_POACDEVFILA::Executar() 
{
    cWF_Acao::Executar();

    getAtendimento();

    // Aplica a mesma regra de DEVOLVER PARA A FILA a todos os outros 
    // atendimentos que estejam associados a este
    if( devolverFilaAtendimentosAgrupados() )
    {
        // processo não irá ser exibido na fila de portout por um período parametrizado
        // atualizarDataDelayFila();

        // Registro a ação no historico de portabilidade
        gravarPessoaPortabilidadeHist("POACDEVFILA");

        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(User.ToInt(),idContato,"POACDEVFILA");
        // <== SM324--DPR--DEZ/2006--Cassio

        SetMessage("Processo de portout devolvido para a fila","S");
    }

    xml_g->closeTag();
}
