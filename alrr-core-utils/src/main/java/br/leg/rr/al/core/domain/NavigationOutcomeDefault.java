package br.leg.rr.al.core.domain;

public enum NavigationOutcomeDefault {
	/** Define o valor <b><i>"atualizado"</i></b>. */
	ATUALIZADO_COM_SUCCESSO("atualizado"),
	/** Define o valor <b><i>"salvo"</i></b>. */
	INSERIDO_COM_SUCCESSO("inserido"),
	/** Define o valor <b><i>"sucesso"</i></b>. */
	SUCCESSO("sucesso"),
	/** Define o valor <b><i>"falha"</i></b>. */
	FALHA("falha"),
	/** Define o valor <b><i>"novo"</i></b>. */
	NOVO("novo"),
	/** Define o valor <b><i>"salvar"</i></b>. */
	SALVAR("salvar"),
	/** Define o valor <b><i>"fechar"</i></b>. */
	FECHAR("fechar"),
	/** Define o valor <b><i>"atualizar"</i></b>. */
	ATUALIZAR("atualizar"),
	/** Define o valor <b><i>"remover"</i></b>. */
	REMOVER("remover"),
	/** Define o valor <b><i>"cancelar"</i></b>. */
	CANCELAR("cancelar"),
	/** Define o valor <b><i>"pesquisar"</i></b>. */
	PESQUISAR("pesquisar"),
	/** Define o valor <b><i>"editar"</i></b>. */
	EDITAR("editar"),
	/** Define o valor <b><i>"sem_resultado"</i></b>. */
	SEM_RESULTADO("sem_resultado"),
	/** Define o valor <b><i>"detalhes"</i></b>. */
	DETALHES("detalhes"),
	/** Define o valor <b><i>"home"</i></b>. */
	HOME("home"),
	/** Define o valor <b><i>"adicionar"</i></b>. */
	ADICIONAR("adicionar"),
	/** Define o valor <b><i>"autenticar"</i></b>. */
	AUTENTICAR("autenticar"),
	/** Define o valor <b><i>"sair"</i></b>. */
	SAIR("SAIR");

	private NavigationOutcomeDefault(String label) {
		this.label = label;
	}

	private String label;

	@Override
	public String toString() {
		return label;
	}
}
