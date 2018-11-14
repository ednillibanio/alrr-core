package br.leg.rr.al.core.web.util;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

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
	 * <p>
	 * Verifica se o parametro de request é considerado verdadeiro usando uma
	 * combinação com vários valores considerados como verd
	 * <p/>
	 * <p>
	 * Valores que são considerados verdadeiros: "true", t, "verdadeiro", v, 1,
	 * enabled, y, yes, sim, s, on.
	 * </p>
	 *
	 * @param request
	 *            the servlet request
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
	 * @param request
	 *            o servlet request.
	 * @param paramName
	 *            o nome do parametro.
	 * @return o parametro sem espaços ou nulo se há apenas espaços vazios.
	 */
	public static String getTrimParam(ServletRequest request, String paramName) {
		return StringUtils.trim(request.getParameter(paramName));
	}

}
