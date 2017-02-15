APPNAME=inclusaoPR

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data

all:

clean:
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.sql
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.ctl

build:	inclusaoPR $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

inclusaoPR: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ] || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ] || mkdir $(DATA_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/inclusaoPR.sh $(EXEC_DIR)/inclusaoPR.sh
	cp -f $(COMPILE_DIR)/inclusaoPR.sql $(EXEC_DIR)/inclusaoPR.sql
	cp -f $(COMPILE_DIR)/inclusaoPRloader.sh $(EXEC_DIR)/inclusaoPRloader.sh
	cp -f $(COMPILE_DIR)/inclusaoPRloader.ctl $(EXEC_DIR)/inclusaoPRloader.ctl
	# PERMISSOES PARA EXECUCAO
	chmod -R 777 $(EXEC_DIR)
	chmod 777 $(EXEC_DIR)/orabatch.cfg
	chmod 777 $(EXEC_DIR)/inclusaoPR.sh
	chmod 777 $(EXEC_DIR)/inclusaoPR.sql