#!/bin/bash
############################################################
# Atualiza o estado
############################################################
function atualizaEstado () {
	log ${LOG_PREFIX} "->> atualizaEstado"
	estado=$1
	log ${LOG_PREFIX} "Estado recebido como parametro [$estado]"
	case "$estado" in
		PROCESSANDO|FINALIZADO|ERRO)
			# Atualiza o estado 
			dummy=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            --SET PAGESIZE 0 
	            --SET FEEDBACK OFF
	            --SET VERIFY OFF
	            SET HEADING OFF
	            --SET ECHO OFF
				WHENEVER SQLERROR EXIT SQL.SQLCODE
				UPDATE APOIO.PARAMETRO SET 
				     DSVALORPARAMETRO = '$estado',
				     IDUSUARIOALTERACAO = 1,
				     DTULTIMAALTERACAO = SYSDATE
				WHERE
				    CDPARAMETRO = 'SEGAUTO_ESTADO';

				COMMIT;

	            EXIT;
	            EOF`
			# Verifica se houve erro no sqlplus 
			sql_return_code=$?
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[atualizaEstado]: Nao foi possivel carregar o parametro 'SEGAUTO_ESTADO' em APOIO.PARAMETRO. Codigo de erro [$dummy]"
				exit -1;
			fi
			log ${LOG_PREFIX} "Estado alterado para [$estado]"
			;;
		*) 
			log ${LOG_PREFIX} "ERRO[atualizaEstado]: Estado recebido como parametro esta invalido: [$estado]."
			log ${LOG_PREFIX} "                      Deve ser: PROCESSANDO, FINALIZADO ou ERRO."
			exit -1 # Aborta, o parametro passado nao existe ou esta com um valor errado
			;;
	esac
	log ${LOG_PREFIX} "<<- atualizaEstado"
}

############################################################
# Atualiza a etapa
############################################################
function atualizaEtapa () {
	log ${LOG_PREFIX} "->> atualizaEtapa"
	etapa=$1
	log ${LOG_PREFIX} "Etapa recebida como parametro [$etapa]"
	case "$etapa" in
		CHECAGEM | INCLUSAO_POS | EXCLUSAO_POS | INCLUSAO_PRE | EXCLUSAO_PRE)
			# Atualiza com a estapa em processo 
			dummy=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
				--SET PAGESIZE 0 
				--SET FEEDBACK OFF
				--SET VERIFY OFF
				SET HEADING OFF
				--SET ECHO OFF
				WHENEVER SQLERROR EXIT SQL.SQLCODE
				UPDATE APOIO.PARAMETRO SET 
					 DSVALORPARAMETRO = '$etapa',
					 IDUSUARIOALTERACAO = 1,
					 DTULTIMAALTERACAO = SYSDATE
				WHERE
					CDPARAMETRO = 'SEGAUTO_ETAPA';
				COMMIT;
				EXIT;
				EOF`

			# Verifica se houve erro no sqlplus 
			sql_return_code=$?
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[atualizaEtapa]: Nao foi possivel carregar o parametro 'SEGAUTO_ETAPA' em APOIO.PARAMETRO. Codigo de erro [$dummy]"
				exit 0;
			fi
			log ${LOG_PREFIX} "Etapa alterada para [$etapa]"
			;;
		*) 
			log ${LOG_PREFIX} "ERRO[atualizaEtapa]: Etapa recebida como parametro esta invalida: [$etapa]."
			log ${LOG_PREFIX} "      Deve ser: CHECAGEM, INCLUSAO_POS, EXCLUSAO_POS, INCLUSAO_PRE e EXCLUSAO_PRE."
			exit -1 # Aborta, o parametro passado nao existe ou esta com um valor errado
			;;
	esac
	log ${LOG_PREFIX} "<<- atualizaEtapa"
}


############################################################
# Atualiza o criterio
############################################################
function atualizaCriterio () {
	log ${LOG_PREFIX} "->> atualizaCriterio"
	# Atualiza o CRITERIO com um dia a mais 
	dummy=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	    --SET PAGESIZE 0 
	    --SET FEEDBACK OFF
	    --SET VERIFY OFF
	    SET HEADING OFF
	    --SET ECHO OFF
		WHENEVER SQLERROR EXIT SQL.SQLCODE
		UPDATE APOIO.PARAMETRO SET 
			 DSVALORPARAMETRO = TO_CHAR(TO_DATE(DSVALORPARAMETRO, 'DD/MM/YYYY')+1, 'DD/MM/YYYY'),
			 IDUSUARIOALTERACAO = 1,
			 DTULTIMAALTERACAO = SYSDATE
		WHERE
			CDPARAMETRO = 'SEGAUTO_CRITERIO';
		COMMIT;
	    EXIT;
	    EOF`

	# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO[atualizaCriterio]: Nao foi possivel atualizar o parametro 'SEGAUTO_CRITERIO' em APOIO.PARAMETRO. Codigo de erro [$dummy]"
		exit -1;
	fi
	log ${LOG_PREFIX} "Creterio incrementado em 1 dia."
	log ${LOG_PREFIX} "<<- atualizaCriterio"
}

