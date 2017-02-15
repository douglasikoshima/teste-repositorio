#ifndef QUERYMACROFILA
	#define QUERYMACROFILA

#define montaWhereFila(dado, valor, tipo) \
{\
	if (!separa) \
	{ \
		query += " WHERE "; \
	} \
	if (separa) \
	{ \
		query += " AND "; \
	} \
	if (tipo == 1) \
	{ \
		query += dado; \
		query += " = "; \
		query += valor; \
		query += " "; \
	} \
	else if (tipo == 2) \
	{ \
		query += dado; \
		query += " LIKE '"; \
		query += valor; \
		query += "' "; \
	} \
	else if (tipo == 3) \
	{ \
		query += dado; \
		query += " = TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY') "; \
	} \
	else if (tipo == 4) \
	{ \
		query += dado; \
		query += " = TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY HH24:MI') "; \
	} \
	else if (tipo == 5) \
	{ \
		query += dado; \
		query += " = TO_DATE('"; \
		query += valor; \
		query += "','HH24:MI') "; \
	} \
	else if (tipo == 6) \
	{ \
		query += dado; \
		query += " > "; \
		query += valor; \
		query += " "; \
	} \
	else if (tipo == 7) \
	{ \
		query += dado; \
		query += " > '"; \
		query += valor; \
		query += "' "; \
	} \
	else if (tipo == 8) \
	{ \
		query += dado; \
		query += " > TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY') "; \
	} \
	else if (tipo == 9) \
	{ \
		query += dado; \
		query += " > TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY HH24:MI') "; \
	} \
	else if (tipo == 10) \
	{ \
		query += dado; \
		query += " > TO_DATE('"; \
		query += valor; \
		query += "','HH24:MI') "; \
	} \
	else if (tipo == 11) \
	{ \
		query += dado; \
		query += " < "; \
		query += valor; \
		query += " "; \
	} \
	else if (tipo == 12) \
	{ \
		query += dado; \
		query += " < '"; \
		query += valor; \
		query += "' "; \
	} \
	else if (tipo == 13) \
	{ \
		query += dado; \
		query += " < TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY') "; \
	} \
	else if (tipo == 14) \
	{ \
		query += dado; \
		query += " < TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY HH24:MI') "; \
	} \
	else if (tipo == 15) \
	{ \
		query += dado; \
		query += " < TO_DATE('"; \
		query += valor; \
		query += "','HH24:MI') "; \
	} \
	else if (tipo == 16) \
	{ \
		query += dado; \
		query += " >= "; \
		query += valor; \
		query += " "; \
	} \
	else if (tipo == 17) \
	{ \
		query += dado; \
		query += " >= '"; \
		query += valor; \
		query += "' "; \
	} \
	else if (tipo == 18) \
	{ \
		query += dado; \
		query += " >= TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY') "; \
	} \
	else if (tipo == 19) \
	{ \
		query += dado; \
		query += " >= TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY HH24:MI') "; \
	} \
	else if (tipo == 20) \
	{ \
		query += dado; \
		query += " >= TO_DATE('"; \
		query += valor; \
		query += "','HH24:MI') "; \
	} \
	else if (tipo == 21) \
	{ \
		query += dado; \
		query += " <= "; \
		query += valor; \
		query += " "; \
	} \
	else if (tipo == 22) \
	{ \
		query += dado; \
		query += " <= '"; \
		query += valor; \
		query += "' "; \
	} \
	else if (tipo == 23) \
	{ \
		query += dado; \
		query += " <= TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY') "; \
	} \
	else if (tipo == 24) \
	{ \
		query += dado; \
		query += " <= TO_DATE('"; \
		query += valor; \
		query += "','DD/MM/YYYY HH24:MI') "; \
	} \
	else if (tipo == 25) \
	{ \
		query += dado; \
		query += " <= TO_DATE('"; \
		query += valor; \
		query += "','HH24:MI') "; \
	} \
	else if (tipo == 26) \
	{ \
		query += dado; \
		query += " LIKE '%%"; \
		query += valor; \
		query += "%%' "; \
	} \
	else if (tipo == 27) \
	{ \
		query += dado; \
		query += " IS NOT NULL "; \
	} \
	else if (tipo == 28) \
	{ \
		query += " UPPER("; \
		query += dado; \
		query += ") "; \
		query += " LIKE UPPER('%%"; \
		query += valor; \
		query += "%%') "; \
	} \
	separa = true; \
}


#define CONV(O) \
{\
	##O.arr[##O.len]=0; \
}
/* ja existe em outro include
#define CONVIND(O,I) \
{\
	if (I == -1) { \
		##O.arr[0]=0; \
        ##O.len = 0; \
	} else { \
		##O.arr[##O.len]=0; \
	} \
}
*/
#endif

