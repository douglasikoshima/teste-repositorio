/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:31 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include "../include/cRegContatoFO.h"
#include "../include/cRCFOException.h"
#include "../../REGCONTATO/include/stRegContato.h"
#include "../../REGCONTATOER/include/cWFRegContatoErro.h"

DECLARE_TUXEDO_SERVICE(REGCONTATOFO);

void implREGCONTATOFO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implREGCONTATOFO::Execute()");

    string codErr = "04I0000";
    string msgErr = "Palitagem Registrada";
    bool exceptionRaised = false;
    char *user = getUser();

    try
    {
        cRegContatoFO cregcontatofo(dnode,xml_g,user);

        cregcontatofo.Executar();
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        codErr = codErro;
        msgErr = ex->pMsg;
        exceptionRaised = true;

        ULOGE("ErroOracle: %d:%s",ex->eCode,ex->pMsg);

        delete ex;
    }
    catch (TuxException *ex)
    {
        codErr = ex->getCode();
        msgErr = ex->getMessage();
        exceptionRaised = true;

        ULOGE("Erro Tuxedo: %s:%s",codErr.c_str(),msgErr.c_str());

        delete ex;
    }
    catch(RCFOException *rcfoex)
    {
        codErr = rcfoex->getCodErr();
        msgErr = rcfoex->getMsgErr();
        exceptionRaised = true;

        delete rcfoex;
    }
    catch ( st_RegContatoErro *erro )
    {
        codErr = erro->sCode;
        msgErr = erro->mCode;
        exceptionRaised = true;

        ULOGE("Erro de validação: %s:%s",codErr.c_str(),msgErr.c_str());
    }
    catch(...)
    {
        codErr = "04E9999";
        msgErr = "Erro desconhecido";
        exceptionRaised = true;

        ULOGE("Erro : %s:%s",codErr.c_str(),msgErr.c_str());
    }

    // M0017 - Se houve algum tratamento de exceção durante o processamento,
    // tenta gravar o ocorrido na tabela de log de erros antes de sair.
    if ( exceptionRaised )
    {
        try
        {
			cWFRegContatoErro oWFRegContatoErro(dnode, user, (char*)msgErr.c_str() );
			oWFRegContatoErro.executar();
        }
        catch (TuxBasicOraException *ex)
        { // Não conseguiu logar o erro! "loga" cod de erro em texto e sai
            ULOGE("Erro Oracle ''%d:%s'' ao tentar persistir falha do REGCONTATO."
                 ,ex->eCode,ex->pMsg);
            delete ex;
        }
        catch(...) {ULOGE("erro desconhecido na gravação do log de erros");}
    }

    setStatusCode((char*)codErr.c_str(),(char*)msgErr.c_str());

    ULOG_END("implREGCONTATOFO::Execute()");
}
