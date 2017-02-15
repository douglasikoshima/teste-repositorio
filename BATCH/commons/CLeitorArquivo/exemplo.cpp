#include <stdio.h>
#include <iostream>
#include "CLeitorArquivo.h"
#include <string>
#include <vector>

int main(int argc, char* argv[])
{

	printf("...INICIO...\n");

	// 1 - CRIA UM VETOR COM O NOME DAS COLUNAS:
	VEC_STRING vecString;
	vecString.push_back(string("Telefone"));
	vecString.push_back(string("CodigoA"));
	vecString.push_back(string("Data"));
	vecString.push_back(string("CodigoB"));

	
	// 2 - DEFINE O CAMINHO EM QUE ESTA O ARQUIVO A SER LIDO
	// string sCaminho = "D:\\Working\\temp\\tools\\FuncaoGenerica\\Main\\arquivos\\";
	string sCaminho = "/home/wsaba/CLeitorArquivo/arquivos/";
						 
					   
	// 3 - DEFINE O NOME DO ARQUIVO A SER LIDO
	string sArquivo = "teste002.txt";
	
	// 4 - DEFINE QUAL O TIPO DE SEPARADOR DE DADOS ESPERADO
	string sSeparador = ";";

	
	// 5 - INSTANCIA O OBJETO CLEITORARQUIVO
	CLeitorArquivo leitorArq(vecString,sCaminho,sArquivo,sSeparador);


	
	int iNumeroLinhasLido = 0;
	// 6 - DEFINE UM TAMANHO DO BLOCO DE LINHAS QUE DEVE SER LIDO POR VEZ.
	int iTamanhoDoBloco = 10;

	int iCountLine=0;

	cout<<"\n";
	cout<<"Telefone|";
	cout<<"CodigoA|";
	cout<<"Data|";
	cout<<"CodigoB";
	cout<<"\n";

	// 6 - DEFINE UM LOOP DE TAMANHO J, ONDE J REPRESENTA QUANTAS VEZES SERA LIDO UM BLOCO DE TAMANHO 'iTAMANHODOBLOCO'
	for( int j=0; j<10 ; ++j)
	{
		leitorArq.LerArquivo(iNumeroLinhasLido, iTamanhoDoBloco);
		if( iNumeroLinhasLido > 0)
		{
			// 7 - EXIBE O CONTEUDO DE CADA LINHA LIDO NO BLOCO CORRENTE
			for( int i=0; i< iNumeroLinhasLido ; ++i)
			{
				cout<<leitorArq["Telefone"][i]<<"|";
				cout<<leitorArq["CodigoA"][i]<<"|";
				cout<<leitorArq["Data"][i]<<"|";
				cout<<leitorArq["CodigoB"][i];
			}
		}
		else
		{
			cout<<"\n Numero de Linhas <= Zero \n";
		}
		iCountLine +=iNumeroLinhasLido;
	}
	cout<<"\n Numero de Linhas Lidas = "<<iCountLine<<"\n";
	//system("pause");
	
	// 8 - QUANDO TERMINAR O ESCOPO O OBJETO 'CLEITORARQUIVO' CHAMA O DESTRUTOR QUE FECHA O ARQUIVO LIDO.
	
	printf("...FIM...\n");

	return 0;
}

