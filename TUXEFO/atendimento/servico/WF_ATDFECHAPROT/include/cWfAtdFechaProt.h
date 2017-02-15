/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../../../commons/definesAtendimento.h"
#include "cWfAtdFechaProtPC.h"

class cWfAtdFechaProt : public TuxBasicSvc
{
    public:
        DOMNode *entrada;
        XMLGen *saida;
        XMLGen xmlGen;
        cWfAtdFechaProtPC cwfAtdFechaProtpc;

        TuxRemoteService rc;
        TuxMessage  tmIn;
        TuxMessage *ptmOut;

        st_DadosEntrada dados;
        st_StatusEntrada status;

    public:

        cWfAtdFechaProt(DOMNode *dnode,XMLGen *xml_g) {saida = xml_g;entrada = dnode;}
        ~cWfAtdFechaProt() {}

        char *getIdAtendimentoProtocolo() {return dados.idAtendimentoProtocolo;}
        bool idAtendimentoProtocoloVazioSimNao() {return dados.idAtendimentoProtocolo[0]==0?true:false;}

        void setUser(const char *dado) { SAFE_STRNCPY(idPessoaUsuarioWeb,dado); }
        const char *getUser() { return idPessoaUsuarioWeb; }

    private:
        char idPessoaUsuarioWeb[39];

    public:
        void Executar();

    private:
        void CarregarDados();
};
