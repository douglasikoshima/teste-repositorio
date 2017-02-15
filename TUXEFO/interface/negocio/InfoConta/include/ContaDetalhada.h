#ifndef CONTADETALHADA_H_HEADER_INCLUDED_BD9057C1
#define CONTADETALHADA_H_HEADER_INCLUDED_BD9057C1
#include "ServiceBase.h"
#include "ImagemConta.h"

class ContaDetalhada : public ServiceBase
{
public:
	ContaDetalhada();
	~ContaDetalhada();
	// executar a regra de negócio
    virtual int execute(DOMNode* dnode, XMLGen* xmlgen);
	int gerarSaidaXML(char*buffer,char*buffer2,XMLGen* xmlgen);
	int gerarSaidaXMLFile(char*buffer,char*buffer2,XMLGen* xmlgen);
	int gerarSaidaHTML(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*nomeArquivo);
	int gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen);
	string mergeString(string out,string out2);
public:
	// atributos
};



#endif /* CONTADETALHADA_H_HEADER_INCLUDED_BD9057C1 */
