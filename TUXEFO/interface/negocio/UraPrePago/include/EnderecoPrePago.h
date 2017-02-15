// EnderecoPrePago.h: interface for the EnderecoPrePago class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ENDERECOPREPAGO_H__B0545A62_2697_4CE3_98EA_35DE4DBD75BA__INCLUDED_)
#define AFX_ENDERECOPREPAGO_H__B0545A62_2697_4CE3_98EA_35DE4DBD75BA__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"Util.h"
#include"../../commons/include/vectorlist.h"

class EnderecoPrePago  
{
public:
	EnderecoPrePago();
	virtual ~EnderecoPrePago();

	void setIdEndereco(char*);
	void setInAtualEndereco(char*);
	void setIdTipoEnderecoSelecionado(char*);
	void setDsTituloLogradouro(char*);
	void setDsTipoLogradouro(char*);
	void setDsLogradouro(char*);
	void setNrEndereco(char*);
	void setDsComplementoEndereco(char*);
	void setDsBairro(char*);
	void setDsMunicipio(char*);
	void setNrCEP(char*);
	void setIdUFEndereco(char*);
	void setDsUFEndereco(char*);
	void setIdPaisEndereco(char*);
	void setDsPaisEndereco(char*);
	void setInEnderecoSujo(int);

	char* getIdEndereco();
	char* getInAtualEndereco();
	char* getIdTipoEnderecoSelecionado();
	char* getDsTituloLogradouro();
	char* getDsTipoLogradouro();
	char* getDsLogradouro();
	char* getNrEndereco();
	char* getDsComplementoEndereco();
	char* getDsBairro();
	char* getDsMunicipio();
	char* getNrCEP();
	char* getIdUFEndereco();
	char* getDsUFEndereco();
	char* getIdPaisEndereco();
	char* getDsPaisEndereco();
	int getInEnderecoSujo();
	
	
private:
	char idEndereco[21+1];
	char inAtualEndereco[21+1];
	char idTipoEnderecoSelecionado[21+1];
	char dsTituloLogradouro[256];
	char dsTipoLogradouro[256];
	char dsLogradouro[256];
	char nrEndereco[256];
	char dsComplementoEndereco[256];
	char dsBairro[256];
	char dsMunicipio[256];
	char nrCEP[256];
	char idUFEndereco[21+1];
	char dsUFEndereco[256];
	char idPaisEndereco[21+1];
	char dsPaisEndereco[256];
	int inEnderecoSujo;
};

typedef CVectorList<EnderecoPrePago> ListEnderecoPrePago;

#endif // !defined(AFX_ENDERECOPREPAGO_H__B0545A62_2697_4CE3_98EA_35DE4DBD75BA__INCLUDED_)
