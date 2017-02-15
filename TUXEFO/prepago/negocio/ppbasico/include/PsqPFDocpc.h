///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase Pesquisa Dados de Pessoa Fisica Por Documento
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

// #ifndef PSQPFDOCPCH
// #define PSQPFDOCPCH

#include "PsqPFDocDdsCttpc.h"


class CPessoaFisicapc
{
public:
    bool proCBuscarPFContato(const char *ptIdPessoa,DadosPFContato *ptDadosPFContato,StatusPFContato *ptStatusPFContato);
    bool proCBuscarPFPorDoc(const char *ptIdPessoa,DadosPFDocumento *ptDadosPFDocumento,StatusPFDocumento *ptStatusPFDocumento);
    bool proCBuscarPFEMailCom(const char *ptIdPessoa,DadosPFEMailComercial *ptDadosPFEMailComercial,StatusPFEMailComercial *ptStatusPFEMailComercial);
    bool proCBuscarPFEMailPart(const char *ptIdPessoa,DadosPFEMailParticular *ptDadosPFEMailParticular,StatusPFEMailParticular *ptStatusPFEMailParticular);
    bool proCBuscarPFPorDocLinPos(DadosPessoaFisicaPorDoc *ptDadosPessoaFisicaPorDoc,StatusPessoaFisicaPorDoc *ptStatusPessoaFisicaPorDoc);
    bool proCBuscarPFPorDocLinPre(DadosPessoaFisicaPorDoc *ptDadosPessoaFisicaPorDoc,StatusPessoaFisicaPorDoc *ptStatusPessoaFisicaPorDoc);
};


//#endif // PSQPFDOCPCH
