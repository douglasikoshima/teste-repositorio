/***************************************************************************************************
 *--Uteis-Commons--CLeitorArquivo.h--Junho/2010
 *  Classe para leitura de arquivos com conteudo utilizando separadores. Após lido, dispõem os dados 
 *amigavelmente utilizando stl e mecanismo operator[] C++.
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Wakim B. Saba
 * @version $Revision: 1.1.4.2 $
 **/
 
//xlC -g -o CLeitorArquivo CLeitorArquivo.cpp main.cpp

#ifndef __CLEITORARQUIVO_H__
#define __CLEITORARQUIVO_H__

#include <string>
#include <iostream>
#include <vector>
#include <map>

using namespace std;

typedef vector <string> VEC_STRING;
typedef map<string,VEC_STRING> MAP_ARQUIVO;

enum ErrosLeitorArquivo
{
	ERRO_COLUNAS_NAO_DEFINIDAS = 0,
	ERRO_LER_ARQUIVO = 1,
	ERRO_ABRIR_ARQUIVO = 2,
	ERRO_QTD_DE_COLUNAS_INCOMPATIVEL = 3,
	OK_SUCESSO = 4

};

enum StatusLeitorArquivo
{
	CONTRUTOR_PADRAO = 0,
	COLUNAS_DEFINIDAS = 1,
	CAMINHOARQUIVO_OK = 2,
	NOMEARQUIVO_OK = 4,
	SEPARADORAQUIVO_OK = 8,
	ARQUIVOABERTO_OK = 16,
	ARQUIVO_LIDO = 1024

};

const int MAX_NUM_LINHAS_ARQUIVO = 500;
const int MAX_COMPRIMENTO_LINHA_ARQUIVO = 500;

class CLeitorArquivo
{
	public:
		VEC_STRING m_veccabecalho; 
		MAP_ARQUIVO m_mapregistros; 
		CLeitorArquivo(); 
		CLeitorArquivo(VEC_STRING &vec_cabecalho, const string &sCaminhoArquivo, const string &sArquivoNome, const string &sSeparador );
		virtual ~CLeitorArquivo(); 

		ErrosLeitorArquivo LerArquivo(int &iNumeroLinhasLido, const int iNumeroLinhas = MAX_NUM_LINHAS_ARQUIVO);
		string ObterLinha(int iIndex);
		const VEC_STRING& operator [] (const string &);

	private:

		int m_istatus;
		string m_sArquivoCaminho;
		string m_sArquivoNome;
		string m_sSeparador;
		char m_cLinhaArquivoTemp[MAX_COMPRIMENTO_LINHA_ARQUIVO];
		FILE *m_pFile;

		void Splitstring(const string &strConteudo, const string &sDelim, VEC_STRING &vecResultados);
		void Clear();
		void Insert(VEC_STRING&);
};

#endif