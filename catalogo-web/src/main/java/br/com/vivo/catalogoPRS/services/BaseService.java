package br.com.vivo.catalogoPRS.services;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public abstract class BaseService {
	
	protected Cache getCache(){
		CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("CatalogoCache");
		return cache;
	}
	
	protected Object getCached(String servico, String metodo){
		Element element = getCache().get(servico+metodo);
		if(element != null)
			return element.getObjectValue();
		return null;
	}
	
	protected void doCache(Object toCache, String servico, String metodo){
		getCache().put(new Element(servico+metodo, toCache));
	}
	
	public static void resetCache(String servico, String metodo){
		CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("CatalogoCache");
		cache.remove(servico+metodo);
	}
	
	public static void resetCache(){
		CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("CatalogoCache");
		cache.removeAll();
	}
		
	public static String detalhesCache(){
		CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("CatalogoCache");
		List keys = cache.getKeys();
		StringBuilder sb = new StringBuilder();
		sb.append("Lista das chaves do cache:<br>");
		for (Object key : keys) {
			sb.append("<li>");
			sb.append((String)key);
			sb.append("</li>");
		}
		return sb.toString();
	}
}
