package com.indracompany.catalogo.ejb.regional;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

import com.indracompany.catalogo.dao.interfaces.RegionalDAO;
import com.indracompany.catalogo.to.RegionalTO;

@Stateless(name = "RegionalBean", mappedName = "RegionalBean")
@Session(ejbName = "RegionalBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RegionalBean implements RegionalBeanLocal {

	private static Logger log = Logger.getLogger(RegionalBean.class);
	@EJB
	private RegionalDAO regionalDAO;
	public List<RegionalTO> findAll() {
		// TODO Auto-generated method stub
	
		log.debug("begin");
		return regionalDAO.findAll();
	}
}
