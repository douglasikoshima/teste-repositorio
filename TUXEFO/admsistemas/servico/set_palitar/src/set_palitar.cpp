#define ATVINSERIRCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCfgPalitagem.h"

DECLARE_TUXEDO_SERVICE(SETPALITAR);

void implSETPALITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implSETPALITAR::Execute()");
	CSafePointer oSafePointer;
    CCfgPalitagem oCfgPalitagem;
	
	char* sAcao = oSafePointer.getTag(dnode,"acao",0);
	if( strlennull( sAcao ) <= 0 )
	{
		setStatusCode("14E0000","sAcao esta nulo");
		ULOG_END("implSETPALITAR::Execute()");
		return;
	}
	char* sidContato = oSafePointer.getTag(dnode,"idContato",0);
	if( strlennull( sidContato ) <= 0 )
	{
		setStatusCode("14E0000","sidContato esta nulo");
		ULOG_END("implSETPALITAR::Execute()");
		return;
	}
    if ( !strcmp( sAcao,"exclusao" ) )
    {
    	char* scdServico = oSafePointer.getTag(dnode,"cdServico",0);
    	if( strlennull( scdServico ) <= 0 )
    	{
    		setStatusCode("14E0000","scdServico esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* idSistema = oSafePointer.getTag(dnode,"idSistema",0);
    	if( strlennull( idSistema ) <= 0 )
    	{
    		setStatusCode("14E0000","idSistema esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
        oCfgPalitagem.Delete( idSistema, scdServico );
        setStatusCode("14I0000","Sucesso na execução do serviço!"); 
    }
    if ( !strcmp( sAcao,"alteracao" ) )
    {
        char* sidContato = oSafePointer.getTag(dnode,"idContato",0);
        if( strlennull( sidContato ) <= 0 )
        {
            setStatusCode("14E0000","sidContato esta nulo");
            ULOG_END("implSETPALITAR::Execute()");
            return;
        }
    	char* dsServico = oSafePointer.getTag(dnode,"dsServico",0);
    	if( strlennull( dsServico ) <= 0 )
    	{
    		setStatusCode("14E0000","dsServico esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* sgServico = oSafePointer.getTag(dnode,"cdServico",0);
    	if( strlennull( sgServico ) <= 0 )
    	{
    		setStatusCode("14E0000","sgServico esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* idProcedencia = oSafePointer.getTag(dnode,"idProcedencia",0);
    	if( strlennull( idProcedencia ) <= 0 )
    	{
    		setStatusCode("14E0000","idProcedencia esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* idSistema = oSafePointer.getTag(dnode,"idSistema",0);
    	if( strlennull( idSistema ) <= 0 )
    	{
    		setStatusCode("14E0000","idSistema esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}

    	char* cidUsuarioAlteracao = getUser();

        ULOG( "Processando ALTERACAO" ); 
        ULOG( "sidContato     [%s]",sidContato );
        ULOG( "idSistemaOrigem [%s]",idSistema );
        ULOG( "sgSistemaOrigem [%s]",sgServico );
        ULOG( "dsServico       [%s]",dsServico );
        ULOG( "idProcedencia   [%s]",idProcedencia );

    	switch(oCfgPalitagem.Update( sidContato, idSistema, sgServico, dsServico, idProcedencia, cidUsuarioAlteracao ))
    	{
    		case 0:	
    			setStatusCode("14E0000","Falha na execução do serviço.");
    			ULOG_END("implATVINSERIR::Execute()");
    			return;
    		break;
    		case -1:
    			//oAtribuicao.ListAll();
    			//oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
    			setStatusCode("14W0000","Já existe esta descrição ou registro, tente novamente com outra diferente.");
    		break;
    		default: 
    			//oAtribuicao.ListAll();
    			//oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
    			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
    		break;
    	}
    }
    if ( !strcmp( sAcao,"inclusao" ) )
    {
        char* sidContato = oSafePointer.getTag(dnode,"idContato",0);
        if( strlennull( sidContato ) <= 0 )
        {
            setStatusCode("14E0000","sidContato esta nulo");
            ULOG_END("implSETPALITAR::Execute()");
            return;
        }
    	char* dsServico = oSafePointer.getTag(dnode,"dsServico",0);
    	if( strlennull( dsServico ) <= 0 )
    	{
    		setStatusCode("14E0000","dsServico esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* sgServico = oSafePointer.getTag(dnode,"cdServico",0);
    	if( strlennull( sgServico ) <= 0 )
    	{
    		setStatusCode("14E0000","cdServico esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* idProcedencia = oSafePointer.getTag(dnode,"idProcedencia",0);
    	if( strlennull( idProcedencia ) <= 0 )
    	{
    		setStatusCode("14E0000","idProcedencia esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}
    	char* idSistema = oSafePointer.getTag(dnode,"idSistema",0);
    	if( strlennull( idSistema ) <= 0 )
    	{
    		setStatusCode("14E0000","idSistema esta nulo");
    		ULOG_END("implSETPALITAR::Execute()");
    		return;
    	}

    	char* cidUsuarioAlteracao = getUser();

        ULOG( "Processando MAIN" ); 
        ULOG( "sidContato      [%s]",sidContato );
        ULOG( "idSistemaOrigem [%s]",idSistema );
        ULOG( "sgSistemaOrigem [%s]",sgServico );
        ULOG( "dsServico       [%s]",dsServico );
        ULOG( "idProcedencia   [%s]",idProcedencia );

    	switch(oCfgPalitagem.Insert( sidContato, idSistema, sgServico, dsServico, idProcedencia, cidUsuarioAlteracao ))
    	{
    		case 0:	
    			setStatusCode("14E0000","Falha na execução do serviço.");
    			ULOG_END("implATVINSERIR::Execute()");
    			return;
    		break;
    		case -1:
    			//oAtribuicao.ListAll();
    			//oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
    			setStatusCode("14W0000","Já existe esta descrição ou registro, tente novamente com outra diferente.");
    		break;
    		case -7:
    			setStatusCode("14E0001","Não é possível realizar a alteração, pois o código do serviço já existe cadastrado para este sistema.");
    		break;
    		default: 
    			//oAtribuicao.ListAll();
    			//oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
    			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
    		break;
    	}
    }
	
	ULOG_END("implSETPALITAR::Execute()");
}
