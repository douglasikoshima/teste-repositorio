/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:27 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetDDenc.h"

class cWFAtdDetDDenc : public TuxBasicSvc
{
    public:
        cWFAtdDetDDenc() {}
        cWFAtdDetDDenc(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetDDenc() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetDDenc   m_stDados;
        st_vlAtdDetDDenc m_vlDados;

        st_AtdDetDDencPesq   m_stAtdDetDDenc;
        st_vlAtdDetDDencPesq m_vlAtdDetDDenc;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString xmlStrAtdBaixa;

        SmallString nmContato;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void ObterAtdBxax(int idBaixa, char* idBaixaMensagem, char* dsMensagem, char* idFormaRetorno, char* dsFormaRetorno, char* dsComentario);
        void ObterAtdBxaAtual();

        void carregaDados();
} ;
