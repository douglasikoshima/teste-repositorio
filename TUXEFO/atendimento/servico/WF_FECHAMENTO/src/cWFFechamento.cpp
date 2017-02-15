/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/03/08 20:16:11 $
 **/

#include "../include/cWFFechamento.h"

//------------------------------------------------------------------------------------------------------------------
cWFFechamento::cWFFechamento(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------
void cWFFechamento::Executar() 
{
    cWF_Acao::Executar();

    if ( existeAtendimentoFechamento() )
    {
        ULOG("o processo %s já havia sido fechado",idAtendimento.c_str());

        SetMessage( "Fechamento já havia sido concluido para este processo.", "S" ); 

        xml_g->closeTag();

        return;
    }

    if ( Fechar() == true )
    {
        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(User.ToInt(),idContato,"FECHAMENTO");
        // <== SM324--DPR--DEZ/2006--Cassio
        SetMessage( "Fechamento Concluído", "S" ); 
        xml_g->closeTag();
    }
}
