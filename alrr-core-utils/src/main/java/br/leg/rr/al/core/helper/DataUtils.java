package br.leg.rr.al.core.helper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.inject.Named;

//TODO: refatorar essa classe. Muito mal organizada. INCLUIR LocalDate PARA MANIPULAR DATA. JDK 8.
@Named
public final class DataUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8876432039374908379L;

	/**
	 * Tipos dos campos de uma data.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @version $Revision: 1.7 $
	 */
	public enum CampoDataTipo {
		DIA(Calendar.DAY_OF_MONTH), MES(Calendar.MONTH), ANO(Calendar.YEAR), DIA_SEMANA(Calendar.DAY_OF_WEEK);

		private CampoDataTipo(int tipoCampoData) {
			tipoCampo = tipoCampoData;
		}

		private int tipoCampo;

		public int getTipoCampo() {
			return tipoCampo;
		}

	}

	private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

	private static SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

	public static String DATA_HORA = "dd/MM/yyyy HH:mm";

	private static Map<String, SimpleDateFormat> cacheDateFormat;

	static {
		cacheDateFormat = new HashMap<String, SimpleDateFormat>();
		cacheDateFormat.put(DATA_HORA, new SimpleDateFormat(DATA_HORA));
		cacheDateFormat.get(DATA_HORA).setLenient(false);

	}

	/**
	 * Retorna a data corrente com com a primeira hora do dia (00:00). Também define
	 * o local igual a pt_BR.
	 * 
	 * @return data corrente com com a primeira hora do dia (00:00).
	 */
	public static Date hoje() {
		return setTimeZero(new Date());
	}

	/**
	 * Cria uma data e horário corrente, com o locale pt_BR.
	 * 
	 * @return data e horário corrente.
	 */
	public static Date agora() {
		return setTime(new Date());
	}

	/**
	 * Retorna a data corrente com com a primeira hora do dia (00:00)
	 * 
	 */
	public static Date ontem() {
		return trocarData(-1);
	}

	/**
	 * Retorna a data corrente com com a primeira hora do dia (00:00)
	 * 
	 */
	public static Date amanha() {

		return trocarData(1);
	}

	/**
	 * Define o calendário com o padrão pt_BR.
	 * 
	 * @param data
	 * @return retorna a data e o horário atual.
	 */
	public static Date setTime(Date data) {
		final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
		calendario.setTime(data);
		return calendario.getTime();
	}

	/**
	 * Retorna verdadeiro se o objeto Date <tt>_data</tt> representar um ano
	 * bisexto.<br>
	 * 
	 * @return Verdadeiro caso o ano seja bissexto, falso caso contrario
	 */
	public static boolean isAnoBisexto(java.util.Date _data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_data);
		return isAnoBisexto(calendar.get(Calendar.YEAR));
	}

	/**
	 * Verifica se o ano é bisexto.
	 * 
	 * @param _ano
	 * @return retorna true se for.
	 */
	public static boolean isAnoBisexto(int _ano) {
		if ((_ano % 4) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna a data com o horário em zerado (milisegundos, segundos, minutos e
	 * hora iguais a zero - primeira hora do dia).
	 * 
	 * @return
	 */
	public static Date setTimeZero(Date data) {
		final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
		calendario.setTime(data);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);

		return calendario.getTime();
	}

	/**
	 * Adiciona dias na dataBase passado como par metro. Para subtrair dias passar
	 * um valor negativo para o par metro numDias.
	 * 
	 * Ex.: para retorna a data de uma semana ap s o dia 26/05/2009 (assumindo que
	 * este o valor do par metro dataBase).
	 * 
	 * DataHelper.getInstance().adicionarDias(dataBase, 7);
	 * 
	 * O valor retornado ser o dia 02/06/2009.
	 * 
	 * @param dataBase a data a ser usada como base para o c lculo.
	 * @param numDias  quantidade de dias a ser adicionado/subtraido da data base
	 * @return
	 */
	public static Date trocarData(Date dataBase, int numDias) {
		final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
		calendario.setTime(dataBase);
		calendario.add(Calendar.DAY_OF_MONTH, numDias);

		return calendario.getTime();
	}

	/**
	 * Calcula uma nova data a partir de hoje.
	 * 
	 * @param numDias Número de dias do mês somado/subtraido. Valor pode ser
	 *                negativo ou positivo.
	 * @return retorna uma data a partir dos dias informados no parametro.
	 */
	public static Date trocarData(int numDias) {
		final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
		calendario.setTime(new Date());
		calendario.add(Calendar.DAY_OF_MONTH, numDias);

		return calendario.getTime();
	}

	/**
	 * Adiciona horas na dataBase passado como parâmetro. Para subtrair horas passar
	 * um valor negativo para o parâmetro numHoras.
	 * 
	 * Ex.: Dado uma data referente ao dia 26/05/2009 às 15:34 (assumindo que este é
	 * o valor do parâmetro dataBase).
	 * 
	 * DataHelper.getInstance().adicionarHoras(dataBase, 24);
	 * 
	 * O valor retornado será o dia 26/05/2009 às 15:34.
	 *
	 * @param data     a data a ser usada como base para o cálculo.
	 * @param numHoras quantidade de horas a ser adicionado/subtraído da data base
	 * @return
	 */
	public static Date adicionarHoras(Date data, int numHoras) {
		final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
		calendario.setTime(data);
		calendario.add(Calendar.HOUR_OF_DAY, numHoras);
		return calendario.getTime();
	}

	/**
	 * Compara duas datas informadas.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param dataArg1
	 * @param dataArg2
	 * @return
	 *         <li>0: as datas s o iguais (se ambas as datas forem null);
	 *         <li>1: a dataArg1 maior que a dataArg2 (se a dataArg2 for nula e a
	 *         dataArg1 n o);
	 *         <li>-1: a dataArg2 maior que a dataArg1 (se a dataArg1 for nula e a
	 *         dataArg2 n o);
	 */
	public static int compararDatas(Date dataArg1, Date dataArg2) {
		int igual = 0;

		if (dataArg1 == null && dataArg2 == null) {
			igual = 0;
		} else if (dataArg1 != null && dataArg2 == null) {
			igual = 1;
		} else if (dataArg1 == null && dataArg2 != null) {
			igual = -1;
		} else {
			igual = dataArg1.compareTo(dataArg2);
		}

		return igual;
	}

	/**
	 * Compara duas datas informadas levando em considera o apenas as datas,
	 * desconsiderando hora, minuto e segundo.
	 * 
	 * @author <a href="mailto:francisco.alves@pdcase.com.br">Francisco Alves</a>.
	 * @param dataArg1
	 * @param dataArg2
	 * @return
	 *         <li>0: as datas s o iguais (se ambas as datas forem null);
	 *         <li>1: a dataArg1 maior que a dataArg2 (se a dataArg2 for nula e a
	 *         dataArg1 n o);
	 *         <li>-1: a dataArg2 maior que a dataArg1 (se a dataArg1 for nula e a
	 *         dataArg2 n o);
	 */
	public static int compararSomenteData(Date dataArg1, Date dataArg2) {
		// Vari vel para armazenar o resultado
		int result = 0;

		// Verifica se alguma das datas possui valor nulo.
		if (dataArg1 == null && dataArg2 == null) {
			result = 0;
		} else if (dataArg1 != null && dataArg2 == null) {
			result = 1;
		} else if (dataArg1 == null && dataArg2 != null) {
			result = -1;
		}
		// Se nenhum dos argumentos for nulo realizar a compara o
		else {

			result = compararDatas(setTimeZero(dataArg1), setTimeZero(dataArg2));
		}

		return result;
	}

	/**
	 * Retorna um campo da data.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param dataBase
	 * @param tipoCampo
	 * @return
	 */
	public static Integer get(Date dataBase, CampoDataTipo tipoCampo) {
		if (dataBase != null) {
			Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
			calendario.setTime(dataBase);

			Integer retorno = calendario.get(tipoCampo.getTipoCampo());
			return retorno;
		}
		return null;
	}

	/**
	 * Verifica se uma data esta no per odo informado pela dataInicio e dataFim.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param dataInicio data inicio do periodo
	 * @param dataFim    data final do periodo
	 * @param data       dentro a ser averiguada dentro do periodo informado.
	 * @return true se:
	 *         <li>dataInicio e dataFim forem nulas;
	 *         <li>a dataInicio for nula e a data estiver anterior a dataFim;
	 *         <li>a dataFim for nula e a data estiver posterior a dataInicio; e
	 *         <li>a data estiver antes da daaFim e depois da dataInicio.
	 */
	public static boolean periodoContemData(Date dataInicio, Date dataFim, Date data) {
		if (dataInicio == null && dataFim == null) {
			return true;
		} else if (dataInicio == null && dataFim.compareTo(data) >= 0) {
			return true;
		} else if (dataFim == null && dataInicio.compareTo(data) <= 0) {
			return true;
		} else if ((dataInicio != null && dataInicio.compareTo(data) <= 0)
				&& (dataFim != null && dataFim.compareTo(data) >= 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Cria uma data com o dia especificado, com o m s e ano corrente.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param diaCorte
	 * @return
	 */
	public static Date criarData(Integer dia) {

		return criarData(dia, null, null);
	}

	/**
	 * Cria uma data com os valores especificados para o dia, mes e ano.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param diaCorte
	 * @param mes
	 * @param ano
	 * @return
	 */
	public static Date criarData(Integer dia, Integer mes, Integer ano) {
		Date retorno = new Date();

		if (dia != null) {
			retorno = set(retorno, CampoDataTipo.DIA, dia);
		}
		if (mes != null) {
			retorno = set(retorno, CampoDataTipo.MES, mes);
		}
		if (ano != null) {
			retorno = set(retorno, CampoDataTipo.ANO, ano);
		}
		return setTimeZero(retorno);
	}

	/**
	 * Cria uma data com os valores especificados para o dia e mes.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param diaCorte
	 * @param mes
	 * @param ano
	 * @return
	 */
	public static Date criarData(Integer dia, Integer mes) {

		return criarData(dia, mes, null);
	}

	/**
	 * Retorna uma data baseada na dataBase com o valor alterado de acordo com o
	 * tipo do campo.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param dataBase
	 * @param tipo
	 * @param valor
	 */
	public static Date set(Date dataBase, CampoDataTipo tipo, Integer valor) {
		// if (CampoDataTipo.MES.equals(tipo)) {
		// valor -= 1;
		// }
		final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
		calendario.setTime(dataBase);
		calendario.set(tipo.getTipoCampo(), valor);
		return calendario.getTime();
	}

	/**
	 * Formata a data de acordo com o padrao informado
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param data
	 * @param padrao
	 * @return
	 */
	public static String formatar(Date data, String padrao) {
		if (!cacheDateFormat.containsKey(padrao)) {
			cacheDateFormat.put(padrao, new SimpleDateFormat(padrao));
		}
		return cacheDateFormat.get(padrao).format(data);
	}

	/**
	 * Alterar a hora da dataBase com o valor da hora.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param dataBase
	 * @param hora
	 * @return
	 */
	public static Date alterarHoraDataBase(Date dataBase, Date hora) {
		if (dataBase != null && hora != null) {
			Calendar calendarioHora = Calendar.getInstance();
			calendarioHora.setTime(hora);

			return alterarHora(dataBase, calendarioHora.get(Calendar.HOUR_OF_DAY), calendarioHora.get(Calendar.MINUTE),
					calendarioHora.get(Calendar.SECOND));
		}
		return dataBase;
	}

	/**
	 * Altea os valores das horas informadas.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param date
	 * @param hora
	 * @param minutos
	 * @param seguntos
	 * @return
	 */
	public static Date alterarHora(Date dataBase, int hora, int minutos, int seguntos) {
		if (dataBase != null) {
			final Calendar calendario = Calendar.getInstance(new Locale("pt", "BR"));
			calendario.setTime(dataBase);
			calendario.set(Calendar.HOUR_OF_DAY, hora);
			calendario.set(Calendar.MINUTE, minutos);
			calendario.set(Calendar.SECOND, seguntos);
			calendario.set(Calendar.MILLISECOND, 0);

			return calendario.getTime();
		}
		return null;

	}

	public static int getAnoAtual() {
		return get(new Date(), CampoDataTipo.ANO);
	}

	public static int getMesAtual() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static String getMesAtualAsString() {
		return getMesAsString(LocalDate.now());
	}

	public static int getDiaAtual() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getDiferencaEmDias(Date dataMaior, Date dataMenor) {

		dataMaior = setTimeZero(dataMaior);
		dataMenor = setTimeZero(dataMenor);

		Long differenceMilliSeconds = dataMaior.getTime() - dataMenor.getTime();

		// segundos/minutos/horas/dias = 1000/60/60/24
		differenceMilliSeconds = (differenceMilliSeconds / 1000 / 60 / 60 / 24);

		return differenceMilliSeconds.intValue();

	}

	/**
	 * Obtém a diferença entre duas datas, de um mesmo dia, formatada segundo o
	 * padrão "[-]99:99".
	 * 
	 * @param data1 primeira data
	 * @param data2 segunda data
	 * @return diferença entre datas formatada
	 */
	public static String getDiferencaIntevalo(final Date data1, final Date data2) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();
		cal1.setTime(data1);
		cal2.setTime(data2);

		long timestamp1 = cal1.getTimeInMillis();
		long timestamp2 = cal2.getTimeInMillis();

		String sinal = "";
		// Se a data 1 for maior que a data 2, o intervalo será negativo
		if (timestamp1 > timestamp2) {
			sinal = "-";
			long aux = timestamp1;
			timestamp1 = timestamp2;
			timestamp2 = aux;
		}

		final long diferenca = timestamp2 - timestamp1;
		final long horas = diferenca / (60 * 60 * 1000);
		final long minutos = diferenca / (60 * 1000) - horas * 60;

		// Formatacao das horas
		StringBuilder s = new StringBuilder("00" + horas);
		String sHoras = s.substring(s.length() - 2, s.length());

		// Formatacao dos minutos
		s = new StringBuilder("00" + minutos);
		String sMinutos = s.substring(s.length() - 2, s.length());

		return sinal + sHoras + ":" + sMinutos;
	}

	/**
	 * 
	 * @param dataini1
	 * @param datafim1
	 * @param dataini2
	 * @param datafim2
	 * @return
	 */
	public static String getDiferencaIntevalos(final Date dataIni1, final Date dataFim1, final Date dataIni2,
			final Date dataFim2) {
		final Calendar cal = Calendar.getInstance();

		cal.setTime(dataIni1);
		final long timestamp1 = cal.getTimeInMillis();

		cal.setTime(dataFim1);
		final long timestamp2 = cal.getTimeInMillis();

		cal.setTime(dataIni2);
		final long timestamp3 = cal.getTimeInMillis();

		cal.setTime(dataFim2);
		final long timestamp4 = cal.getTimeInMillis();

		long diferenca = timestamp2 - timestamp1;
		final long minutos1 = diferenca / (60 * 1000);

		diferenca = timestamp4 - timestamp3;
		final long minutos2 = diferenca / (60 * 1000);

		String sinal = "";

		long diferencaMinutos;
		if (minutos2 >= minutos1) {
			diferencaMinutos = minutos2 - minutos1;
		} else {
			diferencaMinutos = minutos1 - minutos2;
			sinal = "-";
		}

		long horas = diferencaMinutos / 60;
		long minutos = diferencaMinutos - horas * 60;

		// Formatacao das horas
		StringBuilder s = new StringBuilder("00" + horas);
		String sHoras = s.substring(s.length() - 2, s.length());

		// Formatacao dos minutos
		s = new StringBuilder("00" + minutos);
		String sMinutos = s.substring(s.length() - 2, s.length());

		return sinal + sHoras + ":" + sMinutos;
	}

	/**
	 * Retorna a data UTC a partir da data informada.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param dataBase
	 * @return
	 * @throws ParseException
	 */
	public static Date getDataUTC(Date dataBase) {
		if (dataBase == null) {
			throw new IllegalArgumentException("A dataBase não pode ser nula");
		}
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-0"));
		cal.setTime(dataBase);

		Date retorno = criarData(cal.get(Calendar.DATE), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

		return alterarHora(retorno, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}

	/**
	 * Obtem o dia do mes da data especificada.
	 * 
	 * @param data a data da qual sera obtido o dia do mes.
	 * @return dia do mes
	 */
	public static int getDia(final Date data) {
		return get(data, CampoDataTipo.DIA);
	}

	/**
	 * Obtem o dia da semana (2 = segunda, 3 = terca, etc) da data especificada.
	 * 
	 * @param data a data da qual sera obtido o dia da semana.
	 * @return representacao numerica do dia da semana
	 */
	public static int getDiaSemana(final Date data) {
		return get(data, CampoDataTipo.DIA_SEMANA);
	}

	public static String getDiaSemanaAsString(final Date dataNascimento) {
		if (dataNascimento != null) {
			LocalDate data = asLocalDate(dataNascimento);
			return getDiaSemanaAsString(data);
		}
		return null;

	}

	public static String getDiaSemanaAsString(final LocalDate data) {
		if (data != null) {
			return data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
		}
		return null;
	}

	public static int getMes(final Date data) {
		return get(data, CampoDataTipo.MES) + 1;
	}

	public static String getMesAsString(Date dataNascimento) {
		LocalDate data = asLocalDate(dataNascimento);
		return getMesAsString(data);

	}

	public static String getMesAsString(LocalDate data) {
		if (data != null) {
			return data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
		}
		return null;

	}

	/**
	 * Obtem o ano da data especificada.
	 * 
	 * @param data a data da qual sera obtido o ano
	 * @return ano da data especificada
	 */
	public static int getAno(final Date data) {
		return get(data, CampoDataTipo.ANO);
	}

	/**
	 * Obtem um dia da semana qualquer, numa semana qualquer, a partir da data
	 * especificada.
	 * 
	 * @param date      a data a partir da qual sera obtida o proximo dia da semana
	 *                  desejado
	 * @param diaSemana dia da semana para a data a ser obtida
	 * @param semana    numero que identifica a ordem da semana em relacao ao data
	 *                  especificada
	 * @return a proxima data cujo dia da semana e igual ao especificado e na semana
	 *         desejada
	 */
	public static Date computeNext(final Date date, final int diaSemana, final int semana) {
		final Calendar cal = Calendar.getInstance();
		final int diaCorrente = getDiaSemana(date);
		final int fator = (diaCorrente / diaSemana) > 0 ? 1 : 0;
		final int amount = (-1 * diaCorrente) + diaSemana + fator * 7 + (semana - 1) * 7;

		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);

		return setTimeZero(cal.getTime());
	}

	/**
	 * Verifica se todas as datas contidas na lista informada sao maiores que
	 * <code>dataFim</code>.
	 * 
	 * @param datas   lista de datas a ser verificada
	 * @param dataFim a data a ser comparada as datas listadas
	 * @return <code>true</code> se todas as datas da lista forem maiores que
	 *         <code>dataFim</code>, caso contrario <code>false</code>
	 */
	public static boolean todasMaioresQueDataFim(final List<Date> datas, final Date dataFim) {
		boolean resultado = true;

		for (Date date : datas) {
			if (isMenorIgual(date, dataFim)) {
				resultado = false;
				break;
			}
		}

		return resultado;
	}

	/**
	 * Copia horas e minutos da hora informada para a data especificada.
	 * 
	 * @param data a data que tera sua hora/minutos alterados de acordo com a hora
	 *             informada
	 * @param hora a hora a serem copiada para a data
	 * @return data com a hora igual a hora informada
	 */
	public static Calendar copiaHorasEMinutos(final Date data, final Date hora) {
		final Calendar calData = Calendar.getInstance();
		final Calendar calHora = Calendar.getInstance();

		calHora.setTime(hora);
		calData.setTime(data);

		calData.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
		calData.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));

		return calData;
	}

	public static Date copiaTempoReferenteEpoca(final Date hora) {
		final Calendar calEpoca = Calendar.getInstance();
		calEpoca.set(1970, 0, 1);

		final Calendar calHora = Calendar.getInstance();
		calHora.setTime(hora);

		calEpoca.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
		calEpoca.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));
		calEpoca.set(Calendar.SECOND, calHora.get(Calendar.SECOND));
		calEpoca.set(Calendar.MILLISECOND, 0);

		return calEpoca.getTime();
	}

	public static Date getDataInicioMes(final Date date) {
		final Calendar cal = Calendar.getInstance();

		if (date != null) {
			cal.setTime(date);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date getDataFimMes(final Date date) {
		final Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	public static Date getData(int dia, int mes, int ano) {
		return asDate(LocalDate.of(ano, mes, dia));
	}

	/**
	 * Recupera o primeiro dia e ultimo dia da semana com suas respectivas datas
	 * tomando como parametros a data atual do sistema ou a data escolhida pelo
	 * usuario no calendario. Onde a agenda será atualizada de acordo com a data
	 * escolhida.
	 * 
	 * @param data
	 * @param isPrimeiro
	 * @return
	 */
	public static Date recuperaDataPrimeiroUltimoDiaSemana(Date data, boolean isPrimeiro) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);

		if (isPrimeiro) {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.add(Calendar.DAY_OF_WEEK, -1);
		} else {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			calendar.add(Calendar.DAY_OF_WEEK, 1);
		}

		return calendar.getTime();
	}

	/**
	 * Verifica se a hora inicio e menor que a hora fim.
	 * 
	 * @param horaInicio hora de inicio
	 * @param horaFim    hora de fim
	 * @return <code>true</code> se horaInicio for menor que horaFim, caso contrario
	 *         <code>false</code>
	 */
	public static boolean isHoraInferior(final Date horaInicio, final Date horaFim) {
		final Calendar calIni = Calendar.getInstance();
		final Calendar calFim = Calendar.getInstance();
		calIni.setTime(horaInicio);
		calFim.setTime(horaFim);

		// Garante dia/mes/ano iguais para as duas datas
		calIni.set(2000, 0, 1);
		calFim.set(2000, 0, 1);

		return calIni.before(calFim);
	}

	public static boolean isMenorIgual(Date data1, Date data2) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();
		cal1.setTime(setTimeZero(data1));
		cal2.setTime(setTimeZero(data2));
		return !cal1.after(cal2);
	}

	/**
	 * Verifica se a data informada é "hoje".
	 * 
	 * @param data Data verificada.
	 * @return retorna true, se data for hoje, e false caso contrário.
	 */
	public static boolean isHoje(final Date date) {
		final Calendar calToday = Calendar.getInstance();
		final Calendar cal = Calendar.getInstance();

		calToday.setTime(setTimeZero(new Date()));
		cal.setTime(setTimeZero(date));

		return cal.equals(calToday);
	}

	/**
	 * Verifica se a data informada é "amanhã".
	 * 
	 * @param data Data verificada.
	 * @return retorna <i>true</i>, se data for amanhã, e <i>false</i> caso
	 *         contrário.
	 */
	public static boolean isAmanha(final Date date) {

		final Calendar cal = Calendar.getInstance();

		final Calendar amanha = Calendar.getInstance();
		amanha.setTime(setTimeZero(new Date()));
		// adicionar mais um dia na data atual do calendário. Ou seja, define
		// para amanhã.
		amanha.add(Calendar.DAY_OF_YEAR, 1);
		cal.setTime(setTimeZero(date));

		return cal.equals(amanha);
	}

	/**
	 * Verifica se a data informada é "depois" da data de hoje.
	 * 
	 * @param data Data verificada.
	 * @return retorna true, se data for antes de hoje, e false caso contrário.
	 */
	public static boolean isDepoisHoje(final Date data) {
		final Calendar calAtual = Calendar.getInstance();
		final Calendar cal = Calendar.getInstance();

		calAtual.setTime(setTimeZero(new Date()));
		cal.setTime(setTimeZero(data));

		return cal.after(calAtual);
	}

	/**
	 * Verifica se a data informada é "antes" da data de hoje.
	 * 
	 * @param data Data verificada.
	 * @return retorna true, se data for depois de hoje, e false caso contrário.
	 */
	public static boolean isAntesHoje(final Date data) {

		final Calendar calAtual = Calendar.getInstance();
		final Calendar cal = Calendar.getInstance();

		calAtual.setTime(setTimeZero(new Date()));
		cal.setTime(setTimeZero(data));

		return cal.before(calAtual);
	}

	public static int compareTo(final Date date1, final Date date2, final boolean ignoreTime) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();

		if (ignoreTime) {
			cal1.setTime(setTimeZero(date1));
			cal2.setTime(setTimeZero(date2));
		} else {
			cal1.setTime(date1);
			cal2.setTime(date2);
		}

		return cal1.compareTo(cal2);
	}

	public static Date parseData(final String date) {

		formatDate.setLenient(false);

		try {
			return formatDate.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date parseTime(final String time) {
		Date newDate = null;
		formatHora.setLenient(false);
		try {
			newDate = formatHora.parse(time);
		} catch (ParseException e) {
		}
		return newDate;
	}

	public static Date format(final String date, String pattern) {

		if (!cacheDateFormat.containsKey(pattern)) {
			cacheDateFormat.put(pattern, new SimpleDateFormat(pattern));
			cacheDateFormat.get(pattern).setLenient(false);
		}

		try {
			return cacheDateFormat.get(pattern).parse(date);
		} catch (ParseException e) {
		}

		return null;
	}

	/**
	 * @param value
	 * @return retorna true se dia está entre 1 a 31.
	 */
	public static boolean isDia(String value) {
		if (value == null) {
			return true;
		}

		int dia = Integer.valueOf(value);

		return isDia(dia);
	}

	public static boolean isDia(Integer value) {
		if (value == null) {
			return true;
		}

		return (value > 0 && value < 32);
	}

	/**
	 * Transforma uma Data com horário, em apenas uma string no formato HH:mm.
	 * 
	 * @param data data que contém o horário.
	 * @return String formatada HH:mm.
	 */
	public static String getHoraMinuto(Date data) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
		return localDateFormat.format(data);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getHoraMinutoSegundo(Date data) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		return localDateFormat.format(data);
	}

	/**
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static LocalDate asLocalDate(Date data) {
		return Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static LocalDateTime asLocalDateTime(Date data) {
		return Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date getPrimeiraDataMes(Date data) {
		LocalDate dt = asLocalDate(data);
		LocalDate primeiraData = dt.with(TemporalAdjusters.firstDayOfMonth());
		return asDate(primeiraData);
	}

}
