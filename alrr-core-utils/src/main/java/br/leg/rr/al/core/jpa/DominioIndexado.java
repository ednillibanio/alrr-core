package br.leg.rr.al.core.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.apache.lucene.analysis.br.BrazilianStemFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 * <p>
 * Está classe abstrata possui as mesmas características que uma classe do tipo
 * {@code Dominio}. Uma classe do tipo Doninio representa uma entidade de banco
 * de dados que serve como uma tabela ou uma lista de dados auxiliares. Por
 * exemplo, entidades: Profissão, EstadoCivil, Bairro, entre outros. São objetos
 * que não podem ser apagados da base de dados porque possuem muitos
 * relacionamentos a outra entidades que podemos chama-las de primárias.
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
 * <p>
 * A diferença dessa classe para a classe Dominio é que esta classe utiliza os
 * recursos do hibernate-search-annotation. O campo 'nome' foi anotado com esse
 * recurso que implementa uma regra de pesquisa que irá gerar todos os valores
 * do banco de dados localmente no servidor. Esse arquivo, possui um mecanismo
 * de busca mais ágil e dinâmico do que a pesquisa por banco de dados. Os
 * seguintes filtros são implementados no campo 'nome':
 * <ul>
 * <li>todas as palavrãs são pesquisadas em minusculas.</li>
 * <li>letra brasileiras, como ç, ã, é, são pesquisadas</li>
 * <li>palavras de junção são ignodas (de, do, da). Essas palavras são ou devem
 * ser inseridas num arquivos chamado /srs/main/resources/stopwords.txt em casa
 * projeto que use resursos hibernate-search-annotation.</li>
 * </ul>
 * Para mais informações consulte os conceitos de
 * org.hibernate.search.annotations.AnalyzerDef
 * </p>
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * 
 * @see {@link Dominio}
 */
@AnalyzerDef(name = "NomeAnalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = BrazilianStemFilterFactory.class),
		@TokenFilterDef(factory = StopFilterFactory.class, params = {
				@Parameter(name = "words", value = "stopwords.txt"), @Parameter(name = "ignoreCase", value = "true") }),
		@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
				@Parameter(name = "minGramSize", value = "3"), @Parameter(name = "maxGramSize", value = "8") }),
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class) })

@MappedSuperclass
public abstract class DominioIndexado extends BaseEntityStatus<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7280917544480281174L;

	/**
	 * Nome definido no @AnalyzerDef da classe Dominio.
	 */
	public static final String NOME_ANALYZER = "NomeAnalyzer";
	/**
	 * Além de representar o nome do objeto, esse campo implementar o conceito da
	 * pesquisa hibernate-search-annotation. A tag @Analyzer serve para informar
	 * qual o tipo da pesquisa deve ser usada no campo. Neste caso é usado o que foi
	 * defini no escopo do classe.
	 */
	@Analyzer(definition = NOME_ANALYZER)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@NotNull(message = "Nome: campo obrigatório.")
	@Column(nullable = false, length = 250)
	protected String nome;

	/**
	 * Breve descrição do dominio.
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
