/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:40 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdLstComTTGr.h"

class cWFAtdLstComTTGr : public TuxBasicSvc
{
    public:
        cWFAtdLstComTTGr() { entrada=0; saida=0;}
        cWFAtdLstComTTGr(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdLstComTTGr() {}

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
		int start;
		int stop;

    private:
        int idUsuario;
        SmallString codErro;
        SmallString msgErro;

		st_AtdLstComtTgr m_stDados;
		st_vlAtdLstComtTgr m_vlDados;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados();

        bool PesquisarTabelaSegmentacao();
        bool PesquisarTabelaTipoCarteira();
        bool PesquisarTabelaTipoSequencia();
        bool PesquisarOperadoras();
        bool PesquisarUfOperadora();
        bool pesquisarUsuarios();
        bool ConsultarWFGrupos();
        void ObterListaStatusUsuario();
        void pesquisarDivisao();
	    void pesquisarGerencia();
	    void pesquisarCoordinacao();
	    void pesquisarSupervisao();
} ;
