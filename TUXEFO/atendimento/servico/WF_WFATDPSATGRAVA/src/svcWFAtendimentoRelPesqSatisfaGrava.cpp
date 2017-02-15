/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:18 $
 **/

#include "../include/cWFAtendimentoRelPesqSatisfaGrava.h"

DECLARE_TUXEDO_SERVICE(WFATDPSATGRAVA);

void implWFATDPSATGRAVA::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implWFATDPSATGRAVA::Execute()");

    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFATDPSATGRAVA Concluida";

    //char* cnmUrlDestino = walkTree( dnode, "urlDestino", 0 );

    cWFAtRelPqSatGrava rc(dnode, xml_g, getUser());

    try
    {
        // Aviso :
        // Foi aberta uma incidência em homologação Vivo para pesquisa de satisfação
        // de número 3374 com prazo de fechamento incoerente com o que tem de ser
        // feito para corrigir a pesquisa de satisfação. O modelo de dados de pesquisa
        // de satisfação foi alterado pelo Miguel e teremos de alterar TODOS os serviços
        // de pesquisa de satisfação e relatórios para atender ao novo modelo.
        // Devido a isso estou desabilitando a gravação da pesquisa de satisfação, uma
        // vez que os relatórios de pesquisa de satisfação não estão sendo usados.
        // Jan/2006 - Cassio.
        //

        //Estou descomentando o código porque houve um tremenda confusão, a VIVO (Marcos gama, Wander e Robinson de Camargo)
        //Pediram um relatório e ao verificar o código estava comentado.
        //Em conversa com o Miguel Benavente e Victor de la Rosa foi pedido para descomentar o código e corrigi-lo.
        //A correção se resume a gravação do nota de 0 a 10 sobre a pesquisa e a descrição (atualamente não está sendo gravada)
        //13-10-2006 Eder
        rc.Executar();
        //Acrescentando o retorno, pois ocorre erro de parse (incidencia 3037)
        //xml_g->createTag("WFAcaoRetornoVO");
	    //    xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	    //    xml_g->addItem("acaoExecucao", "S");
	    //    xml_g->addItem("mensagem", "Execução concluida com sucesso");
	    //    xml_g->addItem("urlDestino", "quest");
        //xml_g->closeTag();
    }
    catch ( TuxBasicOraException *tboe )
    {
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);

        //retCode = errCode;
        retMesg = tboe->pMsg;

        delete tboe;
    }
    catch( TuxException* pTux )
    {
        //retCode = "04E9998";
        retMesg = pTux->getMessage();

        delete pTux;
    }
    catch(...)
    {
        //retCode = "04E9999";
        retMesg = "Excecao desconhecida";
    }
    
    // Libera o ponteiro do walkTree
    //XMLString::release( &cnmUrlDestino );

    setStatusCode(retCode.c_str(),retMesg.c_str());
    
    ULOG_END("implWFATDPSATGRAVA::Execute()");
}

