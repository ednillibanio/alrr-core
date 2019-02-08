package br.leg.rr.al.core.jpa;

import java.io.Serializable;
import java.util.EnumMap;

import br.leg.rr.al.core.utils.EnumAttributeConverter;

/**
 * <p>
 * Esta interface é um template utilizado por enums que são de dominios. Ela
 * possui um campo chamado {@link #getLabel()} que serve para exibir valores
 * para o usuário, e um outro chamado {@link #getEnumMap()} que possui os
 * valores numa lista (map) que são salvos ou recuperados do banco de dados de
 * acordo com o valor ou enum selecionado/instanciado. Além do enum de dominio
 * implementar essa classe, ela deve criar uma outra classe converter que deve
 * ser anotada do atributo a ser convertido na entidade.
 * 
 * <pre>
 * <code> 
 * public enum Mes implements BasicEnum{@literal<Mes>} {

		JANEIRO("Janeiro"), FEVEREIRO("Fevereiro")...;
	
		private Mes(String label) {
			this.label = label;
		}
		public EnumMap<Mes, String> getEnumMap() {
			EnumMap<Mes, String> map = new EnumMap<Mes, String>(Mes.class);
			map.put(Mes.JANEIRO, "1");
			map.put(Mes.FEVEREIRO, "2");
			...
		}
	}
	
	// Classe que vai converter o enum Mes
	public class MesConverter extends EnumAttributeConverter<Mes> {

	}
	
	
	public entidade extends BaseEntity<Long>{
	
		// Atributo que será aplicado o MesConverter
		{@literal @Convert(converter = MesConverter.class)}
		{@literal @Column(nullable = false, length = 2)}
		{@literal private Mes mes = null;}
	}
	
 * </code>
 * </pre>
 * </p>
 * 
 * @see {@link EnumAttributeConverter}
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <K> The enum type subclass
 */
public interface BasicEnum<K extends Enum<K>> extends Serializable {

	/**
	 * Valor a ser exibido para o usuário.
	 * 
	 * @return label do Enum
	 */
	public String getLabel();

	/**
	 * Valores que são salvos no banco de dados.
	 * 
	 * @return Retorna uma lista com todos os valores.
	 */
	public EnumMap<K, ?> getEnumMap();

}
