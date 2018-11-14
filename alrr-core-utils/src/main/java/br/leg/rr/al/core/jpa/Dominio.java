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
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
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
public abstract class Dominio extends BaseEntityStatus<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7280917544480281174L;

	public static final String NOME_ANALYZER = "NomeAnalyzer";
	/**
	 * 
	 */
	@Analyzer(definition = NOME_ANALYZER)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@NotNull(message = "Nome: campo obrigatório.")
	@Column(nullable = false, length = 250)
	protected String nome;

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
