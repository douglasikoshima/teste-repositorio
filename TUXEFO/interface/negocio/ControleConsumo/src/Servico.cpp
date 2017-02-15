#include "Servico.h"

Servico::Servico()
{
	setNome("");
	setValor("");
}
Servico::Servico(char*nome,char*valor)
{
	memset(m_valor,0,MAX_SIZE);
	memset(m_valor,0,MAX_SIZE);
	setNome(nome);
	setValor(valor);
}
Servico::~Servico()
{

}
void Servico::setNome(char*nome)
{
	if(nome!=NULL)
		strcpy(m_nome,nome);
}

void Servico::setValor(char*valor)
{
	if(valor!=NULL)
		strcpy(m_valor,valor);
}
char* Servico::getNome()
{
	return this->m_nome;
}

char* Servico::getValor()
{
	return this->m_valor;
}