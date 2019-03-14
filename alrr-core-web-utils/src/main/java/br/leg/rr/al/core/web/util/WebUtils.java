package br.leg.rr.al.core.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtils {

	/**
	 * Standard Servlet 2.3+ spec request attributes for include URI and paths.
	 * <p>
	 * If included via a RequestDispatcher, the current resource will see the
	 * originating request. Its own URI and paths are exposed as request attributes.
	 */
	public static final String INCLUDE_REQUEST_URI_ATTRIBUTE = "javax.servlet.include.request_uri";
	public static final String INCLUDE_CONTEXT_PATH_ATTRIBUTE = "javax.servlet.include.context_path";
	public static final String INCLUDE_SERVLET_PATH_ATTRIBUTE = "javax.servlet.include.servlet_path";
	public static final String INCLUDE_PATH_INFO_ATTRIBUTE = "javax.servlet.include.path_info";
	public static final String INCLUDE_QUERY_STRING_ATTRIBUTE = "javax.servlet.include.query_string";

	/**
	 * Standard Servlet 2.4+ spec request attributes for forward URI and paths.
	 * <p>
	 * If forwarded to via a RequestDispatcher, the current resource will see its
	 * own URI and paths. The originating URI and paths are exposed as request
	 * attributes.
	 */
	public static final String FORWARD_REQUEST_URI_ATTRIBUTE = "javax.servlet.forward.request_uri";
	public static final String FORWARD_CONTEXT_PATH_ATTRIBUTE = "javax.servlet.forward.context_path";
	public static final String FORWARD_SERVLET_PATH_ATTRIBUTE = "javax.servlet.forward.servlet_path";
	public static final String FORWARD_PATH_INFO_ATTRIBUTE = "javax.servlet.forward.path_info";
	public static final String FORWARD_QUERY_STRING_ATTRIBUTE = "javax.servlet.forward.query_string";

	/**
	 * Default character encoding to use when
	 * <code>request.getCharacterEncoding</code> returns <code>null</code>,
	 * according to the Servlet spec.
	 *
	 * @see javax.servlet.ServletRequest#getCharacterEncoding
	 */
	public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";

	private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

	/**
	 * <p>
	 * Verifica se o parametro de request é considerado verdadeiro usando uma
	 * combinação com vários valores considerados como verd
	 * <p/>
	 * <p>
	 * Valores que são considerados verdadeiros: "true", t, "verdadeiro", v, 1,
	 * enabled, y, yes, sim, s, on.
	 * </p>
	 *
	 * @param request   the servlet request
	 * @param paramName
	 * @return true se o parametro satisfaz um dos valores.
	 */
	public static boolean isVerdadeiro(ServletRequest request, String paramName) {
		String valor = getTrimParam(request, paramName);
		return valor != null && (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("t")
				|| valor.equalsIgnoreCase("verdadeiro") || valor.equalsIgnoreCase("v") || valor.equalsIgnoreCase("1")
				|| valor.equalsIgnoreCase("enabled") || valor.equalsIgnoreCase("y") || valor.equalsIgnoreCase("yes")
				|| valor.equalsIgnoreCase("sim") || valor.equalsIgnoreCase("s") || valor.equalsIgnoreCase("on"));
	}

	/**
	 * Método que remove espaços em brancos utilizando a função StringUtils.trim()
	 * Apache.
	 *
	 * @param request   o servlet request.
	 * @param paramName o nome do parametro.
	 * @return o parametro sem espaços ou nulo se há apenas espaços vazios.
	 */
	public static String getTrimParam(ServletRequest request, String paramName) {
		return StringUtils.trim(request.getParameter(paramName));
	}

	/**
	 * Return the request URI for the given request, detecting an include request
	 * URL if called within a RequestDispatcher include.
	 * <p>
	 * As the value returned by <code>request.getRequestURI()</code> is <i>not</i>
	 * decoded by the servlet container, this method will decode it.
	 * <p>
	 * The URI that the web container resolves <i>should</i> be correct, but some
	 * containers like JBoss/Jetty incorrectly include ";" strings like
	 * ";jsessionid" in the URI. This method cuts off such incorrect appendices.
	 *
	 * @param request current HTTP request
	 * @return the request URI
	 */
	public static String getRequestUri(HttpServletRequest request) {
		String uri = (String) request.getAttribute(INCLUDE_REQUEST_URI_ATTRIBUTE);
		if (uri == null) {
			uri = request.getRequestURI();
		}
		return normalize(decodeAndCleanUriString(request, uri));
	}

	/**
	 * Normalize a relative URI path that may have relative values ("/./", "/../",
	 * and so on ) it it. <strong>WARNING</strong> - This method is useful only for
	 * normalizing application-generated paths. It does not try to perform security
	 * checks for malicious input. Normalize operations were was happily taken from
	 * org.apache.catalina.util.RequestUtil in Tomcat trunk, r939305
	 *
	 * @param path Relative path to be normalized
	 * @return normalized path
	 */
	public static String normalize(String path) {
		return normalize(path, true);
	}

	/**
	 * Normalize a relative URI path that may have relative values ("/./", "/../",
	 * and so on ) it it. <strong>WARNING</strong> - This method is useful only for
	 * normalizing application-generated paths. It does not try to perform security
	 * checks for malicious input. Normalize operations were was happily taken from
	 * org.apache.catalina.util.RequestUtil in Tomcat trunk, r939305
	 *
	 * @param path             Relative path to be normalized
	 * @param replaceBackSlash Should '\\' be replaced with '/'
	 * @return normalized path
	 */
	private static String normalize(String path, boolean replaceBackSlash) {

		if (path == null)
			return null;

		// Create a place for the normalized path
		String normalized = path;

		if (replaceBackSlash && normalized.indexOf('\\') >= 0)
			normalized = normalized.replace('\\', '/');

		if (normalized.equals("/."))
			return "/";

		// Add a leading "/" if necessary
		if (!normalized.startsWith("/"))
			normalized = "/" + normalized;

		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 1);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return (null); // Trying to go outside our context
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return (normalized);

	}

	/**
	 * Decode the supplied URI string and strips any extraneous portion after a ';'.
	 *
	 * @param request the incoming HttpServletRequest
	 * @param uri     the application's URI string
	 * @return the supplied URI string stripped of any extraneous portion after a
	 *         ';'.
	 */
	private static String decodeAndCleanUriString(HttpServletRequest request, String uri) {
		uri = decodeRequestString(request, uri);
		int semicolonIndex = uri.indexOf(';');
		return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
	}

	/**
	 * Return the context path for the given request, detecting an include request
	 * URL if called within a RequestDispatcher include.
	 * <p>
	 * As the value returned by <code>request.getContextPath()</code> is <i>not</i>
	 * decoded by the servlet container, this method will decode it.
	 *
	 * @param request current HTTP request
	 * @return the context path
	 */
	public static String getContextPath(HttpServletRequest request) {
		String contextPath = (String) request.getAttribute(INCLUDE_CONTEXT_PATH_ATTRIBUTE);
		if (contextPath == null) {
			contextPath = request.getContextPath();
		}
		contextPath = normalize(decodeRequestString(request, contextPath));
		if ("/".equals(contextPath)) {
			// the normalize method will return a "/" and includes on Jetty, will also be a
			// "/".
			contextPath = "";
		}
		return contextPath;
	}

	/**
	 * Decode the given source string with a URLDecoder. The encoding will be taken
	 * from the request, falling back to the default "ISO-8859-1".
	 * <p>
	 * The default implementation uses <code>URLDecoder.decode(input, enc)</code>.
	 *
	 * @param request current HTTP request
	 * @param source  the String to decode
	 * @return the decoded String
	 * @see #DEFAULT_CHARACTER_ENCODING
	 * @see javax.servlet.ServletRequest#getCharacterEncoding
	 * @see java.net.URLDecoder#decode(String, String)
	 * @see java.net.URLDecoder#decode(String)
	 */
	@SuppressWarnings({ "deprecation" })
	public static String decodeRequestString(HttpServletRequest request, String source) {
		String enc = determineEncoding(request);
		try {
			return URLDecoder.decode(source, enc);
		} catch (UnsupportedEncodingException ex) {
			if (log.isWarnEnabled()) {
				log.warn("Could not decode request string [" + source + "] with encoding '" + enc
						+ "': falling back to platform default encoding; exception message: " + ex.getMessage());
			}
			return URLDecoder.decode(source);
		}
	}

	/**
	 * Determine the encoding for the given request. Can be overridden in
	 * subclasses.
	 * <p>
	 * The default implementation checks the request's
	 * {@link ServletRequest#getCharacterEncoding() character encoding}, and if that
	 * <code>null</code>, falls back to the {@link #DEFAULT_CHARACTER_ENCODING}.
	 *
	 * @param request current HTTP request
	 * @return the encoding for the request (never <code>null</code>)
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	protected static String determineEncoding(HttpServletRequest request) {
		String enc = request.getCharacterEncoding();
		if (enc == null) {
			enc = DEFAULT_CHARACTER_ENCODING;
		}
		return enc;
	}
}
