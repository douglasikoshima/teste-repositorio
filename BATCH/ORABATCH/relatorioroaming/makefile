APPNAME=relatorioroaming

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/out

all:  build

clean:
	rm -r -f $(EXEC_DIR)

build:	relatorioroaming $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

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
	cp -f $(COMPILE_DIR)/relatorioRoaming.sh $(EXEC_DIR)/relatorioRoaming.sh
	cp -f $(COMPILE_DIR)/relatorioRoaming.cfg $(EXEC_DIR)/relatorioRoaming.cfg
	cp -f $(COMPILE_DIR)/relatorioRoamingAtualizacao.ctl $(EXEC_DIR)/relatorioRoamingAtualizacao.ctl
	touch $(EXEC_DIR)/relatorioRoamingAtualizacao.txt 2> /dev/null
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)/orabatch.cfg
	chmod 777 $(EXEC_DIR)/relatorioRoaming.sh
	chmod 777 $(EXEC_DIR)/relatorioRoaming.cfg
	chmod 777 $(EXEC_DIR)/relatorioRoamingAtualizacao.ctl
	chmod 777 $(EXEC_DIR)/relatorioRoamingAtualizacao.txt

relatorioroaming:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ] || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ] || mkdir $(DATA_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/relatorioRoaming.sh $(EXEC_DIR)/relatorioRoaming.sh
	cp -f $(COMPILE_DIR)/relatorioRoaming.cfg $(EXEC_DIR)/relatorioRoaming.cfg
	cp -f $(COMPILE_DIR)/relatorioRoamingAtualizacao.ctl $(EXEC_DIR)/relatorioRoamingAtualizacao.ctl
	touch $(EXEC_DIR)/relatorioRoamingAtualizacao.txt 2> /dev/null
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)/orabatch.cfg
	chmod 777 $(EXEC_DIR)/relatorioRoaming.sh
	chmod 777 $(EXEC_DIR)/relatorioRoaming.cfg
	chmod 777 $(EXEC_DIR)/relatorioRoamingAtualizacao.ctl
	chmod 777 $(EXEC_DIR)/relatorioRoamingAtualizacao.txt