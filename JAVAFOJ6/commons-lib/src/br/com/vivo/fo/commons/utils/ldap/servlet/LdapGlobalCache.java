package br.com.vivo.fo.commons.utils.ldap.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Hashtable;
import java.util.Random;
import java.util.Enumeration;

public
    class      LdapGlobalCache
    extends    HttpServlet
    implements Runnable
{

	private static final long serialVersionUID = -4449782731341313789L;

	private static Hashtable objects = new Hashtable();
    
    private boolean isActive = true;
    
    public void init()
    {
        new Thread( this ).start();
    }
    
    public void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException {
        String id = req.getParameter( "id" );
        if ( id == null )
            return;
        if ( id.equals( "MANAGER" ) )
        {
            try
            {
                java.io.PrintWriter out = res.getWriter();
                out.println( "<html><head><title>Vivo Ldap</title></head>" );
                out.println( "<body><b>Total de objetos(s) aguardando em cache : </b>" );
                synchronized (objects) {
                    out.println( objects.size() );
                }
                out.println( "</body></html>" );
            } catch ( java.io.IOException ex ) {
                throw new ServletException(ex);
            }
            return;
        }
        
        CacheObject cached = null;
	synchronized( objects ) {
            cached = (CacheObject) objects.get( id );
	}
        
        try
        {
            ServletOutputStream out = res.getOutputStream();
            res.setContentType( cached.mimeType );
            out.write( (byte[]) cached.object );
        } catch (java.io.IOException ex) {
            throw new ServletException(ex);
        } finally {
	    synchronized( objects ) {
                objects.remove( id );
	    }
        }
    }
    
    public static String addCache( Object object, String mimeType )
    {
        String id = null;
	boolean containsKey = false;
	
        do
        {
            id = generateRndId();
	    synchronized( objects ) {
	        containsKey = objects.containsKey( id );
	    }
        } while( containsKey );

	synchronized( objects ) {
            objects.put( id, new CacheObject(object, mimeType) );
	}

        return id;
    }
    
    public static String generateRndId()
    {
        byte[] b = new byte[ 20 ];
        char[] c = new char[ 20 ];
        Random r = new Random();
        r.nextBytes( b );
        for ( int i = 0; i < b.length; i++ )
        {
            c[ i ] = (char) ((b[ i ] & 0xff) % 123);
            if (  c[ i ] < 65 )
                c[ i ] = (char) (c[ i ] % 10 + 48) ;
            if (  c[ i ] < 97 && c[ i ] > 90 )
                c[ i ] = 95;
            if ( c[ 0 ] < 65 )
                c[ 0 ] += 18;
        }
        return new String( c );
    }

    public void destroy()
    {
        isActive = false;
    }
    
    public void run()
    {
        long SEEK_PERIOD = 30000;
        
        while ( isActive )
        {
            try
            {
                Thread.sleep( SEEK_PERIOD );
            } catch ( InterruptedException ex ) {}
            synchronized( objects )
            {
                for ( Enumeration e = objects.keys(); e.hasMoreElements(); )
                {
                    Object key = e.nextElement();
                    CacheObject o = (CacheObject) objects.get( key );
                    if ( o.isExpired() )
                        objects.remove( key );
                }
            }
        }
    }
    
}

class CacheObject
{
    private static final long DELAY = 180000;   // 3 minutues
    Object object;
    String mimeType;
    long   timeStamp = System.currentTimeMillis();
    
    CacheObject( Object _object, String _mimeType )
    {
        this.object = _object;
        this.mimeType = _mimeType;
    }
    
    boolean isExpired()
    {
        return (System.currentTimeMillis() - timeStamp) > DELAY;
    }
}
   