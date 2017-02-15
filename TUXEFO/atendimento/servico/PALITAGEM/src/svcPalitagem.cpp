/**
 * 
 * @modulo  Atendimento
 * @usecase Pré-processamento do relatório de palitagem
 * @author  Max
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:08 $
 **/

#include "../include/cPalitagem.h"

DECLARE_TUXEDO_SERVICE(PALITAGE);

void implPALITAGE::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    char *szCodeNum;
    char *szCodeMsg;

    try
    {
        cPalitagem cpalitagem;

        cpalitagem.CarregaDados(dnode);
        cpalitagem.ProcessaPalitagem();

        szCodeNum = "04I9999";
        szCodeMsg = "Sucesso no processo de palitagem";

    }
    catch (...)
    {
        szCodeNum = "04E9999";
        szCodeMsg = "Erro no processo de palitagem";

        ULOG("szCodeMsg [%s]", szCodeMsg);
        ULOG("szCodeNum [%s]", szCodeNum);
    }

    setStatusCode(szCodeNum, szCodeMsg);
}
