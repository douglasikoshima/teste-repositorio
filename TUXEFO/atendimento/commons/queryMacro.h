
#include <stdio.h>


#ifdef WIN32
#   include <stdlib.h>
#endif

#ifndef QUERYMACRO
	#define QUERYMACRO

#define TIPO_INT                     1
#define TIPO_CHAR                    2
#define TIPO_DATA                    3
#define TIPO_DATAHORA                4
#define TIPO_HORA                    5
#define TIPO_ULONG                   6
#define TIPO_FLOAT                   7
#define TIPO_LONG                    8

#define WHERE_INT                    1
#define WHERE_CHAR                   2
#define WHERE_DATA                   3
#define WHERE_DATAHORA               4
#define WHERE_HORA                   5
#define WHERE_INT_MAIOR              6
#define WHERE_CHAR_MAIOR             7
#define WHERE_DATA_MAIOR             8
#define WHERE_DATAHORA_MAIOR         9
#define WHERE_HORA_MAIOR            10
#define WHERE_INT_MENOR             11
#define WHERE_CHAR_MENOR            12
#define WHERE_DATA_MENOR            13
#define WHERE_DATAHORA_MENOR        14
#define WHERE_HORA_MENOR            15
#define WHERE_INT_MAIORIGUAL        16
#define WHERE_CHAR_MAIORIGUAL       17
#define WHERE_DATA_MAIORIGUAL       18
#define WHERE_DATAHORA_MAIORIGUAL   19
#define WHERE_HORA_MAIORIGUAL       20
#define WHERE_INT_MENORIGUAL        21
#define WHERE_CHAR_MENORIGUAL       22
#define WHERE_DATA_MENORIGUAL       23
#define WHERE_DATAHORA_MENORIGUAL   24
#define WHERE_HORA_MENORIGUAL       25
#define WHERE_CHAR_LIKE             26
#define WHERE_IS_NOT_NULL           27
#define WHERE_CHAR_LIKE_UPPER       28
#define WHERE_ULONG                 29
#define WHERE_ULONG_MAIOR           30
#define WHERE_ULONG_MAIORIGUAL      31
#define WHERE_ULONG_MENOR           32
#define WHERE_ULONG_MENORIGUAL      33
#define WHERE_FLOAT                 34
#define WHERE_LONG                  35

#define montaUpdate(dado, valor, tipo) \
{ \
	if (separa) \
		sprintf(query,"%s ,", query); \
	if (tipo == TIPO_INT) \
		sprintf(query, "%s %s = %i ", query, dado, valor); \
	else if (tipo == TIPO_LONG) \
		sprintf(query, "%s %s = %ld ", query, dado, valor); \
	else if (tipo == TIPO_ULONG) \
		sprintf(query, "%s %s = %lu ", query, dado, valor); \
	else if (tipo == TIPO_FLOAT) \
		sprintf(query, "%s %s = %f ", query, dado, valor); \
	else if (tipo == TIPO_CHAR)\
		sprintf(query, "%s %s = '%s' ", query, dado, valor); \
	else if (tipo == TIPO_DATA)\
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == TIPO_DATAHORA)\
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY HH24:MI:SS') ", query, dado, valor); \
	else if (tipo == TIPO_HORA)\
		sprintf(query, "%s %s = TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	separa = true; \
}

