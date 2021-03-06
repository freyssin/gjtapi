/*
	Copyright (c) 2005 Serban Iordache 
	
	All rights reserved. 
	
	Permission is hereby granted, free of charge, to any person obtaining a 
	copy of this software and associated documentation files (the 
	"Software"), to deal in the Software without restriction, including 
	without limitation the rights to use, copy, modify, merge, publish, 
	distribute, and/or sell copies of the Software, and to permit persons 
	to whom the Software is furnished to do so, provided that the above 
	copyright notice(s) and this permission notice appear in all copies of 
	the Software and that both the above copyright notice(s) and this 
	permission notice appear in supporting documentation. 
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS 
	OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT 
	OF THIRD PARTY RIGHTS. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
	HOLDERS INCLUDED IN THIS NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL 
	INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING 
	FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, 
	NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION 
	WITH THE USE OR PERFORMANCE OF THIS SOFTWARE. 
	
	Except as contained in this notice, the name of a copyright holder 
	shall not be used in advertising or otherwise to promote the sale, use 
	or other dealings in this Software without prior written authorization 
	of the copyright holder.
*/
package net.sourceforge.gjtapi.test.tapi3;

/**
 * An implementation of the {@link net.sourceforge.gjtapi.raw.tapi3.logging.Logger} interface that delegates its job to a log4j Logger
 */
public class Tapi3Logger implements net.sourceforge.gjtapi.raw.tapi3.logging.Logger {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Tapi3Logger.class);

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#debug(java.lang.Object)
     */
    public void debug(Object message) {
        logger.debug(message);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#debug(java.lang.Object, java.lang.Throwable)
     */
    public void debug(Object message, Throwable t) {
        logger.debug(message, t);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#info(java.lang.Object)
     */
    public void info(Object message) {
        logger.info(message);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#info(java.lang.Object, java.lang.Throwable)
     */
    public void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#warn(java.lang.Object)
     */
    public void warn(Object message) {
        logger.warn(message);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#warn(java.lang.Object, java.lang.Throwable)
     */
    public void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#error(java.lang.Object)
     */
    public void error(Object message) {
        logger.error(message);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#error(java.lang.Object, java.lang.Throwable)
     */
    public void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#fatal(java.lang.Object)
     */
    public void fatal(Object message) {
        logger.fatal(message);
    }

    /* (non-Javadoc)
     * @see net.sourceforge.gjtapi.raw.tapi3.logging.Logger#fatal(java.lang.Object, java.lang.Throwable)
     */
    public void fatal(Object message, Throwable t) {
        logger.fatal(message, t);
    }

}
