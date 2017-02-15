#define ADMPSQCONRELACPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CTabConsRelac.h"

DECLARE_TUXEDO_SERVICE(ADMPSQCONRELA);

void implADMPSQCONRELA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMPSQCONRELA::Execute()");

	CSafePointer oSafePointer;

    char *ctpOperacao = oSafePointer.getTag( dnode, "tpOperacao", 0 );
    char *inAssociado = oSafePointer.getTag( dnode, "inAssociado", 0 );

    CTabConsRelac cTabConsRelac;

	if( strcmp(ctpOperacao,"pesquisarClienteVOFuncionalidade")==0 )
	{
    
        char * idFuncPrm  = oSafePointer.getTag( dnode, "idFuncionalidade", 0 );
        char * idFrmPrm  = oSafePointer.getTag( dnode, "idFormulario", 0 );
        
        if ( cTabConsRelac.PesquisaEmpresaFrm( idFrmPrm, idFuncPrm, xml_g ) > 0 )
        {
            setStatusCode("14I0000","Sucesso na execução.");
        }
        else
        {
            setStatusCode("14I0000","Nenhum formulario encontrado para o padrão de pesquisa informado.");
        }
        
    }
    else if( strcmp(ctpOperacao,"pesquisarEmpresaVO")==0 )
	{
        char * nrCNPJ  = oSafePointer.getTag( dnode, "nrCNPJ", 0 );
    
        if ( !strcmp(inAssociado,"0") )
        {
            if ( cTabConsRelac.PesquisaEmpresaSemFrm( nrCNPJ, xml_g ) > 0 )
            {
                setStatusCode("14I0000","Sucesso na execução.");
            }
            else
            {
                setStatusCode("14I0000","Nenhuma empresa encontrada para o padrão de pesquisa informado.");
            }
        }
        else
        {
            
            if ( cTabConsRelac.PesquisaEmpresaCNPJ( nrCNPJ, xml_g ) > 0 )
            {
                setStatusCode("14I0000","Sucesso na execução.");
            }
            else
            {
                setStatusCode("14I0000","Nenhuma empresa encontrada para o padrão de pesquisa informado.");
            }
        }
        
    }
    else if( strcmp(ctpOperacao,"pesquisarConsultorVO")==0 )
	{
        char *dsLogin = oSafePointer.getTag( dnode, "dsLogin", 0 );
        char *nmConsultor = oSafePointer.getTag( dnode, "nmConsultor", 0 );
        char *nrDocumento = oSafePointer.getTag( dnode, "nrCPF", 0 );

        if ( cTabConsRelac.PesquisarConsultor(xml_g,dsLogin,nmConsultor,nrDocumento) > 0 )
        {
	        setStatusCode("14I0000","Sucesso na execução.");
        }
        else
        {
		    setStatusCode("14I0000","Nenhum consultor encontrado para o padrão de pesquisa informado.");
        }
    }
    else if( strcmp(ctpOperacao,"pesquisarClienteVO")==0 )
    {
        char *nrConta = oSafePointer.getTag( dnode, "nrConta", 0 );
        char *nrDocumento = oSafePointer.getTag( dnode, "nrCNPJ", 0 );
        char *nmRazaoSocial = oSafePointer.getTag( dnode, "nmRazaoSocial", 0 );
        char *inAssociado = oSafePointer.getTag( dnode, "inAssociado", 0 );

        if ( cTabConsRelac.PesquisarCliente(xml_g,nrConta,nrDocumento,nmRazaoSocial,inAssociado) > 0 )
        {
	        setStatusCode("14I0000","Sucesso na execução.");
        }
        else
        {
		    setStatusCode("14I0000","Nenhum cliente encontrado para o filtro solicitado.");
        }
    }
    else if( strcmp(ctpOperacao,"pesquisarClienteVOPorIDConsultor")==0 )
    {
        char *idPessoaUsuario = oSafePointer.getTag( dnode, "idConsultor", 0 );

        if ( idPessoaUsuario && *idPessoaUsuario )
        {
            if ( cTabConsRelac.PesquisarClientePorIDConsultor(xml_g,idPessoaUsuario) > 0 )
            {
	            setStatusCode("14I0000","Sucesso na execução.");
            }
            else
            {
		        setStatusCode("14I0000","Nenhum cliente associado ao consultor informado.");
            }
        }
        else
        {
		    setStatusCode("00W0002","Faltou informar o ID do consultor a ser pesquisado.");
        }
    }
    else if( strcmp(ctpOperacao,"pesquisarConsultorVOPorIDCliente")==0 )
    {
        char *nrDocumento = oSafePointer.getTag( dnode, "nrCNPJ", 0 );

        if ( cTabConsRelac.PesquisarConsultorIdCliente( xml_g, nrDocumento ) > 0 )
        {
	        setStatusCode("14I0000","Sucesso na execução.");
        }
        else
        {
		    setStatusCode("14I0000","Nenhum cliente encontrado para o filtro solicitado.");
        }
    }
	else
	{
		setStatusCode("00W0001","Faltou informar qual a pesquisa desejada.");
	}

	ULOG_END("implADMPSQCONRELA::Execute()");
}
