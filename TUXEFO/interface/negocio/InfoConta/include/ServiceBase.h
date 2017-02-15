#ifndef SERVICEBASE_H_HEADER_INCLUDED_BD9014C5
#define SERVICEBASE_H_HEADER_INCLUDED_BD9014C5
#include "XenosPlugin.h"
#include "ServiceAtlys.h"
#include <string>


struct stCommonFields
{
	string out;
	int docType;
	char*cDocType;
	char*canal;
	char*conta;
	char*data;
	char*retorno;
	char*accessNbr;
	char outputPathXenos[255+1];
};

class ServiceBase
{
  public:
    // executar a regra de negócio
    virtual int execute(DOMNode* dnode, XMLGen* xmlgen)=0;

    // setar o xml de entrada
    virtual void setXMLIn(DOMNode* dnode);
    // finalizar o objeto retornado
    virtual void finalize();
	// Colocar a operação
	virtual void setOperation(int);
	// retorna uma instancia de ServiceBase
	static ServiceBase* getInstance(int);
	// procedimentos comuns para todos os ServiceBase
	int exec(stCommonFields &field,XMLGen* xmlgen);
	// Recuperar uma imagem de saída do xenos sem aguardar
	int getImagemXenosSemEspera(stCommonFields &field,char* pathOutput,int typeRetorno=1);
	// copiar o usuario da operacao
	void setUser(char*);
	// recupera o nome de arquivo de um caminho
	int getNomeArquivo(char* nomeImagem,char* nmPath);

  protected:
    // faz o decode da imagem
    void decodeImage();

  protected:
    // operação que está sendo executada
    int m_operation;

    // XMLGen de entrada dos serviço tuxedo
    XMLGen* m_xmlgen;

    // Plugin do xenos para o tratamento
    XenosPlugin m_xenos;

    // Classe para chamada de APIS do atlys. regra de negócio
    ServiceAtlys m_service;

	// Dom node de entrada
	DOMNode* m_dnode;

	// gerenciador de memoria
	CMemoryManager m_mem;

	TuxHelper tuxhp;

};



#endif /* SERVICEBASE_H_HEADER_INCLUDED_BD9014C5 */
