APPNAME=extracaoDadosGestor

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
DATA_DIR_FULL=$(EXEC_DIR)/data/full


clean:
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_DIR)/$(APPNAME).ctl
	rm -f $(EXEC_DIR)/*.sql


build:	extracaoDadosGestor $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

extracaoDadosGestor: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ] || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ] || mkdir $(DATA_DIR)
	[ -d $(DATA_DIR_FULL) ] || mkdir $(DATA_DIR_FULL)
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/extracaoDadosGestor.sh $(EXEC_DIR)/extracaoDadosGestor.sh
	cp -f $(COMPILE_DIR)/extracaoDadosGestorDiario.sh $(EXEC_DIR)/extracaoDadosGestorDiario.sh
	cp -f $(COMPILE_DIR)/extracaoDadosGestorFull.sh $(EXEC_DIR)/extracaoDadosGestorFull.sh
	cp -f $(COMPILE_DIR)/extracaoDadosGestor.sql $(EXEC_DIR)/extracaoDadosGestor.sql
	cp -f $(COMPILE_DIR)/extracaoDadosGestorDiario.sql $(EXEC_DIR)/extracaoDadosGestorDiario.sql
	cp -f $(COMPILE_DIR)/extracaoDadosGestorFull.sql $(EXEC_DIR)/extracaoDadosGestorFull.sql
	cp -f $(COMPILE_DIR)/extracaoDadosGestor.ctl $(EXEC_DIR)/extracaoDadosGestor.ctl
	cp -f $(COMPILE_DIR)/extracaoDadosGestorMaster.sql $(EXEC_DIR)/extracaoDadosGestorMaster.sql
	cp -f $(COMPILE_DIR)/extracaoDadosGestorMasterDiario.sql $(EXEC_DIR)/extracaoDadosGestorMasterDiario.sql
	cp -f $(COMPILE_DIR)/extracaoDadosGestorMasterFull.sql $(EXEC_DIR)/extracaoDadosGestorMasterFull.sql
	# PERMISSOES PARA EXECUCAO
	chmod 0755 $(EXEC_DIR)
	chmod 0755 $(DATA_DIR_FULL)
	chmod 777 $(DATA_DIR)
	chmod 777 $(EXEC_DIR)/orabatch.cfg
	chmod 0777 $(EXEC_DIR)/*.sh
	chmod 777 $(EXEC_DIR)/extracaoDadosGestor.ctl
	chmod 0744 $(EXEC_DIR)/*.sql
