/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.exceptions;

/**
 * Thrown to indicate that a method has been passed an illegal or inappropriate
 * argument.
 *
 * @author unascribed
 * @since JDK1.0
 */
public class HibernatePlusIllegalArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = -5365630128856068164L;

    /**
     * Constructs an <code>IllegalArgumentException</code> with no detail
     * message.
     */
    public HibernatePlusIllegalArgumentException() {
        super();
    }

    /**
     * Constructs an <code>IllegalArgumentException</code> with the specified
     * detail message.
     *
     * @param s
     *            the detail message.
     */
    public HibernatePlusIllegalArgumentException(String s) {
        super(s);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * <p>
     * Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail message.
     *
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link Throwable#getMessage()} method).
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
     *            is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * @since 1.5
     */
    public HibernatePlusIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
     * contains the class and detail message of <tt>cause</tt>). This
     * constructor is useful for exceptions that are little more than wrappers
     * for other throwables (for example,
     * {@link java.security.PrivilegedActionException}).
     *
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
     *            is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * @since 1.5
     */
    public HibernatePlusIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
