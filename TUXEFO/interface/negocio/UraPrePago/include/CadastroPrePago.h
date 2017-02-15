// CadastroPrePago.h: interface for the CadastroPrePago class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_CADASTROPREPAGO_H__9C33F574_1DD3_41D6_9F70_0599CC4B391D__INCLUDED_)
#define AFX_CADASTROPREPAGO_H__9C33F574_1DD3_41D6_9F70_0599CC4B391D__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"Common.h"
#include"Constant.h"
#include"Pessoa.h"
#include"PessoaComunicacao.h"
#include"PessoaDocumento.h"
#include"PessoaEndereco.h"
#include"PessoaFisica.h"
#include"PessoaLinha.h"
#include"ListaContato.h"

class CadastroPrePago  
{
public:
	CadastroPrePago();
	virtual ~CadastroPrePago();
	int gravar(stParametrosUren*buffer);
	int gravarCadastro(stParametrosUren*buffer);
	int gravarAlteracao(stParametrosUren*buffer);
	int gravarTrocaTitularidade(stParametrosUren*buffer);
    int gravarRecadastro(stParametrosUren*buffer);
	void setControle(bool controle);
	bool isControle();
	PessoaLinha m_pessoaLinha;
	void setTipoCadastro(int tipo);
	int getTipoCadastro();
	void setOperacao(int operacao);
	int getOperacao();
	void setInProtocolo(char*inProtocolo);
	void setInPromocoes(char*inPromocoes);
	void setInProdutos(char*inProdutos);
	void setInParceiros(char*inParceiros);

	char* getInProtocolo();
	char* getInPromocoes();
	char* getInProdutos();
	char* getInParceiros();

	char* getIdLinhaTelefonica();
	char* getIdPessoaDepara();

	void getDadosLinha();

	bool getInterceptacao();
	void setInterceptacao(bool interceptacao);

	bool getEstadoLinha();
	void setEstadoLinha(bool estadoLinha);

	void setIdCanal(char*idCanal);
	char*getIdCanal();
	
	int getTipoServico();
	void setTipoServico(int tipo);

protected:
	DOMNode* gerarXMLCadastro(XMLGen*xml);
	void gerarXMLPrincipal(XMLGen*xml,char*nomeTag);
	void gerarXMLDocumento(XMLGen*xml);
	void gerarXMLDoc(XMLGen*xml);
	void gerarXMLEndereco(XMLGen*xml);
	void gerarXMLContato(XMLGen*xml);
	void gerarXMLLinha(XMLGen*xml);
	void callRemoteService(DOMNode*dnode);
	stPessoaLinha*getPrimeiraPessoa(char*tipoLinha[],int sizeTipo,char*estadoLinha[],int sizeEstado);
	int validaCampoObrigatorio(char*idPessoa);
	int getPessoa(char*idPessoa);	
	void agruparPessoas();
	int gravarNGIN();
	int novoCadastro(stParametrosUren*buffer);

private:
	// attributes
	Pessoa m_pessoa;
	PessoaEndereco m_pessoaEndereco;
	PessoaComunicacao m_pessoaComunicacao;
	PessoaDocumento m_pessoaDocumento;
	PessoaFisica m_pessoaFisica;
	ListPessoaLinha m_listPessoaLinha;
	ListDocumento m_listDocumento;
	ListEnderecoPrePago m_listEnderecoPrePago;
	ListEnderecoPrePago m_listEnderecoPrePagoNovo;
	ListListaContato m_listListaContato;
	stPessoaFisica m_stPessoaFisica;
	ListPessoaLinha m_listPessoaLinhaGroup;
	stLinha m_stLinha;
	int m_regra;
	bool m_isControle;
	int m_tipoCadastro;
	int m_tipoServico;
	int m_operacao;
	// black list
	char m_inProtocolo[2];
	char m_inPromocoes[2];
	char m_inProdutos[2];
	char m_inParceiros[2];
	char m_idPessoaDepara[255];
	char m_idLinhaTelefonica[255];
	char m_idCanal[15];
	bool m_interceptacao;
	bool m_estadoLinha;
};

#endif // !defined(AFX_CADASTROPREPAGO_H__9C33F574_1DD3_41D6_9F70_0599CC4B391D__INCLUDED_)
