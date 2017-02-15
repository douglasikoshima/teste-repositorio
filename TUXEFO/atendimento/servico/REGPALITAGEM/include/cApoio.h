/**
 * Esse arquivo possui as classes de apoio para uso durante o processamento.
 *
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:31 $
 **/ 

// Classes de apoio.
#include "../../../commons/Collection/include/Collection.h"

class AtendimentoLinhas
{
	public:
        AtendimentoLinhas() { cdConta=0;nrTelefone=0; }
        ~AtendimentoLinhas() 
        { 
            if (cdConta) XMLString::release(&cdConta);
            if (nrTelefone) XMLString::release(&nrTelefone);
        }

	public:
		char *cdConta;
		char *nrTelefone;
};

class AtendimentoRetorno
{
	public:
        AtendimentoRetorno() { dsComunicacao=0;idTipoComunicacao=0; }
        ~AtendimentoRetorno() { if (dsComunicacao) XMLString::release(&dsComunicacao); }

	public:
		char* dsComunicacao;
		int idTipoComunicacao;
};

class FormularioResposta
{
	public:
        FormularioResposta() { dsResposta=0; }
        ~FormularioResposta() { if (dsResposta) XMLString::release(&dsResposta); }

	public:
		int idDominio;
		char* dsResposta;
};

class AtendimentoFormulario
{
	public:
        AtendimentoFormulario() { respostas=0; }
        ~AtendimentoFormulario() { if (respostas) delete respostas; }

	public:
		int idCampo;
		Collection* respostas;
};