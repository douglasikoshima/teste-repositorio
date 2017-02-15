############################################################
#  BATCH......: relressarcimprocedente
#  Criado por.: Eder Jani Martins (04/05/2015)
#  Funcional..: Erika Brum
############################################################
APPNAME=relressarcimprocedente

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
PRC_DIR=$(EXEC_DIR)/prc
BAD_DIR=$(EXEC_DIR)/bad
SQL_DIR=$(EXEC_DIR)/sql



clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	relressarcimprocedente $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


relressarcimprocedente: install


install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(PRC_DIR) ]       || mkdir $(PRC_DIR)
	[ -d $(BAD_DIR) ]       || mkdir $(BAD_DIR)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg          $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/relRessarcImprocedente.sh                 $(EXEC_DIR)/relRessarcImprocedente.sh
	cp -f $(COMPILE_DIR)/relRessarcImprocedente_db_functions.sh    $(EXEC_DIR)/relRessarcImprocedente_db_functions.sh
	cp -f $(COMPILE_DIR)/relRessarcImprocedente_files_functions.sh $(EXEC_DIR)/relRessarcImprocedente_files_functions.sh
	cp -f $(COMPILE_DIR)/LEIAME.TXT                                $(EXEC_DIR)/LEIAME.TXT
	# COPIA DE ARQUIVOS PL/SQL  E AFINS
	cp -f $(COMPILE_SQL)/RELRESSARCIMPROCEDENTE.ctl        $(SQL_DIR)/RELRESSARCIMPROCEDENTE.ctl
	cp -f $(COMPILE_SQL)/RELRESSARCIMPROCEDENTE.par        $(SQL_DIR)/RELRESSARCIMPROCEDENTE.par
	cp -f $(COMPILE_SQL)/RELRESSARCIMPROCEDENTE.sql        $(SQL_DIR)/RELRESSARCIMPROCEDENTE.sql
	
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/relRessarcImprocedente.sh
	chmod 755 $(EXEC_DIR)/relRessarcImprocedente_db_functions.sh
	chmod 755 $(EXEC_DIR)/relRessarcImprocedente_files_functions.sh
	# Arquivos SQL e afins
	chmod 644 $(SQL_DIR)/RELRESSARCIMPROCEDENTE.ctl
	chmod 644 $(SQL_DIR)/RELRESSARCIMPROCEDENTE.par
	chmod 644 $(SQL_DIR)/RELRESSARCIMPROCEDENTE.sql
