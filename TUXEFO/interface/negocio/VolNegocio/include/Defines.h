
#ifndef CDEFINES
#define CDEFINES

/*******************************************************************************/
/* Defines dos codigos de erro para o ProC/C++ */

#define NO_SQL_ERROR					0
#define NO_MORE_ROWS					1403
#define NO_DATA_FOUND					1403
#define END_OF_CURSOR					1403
#define TOO_MANY_ROWS					1422
#define NULL_FETCHED					1405
#define COL_TRUNCATED					1406
#define DUPLICATE_KEY					-1

#define ASSERT_STR(value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			if (value == NULL) \
				throw new TuxBasicSvcException("11E0000", strcat(cTag, "INEXISTENTE")); \
			if (!*value) \
				throw new TuxBasicSvcException("11E0000", strcat(cTag, "VALOR_VAZIO")); \
		}

#define ASSERT_INT(output, value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			ASSERT_STR(value, tag) \
			if ((output = atoi(value)) <= 0) \
				throw new TuxBasicSvcException("11E0000", strcat(cTag, "VALOR_INVALIDO")); \
		}

#endif // CDEFINES