#define montaWhere(dado, valor, tipo) \
{ \
	if (!separa) \
		sprintf(query,"%s WHERE ", query); \
	if (separa) \
		sprintf(query,"%s AND ", query); \
	if (tipo == WHERE_INT) \
		sprintf(query, "%s %s = %i ", query, dado, valor); \
	else if (tipo == WHERE_LONG) \
		sprintf(query, "%s %s = %ld ", query, dado, valor); \
	else if (tipo == WHERE_ULONG) \
		sprintf(query, "%s %s = %lu ", query, dado, valor); \
	else if (tipo == WHERE_FLOAT) \
		sprintf(query, "%s %s = %f ", query, dado, valor); \
	else if (tipo == WHERE_CHAR) \
		sprintf(query, "%s %s LIKE '%s' ", query, dado, valor); \
	else if (tipo == WHERE_DATA) \
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == WHERE_DATAHORA) \
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY HH24:MI:SS') ", query, dado, valor); \
	else if (tipo == WHERE_HORA) \
		sprintf(query, "%s %s = TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == WHERE_INT_MAIOR) \
		sprintf(query, "%s %s > %i ", query, dado, valor); \
	else if (tipo == WHERE_ULONG_MAIOR) \
		sprintf(query, "%s %s > %lu ", query, dado, valor); \
	else if (tipo == WHERE_CHAR_MAIOR) \
		sprintf(query, "%s %s > '%s' ", query, dado, valor); \
	else if (tipo == WHERE_DATA_MAIOR) \
		sprintf(query, "%s %s > TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == WHERE_DATAHORA_MAIOR) \
		sprintf(query, "%s %s > TO_DATE('%s','DD/MM/YYYY HH24:MI:SS') ", query, dado, valor); \
	else if (tipo == WHERE_HORA_MAIOR) \
		sprintf(query, "%s %s > TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == WHERE_INT_MENOR) \
		sprintf(query, "%s %s < %i ", query, dado, valor); \
	else if (tipo == WHERE_ULONG_MENOR) \
		sprintf(query, "%s %s < %lu ", query, dado, valor); \
	else if (tipo == WHERE_CHAR_MENOR) \
		sprintf(query, "%s %s < '%s' ", query, dado, valor); \
	else if (tipo == WHERE_DATA_MENOR) \
		sprintf(query, "%s %s < TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == WHERE_DATAHORA_MENOR) \
		sprintf(query, "%s %s < TO_DATE('%s','DD/MM/YYYY HH24:MI:SS') ", query, dado, valor); \
	else if (tipo == WHERE_HORA_MENOR) \
		sprintf(query, "%s %s < TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == WHERE_INT_MAIORIGUAL) \
		sprintf(query, "%s %s >= %i ", query, dado, valor); \
	else if (tipo == WHERE_ULONG_MAIORIGUAL) \
		sprintf(query, "%s %s >= %lu ", query, dado, valor); \
	else if (tipo == WHERE_CHAR_MAIORIGUAL) \
		sprintf(query, "%s %s >= '%s' ", query, dado, valor); \
	else if (tipo == WHERE_DATA_MAIORIGUAL) \
		sprintf(query, "%s %s >= TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == WHERE_DATAHORA_MAIORIGUAL) \
		sprintf(query, "%s %s >= TO_DATE('%s','DD/MM/YYYY HH24:MI:SS') ", query, dado, valor); \
	else if (tipo == WHERE_HORA_MAIORIGUAL) \
		sprintf(query, "%s %s >= TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == WHERE_INT_MENORIGUAL) \
		sprintf(query, "%s %s <= %i ", query, dado, valor); \
	else if (tipo == WHERE_ULONG_MENORIGUAL) \
		sprintf(query, "%s %s <= %lu ", query, dado, valor); \
	else if (tipo == WHERE_CHAR_MENORIGUAL) \
		sprintf(query, "%s %s <= '%s' ", query, dado, valor); \
	else if (tipo == WHERE_DATA_MENORIGUAL) \
		sprintf(query, "%s %s <= TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == WHERE_DATAHORA_MENORIGUAL) \
		sprintf(query, "%s %s <= TO_DATE('%s','DD/MM/YYYY HH24:MI:SS') ", query, dado, valor); \
	else if (tipo == WHERE_HORA_MENORIGUAL) \
		sprintf(query, "%s %s <= TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == WHERE_CHAR_LIKE) \
		sprintf(query, "%s %s LIKE '%%%s%%'", query, dado, valor); \
	else if (tipo == WHERE_IS_NOT_NULL) \
		sprintf(query, "%s %s IS NOT NULL", query, dado); \
	else if (tipo == WHERE_CHAR_LIKE_UPPER) \
		sprintf(query, "%s UPPER(%s) LIKE UPPER('%%%s%%')", query, dado, valor); \
	separa = true; \
}

