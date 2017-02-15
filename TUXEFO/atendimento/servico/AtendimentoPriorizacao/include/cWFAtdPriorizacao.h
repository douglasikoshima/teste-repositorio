/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#ifndef CWFATENDIMENTOPRIORIZACAO
	#define CWFATENDIMENTOPRIORIZACAO

#include <tuxfw.h>
#include "stWFAtdPriorizacao.h"
#include "../../../commons/SmallString.h"
#include "../../../commons/Collection/include/Collection.h"

class Erro
{
    public:
        inline char *ObterMsgErro() { return (char*)msgErro; }
        inline int ObterTamMsgErro() { return msgErro.Size(); }
        inline char *ObterCodErro() { return (char*)codErro; }
        inline int ObterTamCodErro() { return codErro.Size(); }

        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro=*me; }
        void SetarErro(char *ce,char *me) { if (ce) codErro=ce;msgErro=me; }

    private:
        SmallString codErro;
        SmallString msgErro;
};

class cWFAtendimentoPriorizacao : public Erro
{
	public:
        cWFAtendimentoPriorizacao();
		cWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status, XMLGen*xml_g);
        cWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status);
		cWFAtendimentoPriorizacao(DOMNode*dnode, XMLGen*xml_g);

        ~cWFAtendimentoPriorizacao() {}

		long incluir();
		long alterar();
		long excluir();
		long consultar();
        int alterarCMS();

	private:
        // Métodos Pro*C++
        int proCExcluirWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status);
        int proCAlterarWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status);
        int proCIncluirWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status);
        int proCConsultarWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status, char* order, XMLGen* saida);

	public:
	    st_AtendimentoPriorizacao m_stDados;
	    st_vlAtendimentoPriorizacao m_vlDados;

	    DOMNode* entrada;
	    XMLGen*  saida;

	    TuxHelper tx;

	private:
		void carregaDados();
};

#endif
