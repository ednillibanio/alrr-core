package br.leg.rr.al.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.leg.rr.al.core.util.comparator.BeanPropertyComparator;

/**
 * Utilizado para ordenacao de listas.
 * 
 */
public class ListUtils {

	private ListUtils() {
	}

	/**
	 * Metodo que retorna uma sublista, baseando-se nos valores a sere
	 * utilizados como limite e offset, sempre considerando tambem o tamanho
	 * maximo da lista
	 * 
	 * @param lista
	 *            Lista original
	 * @param limite
	 *            Tamanho maximo da lista a ser retornada
	 * @param offset
	 *            Offset a ser utilizado
	 * @return Sublista da lista original
	 */
	public static final <T> List<T> sublista(List<T> lista, Integer limite, Integer offset) {

		int primeiro = 0;
		int ultimo = lista.size();

		if (offset != null) {
			primeiro = offset;
		}

		if (limite != null) {
			ultimo = primeiro + limite;
		}

		if (ultimo > lista.size()) {
			ultimo = lista.size();
		}

		return lista.subList(primeiro, ultimo);
	}

	/**
	 * Ordena uma lista, de acordo com a propriedade definida, em ordem
	 * crescente
	 * 
	 * @param lista
	 *            Lista a ser ordenada
	 * @param propriedade
	 *            Propriedade a ser utilizada na comparacao
	 * @return Lista ordenada
	 */
	public static final <T> List<T> ordenaLista(List<T> lista, String propriedade) {
		return ordenaLista(lista, propriedade, true);
	}

	/**
	 * Ordena uma lista, de acordo com a propriedade definida, em ordem
	 * crescente
	 * 
	 * @param lista
	 *            Lista a ser ordenada
	 * @param propriedade
	 *            Propriedade a ser utilizada na comparacao
	 * @return Lista ordenada
	 */
	public static final <T> List<T> ordenaLista(List<T> lista, String propriedade, boolean crescente) {
		return ordenaLista(lista, propriedade, true, true);
	}

	/**
	 * Ordena uma lista, de acordo com a propriedade definida
	 * 
	 * @param lista
	 *            Lista a ser ordenada
	 * @param propriedade
	 *            Propriedade a ser utilizada na comparacao
	 * @param crescente
	 *            Indica se a ordenacao sera crescente
	 * @param novaLista
	 *            Indica se uma nova lista deve ser criada ou se a propria lista
	 *            de entrada deve ser ordenada
	 * @return Lista ordenada
	 */
	public static final <T> List<T> ordenaLista(List<T> lista, String propriedade, boolean crescente,
			boolean criarNovaLista) {

		// Comparator
		BeanPropertyComparator<T> beanPropertyComparator = new BeanPropertyComparator<T>(propriedade, crescente);

		// Ordenacao
		List<T> listaOrdenada = criarNovaLista ? new ArrayList<T>(lista) : lista;
		Collections.sort(listaOrdenada, beanPropertyComparator);

		return listaOrdenada;

	}

	/**
	 * Metodo que retorna uma sublista ordenada, baseando-se nos valores a sere
	 * utilizados como limite e offset, sempre considerando tambem o tamanho
	 * maximo da lista.
	 * 
	 * @see sublista(java.util.List, java.lang.Integer, java.lang.Integer)
	 * @see ordenaLista(java.util.List, java.lang.String, boolean)
	 * @param lista
	 *            Lista original
	 * @param limite
	 *            Tamanho maximo da lista a ser retornada
	 * @param offset
	 *            Offset a ser utilizado
	 * @param propriedade
	 *            Propriedade a ser utilizada na comparacao
	 * @param ascendente
	 *            Indica se a ordenacao sera crescente
	 * @return Sublista ordenada
	 */
	public static final <T> List<T> sublistaOrdenada(List<T> lista, Integer limite, Integer offset, String propriedade,
			boolean ascendente) {

		return sublista(ordenaLista(lista, propriedade, ascendente, true), limite, offset);

	}

	public static final <T> List<T> truncate(List<T> lista, int qtdeRegistros) {
		if (lista.size() > qtdeRegistros) {
			List<T> sublist = lista.subList(0, qtdeRegistros);
			return sublist;
		}
		return lista;
	}

	/**
	 * Encontra o indice do proximo elemento na lista, caso o elemento exista na
	 * lista retorna o indice do proximo, caso nao exista encontra a "posicao"
	 * onde o elemento estaria na lista e retorna o proximo indice. Caso a lista
	 * esteja vazia ou o elemento seja o ultimo da lista retorna -1
	 * 
	 * @param items
	 * @param valor
	 * @param comparator
	 * @return posicao do proximo elemento da lista ou -1 caso a lista esteja
	 *         vazia ou o elemento seja o ultimo
	 */
	public static final <T> int nextIndex(List<T> items, T valor, Comparator<T> comparator) {
		int inicio = 0;
		int fim = items.size() - 1;
		int posicaoElemento = -1;
		while (inicio <= fim) {
			int meio = (inicio + fim) / 2;
			posicaoElemento = meio;
			T itemMeio = items.get(meio);
			int comparacaoMeio = comparator.compare(itemMeio, valor);
			if (comparacaoMeio == 0) {
				break;
			} else if (comparacaoMeio < 0) {
				inicio = meio + 1;
			} else if (comparacaoMeio > 0) {
				fim = meio - 1;
			}
		}
		if (posicaoElemento >= 0) {
			T elemento = items.get(posicaoElemento);
			if (comparator.compare(elemento, valor) <= 0) {
				posicaoElemento++;
			}
		}
		return posicaoElemento < items.size() ? posicaoElemento : -1;
	}

}
