//
// $Id: libfinal_retencao.cpp,v 1.1 2009/07/31 15:34:49 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"
#include "../../negocio/fidutil/include/CCoreRetencao.h"

extern int final_retencao(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE( FINALRETENCAO );

void implFINALRETENCAO::Execute( DOMNode* dnode, XMLGen* xml_g )
{
    ULOG_START( "FINALRETENCAO" );

    int retorno = 0;
    int idUsr;
    char parm[256];
    int iCtrREGCONTATO;
    char idUsuario[40];
    CCoreRetencao oCoreRetencao;
    parm[0] = 0x0;

    char sParam[256];
    char cdRetorno[16];
    char sMsgRetorno[256];
    memset( cdRetorno,0x0,sizeof(cdRetorno) );
    memset( sMsgRetorno,0x0,sizeof(sMsgRetorno) );

  //  oCoreRetencao.m_inPortOut = get_tag( parm, dnode, "nrProtocoloPortabilidade", 0, -1 );
   
    /*if ( parm[0] != NULL )
        oCoreRetencao.m_inPortOut = 0;
    else
        oCoreRetencao.m_inPortOut = 1;*/

    //processa a retencao	
	strcpy( idUsuario, getUser() );
    parm[0] = 0x0;
    get_tag(parm,dnode,"nrTipo",0,-1);
    if (parm[0] != 0x0 )
    {
        if ( atoi(parm) != 1 && atoi(parm) != 2 )
        {
            setStatusCode( OKFID, "TIPO DE INTERCEPTAÇÃO INVÁLIDO" );
            return;
        }
        else
        {
            if ( atoi(parm) == 1 )
            {
                strcpy( idUsuario, "40" );
            }
            else
            {
                if ( atoi(parm) == 2 )
                {
                    strcpy( idUsuario, "30" );
                }
        	}
        }
    }
    
	memset(sParam,0x0,sizeof(sParam));
    get_tag(sParam,dnode,"inURA",0,-1);
    if( atoi(sParam) > 0 )
    {
        oCoreRetencao.m_idRetencao[0] = 0x0;
        retorno = oCoreRetencao.RetencaoBonusURA(dnode,idUsuario );
        dnode = oCoreRetencao.pDocURA;
    }
    
    if ( retorno != 0 )
    {
        get_tag(cdRetorno,dnode,"cdRetorno",0,-1);
        get_tag(sMsgRetorno,dnode,"msgRetorno",0,-1);

        xml_g->createTag("RetornoVO");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        xml_g->addItem( "cdRetorno",cdRetorno );
        xml_g->addItem( "msgRetorno",sMsgRetorno );
           oCoreRetencao.GeraMsgRetorno(xml_g);
        xml_g->closeTag();

        setStatusCode( OKFID, OKMSG );
        ULOG_END( "FINALRETENCAO" );
        return;
    }
    
    oCoreRetencao.Executar( dnode, idUsuario );

	int ret = 0;
    if ( oCoreRetencao.m_inPortOut == -1 )
    {
        ULOG( "Antes de REGCONTATO m_nrTelefoneContato [%s]",oCoreRetencao.m_nrTelefoneContato );
        //registra o contato de acordo com a retencao
        iCtrREGCONTATO = oCoreRetencao.ValidaChamadaREGCONTATO();

        ULOG( "iCtrREGCONTATO: [%d]", iCtrREGCONTATO );
        if ( iCtrREGCONTATO == 0 )
        {
            ULOG( "if (iCtrREGCONTATO==0)" );
            oCoreRetencao.RegistraContato(dnode,idUsuario);
        }
        ULOG( "Depois de REGCONTATO m_nrTelefoneContato [%s]",oCoreRetencao.m_nrTelefoneContato );
    }
    else
    {
        ret = oCoreRetencao.CoreWorkflow(dnode,idUsuario);
    }


    get_tag(parm,dnode,"inURA",0,-1);
    if( atoi(parm) == 0 )
    {
        xml_g->createTag("tns:retornoVO");
        xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        xml_g->addProp("xmlns:ns1","fidelizacao.fo.vivo.com.br/vo");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
    }
    else
    {
        memset( cdRetorno,0x0,sizeof(cdRetorno) );
        memset( sMsgRetorno,0x0,sizeof(sMsgRetorno) );
        
        get_tag(cdRetorno,dnode,"cdRetorno",0,-1);
        get_tag(sMsgRetorno,dnode,"msgRetorno",0,-1);
        if ( cdRetorno[0] == 0x0 )
        {
           strcpy( cdRetorno, "00" );
        }
        if ( ret == -1 )
    	{
           strcpy( cdRetorno, "08" );
           strcpy( sMsgRetorno, "processo esta sendo tratado por outro usuário" );
    	}
        xml_g->createTag("RetornoVO");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        xml_g->addItem( "cdRetorno",cdRetorno );
        xml_g->addItem( "msgRetorno",sMsgRetorno );
    }

    ULOG( "Antes de GeraMsgRetorno m_nrTelefoneContato [%s]",oCoreRetencao.m_nrTelefoneContato );
    //gera xml de retorno
    oCoreRetencao.GeraMsgRetorno(xml_g);
    xml_g->closeTag();

    /*if ( oCoreRetencao.m_inPortOut == 1 )
        {
            
        }*/

    setStatusCode( OKFID, OKMSG );

    ULOG_END( "FINALRETENCAO" );
}

