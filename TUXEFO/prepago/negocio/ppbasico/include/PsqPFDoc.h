///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase Pesquisa Dados de Pessoa Fisica Por Documento
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PSQPFDOCH
#define PSQPFDOCH

#include <tuxfw.h>
#include <Global.h>

class CPesquisaPFPorDoc : public TuxHelper
{

public:
    DOMNode* dnode;
    XMLGen*  xml_g;
    char szMessageException[LEN_RETURN_MESSAGE + LEN_EOS];

public:
    CPesquisaPFPorDoc() {dnode=0;xml_g=0;}
    CPesquisaPFPorDoc(DOMNode*dnode,XMLGen*xml_g) {this->dnode=dnode;this->xml_g=xml_g;}

    void Executar();

private:
    void BuscarDadosPessoaFisica(const char *pszNrDocumento,const char *pszIdTipoDocumento);
    void BuscarDadosPessoaJuridica(const char *pszNrDocumento,const char *pszIdTipoDocumento);
    void removerPontuacaoNrDocumento(char *pszNrDocumento);

};

#endif // PSQPFDOCH
