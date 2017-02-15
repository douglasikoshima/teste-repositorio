/**
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Marcelo Nunes
 * @version $Revision: 1.1.2.3.112.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/09/09 19:42:31 $
 **/

#include "../include/cRegPalitageFOPC.h"
//#include "../../REGPALITAGEM/include/cRegPalitagem.h"
#include "../../REGCONTATO/include/stRegContato.h"

DECLARE_TUXEDO_SERVICE(REGPALITAGEFO);

void implREGPALITAGEFO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    char s_idAtendimento[256];
    string sCode = "04I0000";
    string mCode = "Atendimento registrado.";
    char *user = getUser();

    CRegPalitageFO cregpalitagefo;

    try
    {
        cregpalitagefo.executar( dnode, user );

        xml_g->createTag( "AtendimentoVO" );
            xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            xml_g->addItem("nrProtocolo", cregpalitagefo.getIdAtendimentoProtocolo());
            sprintf( s_idAtendimento, "%ld", cregpalitagefo.getIdAtendimento() );
            //xml_g->addItem("idAtendimento", cregpalitagefo.getIdAtendimento());
            xml_g->addItem("idAtendimento", s_idAtendimento );
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
    catch ( st_RegContatoErro *erro )
    {
        sCode = erro->sCode;
        mCode = erro->mCode;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        sCode = "04E9999";
        mCode = "erro desconhecido";
    }

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());
}
