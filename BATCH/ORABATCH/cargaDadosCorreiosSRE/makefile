#!/bin/bash
############################################################
#  BATCH......: cargaDadosCorreiosSRE
#  Criado por.: Denis Hideki 04/03/2016
#  Funcional..: Loiana Moura
############################################################
APPNAME=cargaDadosCorreiosSRE

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
REL_CARGA_CD_DNE=$(EXEC_DIR)/relatoriosCargaCdDNE


clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par
	rm -f $(REL_CARGA_CD_DNE)/*.csv


build:	cargaDadosCorreiosSRE $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


cargaDadosCorreiosSRE: install


install:
	umask 022
# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    	|| mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] 	|| mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      	|| mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      	|| mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      	|| mkdir $(DATA_DIR)
	[ -d $(HIST_DIR) ]      	|| mkdir $(HIST_DIR)
	[ -d $(SQL_DIR) ]       	|| mkdir $(SQL_DIR)
	[ -d $(PRC_DIR) ]       	|| mkdir $(PRC_DIR)
	[ -d $(BAD_DIR) ]       	|| mkdir $(BAD_DIR)
	[ -d $(REL_CARGA_CD_DNE) ] 	|| mkdir $(REL_CARGA_CD_DNE)


# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg    $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/cargaDadosCorreiosSRE.sh                 $(EXEC_DIR)/cargaDadosCorreiosSRE.sh
	cp -f $(COMPILE_DIR)/cargaDadosCorreiosSRE_db_functions.sh    $(EXEC_DIR)/cargaDadosCorreiosSRE_db_functions.sh
	cp -f $(COMPILE_DIR)/cargaDadosCorreiosSRE_files_functions.sh $(EXEC_DIR)/cargaDadosCorreiosSRE_files_functions.sh
	cp -f $(COMPILE_SQL)/cargaDadosCorreiosSRE.sql                $(SQL_DIR)/cargaDadosCorreiosSRE.sql
	cp -f $(COMPILE_SQL)/cargaDadosBairro.ctl                	  $(SQL_DIR)/cargaDadosBairro.ctl
	cp -f $(COMPILE_SQL)/cargaDadosBairro.par                	  $(SQL_DIR)/cargaDadosBairro.par
	cp -f $(COMPILE_SQL)/cargaDadosLocalidade.ctl                 $(SQL_DIR)/cargaDadosLocalidade.ctl
	cp -f $(COMPILE_SQL)/cargaDadosLocalidade.par                 $(SQL_DIR)/cargaDadosLocalidade.par
	cp -f $(COMPILE_SQL)/cargaDadosTipoLogradouro.ctl             $(SQL_DIR)/cargaDadosTipoLogradouro.ctl
	cp -f $(COMPILE_SQL)/cargaDadosTipoLogradouro.par             $(SQL_DIR)/cargaDadosTipoLogradouro.par
	cp -f $(COMPILE_SQL)/cargaDadosTituloLogradouro.ctl           $(SQL_DIR)/cargaDadosTituloLogradouro.ctl
	cp -f $(COMPILE_SQL)/cargaDadosTituloLogradouro.par           $(SQL_DIR)/cargaDadosTituloLogradouro.par
	cp -f $(COMPILE_SQL)/cargaDadosCaixaPostalComunitaria.ctl     $(SQL_DIR)/cargaDadosCaixaPostalComunitaria.ctl
	cp -f $(COMPILE_SQL)/cargaDadosCaixaPostalComunitaria.par     $(SQL_DIR)/cargaDadosCaixaPostalComunitaria.par
	cp -f $(COMPILE_SQL)/cargaDadosLogradouros.ctl     			  $(SQL_DIR)/cargaDadosLogradouros.ctl
	cp -f $(COMPILE_SQL)/cargaDadosLogradouros.par     			  $(SQL_DIR)/cargaDadosLogradouros.par
	cp -f $(COMPILE_SQL)/cargaDadosCentroDistribuicao.ctl     	  $(SQL_DIR)/cargaDadosCentroDistribuicao.ctl
	cp -f $(COMPILE_SQL)/cargaDadosCentroDistribuicao.par     	  $(SQL_DIR)/cargaDadosCentroDistribuicao.par
	cp -f $(COMPILE_SQL)/cargaDadosGrandesUsuarios.ctl     	  	  $(SQL_DIR)/cargaDadosGrandesUsuarios.ctl
	cp -f $(COMPILE_SQL)/cargaDadosGrandesUsuarios.par     	  	  $(SQL_DIR)/cargaDadosGrandesUsuarios.par
	cp -f $(COMPILE_SQL)/relCargaCdDNECep.sql 					  $(SQL_DIR)/relCargaCdDNECep.sql
	cp -f $(COMPILE_SQL)/relCargaCdDNELimpeza.sql 				  $(SQL_DIR)/relCargaCdDNELimpeza.sql
	cp -f $(COMPILE_SQL)/relCargaCdDNETotal.sql 				  $(SQL_DIR)/relCargaCdDNETotal.sql
	cp -f $(COMPILE_SQL)/truncateTempTables.sql 				  $(SQL_DIR)/truncateTempTables.sql
 

	chmod 777 $(EXEC_DIR)
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	chmod 755 $(EXEC_DIR)/cargaDadosCorreiosSRE.sh
	chmod 755 $(EXEC_DIR)/cargaDadosCorreiosSRE_db_functions.sh
	chmod 755 $(EXEC_DIR)/cargaDadosCorreiosSRE_files_functions.sh
	chmod 644 $(SQL_DIR)/cargaDadosCorreiosSRE.sql
	chmod 644 $(SQL_DIR)/cargaDadosBairro.ctl
	chmod 644 $(SQL_DIR)/cargaDadosBairro.par
	chmod 644 $(SQL_DIR)/cargaDadosLocalidade.ctl
	chmod 644 $(SQL_DIR)/cargaDadosLocalidade.par
	chmod 644 $(SQL_DIR)/cargaDadosTipoLogradouro.ctl
	chmod 644 $(SQL_DIR)/cargaDadosTipoLogradouro.par
	chmod 644 $(SQL_DIR)/cargaDadosTituloLogradouro.ctl
	chmod 644 $(SQL_DIR)/cargaDadosTituloLogradouro.par
	chmod 644 $(SQL_DIR)/cargaDadosCaixaPostalComunitaria.ctl
	chmod 644 $(SQL_DIR)/cargaDadosCaixaPostalComunitaria.par
	chmod 644 $(SQL_DIR)/cargaDadosLogradouros.ctl
	chmod 644 $(SQL_DIR)/cargaDadosLogradouros.par
	chmod 644 $(SQL_DIR)/cargaDadosCentroDistribuicao.ctl
	chmod 644 $(SQL_DIR)/cargaDadosCentroDistribuicao.par
	chmod 644 $(SQL_DIR)/cargaDadosGrandesUsuarios.ctl
	chmod 644 $(SQL_DIR)/cargaDadosGrandesUsuarios.par
	chmod 777 $(DATA_DIR)/ *
	chmod 644 $(SQL_DIR)/relCargaCdDNECep.sql
	chmod 644 $(SQL_DIR)/relCargaCdDNELimpeza.sql
	chmod 644 $(SQL_DIR)/relCargaCdDNETotal.sql
	chmod 644 $(SQL_DIR)/truncateTempTables.sql
