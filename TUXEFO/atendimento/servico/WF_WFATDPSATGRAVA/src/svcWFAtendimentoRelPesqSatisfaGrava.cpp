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
        // Foi aberta uma incid�ncia em homologa��o Vivo para pesquisa de satisfa��o
        // de n�mero 3374 com prazo de fechamento incoerente com o que tem de ser
        // feito para corrigir a pesquisa de satisfa��o. O modelo de dados de pesquisa
        // de satisfa��o foi alterado pelo Miguel e teremos de alterar TODOS os servi�os
        // de pesquisa de satisfa��o e relat�rios para atender ao novo modelo.
        // Devido a isso estou desabilitando a grava��o da pesquisa de satisfa��o, uma
        // vez que os relat�rios de pesquisa de satisfa��o n�o est�o sendo usados.
        // Jan/2006 - Cassio.
        //

        //Estou descomentando o c�digo porque houve um tremenda confus�o, a VIVO (Marcos gama, Wander e Robinson de Camargo)
        //Pediram um relat�rio e ao verificar o c�digo estava comentado.
        //Em conversa com o Miguel Benavente e Victor de la Rosa foi pedido para descomentar o c�digo e corrigi-lo.
        //A corre��o se resume a grava��o do nota de 0 a 10 sobre a pesquisa e a descri��o (atualamente n�o est� sendo gravada)
        //13-10-2006 Eder
        rc.Executar();
        //Acrescentando o retorno, pois ocorre erro de parse (incidencia 3037)
        //xml_g->createTag("WFAcaoRetornoVO");
	    //    xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	    //    xml_g->addItem("acaoExecucao", "S");
	    //    xml_g->addItem("mensagem", "Execu��o concluida com sucesso");
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

