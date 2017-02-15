APPNAME=cargaProgramaRelacionamento

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
LOG_DIR=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
PROC_DIR=$(EXEC_DIR)/processado

clean:
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh

build:	$(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

cargaProgramaRelacionamento: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(LOG_DIR) ]       || mkdir $(LOG_DIR)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(PROC_DIR) ]      || mkdir $(PROC_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/cargaProgramaRelacionamento.sh $(EXEC_DIR)/cargaProgramaRelacionamento.sh
	cp -f $(COMPILE_DIR)/cargaProgramaRelacionamento.ctl $(EXEC_DIR)/cargaProgramaRelacionamento.ctl
	# PERMISSOES PARA EXECUCAO
	chmod -R 777 $(EXEC_DIR)