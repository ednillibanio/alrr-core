//adiciona mascara de cnpj
function mascaraCnpj(cnpj) {
	if (mascaraInteiro(cnpj) == false) {
		event.returnValue = false;
	}
	return formataCampo(cnpj, '00.000.000/0000-00', event);
}

// adiciona mascara de cep
function mascaraCep(cep) {
	if (mascaraInteiro(cep) == false) {
		event.returnValue = false;
	}
	return formataCampo(cep, '00.000-000', event);
}

// adiciona mascara de data
function mascaraData(data) {
	if (mascaraInteiro(data) == false) {
		event.returnValue = false;
	}
	return formataCampo(data, '00/00/0000', event);
}

// adiciona mascara ao telefone
function mascaraTelefone(tel) {
	if (mascaraInteiro(tel) == false) {
		event.returnValue = false;
	}
	return formataCampo(tel, '(00) 0000-0000', event);
}

// adiciona mascara ao Cpf
function mascaraCpf(cpf) {
	if (mascaraInteiro(cpf) == false) {
		event.returnValue = false;
	}
	return formataCampo(cpf, '000.000.000-00', event);
}

// valida telefone
function validarTelefone(tel) {
	exp = /\(\d{2}\)\ \d{4}\-\d{4}/;
	if (!exp.test(tel.value))
		alert('Numero de Telefone Invalido!');
}

// valida CEP
function validarCep(cep) {
	exp = /\d{2}\.\d{3}\-\d{3}/;
	return (!exp.test(cep.value));
}

// valida data
function validarData(data) {
	exp = /\d{2}\/\d{2}\/\d{4}/;
	return (!exp.test(data.value));
}

/**
 * Método que valida o cpf da pessoa física
 * 
 * @param Objcpf
 *            argumento a ser validado.
 * @returns true se validado com sucesso. Se cpf for nulo, também retorna true.
 */
function validarCpf(cpf) {

	// permite cpf nulo e retorna true.
	if (cpf == null || cpf == '') {
		return true;
	}

	cpf = cpf.replace(/[^\d]+/g, '');

	if (cpf.length != 11) {
		return false;
	}

	// Valida primeiro digito
	soma_digito = 0;
	for (i = 0; i < 9; i++) {
		soma_digito += parseInt(cpf.charAt(i)) * (10 - i);
	}
	resultado = 11 - (soma_digito % 11);
	if (resultado == 10 || resultado == 11) {
		resultado = 0;
	}
	if (resultado != parseInt(cpf.charAt(9))) {
		return false;
	}
	// Valida segundo digito
	soma_digito = 0;
	for (i = 0; i < 10; i++) {
		soma_digito += parseInt(cpf.charAt(i)) * (11 - i);
	}
	resultado = 11 - (soma_digito % 11);
	if (resultado == 10 || resultado == 11) {
		resultado = 0;
	}
	return (resultado == parseInt(cpf.charAt(10)));
}

/**
 * Método que valida o cnpj da pessoa jurídica.
 * 
 * @param cnpj
 *            argumento a ser validado.
 * @returns true se validado com sucesso. Se cnpj for nulo, também retorna true.
 */
function validarCnpj(cnpj) {
	// permite cnpj nulo e retorna true.
	if (cnpj == null || cnpj == '')
		return true;

	cnpj = cnpj.replace(/[^\d]+/g, '');
	if (cnpj.length != 14)
		return false;

	// Valida DVs
	tamanho = cnpj.length - 2
	numeros = cnpj.substring(0, tamanho);
	digitos = cnpj.substring(tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(0))
		return false;

	tamanho = tamanho + 1;
	numeros = cnpj.substring(0, tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	return (resultado == digitos.charAt(1));

}

/**
 * Verifica se cpf informado como argumento é valido.
 * 
 * @param cpf
 *            argumento a ser validado.
 * @returns true se cpf não fizer parte da lista de cpf´s não permitidos.
 */
function isCpfPadrao(cpf) {
	return (cpf == "00000000000" || cpf == "11111111111"
			|| cpf == "22222222222" || cpf == "33333333333"
			|| cpf == "44444444444" || cpf == "55555555555"
			|| cpf == "66666666666" || cpf == "77777777777"
			|| cpf == "88888888888" || cpf == "99999999999");

}

/**
 * Verifica se cnpj informado como argumento é valido.
 * 
 * @param cnpj
 *            argumento a ser validado.
 * @returns true se cnpj não fizer parte da lista de cnpj´s não permitidos.
 */
function isCnpjPadrao(cnpj) {
	return (cnpj == "00000000000000" || cnpj == "11111111111111"
			|| cnpj == "22222222222222" || cnpj == "33333333333333"
			|| cnpj == "44444444444444" || cnpj == "55555555555555"
			|| cnpj == "66666666666666" || cnpj == "77777777777777"
			|| cnpj == "88888888888888" || cnpj == "99999999999999");
}

// valida numero inteiro com mascara
function mascaraInteiro() {
	if (event.which < 48 || event.which > 57) {
		event.returnValue = false;
		return false;
	}
	return true;
}

// formata de forma generica os campos
function formatarCampo(campo, Mascara, evento) {
	var boleanoMascara;

	var Digitato = evento.keyCode;
	exp = /\-|\.|\/|\(|\)| /g;
	campoSoNumeros = campo.value.toString().replace(exp, '');

	var posicaoCampo = 0;
	var NovoValorCampo = '';
	var TamanhoMascara = campoSoNumeros.length;

	if (Digitato != 8) { // backspace
		for (i = 0; i <= TamanhoMascara; i++) {
			boleanoMascara = ((Mascara.charAt(i) == '-')
					|| (Mascara.charAt(i) == '.') || (Mascara.charAt(i) == '/'))
			boleanoMascara = boleanoMascara
					|| ((Mascara.charAt(i) == '(')
							|| (Mascara.charAt(i) == ')') || (Mascara.charAt(i) == ' '))
			if (boleanoMascara) {
				NovoValorCampo += Mascara.charAt(i);
				TamanhoMascara++;
			} else {
				NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
				posicaoCampo++;
			}
		}
		campo.value = NovoValorCampo;
		return true;
	} else {
		return true;
	}
}