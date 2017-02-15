/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:44 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

#include "cRegContatoFOPC.h"
#include "stRegContatoFO.h"

#include "../../REGCONTATO/include/cRegContato.h"

class cRegContatoFO : public TuxBasicSvc
{
    public:
        DOMNode *entrada;
        XMLGen *saida;

    public:
        st_RegContatoFO dados;
        string codUsuario;

        cRegContatoFO(DOMNode *dnode,XMLGen *xml_g,char *user)
        {
            saida = xml_g;
            entrada = dnode;
            codUsuario = user?user:"";
            nomeServico = "REGCONTATO";
            descricaoServico = "registro de contato";
        }

        ~cRegContatoFO() {}

    public:
        void Executar();

    private:
        XMLGen pXMLGen;
        cRegContatoFOPC rcfoProC;
        st_RegContato dadosRC;

        string nomeServico;
        string descricaoServico;
        bool pesquisarDadosLinha;

        bool ObterDadosParaUsuario();
        bool ObterDadosParaCliente();

        void CarregarDados();
        void CompletarXMLInPalitagem();
        void ValidarDadosEntrada();
        void ObterDadosParaClienteUsuario();
        void ObterDadosParaNaoCliente();
        void ObterIdContato();

        inline char *getNomeServico() { return (char*)nomeServico.c_str(); }
        inline char *getDescricaoServico() { return (char*)descricaoServico.c_str(); }

        void RegistrarContato();
};
