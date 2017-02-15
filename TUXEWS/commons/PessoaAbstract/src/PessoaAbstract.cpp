// PessoaAbstract.cpp: implementation of the CPessoaAbstract class.
//
//////////////////////////////////////////////////////////////////////

#include <string.h>
#include <Defines/Defines.h>
#include <Senha/Senha.hpp>
#include <PessoaAbstract/PessoaAbstract.hpp>
#include <Lojista/Lojista.hpp>

CPessoaAbstract::CPessoaAbstract()
{
	this->setIdPessoa(-1);
	this->setIdPessoaDePara(-1);
	this->setNmPessoa("");
	this->setIdTipoRelacionamento(-1);
	this->setSgTipoRelacionamento("");

	m_pSenha = NULL;
}

CPessoaAbstract::~CPessoaAbstract()
{
	if(NULL != m_pSenha)
		delete m_pSenha;
}

CPessoaAbstract *CPessoaAbstract::CriaPessoa(PessoaType tpPessoa)
{
	CPessoaAbstract *o_Pessoa;

	if(TP_LOJISTA == tpPessoa)
		o_Pessoa = new CLojista;

	o_Pessoa->setTipoPessoa(tpPessoa);

	return o_Pessoa;
}

// Getters
int CPessoaAbstract::getIdPessoa() const
{
	return this->m_iIdPessoa;
}

int CPessoaAbstract::getIdPessoaDePara() const
{
	return this->m_iIdPessoaDePara;
}

char *CPessoaAbstract::getNmPessoa(char *cNmPessoa) const
{
	strcpy(cNmPessoa, this->m_cNmPessoa);
	return cNmPessoa;
}
	
int CPessoaAbstract::getIdTipoRelacionamento() const
{
	return this->m_iIdTipoRelacionamento;
}

char *CPessoaAbstract::getSgTipoRelacionamento(char *cSgTipoRelacionamento) const
{
	strcpy(cSgTipoRelacionamento, this->m_cSgTipoRelacionamento);
	return cSgTipoRelacionamento;
}

CPessoaAbstract::PessoaType CPessoaAbstract::getTipoPessoa() const
{
	return this->eTipoPessoa;
}

CSenha *CPessoaAbstract::getSenha()
{
	if(NULL == m_pSenha)
		m_pSenha = new CSenha(this);

	return m_pSenha;
}

CLojista *CPessoaAbstract::AsLojista()
{
	return static_cast <CLojista *> (this);
}

// Setters
void CPessoaAbstract::setIdPessoa(int value)
{
	this->m_iIdPessoa = value;
}

void CPessoaAbstract::setIdPessoaDePara(int value)
{
	this->m_iIdPessoaDePara = value;
}

void CPessoaAbstract::setNmPessoa(char *value)
{
	ASSERT_SET_CHAR(this->m_cNmPessoa, value);

	strcpy(this->m_cNmPessoa, value);
}

void CPessoaAbstract::setIdTipoRelacionamento(int value)
{
	this->m_iIdTipoRelacionamento = value;
}

void CPessoaAbstract::setSgTipoRelacionamento(char *value)
{
	ASSERT_SET_CHAR(this->m_cSgTipoRelacionamento, value);

	strcpy(this->m_cSgTipoRelacionamento, value);
}

void CPessoaAbstract::setTipoPessoa(PessoaType tpPessoa)
{
	this->eTipoPessoa = tpPessoa;
}