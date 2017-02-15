// ListaContato.h: interface for the ListaContato class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LISTACONTATO_H__82B4A185_C555_4901_9711_47058387D3D8__INCLUDED_)
#define AFX_LISTACONTATO_H__82B4A185_C555_4901_9711_47058387D3D8__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"../../commons/include/vectorlist.h"

class ListaContato  
{
public:
	ListaContato();
	virtual ~ListaContato();
	char*getIdContato();
	char*getIdTipoContatoSelecionado();
	char*getDsContato();
	char*getNmContato();
	void setIdContato(char*);
	void setIdTipoContatoSelecionado(char*);
	void setDsContato(char*);
	void setNmContato(char*);
	void setNovoCadastro(bool novoCadastro);
	bool getNovoCadastro();
private:
	// atributos
	char idContato[21];
	char idTipoContatoSelecionado[21];
	char dsContato[256];
	char nmContato[256];
	bool novoCadastro;
};

typedef CVectorList<ListaContato> ListListaContato;

#endif // !defined(AFX_LISTACONTATO_H__82B4A185_C555_4901_9711_47058387D3D8__INCLUDED_)
