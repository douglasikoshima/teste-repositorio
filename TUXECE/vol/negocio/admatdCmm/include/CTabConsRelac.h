#ifndef CTabConsRelacH
#define CTabConsRelacH

#include <tuxfw.h>
#include "CTabConsRelacItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CTabConsRelac
{
	public:
		CTabConsRelac();
		~CTabConsRelac();
        
        int PesquisaEmpresaSemFrm( char * nrDoc, XMLGen * saida );
        int PesquisaEmpresaCNPJ( char * nrDoc, XMLGen * saida );
        int PesquisaEmpresaFrm( char * idFormulario, char * idFuncionalidade, XMLGen * saida );
        int PesquisarConsultorIdCliente( XMLGen*saida,const char *nrDocumento );
        int PesquisarConsultor( XMLGen*saida,const char *dsLogin,const char *nmConsultor,const char *nrDocumento );
        int PesquisarCliente( XMLGen*saida,const char *nrConta,const char *nrDocumento);
        int PesquisarCliente( XMLGen*saida,const char *nrConta,const char *nrDocumento,const char *nmRazaoSocial,const char *inAssociado );
        int PesquisarClientePorIDConsultor( XMLGen*saida,const char *idPessoaUsuario );
        int GravarAssociacaoDeClientesConsultor( XMLGen*saida,const char *idUser,const char *idPessoa,CTabConsRelacItr &cTabConsRelacItr );
        int RemoverAssociacaoDeConsultor( const char *idUser,const char *idPessoa,CTabConsRelacItr &cTabConsRelacItr );
        int AlterarAssociacaoDeConsultor( XMLGen*saida,const char *idUser,const char *idPessoa,CTabConsRelacItr &cTabConsRelacItr );
};

class CTabEmpresaRelac
{
	public:
		CTabEmpresaRelac();
		~CTabEmpresaRelac();
        
        int GravarAssociacaoEmpresaFrm( XMLGen     * saida,
                                        const char * idUser,
                                        const char * idContato,
                                        const char * idFuncionalidade,
                                        CTabEmpresaRelacItr &cTabEmpresaRelacItr );
};

#endif
