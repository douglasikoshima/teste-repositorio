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
			setStatusCode("14E0000","idPessoa est� nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdUnidade     = oSafePointer.getTag( dnode,"idUnidade"    ,0 );
		if( strlennull( pszIdUnidade ) <= 0 )
		{
			setStatusCode("14E0000","idUnidade est� nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdOrganizacao = oSafePointer.getTag( dnode,"idOrganizacao",0 );
		if( strlennull( pszIdOrganizacao ) <= 0 )
		{
			setStatusCode("14E0000","idOrganizacao est� nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdCargo       = oSafePointer.getTag( dnode,"idCargo"      ,0 );
		if( strlennull( pszIdCargo ) <= 0 )
		{
			setStatusCode("14E0000","idCargo est� nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdNivel       = oSafePointer.getTag( dnode,"idNivel"      ,0 );
		if( strlennull( pszIdNivel ) <= 0 )
		{
			setStatusCode("14E0000","idNivel est� nulo");
			ULOG_END("implPSSRELACIONAHU::Execute()");
			return;
		}
        char* pszIdUser        = getUser();
		if( strlennull( pszIdUser ) <= 0 )
		{
			setStatusCode("14E0000","idUser est� nulo");
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
            setStatusCode("14E0099","Falta par�metro de entrada");
            ULOG_END("implPSSRELACIONAHU::Execute()");
            return;
        }

        //////////////////////////////////////////////////////////////////
        // Atribui��o dos valores para a estrutura do objeto principal. //
        //////////////////////////////////////////////////////////////////
        clHierarquiaDeptoPessoa.setIdPessoa( pszIdPessoa );
        clHierarquiaDeptoPessoa.setIdUsuarioAlteracao( pszIdUser );

        // Busca o IdOrganizacaoDepartamento e atribui ao objeto para insers�o da Hierarquia da Pessoa.
        if( !clHierarquiaDeptoPessoa.buscaIdOrganizacaoDepartamento( pszIdOrganizacao
                                                                   , pszIdUnidade ) )
        {
            setStatusCode("14E0099","IdOrganizacaoDepartamento n�o encontrado");
            ULOG_END("implPSSRELACIONAHU::Execute()");
            return;
        }

        // Busca o IdNivelCargo e atribui ao objeto para insers�o da Hierarquia da Pessoa.
        if( !clHierarquiaDeptoPessoa.buscaIdNivelCargo( pszIdNivel
                                                      , pszIdCargo ) )
        {
            setStatusCode("14E0099","IdNivelCargo n�o encontrado");
            ULOG_END("implPSSRELACIONAHU::Execute()");
            return;
        }
        //////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////
        // Caso encontre registro para este usu�rio este � atualizado,  //
        // canso contr�rio � inserido                                   //
        //////////////////////////////////////////////////////////////////
        // Busca o registro para o idPessoa inserido.
        // Caso encontre apenas atualiza o registro.
        if( clHierarquiaDeptoPessoa.buscaPorIdPessoa() )
        {
            clHierarquiaDeptoPessoa.atualizaHierarquiaDeptoPessoa();
        }
        // Caso n�o encontre, busca os ids necess�rios e insere o registro.
        else
        {
            // Insere a Hierarquia da Pessoa.
            clHierarquiaDeptoPessoa.insereHierarquiaDeptoPessoa();
        }
        //////////////////////////////////////////////////////////////////

        setStatusCode("14I0099","Sucesso na execu��o do relacionamento de Hierarquia da Pessoa.");
        ULOG_END("implPSSRELACIONAHU::Execute()");
    }
    catch( ... )
    {
        setStatusCode("14E0099", "Procedimento encerrado com ERRO." );
        throw;
    }    
    
}
