// PessoaAbstract.hpp: interface for the CPessoaAbstract class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_PESSOAABSTRACT__
#define __C_PESSOAABSTRACT__

class CSenha;
class CLojista;

class CPessoaAbstract
{

public:

	enum PessoaType
	{
		TP_USUARIO = 1,
		TP_LOJISTA = 2,
		TP_CLIENTE = 3
	};

	CPessoaAbstract();
	virtual ~CPessoaAbstract();

	static CPessoaAbstract *CriaPessoa(PessoaType tpPessoa);

	// Métodos de acesso aos atributos
 	// Getters
	int        getIdPessoa() const;
	int        getIdPessoaDePara() const;
	char      *getNmPessoa(char *cNmPessoa) const;
	int        getIdTipoRelacionamento() const;
	char      *getSgTipoRelacionamento(char *cSgTipoRelacionamento) const;
	PessoaType getTipoPessoa() const;
	CSenha    *getSenha();
	
	// Setters
	void       setIdPessoa(int value);
	void       setIdPessoaDePara(int value);
	void       setNmPessoa(char *value);
	void       setIdTipoRelacionamento(int value);
	void       setSgTipoRelacionamento(char *value);
	
	//
	CLojista  *AsLojista();

protected:

	// Atributos de Negocio
 	int        m_iIdPessoa;
	int        m_iIdPessoaDePara;
	char       m_cNmPessoa[256];
	int        m_iIdTipoRelacionamento;
	char       m_cSgTipoRelacionamento[2];

	void       setTipoPessoa(PessoaType tpPessoa);

private:

	// Indentificador de tipo instanciado
	PessoaType eTipoPessoa;

	CSenha     *m_pSenha;

};

#endif //__C_PESSOAABSTRACT__