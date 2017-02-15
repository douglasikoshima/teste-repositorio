#include "CLeitorArquivo.h"



CLeitorArquivo::CLeitorArquivo()
{
	m_istatus = CONTRUTOR_PADRAO;

}

CLeitorArquivo::CLeitorArquivo(VEC_STRING &vec_cabecalho, const string &sArquivoCaminho, const string &sArquivoNome, const string &sSeparador )
{
	m_istatus = 0;
	memset(m_cLinhaArquivoTemp,0,MAX_COMPRIMENTO_LINHA_ARQUIVO-1);
	m_pFile = NULL;

	if( vec_cabecalho.size() == 0 )
	{
		return;
	}

	if( m_mapregistros.size() != 0)
	{
		m_mapregistros.clear();
	}
	
	for( int iIndice = 0; iIndice < vec_cabecalho.size(); ++iIndice)
	{
		VEC_STRING vec_coluna;
		m_mapregistros.insert(pair<string,VEC_STRING>(vec_cabecalho[iIndice],vec_coluna));
	}
	m_veccabecalho.insert(m_veccabecalho.begin(), vec_cabecalho.begin(), vec_cabecalho.end());
	m_istatus += COLUNAS_DEFINIDAS;

	if( sArquivoCaminho.length() != 0 )
	{
		m_sArquivoCaminho = sArquivoCaminho;
		m_istatus += CAMINHOARQUIVO_OK;
	}

	if( sArquivoNome.length() != 0 )
	{
		m_sArquivoNome = sArquivoNome;
		m_istatus += NOMEARQUIVO_OK;
	}

	if( sSeparador.length() != 0 )
	{
		m_sSeparador = sSeparador;
		m_istatus += SEPARADORAQUIVO_OK;
	}

	string sCaminhoArquivoCompleto = m_sArquivoCaminho + m_sArquivoNome;
	
	//ABRIR ARQUIVO
	m_pFile = fopen(sCaminhoArquivoCompleto.c_str(),"r");
	if(m_pFile != NULL)
	{
		m_istatus += ARQUIVOABERTO_OK;
	}

	return;
}

CLeitorArquivo::~CLeitorArquivo()
{
	if(m_pFile != NULL)
	{
		fclose(m_pFile);
	}

	Clear();
}

const VEC_STRING& CLeitorArquivo::operator[](const string &sCampo)
{

	static VEC_STRING vecTemp;

	
	MAP_ARQUIVO::iterator it = m_mapregistros.find(sCampo);
	if( it != m_mapregistros.end() )
	{
		return m_mapregistros[sCampo];
	}

	return vecTemp;

}

string CLeitorArquivo::ObterLinha(int iIndex)
{
	
	MAP_ARQUIVO::iterator itCur = m_mapregistros.begin();
	MAP_ARQUIVO::iterator itEnd = m_mapregistros.end();

	string strLinha;
	strLinha = "";
	
	if( itCur == itEnd )
	{
		return strLinha;
	}

	if( iIndex > m_mapregistros[(*itCur).first].size() )
	{
		return strLinha;
	}

	for( int jIndiceHeader = 0; jIndiceHeader < m_veccabecalho.size() ; ++jIndiceHeader )
	{
		strLinha += m_mapregistros[m_veccabecalho[jIndiceHeader]][iIndex];  
		
		if( jIndiceHeader+1 < m_veccabecalho.size()  )
		{
			strLinha += m_sSeparador;
		}
	}

	return strLinha;
}

ErrosLeitorArquivo CLeitorArquivo::LerArquivo(int &iNumeroLinhasLido, const int iNumeroLinhas)
{

	int iLinhas = iNumeroLinhas;
	iNumeroLinhasLido = 0;
	
	//VERIFICA ARQUIVO ABERTO
	if( m_pFile == NULL )
	{
		return ERRO_ABRIR_ARQUIVO;
	}

	Clear();

	if( MAX_NUM_LINHAS_ARQUIVO < iLinhas)
	{
		iLinhas = MAX_NUM_LINHAS_ARQUIVO;
	}


	for( ;m_cLinhaArquivoTemp != NULL && (iNumeroLinhasLido < iLinhas ) && feof(m_pFile) == 0; )
	{	
		// LER UMA LINHA
		memset(m_cLinhaArquivoTemp,0,MAX_COMPRIMENTO_LINHA_ARQUIVO);
		fgets(m_cLinhaArquivoTemp,MAX_COMPRIMENTO_LINHA_ARQUIVO-1,m_pFile);

		if( m_cLinhaArquivoTemp != NULL )
		{
			// QUEBRAR A LINHA EM COLUNAS
			string sLinha = m_cLinhaArquivoTemp;
			VEC_STRING vecResultados;
			Splitstring(sLinha,m_sSeparador,vecResultados);
			
			if( vecResultados.size() != m_mapregistros.size() )
			{
				return ERRO_QTD_DE_COLUNAS_INCOMPATIVEL;
			}

			Insert(vecResultados);

			++iNumeroLinhasLido;
		}
	}
	return OK_SUCESSO;
}


void CLeitorArquivo::Splitstring(const string &strConteudo, const string &sDelim, VEC_STRING &vecResultados)
{
	int cutAt;
	string sTemp (strConteudo);

	while( (cutAt = sTemp.find_first_of(sDelim)) != sTemp.npos )
	{
		if(cutAt > 0)
		{
			vecResultados.push_back(sTemp.substr(0,cutAt));
		}
		else
		{
			string strVazia;
			vecResultados.push_back(strVazia);
		}


		sTemp = sTemp.substr(cutAt + sDelim.length());
	}

	if(sTemp.length() > 0)
	{
		vecResultados.push_back(sTemp);
	}
	else
	{
		string strVazia;
		vecResultados.push_back(strVazia);
	}
}


void CLeitorArquivo::Insert(VEC_STRING &vecResultadosLinha)
{
	
	if( vecResultadosLinha.size() != m_mapregistros.size() )
	{
		return;
	}

	for( int iIndice = 0; iIndice < m_veccabecalho.size() ; ++iIndice )
	{
		m_mapregistros[m_veccabecalho[iIndice]].push_back(vecResultadosLinha[iIndice]);  
	}
}

void CLeitorArquivo::Clear()
{
	if( m_mapregistros.size() != 0)
	{
		m_mapregistros.clear();
	}
	
	for( int iIndice = 0; iIndice < m_veccabecalho.size(); ++iIndice)
	{
		VEC_STRING vec_coluna;
		m_mapregistros.insert(pair<string,VEC_STRING>(m_veccabecalho[iIndice],vec_coluna));
	}
}