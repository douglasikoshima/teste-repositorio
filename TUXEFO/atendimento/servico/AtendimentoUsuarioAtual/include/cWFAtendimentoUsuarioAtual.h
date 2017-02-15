/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoUsuarioAtual.h"

#ifndef CWFATENDIMENTOUSUARIOATUAL
    #define CWFATENDIMENTOUSUARIOATUAL

class cWFAtendimentoUsuarioAtual
{
	st_AtendimentoUsuarioAtual m_stDados;
	st_vlAtendimentoUsuarioAtual m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFAtendimentoUsuarioAtual() { entrada=0;saida=0; }
		cWFAtendimentoUsuarioAtual(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status, XMLGen*xml_g);
        cWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status);
        bool incluir(XMLDPR *xmlDpr);
        bool incluir1aVez(long idAtendimento,long idPessoaUsuarioAtual,XMLDPR *xmlDpr);
		bool incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
		int alterar(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar(XMLDPR *xmlDpr);
		bool excluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
		bool excluir(XMLDPR *xmlDpr);
        bool excluir(long idAtendimento,int idPessoaUsuario,XMLDPR *xmlDpr);
        int excluirLogicamente();
        long obterIdPessoaUsuarioAtual(long idAtendimento);
        bool existe(long idAtendimento);

	private:
		void carregaDados();

};

#endif
