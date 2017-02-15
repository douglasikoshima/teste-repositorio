/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * remarks  Deep Purple (song)
 *          When the deep purple falls over sleepy garden walls
 *          And the stars begin to twinkle in the sky
 *          In the mist of a memory you wander back to me
 *          Breathing my name with a sigh... 
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:36 $
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

#include "../include/cRegPalitagem.h"
#include "../../../commons/SmallString.h"

DECLARE_TUXEDO_SERVICE(REGPALITAGEM);

void implREGPALITAGEM::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    string sCode = "04I0000";
    string mCode = "Atendimento registrado.";
    char *user = getUser();

    cRegPalitagem rc(xml_g);

    try
    {
        rc.carregaDados(dnode);

        rc.dados.idUsuarioBKO = user ? atoi(user) : 0;
        SAFE_STRNCPY(rc.dados.idUsuarioBKOSZ,user);

        rc.registra();

        xml_g->createTag( "AtendimentoVO" );
            xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            xml_g->addItem("idAtendimento", rc.obterIdAtendimento());
            xml_g->addItem("nrProtocolo", rc.obterNrProtocolo());
        xml_g->closeTag();
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
}
