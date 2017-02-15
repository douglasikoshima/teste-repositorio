// ImagemConta.h: interface for the ImagemConta class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_IMAGEMCONTA_H__75AEFBEB_ED69_499A_BA3B_FF895EB794FC__INCLUDED_)
#define AFX_IMAGEMCONTA_H__75AEFBEB_ED69_499A_BA3B_FF895EB794FC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<tuxfw.h>
#include"Util.h"

struct stImagemConta{
	char idImagemConta[21+1];
	char idConta[21+1];
	char idContaSistemaOrigem[255+1];
	char idTipoImagem[21+1];
	char sgTipoImagem[2+1];
	char dtFimCiclo[10+1];
	char nmImagem[255+1];
	char tipoImagem[2+1];
	int inXml;
	char nmPathDetalhada[255+1];
	char nmPathResumida[255+1];
	char nmPathBoleto[255+1];
	int inProcessado;
};

class ImagemConta  
{
public:
	ImagemConta();
	virtual ~ImagemConta();
	int getMinutoParametro();
	bool selectImagemConta(struct stImagemConta &stDados);
	bool salvarDados();
	// chamar serviço SETIMAGEM conta para gravar registro
	bool callProcessamento(int processado);
	// chamar serviço para remover imagem
	bool callRemoverImagem();
	// remover registro
	bool removerRegistro(struct stImagemConta &stDados);
	// método antigo para gravação de imagem em cache
	bool salvarDados(struct stImagemConta &stDados);
	// gravar registro em cache ou em processamento
	bool gravarRegistro(struct stImagemConta &stDados, int processado);
	bool getParametroCache();
	int getAreaRegistro(char*cdAreaRegistro,char*sgUf);
	static int getDataFase();
	static int getURLImagem(char*url);
	static int consultarTipoPessoa(int nrLinha, int cdAreaRegistro, int intTipoRelacionamento);
private:	
	int selectIdContaSistemaOrigem();
	int deleteImagemConta();
	int insertImagemConta();
private:
	struct stImagemConta *m_stDados;

};

#endif // !defined(AFX_IMAGEMCONTA_H__75AEFBEB_ED69_499A_BA3B_FF895EB794FC__INCLUDED_)
