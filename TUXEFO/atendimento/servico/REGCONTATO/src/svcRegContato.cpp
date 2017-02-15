/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.126.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/12/05 19:56:47 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include <string>
using namespace std;

#include "../include/cRegContato.h"
#include "../../REGCONTATOER/include/cWFRegContatoErro.h"
#include "../../../commons/SmallString.h"

DECLARE_TUXEDO_SERVICE(REGCONTATO);

void userNumericoSimNao(const char *idUser);

void implREGCONTATO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implREGCONTATO::Execute()");

    char idAtendimento[256];
	bool exceptionRaised = false;
    string sCode = "04I0000";
    string mCode = "Atendimento registrado.";
    char *user = getUser();

    cRegContato rc(xml_g);

    try
    {
        // Verifica se id do usuário veio somente com números
        userNumericoSimNao(user);

        rc.carregaDados(dnode);

        rc.dados.idUsuarioBKO = user ? atoi( user ) : 0;
        SAFE_STRNCPY(rc.dados.idUsuarioBKOSZ,user);

        rc.EntradaValidacao(rc.obterDadosRC());

        rc.registra();

        xml_g->createTag( "AtendimentoVO" );
            xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
			sprintf( idAtendimento, "%ld", rc.obterIdAtendimento() );
            xml_g->addItem( "idAtendimento" , idAtendimento );  // Maldito Xerces se perde na sobrecarga, tem que ser explicito usando tipos primitivos
            xml_g->addItem( "nrProtocolo" , rc.obterNrProtocolo() );
        xml_g->closeTag();
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;
        exceptionRaised = true;

        delete ex;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        sCode = ex->getCode();
        mCode = ex->getMessage();
        exceptionRaised = true;

        delete ex;
    }
    catch (char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0097";
        mCode = errMsg;
        exceptionRaised = true;
    }
    catch (const char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0098";
        mCode = errMsg;
        exceptionRaised = true;
    }
    catch ( st_RegContatoErro *erro )
    {
        sCode = erro->sCode;
        mCode = erro->mCode;
        exceptionRaised = true;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        sCode = "04E9999";
        mCode = "erro desconhecido";
        exceptionRaised = true;
    }

     //M0017 - Se houve algum tratamento de exceção durante o processamento,
     //tenta gravar o ocorrido na tabela de log de erros antes de sair.
     if ( exceptionRaised )
     {
        try
        {
            cWFRegContatoErro oWFRegContatoErro(dnode, user, (char*)mCode.c_str() );
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

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());

    ULOG_END("implREGCONTATO::Execute()");
}

void userNumericoSimNao(const char *idUser)
{
    char *p = (char*)idUser;

    while ( p && *p )
    {
        if ( *p < '0' || *p > '9' ) 
        {
            char erro[256];

            sprintf(erro,"Usuario (%s) nao completamente numerico",idUser);

            throw new TuxException("04E0000",erro);
        }

        p++;
    }
}
