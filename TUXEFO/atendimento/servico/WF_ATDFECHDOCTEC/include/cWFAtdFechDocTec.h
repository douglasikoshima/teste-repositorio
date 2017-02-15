/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:40 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdFechDocTec.h"
#include "../../DocumentoTecnico/include/cWFDocumentoTecnico.h"

class cWFAtdFechDocTec : public TuxBasicSvc
{
    public:
        cWFAtdFechDocTec() {}
        cWFAtdFechDocTec(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdFechDocTec() {}

    public:
        bool Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

        inline char *ObterMsgErro() { return (char*)msgErro; }
        inline int ObterTamMsgErro() { return msgErro.Size(); }
        inline char *ObterCodErro() { return (char*)codErro; }
        inline int ObterTamCodErro() { return codErro.Size(); }

    public:
        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro = *me; }
        void SetarErro(char *ce,char *me) { if (ce) codErro=ce;msgErro = me; }

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

	 	st_DocumentoTecnico	m_stDados;
		st_vlDocumentoTecnico m_vlDados;
   private:
        int idUsuario;
        SmallString codErro;
        SmallString msgErro;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados() {}
        bool FecharDocumentos();
        bool AtualizaAtdAlerta( char *nrDocumento,int idAtividade,int idUsuario );
        int  ValidaDocumentos( char *nrDocumento );


} ;
