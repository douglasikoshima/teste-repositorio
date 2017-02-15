#ifndef CONTADETALHADALOCAL_H_HEADER_INCLUDED_BD9057C1
#define CONTADETALHADALOCAL_H_HEADER_INCLUDED_BD9057C1
#include "ServiceBase.h"
#include "ImagemConta.h"
#include "ContaDetalhada.h"

class ContaDetalhadaLocal : public ServiceBase
{
public:
	ContaDetalhadaLocal();
	~ContaDetalhadaLocal();
	// executar a regra de negócio
    virtual int execute(DOMNode* dnode, XMLGen* xmlgen);
	int gerarSaidaXML(char*buffer,XMLGen* xmlgen);
	int gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen);
	int gerarSaidaHTML(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*conta);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*conta,char*nomeArquivo);
	int gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen);
public:
	// atributos
};



#endif /* CONTADETALHADALOCAL_H_HEADER_INCLUDED_BD9057C1 */
