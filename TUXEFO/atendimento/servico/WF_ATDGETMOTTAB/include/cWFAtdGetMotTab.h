/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include <tuxfw.h>
#include "../../../commons/msgPadrao.h"
#include "../../../commons/SmallString.h"

#include "stWFAtdGetMotTab.h"

class cWFAtdGetMotTab : public TuxBasicSvc
{
    public:
        cWFAtdGetMotTab() {}
        cWFAtdGetMotTab(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdGetMotTab() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdGetMotTab   m_stDados;
        st_vlAtdGetMotTab m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString xmlObtemPsqGr;
        SmallString xmlObtemPsqTab;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados();
        void ConsultarWFGrupos();
        void ConsultarGruposAtendimento( int idContato,int idFase, int iTipoRetorno);            
        void ConsultarGruposAbertura( int idContato,int idFase );
	    void ConsultarGruposReAbertura( int idContato, int idFase );
        void ConsultarMotivoAtividade();
        void ConsultarMotivoAtividade(int _idFase );
        void ConsultarGruposPorAtendimento(int idContato);        
        void ConsultarGruposCRIAssociados( int idContato );
        void ConsultarGruposMC1Associados( long idAtendimento );

        // void ConsultarMotivoAtividade(int _Atividade);
};
