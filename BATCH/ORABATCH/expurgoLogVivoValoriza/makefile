APPNAME=expurgoLogVivoValoriza

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
LOG_DIR=$(EXEC_DIR)/log


clean:
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh

build:	expurgoLogVivoValoriza $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

expurgoFWTPR: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(LOG_DIR) ] || mkdir $(LOG_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/expurgoLogVivoValoriza.sh $(EXEC_DIR)/expurgoLogVivoValoriza.sh
	# PERMISSOES PARA EXECUCAO
	chmod -R 777 $(EXEC_DIR)