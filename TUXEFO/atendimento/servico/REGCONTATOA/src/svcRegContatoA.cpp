/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:57 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include "../../../commons/queryMacro.h"
#include "../../../commons/msgPadrao.h"

#include <string>
using namespace std;

#include "../../REGCONTATO/include/cRegContato.h"
#include "../../REGCONTATOER/include/cWFRegContatoErro.h"

DECLARE_TUXEDO_SERVICE(REGCONTATOA);

void userNumericoSimNao(const char *idUser);

void implREGCONTATOA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implREGCONTATOA::Execute()");

    bool exceptionRaised = false;
    string sCode = "04I0000";
    string mCode = "Atendimento registrado.";
    char *user = getUser();

    try
    {
        cRegContato rc(xml_g);

        // verifica se id do usuário veio somente com números
        if ( memcmp(user,"URA_",4) == 0 || memcmp(user,"ura_",4) == 0 )
        {
            char *userNN = *(user+4)=='0'?user+5:user+4;

            if ( rc.VerificarUserNumericoSimNao(userNN) == true )
            {
                userNumericoSimNao(user);
            }

            char idPessoaUsuarioUra[39];
            if ( rc.ObterIdUserUra(userNN,idPessoaUsuarioUra) == false )
            {
                throw new TuxException("04E0000","ID de usuário URA não encontrado");
            }

            user = idPessoaUsuarioUra;
        }
        else
        {
            userNumericoSimNao(user);
        }

        rc.carregaDados(dnode);

        rc.dados.idUsuarioBKO = user ? atoi( user ) : 0;
        SAFE_STRNCPY(rc.dados.idUsuarioBKOSZ,user);

        rc.EntradaValidacao(rc.obterDadosRC());

        rc.registra();

        xml_g->createTag( "AtendimentoVO" );
            xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem( "idAtendimento" , rc.obterIdAtendimento() );
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

	// M0017 - Se houve algum tratamento de exceção durante o processamento,
	// tenta gravar o ocorrido na tabela de log de erros antes de sair.
	if ( exceptionRaised )
	{
		try
		{
			cWFRegContatoErro oWFRegContatoErro(dnode, user, (char*)mCode.c_str() );
			oWFRegContatoErro.executar();
		}
		catch (TuxBasicOraException *ex)
		{ // Não conseguiu logar o erro! "loga" cod de erro em texto e sai
			ULOGE("Erro Oracle ''%d:%s'' ao tentar persistir falha do REGCONTATO.",ex->eCode,ex->pMsg);
			delete ex;
		}
		catch(...) {ULOGE("erro desconhecido na gravação do log de erros");}
	}

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());

    ULOG_END("implREGCONTATOA::Execute()");
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
