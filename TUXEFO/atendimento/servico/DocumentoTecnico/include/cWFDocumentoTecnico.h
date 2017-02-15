/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:15 $
 **/ 

#include <tuxfw.h>
#include "stWFDocumentoTecnico.h"

class cWFDocumentoTecnico
{

	st_DocumentoTecnico	m_stDados;
	st_vlDocumentoTecnico	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFDocumentoTecnico(DOMNode*dnode, XMLGen*xml_g);
		cWFDocumentoTecnico(XMLGen*xml_g);
		bool incluir();
		bool incluir(st_DocumentoTecnico dados, st_vlDocumentoTecnico status);
		int alterar();
		int excluir();
		bool consultar();
		bool consultarAtendimento();
		bool consultarTodosDocumentos();
		bool consultarSelecaoDocumentos();
		bool FecharDocumentos(st_DocumentoTecnico dados, st_vlDocumentoTecnico status);
        bool ValidarNrDocumento( char *sNrDocumento );

    private:
		void carregaDados();

};
