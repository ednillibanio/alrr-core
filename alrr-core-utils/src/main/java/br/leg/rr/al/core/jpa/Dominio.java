package br.leg.rr.al.core.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * Uma classe do tipo Doninio representa uma entidade de banco de dados que
 * serve como uma tabela ou uma lista de dados auxiliares. Por exemplo,
 * entidades: Profissão, EstadoCivil, Bairro, entre outros. São objetos que não
 * podem ser apagados da base de dados porque possuem muitos relacionamentos a
 * outra entidades que podemos chama-las de primárias.
 * </p>
 * <p>
 * Quando um objeto do tipo Dominio deixar de ser útil ou parar de permitir que
 * seja associado a outros novos objetos, deve-se mudar a situação para
 * 'inativa'. Está situação indica que o objeto não faz mais parte do cadastro
 * de novas entidades que possuem qualquer relacionamento com este objeto. Por
 * exemplo, para não exibir o estado civil 'solteiro/a' num cadastro de Pessoa
 * Física, basta desabilitar ou inativar o objeto 'solteiro/a'. 'solteiro/a'. OS
 * cadastros que já possuem este estado civil associado, continuarão tendo.
 * </p>
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class Dominio extends BaseEntityStatus<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7280917544480281174L;

	/**
	 * Nome do dominio instânciado.
	 */
	@NotNull(message = "Nome: campo obrigatório.")
	@Column(nullable = false, length = 250)
	protected String nome;

	/**
	 * Breve descrição do dominio instânciado.
	 */
	@Column(nullable = true, length = 500)
	protected String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
