/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:32:56 $
 **/


#include "../include/cWF_CERRAMERET.h"

DECLARE_TUXEDO_SERVICE(CERRAMERET);

void implCERRAMERET::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implCERRAMERET::Execute()");
    TString retCode = "04I0000";
    TString retMesg = "Execucao de CERRAMERET Concluida";

    cWF_CERRAMERET rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch ( TuxBasicOraException *tboe )
    {
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);

        retCode = errCode;
        retMesg = tboe->pMsg;

        delete tboe;
    }
    catch( TuxException* pTux )
    {
        retCode = "04E9998";
        retMesg = pTux->getMessage();

        delete pTux;
    }
    catch(...)
    {
        retCode = "04E9999";
        retMesg = "Excecao desconhecida";
    }

    setStatusCode(retCode.c_str(),retMesg.c_str());
    ULOG_END("implCERRAMERET::Execute()");

}

//******************************************************************************************************************
// cWF_CERRAMERET - Component Implementation
//******************************************************************************************************************

cWF_CERRAMERET::cWF_CERRAMERET(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------
void cWF_CERRAMERET::Executar()
{
    cWF_Acao::Executar();

    getAtendimento();

    // if ( TP_RET_COM_RET_GRP_RET != idTipoRetornoContato )
    // {
    //     SetMessage( "Processo n�o � do tipo 'RETORNO POR GRUPO DE RETORNO'."
    //                 "\nParametriza��es alteradas. Favor tentar encerrar o processo novamente.","M"
    //               );
    // 
    //     xml_g->closeTag();
    // 
    //     return;
    // }

    // urlDestino=1, � interpretado na p�gina web que ap�s a execu��o da a��o o sistema
    // dever� retornar para a fila ou inbox do usu�rio e quando urlDestino=3 a p�gina
    // web interpreta que o sistema dever� retornar para o detalhe do processo.
    // No caso de RETORNO POR GRUPO DE RETORNO, como o processo sai das m�os do usu�rio
    // respons�vel e vai para a fila do grupo de retorno, ent�o urlDestino=1 e no caso
    // de RETORNO POR GRUPO BKO ent�o urlDestino=3.
    urlDestino = TP_RET_COM_RET_GRP_RET == idTipoRetornoContato ? "1" :"3";

    Encerrar();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"CERRAMERET");
    // <== SM324--DPR--DEZ/2006--Cassio

    if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato )
    {
        SetMessage( "Encerramento Conclu�do para Grupo de Retorno", "S" );
    }
    else if ( TP_RET_COM_RET_GRP_CRI == idTipoRetornoContato )
    {
        SetMessage( "Encerramento Conclu�do Para Grupo de CRI", "S" );
    }
    else if ( strcmp(szSgRegraEncaminhamento,"MC")==0 )
    {
        SetMessage( "Encerramento Conclu�do Para 'Meu Cliente'", "S" );
    }
    else
    {
        SetMessage( "Encerramento Conclu�do Para Grupo de BKO", "S" );
    }

    xml_g->closeTag();
}
