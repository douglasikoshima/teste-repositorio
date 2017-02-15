/**
 * @modulo  Admsistemas
 * @usecase Admsistemas
 * @author  Max
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/01/13 22:56:56 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif
                     
#include <string>
#include <list>
#include <vector>
#include <map>

#define TIPODADOCAMPO              6
#define MASCARAAPRESENCAOCAMPO     3
#define LAYOUTAPRESENTACAOCAMPO    3
#define CLASSIFICADORCAMPO        41

#define OPERACAO_GETCAMPOSDEPENDENTES   0
#define OPERACAO_SETCAMPOSVALORES       1
#define OPERACAO_GETCAMPOSVALORES       2
#define OPERACAO_GETTREE                3
#define OPERACAO_GETCAMPOSNIVEL         4
#define OPERACAO_PROXIMONIVEL           5
#define OPERACAO_SETTREE                6
#define OPERACAO_LUPAGETCAMPODPD        7

#define MAX_NIVEIS  20 // OS566 especifica no máximo 20 níveis
using namespace std;

#include "tuxfw.h"

struct DadosEntradaGetCamposNivel
{
    int idSubFormulario;
    int idNivel;
    int idDominioPai;
    const char *nmPath;
};

struct ProcessaDominioHierarquia
{
    int idSubFormulario;
    int idCampo;
    int idDominioPai;
    int idDominioFilho;
    string nmPath;
    string nmDominioPai;
    string nmDominioFilho;
};

typedef vector<ProcessaDominioHierarquia> VEC_PRODOMHIERARQUIA;
typedef map<int,int> MAP_INT;

class CAdmCamposDepend
{
    public:
        CAdmCamposDepend( char * idUsuarioPrm, DOMNode * dnode ,XMLGen * xml_g );
        void ListaCamposDependentes();
        void ListaComponentes();
        void InsereGrupo();
        void ListaGrupos();
        void InsereValor();
        void CamposValores();
        void ListaArvore();
        void ListaCamposNiveis();
        void ListaProximoNivel();
        void GravarArvore();
        void AtualizarCampos();
        void LupaGetCampo();

        int getIOperacao() { return iOperacao; }

    private:
        char nmGrupoPrm[256];
        char nmCampoPrm[2001];
        DOMNode* entrada;
        int idCampoPrm;
        int idContatoPrm;
        int idSubFormularioPrm;
        int idUsuario;
        int iNivel;
        int iOperacao;
        string nmSubFormularioPrm;
        string inOperacao;
        string nmPath;
        TuxHelper tx;
        XMLGen* saida;

    private:
        CAdmCamposDepend() {}
        bool ObterOperacao(const char *inOperacao);
        void CarregaParam();
};
