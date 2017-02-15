#ifndef QUERYMACRO
	#define QUERYMACRO

#define TIPO_INT 1
#define TIPO_CHAR 2
#define	TIPO_DATA 3
#define TIPO_DATAHORA 4
#define TIPO_HORA 5

#define WHERE_INT 1
#define WHERE_CHAR 2
#define	WHERE_DATA 3
#define WHERE_DATAHORA 4
#define WHERE_HORA 5
#define WHERE_INT_MAIOR 6
#define WHERE_CHAR_MAIOR 7
#define	WHERE_DATA_MAIOR 8
#define WHERE_DATAHORA_MAIOR 9
#define WHERE_HORA_MAIOR 10
#define WHERE_INT_MENOR 11
#define WHERE_CHAR_MENOR 12
#define	WHERE_DATA_MENOR 13
#define WHERE_DATAHORA_MENOR 14
#define WHERE_HORA_MENOR 15
#define WHERE_INT_MAIORIGUAL 16
#define WHERE_CHAR_MAIORIGUAL 17
#define	WHERE_DATA_MAIORIGUAL 18
#define WHERE_DATAHORA_MAIORIGUAL 19
#define WHERE_HORA_MAIORIGUAL 20
#define WHERE_INT_MENORIGUAL 21
#define WHERE_CHAR_MENORIGUAL 22
#define	WHERE_DATA_MENORIGUAL 23
#define WHERE_DATAHORA_MENORIGUAL 24
#define WHERE_HORA_MENORIGUAL 25
#define WHERE_CHAR_LIKE 26
#define WHERE_IS_NOT_NULL 27

#define montaUpdate(dado, valor, tipo) \
{\
	if (separa) \
		sprintf(query,"%s ,", query); \
	if (tipo == 1) \
		sprintf(query, "%s %s = %i ", query, dado, valor); \
	else if (tipo == 2)\
		sprintf(query, "%s %s = '%s' ", query, dado, valor); \
	else if (tipo == 3)\
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == 4)\
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY HH24:MI') ", query, dado, valor); \
	else if (tipo == 5)\
		sprintf(query, "%s %s = TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	separa = true; \
}

#define montaWhere(dado, valor, tipo) \
{\
	if (!separa) \
		sprintf(query,"%s WHERE ", query); \
	if (separa) \
		sprintf(query,"%s AND ", query); \
	if (tipo == 1) \
		sprintf(query, "%s %s = %i ", query, dado, valor); \
	else if (tipo == 2) \
		sprintf(query, "%s %s LIKE '%s' ", query, dado, valor); \
	else if (tipo == 3) \
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == 4) \
		sprintf(query, "%s %s = TO_DATE('%s','DD/MM/YYYY HH24:MI') ", query, dado, valor); \
	else if (tipo == 5) \
		sprintf(query, "%s %s = TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == 6) \
		sprintf(query, "%s %s > %i ", query, dado, valor); \
	else if (tipo == 7) \
		sprintf(query, "%s %s > '%s' ", query, dado, valor); \
	else if (tipo == 8) \
		sprintf(query, "%s %s > TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == 9) \
		sprintf(query, "%s %s > TO_DATE('%s','DD/MM/YYYY HH24:MI') ", query, dado, valor); \
	else if (tipo == 10) \
		sprintf(query, "%s %s > TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == 11) \
		sprintf(query, "%s %s < %i ", query, dado, valor); \
	else if (tipo == 12) \
		sprintf(query, "%s %s < '%s' ", query, dado, valor); \
	else if (tipo == 13) \
		sprintf(query, "%s %s < TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == 14) \
		sprintf(query, "%s %s < TO_DATE('%s','DD/MM/YYYY HH24:MI') ", query, dado, valor); \
	else if (tipo == 15) \
		sprintf(query, "%s %s < TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == 16) \
		sprintf(query, "%s %s >= %i ", query, dado, valor); \
	else if (tipo == 17) \
		sprintf(query, "%s %s >= '%s' ", query, dado, valor); \
	else if (tipo == 18) \
		sprintf(query, "%s %s >= TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == 19) \
		sprintf(query, "%s %s >= TO_DATE('%s','DD/MM/YYYY HH24:MI') ", query, dado, valor); \
	else if (tipo == 20) \
		sprintf(query, "%s %s >= TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == 21) \
		sprintf(query, "%s %s <= %i ", query, dado, valor); \
	else if (tipo == 22) \
		sprintf(query, "%s %s <= '%s' ", query, dado, valor); \
	else if (tipo == 23) \
		sprintf(query, "%s %s <= TO_DATE('%s','DD/MM/YYYY') ", query, dado, valor); \
	else if (tipo == 24) \
		sprintf(query, "%s %s <= TO_DATE('%s','DD/MM/YYYY HH24:MI') ", query, dado, valor); \
	else if (tipo == 25) \
		sprintf(query, "%s %s <= TO_DATE('%s','HH24:MI') ", query, dado, valor); \
	else if (tipo == 26) \
		sprintf(query, "%s %s LIKE '%%%s%%'", query, dado, valor); \
	else if (tipo == 27) \
		sprintf(query, "%s %s IS NOT NULL", query, dado); \
	separa = true; \
}


#define CONV(O) \
{\
	##O.arr[##O.len]=0; \
}

#define CONVIND(O,I) \
{\
	if (I == -1) { \
		##O.arr[0]=0; \
	} else { \
		##O.arr[##O.len]=0; \
	} \
}

#endif

