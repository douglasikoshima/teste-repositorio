#ifndef CONTARESUMIDA_H_HEADER_INCLUDED_BD901AD3
#define CONTARESUMIDA_H_HEADER_INCLUDED_BD901AD3
#include "ServiceBase.h"
#include "ImagemConta.h"

class ContaResumida : public ServiceBase
{
public:
	ContaResumida();
	~ContaResumida();
	// executar a regra de negócio
    virtual int execute(DOMNode* dnode, XMLGen* xmlgen);
	int gerarSaidaXML(char*buffer,XMLGen* xmlgen);
	int gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen);
	int gerarSaidaHTML(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*nomeArquivo);
	int gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen);
public:
	// atributos
};



#endif /* CONTARESUMIDA_H_HEADER_INCLUDED_BD901AD3 */
