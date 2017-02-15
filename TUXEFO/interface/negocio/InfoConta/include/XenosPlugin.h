#ifndef INCLUDE_XENOSPLUGIN1_H_HEADER_INCLUDED_BD9067CC
#define INCLUDE_XENOSPLUGIN1_H_HEADER_INCLUDED_BD9067CC
#include "CFileXenos.h"
#include "XenosPluginBase.h"
#include <xercesc/util/Base64.hpp>
#include "Util.h"
#include <wchar.h>
#include <time.h>

#define SIZE_FILE_PATH 255

class XenosPlugin : public XenosPluginBase
{
  public:
	XenosPlugin();
	~XenosPlugin();

	// retorna a imagem
    string getImage(CFileXenos *file,int typeRetorno,int typeImagem,int fase);

	// retorna a imagem de saída do xenos dado um determinado caminho da fila de saída do xenos
	string getImageXenos(char* pathOutput,int typeRetorno, int typeImage, int fase);

	// Dá permissão do arquivo de input do xenos
	int setInputFilePermission(char*inputFile);

    // escreve no arquivo temporário, retorna o número de bytes escritos no arquivo
    int decodeFile(CFileXenos*encodedFile,FILE*decodedFile);

	// decodifica uma imagem do atlys
	int decodeString(char*buffer,FILE*file);

	// Coloca Buffer em arquivo
	CFileXenos* writeBuffer(char*buffer,char*path,int typeFile);

	// faz o decode de base64 e retorna um int
	int decodeBase64(char*buffer,FILE*file);

	// Retorna um ponteiro para o arquivo temporário
	char*getPathFile();

	// Retorna o nome do arquivo gravado
	char*getNameTempFile();

	// set o nome do arquivo
	void setNameTempFile(char*name);

	// Setar a conta corrente
	void setConta(char*conta);

	// Retorna a conta
	char*getConta();

	// retorna o numero randomico das filas
	int getRandNumber();

	// seta o numero randomico das filas
	void setRandNumber(int randNumber);

	// gera o numero randomico para as filas
	void generateRandNumber();

	// tentar ler um arquivo no path até retornar conteudo para o arquivo em n segundos
	string waitToReadFile(char*path,int segundos);

	// remove os arquivos que não serão mais utilizados
	int removeFiles();

  private:
	char *m_cbuffer;
	char m_pathTempFile[300];
	char m_nameTempFile[255];
	int m_randNumber;
	char m_pathAFP[SIZE_FILE_PATH];			// caminho do arquivo AFP
	char m_pathHTML[SIZE_FILE_PATH];		// caminho do arquivo HTML
	char m_pathCSS[SIZE_FILE_PATH];			// caminho do arquivo CSS
	char m_pathOK[SIZE_FILE_PATH];			// caminho do arquivo ok
  public:
	char m_conta[256];

};



#endif /* INCLUDE_XENOSPLUGIN1_H_HEADER_INCLUDED_BD9067CC */