#define montarUpdate(sql,dado,valor,tipo) \
	if (separa) \
    { \
		sql += ","; \
    } \
	if (tipo == TIPO_INT) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += valor; \
    } \
	else if (tipo == TIPO_LONG) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += valor; \
    } \
	else if (tipo == TIPO_ULONG) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += valor; \
    } \
	else if (tipo == TIPO_CHAR) \
    { \
        sql += dado; \
        sql += " = '"; \
        sql += valor; \
        sql += "\'"; \
    } \
	else if (tipo == TIPO_DATA) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += "TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY') "; \
    } \
	else if (tipo == TIPO_DATAHORA) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += "TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY HH24:MI:SS') "; \
    } \
	else if (tipo == TIPO_HORA) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += "TO_DATE('"; \
        sql += valor; \
        sql += "','HH24:MI') "; \
    } \
	separa = true;

#define montarWhere(sql,dado,valor,tipo) \
	if (!separa) \
    { \
        sql += " WHERE "; \
    } \
	if (separa) \
    { \
        sql += " AND "; \
    } \
	if (tipo == WHERE_INT) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_LONG) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_ULONG) \
    { \
        sql += dado; \
        sql += " = "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_CHAR) \
    { \
        sql += dado; \
        sql += " LIKE '"; \
        sql += valor; \
        sql += "\'"; \
    } \
	else if (tipo == WHERE_DATA) \
    { \
        sql += dado; \
        sql += " = TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY') "; \
    } \
	else if (tipo == WHERE_DATAHORA) \
    { \
        sql += dado; \
        sql += " = TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY HH24:MI:SS') "; \
    } \
	else if (tipo == WHERE_HORA) \
    { \
        sql += dado; \
        sql += " = TO_DATE("; \
        sql += "\'"; \
        sql += valor; \
        sql += "\'"; \
        sql += ","; \
        sql += "\'"; \
        sql += ",HH24:MI) "; \
        sql += "\'"; \
        sql += ") "; \
    } \
	else if (tipo == WHERE_INT_MAIOR) \
    { \
        sql += dado; \
        sql += " > "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_ULONG_MAIOR) \
    { \
        sql += dado; \
        sql += " > "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_CHAR_MAIOR) \
    { \
        sql += dado; \
        sql += " > '"; \
        sql += valor; \
        sql += "\'"; \
    } \
	else if (tipo == WHERE_DATA_MAIOR) \
    { \
        sql += dado; \
        sql += " > TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY') "; \
    } \
	else if (tipo == WHERE_DATAHORA_MAIOR) \
    { \
        sql += dado; \
        sql += " > TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY HH24:MI:SS') "; \
    } \
	else if (tipo == WHERE_HORA_MAIOR) \
    { \
        sql += dado; \
        sql += " > TO_DATE('"; \
        sql += valor; \
        sql += "','HH24:MI') "; \
    } \
	else if (tipo == WHERE_INT_MENOR) \
    { \
        sql += dado; \
        sql += " < "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_ULONG_MENOR) \
    { \
        sql += dado; \
        sql += " < "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_CHAR_MENOR) \
    { \
        sql += dado; \
        sql += " < '"; \
        sql += valor; \
        sql += "\'"; \
    } \
	else if (tipo == WHERE_DATA_MENOR) \
    { \
        sql += dado; \
        sql += " < TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY') "; \
    } \
	else if (tipo == WHERE_DATAHORA_MENOR) \
    { \
        sql += dado; \
        sql += " < TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY HH24:MI:SS') "; \
    } \
	else if (tipo == WHERE_HORA_MENOR) \
    { \
        sql += dado; \
        sql += " < TO_DATE('"; \
        sql += valor; \
        sql += "','HH24:MI') "; \
    } \
	else if (tipo == WHERE_INT_MAIORIGUAL) \
    { \
        sql += dado; \
        sql += " >= "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_ULONG_MAIORIGUAL) \
    { \
        sql += dado; \
        sql += " >= "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_CHAR_MAIORIGUAL) \
    { \
        sql += dado; \
        sql += " >= '"; \
        sql += valor; \
        sql += "\'"; \
    } \
	else if (tipo == WHERE_DATA_MAIORIGUAL) \
    { \
        sql += dado; \
        sql += " >= TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY') "; \
    } \
	else if (tipo == WHERE_DATAHORA_MAIORIGUAL) \
    { \
        sql += dado; \
        sql += " >= TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY HH24:MI:SS') "; \
    } \
	else if (tipo == WHERE_HORA_MAIORIGUAL) \
    { \
        sql += dado; \
        sql += " >= TO_DATE('"; \
        sql += valor; \
        sql += "','HH24:MI') "; \
    } \
	else if (tipo == WHERE_INT_MENORIGUAL) \
    { \
        sql += dado; \
        sql += " <= "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_ULONG_MENORIGUAL) \
    { \
        sql += dado; \
        sql += " <= "; \
        sql += valor; \
    } \
	else if (tipo == WHERE_CHAR_MENORIGUAL) \
    { \
        sql += dado; \
        sql += " <= '"; \
        sql += valor; \
        sql += "\'"; \
    } \
	else if (tipo == WHERE_DATA_MENORIGUAL) \
    { \
        sql += dado; \
        sql += " <= TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY') "; \
    } \
	else if (tipo == WHERE_DATAHORA_MENORIGUAL) \
    { \
        sql += dado; \
        sql += " <= TO_DATE('"; \
        sql += valor; \
        sql += "','DD/MM/YYYY HH24:MI:SS') "; \
    } \
	else if (tipo == WHERE_HORA_MENORIGUAL) \
    { \
        sql += dado; \
        sql += " <= TO_DATE('"; \
        sql += valor; \
        sql += "','HH24:MI') "; \
    } \
	else if (tipo == WHERE_CHAR_LIKE) \
    { \
        sql += dado; \
        sql += " LIKE '%"; \
        sql += valor; \
        sql += "%'"; \
    } \
	else if (tipo == WHERE_IS_NOT_NULL) \
    { \
        sql += dado; \
        sql += " IS NOT NULL"; \
    } \
	else if (tipo == WHERE_CHAR_LIKE_UPPER) \
    { \
        sql += " UPPER("; \
        sql += dado; \
        sql += ")"; \
        sql += " LIKE UPPER('%"; \
        sql += valor; \
        sql += "%')"; \
    } \
	separa = true;

