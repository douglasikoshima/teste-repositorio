#!/bin/bash
############################################################
#  BATCH......: movimentacaoPupSdp
#  Criado por.: Williams Santos 16/09/2016
#  Funcional..: Erika Brum
############################################################
APPNAME=movimentacaoPupSdp

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
HIST_DIR=$(EXEC_DIR)/hist
SQL_DIR=$(EXEC_DIR)/sql
PRC_DIR=$(EXEC_DIR)/prc
BAD_DIR=$(EXEC_DIR)/bad


clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	movimentacaoPupSdp $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


movimentacaoPupSdp: install


install:
	umask 022
# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(HIST_DIR) ]      || mkdir $(HIST_DIR)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	[ -d $(PRC_DIR) ]       || mkdir $(PRC_DIR)
	[ -d $(BAD_DIR) ]       || mkdir $(BAD_DIR)


# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg      $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/movimentacaoPupSdp.sh                 $(EXEC_DIR)/movimentacaoPupSdp.sh
	cp -f $(COMPILE_DIR)/movimentacaoPupSdp_db_functions.sh    $(EXEC_DIR)/movimentacaoPupSdp_db_functions.sh
	cp -f $(COMPILE_DIR)/movimentacaoPupSdp_files_functions.sh $(EXEC_DIR)/movimentacaoPupSdp_files_functions.sh
	cp -f $(COMPILE_SQL)/movimentacaoPupSdp.sql                $(SQL_DIR)/movimentacaoPupSdp.sql
	cp -f $(COMPILE_SQL)/movimentacaoPupSdp_DataDeCorte.sql    $(SQL_DIR)/movimentacaoPupSdp_DataDeCorte.sql
	


	chmod 777 $(EXEC_DIR)
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	chmod 755 $(EXEC_DIR)/movimentacaoPupSdp.sh
	chmod 755 $(EXEC_DIR)/movimentacaoPupSdp_db_functions.sh
	chmod 755 $(EXEC_DIR)/movimentacaoPupSdp_files_functions.sh
	chmod 644 $(SQL_DIR)/movimentacaoPupSdp.sql
	chmod 644 $(SQL_DIR)/movimentacaoPupSdp_DataDeCorte.sql