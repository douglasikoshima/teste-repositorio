#define PSSRELACIONAHUCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CHUP.h"

DECLARE_TUXEDO_SERVICE(PSSRELACIONAHU);

void implPSSRELACIONAHU::Execute(DOMNode*dnode,XMLGen*xml_g)
{    
    ULOG_START("implPSSRELACIONAHU::Execute()");
    try
    {
		CSafePointer oSafePointer;
      CHierarquiaDeptoPessoa clHierarquiaDeptoPessoa;
        
      char* pszIdPessoa      = oSafePointer.getTag( dnode,"idPessoa"     ,0 );
		if( strlennull( pszIdPessoa ) <= 0 )
		{
			setStatusCode("14E0000","idPessoa está nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdUnidade     = oSafePointer.getTag( dnode,"idUnidade"    ,0 );
		if( strlennull( pszIdUnidade ) <= 0 )
		{
			setStatusCode("14E0000","idUnidade está nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdOrganizacao = oSafePointer.getTag( dnode,"idOrganizacao",0 );
		if( strlennull( pszIdOrganizacao ) <= 0 )
		{
			setStatusCode("14E0000","idOrganizacao está nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdCargo       = oSafePointer.getTag( dnode,"idCargo"      ,0 );
		if( strlennull( pszIdCargo ) <= 0 )
		{
			setStatusCode("14E0000","idCargo está nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdNivel       = oSafePointer.getTag( dnode,"idNivel"      ,0 );
		if( strlennull( pszIdNivel ) <= 0 )
		{
			setStatusCode("14E0000","idNivel está nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdUser        = getUser();
		if( strlennull( pszIdUser ) <= 0 )
		{
			setStatusCode("14E0000","idUser está nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}

        // Valida os parametros de entrada do XML.
        if (  ( strlennull( pszIdPessoa      ) <= 0 )
           || ( strlennull( pszIdUnidade     ) <= 0 )
           || ( strlennull( pszIdOrganizacao ) <= 0 )
           || ( strlennull( pszIdCargo       ) <= 0 )
           || ( strlennull( pszIdNivel       ) <= 0 )
           || ( strlennull( pszIdUser        ) <= 0 ) )
        {
            setStatusCode("14E0099","Falta parâmetro de entrada");
            ULOG_END("implPSSRELACIONAHU::Execute()");
            return;
        }

        //////////////////////////////////////////////////////////////////
        // Atribuição dos valores para a estrutura do objeto principal. //
        //////////////////////////////////////////////////////////////////
        clHierarquiaDeptoPessoa.setIdPessoa( pszIdPessoa );
        clHierarquiaDeptoPessoa.setIdUsuarioAlteracao( pszIdUser );

        // Busca o IdOrganizacaoDepartamento e atribui ao objeto para insersão da Hierarquia da Pessoa.
        if( !clHierarquiaDeptoPessoa.buscaIdOrganizacaoDepartamento( pszIdOrganizacao
                                                                   , pszIdUnidade ) )
        {
            setStatusCode("14E0099","IdOrganizacaoDepartamento não encontrado");
            ULOG_END("implPSSRELACIONAHU::Execute()");
            return;
        }

        // Busca o IdNivelCargo e atribui ao objeto para insersão da Hierarquia da Pessoa.
        if( !clHierarquiaDeptoPessoa.buscaIdNivelCargo( pszIdNivel
                                                      , pszIdCargo ) )
        {
            setStatusCode("14E0099","IdNivelCargo não encontrado");
            ULOG_END("implPSSRELACIONAHU::Execute()");
            return;
        }
        //////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////
        // Caso encontre registro para este usuário este é atualizado,  //
        // canso contrário é inserido                                   //
        //////////////////////////////////////////////////////////////////
        // Busca o registro para o idPessoa inserido.
        // Caso encontre apenas atualiza o registro.
        if( clHierarquiaDeptoPessoa.buscaPorIdPessoa() )
        {
            clHierarquiaDeptoPessoa.atualizaHierarquiaDeptoPessoa();
        }
        // Caso não encontre, busca os ids necessários e insere o registro.
        else
        {
            // Insere a Hierarquia da Pessoa.
            clHierarquiaDeptoPessoa.insereHierarquiaDeptoPessoa();
        }
        //////////////////////////////////////////////////////////////////

        setStatusCode("14I0099","Sucesso na execução do relacionamento de Hierarquia da Pessoa.");
        ULOG_END("implPSSRELACIONAHU::Execute()");
    }
    catch( ... )
    {
        setStatusCode("14E0099", "Procedimento encerrado com ERRO." );
        throw;
    }    
    
}
