############################################################
#  BATCH......: expurgoSincronismoAMDOCS
#  Criado por.: Lucas Gomes Fenelon de Moraes (12/04/2016)
#  Funcional..: Erika Brum
############################################################
APPNAME=expurgoSincronismoAMDOCS

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
SQL_DIR=$(EXEC_DIR)/sql
EXEC_DTT=$(EXEC_DIR)/datatmp
EXEC_HST=$(EXEC_DIR)/hist

clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(SQL_DIR)/*.sql

build:	expurgoSincronismoAMDOCS $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

expurgoSincronismoAMDOCS: install

install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(SQL_DIR) ]  		|| mkdir $(SQL_DIR) 
	[ -d $(EXEC_DTT) ]      || mkdir $(EXEC_DTT)
	[ -d $(EXEC_HST) ]      || mkdir $(EXEC_HST)

	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg     $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/expurgoSincronismoAMDOCS.sh          $(EXEC_DIR)/expurgoSincronismoAMDOCS.sh
	cp -f $(COMPILE_DIR)/expurgoSincronismoArquivosAMDOCS.sh  $(EXEC_DIR)/expurgoSincronismoArquivosAMDOCS.sh
	cp -f $(COMPILE_DIR)/expurgoSincronismoAMDOCS_Apoio.sh    $(EXEC_DIR)/expurgoSincronismoAMDOCS_Apoio.sh
	cp -f $(COMPILE_DIR)/expurgoSincronismoAMDOCS_Dao.sh      $(EXEC_DIR)/expurgoSincronismoAMDOCS_Dao.sh
	cp -f $(COMPILE_DIR)/expurgoSincronismoAMDOCS_Negocio.sh  $(EXEC_DIR)/expurgoSincronismoAMDOCS_Negocio.sh
	cp -f $(COMPILE_DIR)/LEIAME.TXT                           $(EXEC_DIR)/LEIAME.TXT

	# COPIA DE ARQUIVOS PL/SQL  E AFINS
	cp -f $(COMPILE_SQL)/BACKUP_FILASINCRONISMOAMDOCS.sql  $(SQL_DIR)/BACKUP_FILASINCRONISMOAMDOCS.sql
	cp -f $(COMPILE_SQL)/BACKUP_LOG_SINCRONISMO.sql        $(SQL_DIR)/BACKUP_LOG_SINCRONISMO.sql
	cp -f $(COMPILE_SQL)/BACKUP_LOG_SINCRONISMO_XML.sql    $(SQL_DIR)/BACKUP_LOG_SINCRONISMO_XML.sql
	cp -f $(COMPILE_SQL)/EXPURGO_FILASINCRONISMOAMDOCS.sql $(SQL_DIR)/EXPURGO_FILASINCRONISMOAMDOCS.sql
	cp -f $(COMPILE_SQL)/EXPURGO_LOG_SINCRONISMO.sql       $(SQL_DIR)/EXPURGO_LOG_SINCRONISMO.sql
	cp -f $(COMPILE_SQL)/EXPURGO_LOG_SINCRONISMO_XML.sql   $(SQL_DIR)/EXPURGO_LOG_SINCRONISMO_XML.sql
	
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/expurgoSincronismoAMDOCS.sh
	chmod 755 $(EXEC_DIR)/expurgoSincronismoArquivosAMDOCS.sh
	# Arquivos SQL e afins
	chmod 644 $(SQL_DIR)/BACKUP_FILASINCRONISMOAMDOCS.sql
	chmod 644 $(SQL_DIR)/BACKUP_LOG_SINCRONISMO.sql
	chmod 644 $(SQL_DIR)/BACKUP_LOG_SINCRONISMO_XML.sql
	chmod 644 $(SQL_DIR)/EXPURGO_FILASINCRONISMOAMDOCS.sql
	chmod 644 $(SQL_DIR)/EXPURGO_LOG_SINCRONISMO.sql
	chmod 644 $(SQL_DIR)/EXPURGO_LOG_SINCRONISMO_XML.sql
