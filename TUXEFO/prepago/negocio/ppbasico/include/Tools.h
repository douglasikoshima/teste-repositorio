///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Tools
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef TOOLSH
#define TOOLSH

void DesmembraNome(TDesmembraNome *ptDesmembraNome);
void NormalizaNome(char *pszString);
void RetiraMultiplosEspacos(char *pszString);
void alltrim(char *pszString);
void rtrim(char *pszString);
void ltrim(char *pszString);
bool DataMenorDataAtual(char *pszData);
char *TimeStamp(char *pszTimeStamp);
char *DataAtual(void);
char *DataHoraAtual(void);
bool isNumeric(char* pszString);
void TrataNrDocumento(char *pszNrDocumento);
void ConvDataFromJava(char *pszDataJava);
void upper(char *pTok);

#endif