#define CONV(O) \
{ \
	##O.arr[##O.len]=0; \
}

#define CONVIND(O,I) \
{ \
	if (I == -1) { \
		##O.arr[0]=0; \
	} else { \
		##O.arr[##O.len]=0; \
	} \
}

// Previne o uso de valores inteiros não gravados pelo fetch do Pro*C
class CONVINT
{
    public:
        CONVINT(int _valor,int _index) { valor = _valor;index = _index; }

        operator const char*() { if ( -1 == index ) return ""; else return itoa(valor,valorStr,10); }
        operator char*() { if ( -1 == index ) return ""; else return itoa(valor,valorStr,10); }

    private:
        int valor;
        short index;
        char valorStr[132];

#ifndef WIN32
    // itoa não é uma operação ANSI e portanto não existe em alguns compiladores que é
    // infelizmente o caso do xlC. Abaixo é uma função simplificada que converte valores
    // inteiros em strings do tipo C na base 10.
    // Nota:
    // É responsabilidade do desenvolvedor prover um buffer válido com tamanho suficiente
    // para a conversão.
    char * itoa(int i,char *dest,int /*radix*/)
    {
        sprintf(dest,"%d",i);
        return dest;
    }
#endif

};

// Previne o uso de valores inteiros não gravados pelo fetch do Pro*C
class CONVLONG
{
    public:
        CONVLONG(unsigned long _valor,int _index) {  valor = _valor; index = _index; }

        operator const char*() { if ( -1 == index ) return ""; else return ltoa(valor,valorStr); }
        operator char*() { if ( -1 == index ) return ""; else return ltoa(valor,valorStr); }

    private:
        unsigned long valor;
        short index;
        char valorStr[132];


    // itoa não é uma operação ANSI e portanto não existe em alguns compiladores que é
    // infelizmente o caso do xlC. Abaixo é uma função simplificada que converte valores
    // inteiros em strings do tipo C na base 10.
    // Nota:
    // É responsabilidade do desenvolvedor prover um buffer válido com tamanho suficiente
    // para a conversão.
    char * ltoa(unsigned long i,char *dest)
    {
        sprintf(dest,"%ld",i);
        return dest;
    }

};

#endif

