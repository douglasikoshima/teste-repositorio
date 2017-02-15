/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.4.4 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/02/11 18:00:51 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../include/cWF_ATDCANOVACAOPC.h"

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(ATDCANOVACAO);

void implATDCANOVACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implATDCANOVACAO::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de ATDCANOVACAO Concluida";

    cWF_ATDCANOVACAO rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implATDCANOVACAO::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_ATDCANOVACAO::cWF_ATDCANOVACAO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Informar ao Cliente sobre o Cancelamento de Ordem de Venda
//------------------------------------------------------------------------------

void cWF_ATDCANOVACAO::Executar() 
{
    cWF_Acao::Executar();

    if ( existeAtendimentoFechamento() )
    {
        ULOG("o processo %s já havia sido fechado",idAtendimento.c_str());

        SetMessage( "Fechamento já havia sido concluido para este processo.", "S" ); 

        xml_g->closeTag();

        return;
    }

    Encerrar();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"ATDCANOVACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Cancelamento da Ordem de Venda Concluído", "S" ); 

    xml_g->closeTag();
}
