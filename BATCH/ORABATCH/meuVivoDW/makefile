############################################################
#  BATCH......: meuVivoDW
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################
APPNAME=meuVivoDW

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
DATA_TMP_DIR=$(EXEC_DIR)/datatmp
SQL_DIR=$(EXEC_DIR)/sql



clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	meuVivoDW $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


meuVivoDW: install


install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(DATA_TMP_DIR) ]  || mkdir $(DATA_TMP_DIR)
	[ -d $(SQL_DIR) ]  || mkdir $(SQL_DIR)
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg  $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/*.sh                      $(EXEC_DIR)/
	cp -f $(COMPILE_DIR)/LEIAME.TXT                        $(EXEC_DIR)/LEIAME.TXT
	# COPIA DE ARQUIVOS PL/SQL  E AFINS
	cp -f $(COMPILE_SQL)/*.sql  $(SQL_DIR)/
	cp -f $(COMPILE_SQL)/*.ctl  $(SQL_DIR)/
	cp -f $(COMPILE_SQL)/*.par  $(SQL_DIR)/
	
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/*.sh
	# Arquivos SQL e afins
	chmod 644 $(SQL_DIR)/*.ctl
	chmod 644 $(SQL_DIR)/*.par
	chmod 644 $(SQL_DIR)/*.sql
	
