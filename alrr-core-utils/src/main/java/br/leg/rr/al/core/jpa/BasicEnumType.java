package br.leg.rr.al.core.jpa;

/**
 * <p>
 * Além dos campos e caracteristicas da interface {@code BasicEnum}, esta
 * interface possui o campo {@link #getValue()}. Está interface deve ser
 * implementada por classes que possuam além do campo {@link #getLabel()},
 * tenham valores do campo ({@link #getValue()}) definidos. Por exemplo, o enum
 * Mes.JANEIRO pode exibir tanto o label "Janeiro" quanto o valor "1".
 * 
 * <pre>
 * <code>
 * String label = Mes.JANEIRO.getLabel(); //"Janeiro"
 * String valor = Mes.JANEIRO.getValue(); // "1"
 * 
	public enum Mes implements BasicEnumType{@literal<Mes>} {
		
		JANEIRO("Janeiro", "1"), FEVEREIRO("Fevereiro", "2")...;
	
		String label;
		String value;
		
		private Mes(String label, String value) {
			this.label = label;
			this.value = value;
		}
 	}	
 * </code>
 * </pre>
 * </p>
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <K>
 * @see {@link BasicEnum}
 */
public interface BasicEnumType<K extends Enum<K>> extends BasicEnum<K> {

	/**
	 * Valor a ser exibido para o usúario. Esse valor não é o valor que é salvo no
	 * banco de dados. É valor de exibição.
	 * 
	 * @return value do Enum.
	 */
	public String getValue();

}
