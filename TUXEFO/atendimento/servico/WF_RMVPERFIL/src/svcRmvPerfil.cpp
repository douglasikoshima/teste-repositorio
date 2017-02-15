

#include "../include/cRmvPerfil.h"

DECLARE_TUXEDO_SERVICE(RMVPERFIL);

void implRMVPERFIL::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implRMVPERFIL::Execute()");
    
    short retorno;
    cRmvPerfil ob( dnode,xml_g );
    try
    {
        retorno = ob.Processa();
        //if (retorno == 0 )
        //{ 
        //    xml_g->createTag( "WFAcaoRetornoVO" );
        //    xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
       // 
       //     xml_g->addItem( "acaoExecucao", "S" );
       //     xml_g->addItem( "mensagem"    , "Perfil excluÌdo com sucesso" );
       // 
       //     xml_g->closeTag();
       //     setStatusCode("09I0000","OperaÁ„o concluÌda");
       // }
       // else
       // {
       // }
        switch (retorno) {
        case 0: { 
                  xml_g->createTag( "WFAcaoRetornoVO" );
                    xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
                    xml_g->addItem( "acaoExecucao", "S" );
                    xml_g->addItem( "mensagem"    , "Perfil excluÌdo com sucesso" );
                  xml_g->closeTag();
                  setStatusCode("09I0000","Servico executado com sucesso."); 
                  break;
        	}
        case 1:  {
		        xml_g->createTag( "WFAcaoRetornoVO" );
		        xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
		                    xml_g->addItem( "acaoExecucao", "N" );
		                    xml_g->addItem( "mensagem"    , "Este registro n„o pode ser apagado, Existe atendimento para este Perfil" );
		        xml_g->closeTag();
		                    setStatusCode("09W0100","Este registro n„o pode ser apagado, Existe atendimento para este Perfil ");
	            break;
	         }
        default: setStatusCode("09E9999","Erro n“o classificado."); break;
        }
    }
    catch(...)
    {
        throw;
    }

    ULOG_END("implRMVPERFIL::Execute()");

}

