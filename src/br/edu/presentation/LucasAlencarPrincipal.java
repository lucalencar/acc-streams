package br.edu.presentation;

import java.util.ArrayList;
import java.util.List;
//import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//import javax.security.auth.login.AccountException;

import br.edu.entities.Account;
import br.edu.entities.AccountEnum;
import br.edu.entities.Address;
import br.edu.entities.Client;
import br.edu.service.BankService;
import br.edu.service.ServiceFactory;

/**
 * OBSERVAÇÕES: 
 * NÃO é permitido o uso de nenhuma estrutura de repetição (for, while, do-while).
 * Tente, ao máximo, evitar o uso das estruturas if, else if, else e switch-case
 * 
 */
public class LucasAlencarPrincipal {

	private static BankService service = ServiceFactory.getService();	

	private static List<Client> clientes = new ArrayList<>();
	private static List<Address> enderecos = new ArrayList<>();
	private static List<Account> contas = new ArrayList<>();
	
	
	public static void main(String[] args) {
		//TO test here
		// Complete o código aqui
		AccountEnum checking = AccountEnum.CHECKING;
		AccountEnum saving = AccountEnum.SAVING;
		AccountEnum joint = AccountEnum.JOINT;


		Address endereco1 = new Address("Rua Larousse", 76, "Apt. 1301", "PE", "Brazil");
		Address endereco2 = new Address("Rua Azeredo Mota", 110, "Apt. 205", "PE", "Brazil");
		Address endereco3 = new Address("Rua Souza Lima", 300, "Apt. 100", "SP", "Brazil");
		Address endereco4 = new Address("Crescent Street", 50, "Office. 101", "NY", "United States");
		Address endereco5 = new Address("St. Catherine Street", 100, "Office. 200", "CA", "United States");
		Address endereco6 = new Address("Amhrest Street", 205, "app. 501", "NY", "United States");

		enderecos.add(endereco1);
		enderecos.add(endereco2);
		enderecos.add(endereco3);
		enderecos.add(endereco4);
		enderecos.add(endereco5);
		enderecos.add(endereco6);

		Client cliente1 = new Client("José Alves", "jose.alves@gmail.com", endereco1);
		Client cliente2 = new Client("Amanda Nunes", "amanda.nunes15@outlook.com", endereco2);
		Client cliente3 = new Client("Luana Cavalcanti", "luana.calva10@gmail.com", endereco3);
		Client cliente4 = new Client("Kendrick Jones", "kendrick.jones@gmail.com", endereco4);
		Client cliente5 = new Client("Ellen Gates", "ellen.gates10@gmail.com", endereco5);
		Client cliente6 = new Client("John Greenwood", "john.greenw@protonmail.com", endereco6);


		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		clientes.add(cliente6);
		
		Account conta1 = new Account(143, 12345, 15.450, cliente1, checking);
		Account conta2 = new Account(144, 34345, 3.200, cliente2, saving);
		Account conta3 = new Account(143, 54334, 40.200, cliente3, checking);
		Account conta4 = new Account(222, 21324, 20.200, cliente4, saving);
		Account conta5 = new Account(321, 43546, 30.200, cliente5, checking);
		Account conta6 = new Account(222, 87675, 70.100, cliente6, checking);
		Account conta7 = new Account(143, 75755, 5.100, cliente1, joint);
		Account conta8 = new Account(143, 75755, 5.100, cliente3, joint);
		


		contas.add(conta1);
		contas.add(conta2);
		contas.add(conta3);
		contas.add(conta4);
		contas.add(conta5);
		contas.add(conta6);
		contas.add(conta7);
		contas.add(conta8);		

		//imprimirNomesClientes();
		System.out.println("....................................................");
		System.out.printf("The richest country is ");
		if (imprimirPaisClienteMaisRico() == 1) {
			System.out.println("Brazil");
		} else {
			System.out.println("The United States");
		}
		System.out.println("....................................................");
		System.out.printf("Número das contas no Brasil: " + getNumerosContas("Brazil") + "\n");
		System.out.printf("Número das contas nos EUA: " + getNumerosContas("United States") + "\n");

		System.out.println("....................................................");
		System.out.println("Clientes com conta poupança: ");
		imprimirClientesComPoupanca();

		//System.out.println(service.listAccounts());
		System.out.println("....................................................");
		System.out.println("Lista de contas conjuntas: ");
		System.out.println(getContasConjuntas(clientes));
		//Tentei usar o método getContasConjuntas com a classe BankSerice e sem ela mas das duas formas retornaram nulo


	}

	/**
	 * 1. Imprima na tela o nome e e-mail de todos os clientes (sem repetição), usando o seguinte formato:
	 * Ze Mane - ze@mail.br
	 */
	public static void imprimirNomesClientes() {
		clientes.stream()
		.map(cliente -> cliente.getName() +" - "+ cliente.getEmail())
		.distinct()
		.forEach(System.out::println);
	}

	/**
	 * 2. Imprima na tela o nome do cliente e a média do saldo de suas contas, ex:
	 * Ze Mane - 352
	 */
	public static void imprimirMediaSaldos() {
		clientes
		.stream()
		.forEach(cliente -> {
			double media = 
					contas
					.stream()
					.filter(conta -> conta.getClient().getName().equals(cliente.getName()))
					.mapToDouble(conta -> conta.getBalance())
					.average()
					.getAsDouble();
			
			System.out.println(cliente.getName() + " - " + media);
		});
	}

	/**
	 * 3. Considerando que só existem os países "Brazil" e "United States", 
	 * imprima na tela qual deles possui o cliente mais rico, ou seja,
	 * com o maior saldo somando todas as suas contas.
	 */
	public static int imprimirPaisClienteMaisRico() {
		double sumClientBrazil = 
				contas
				.stream()
				.filter(conta -> conta.getClient().getAddress().getCountry().equals("Brazil"))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		// Complete o código aqui
		double sumClienteUSA =
				contas
				.stream()
				.filter(conta -> conta.getClient().getAddress().getCountry().equals("United States"))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		return Double.compare(sumClientBrazil, sumClienteUSA);

	}

	/**
	 * 4. Imprime na tela o saldo médio das contas da agência
	 * @param agency
	 */
	public static void imprimirSaldoMedio(int agency) {	
		double average = 
				contas
				.stream()
				.filter(conta -> conta.getAgency() == agency)
				.mapToDouble(conta -> conta.getBalance())
				.average()
				.getAsDouble();
		System.out.println(average);
	}

	/**
	 * 5. Imprime na tela o nome de todos os clientes que possuem conta poupança (tipo SAVING)
	 */
	public static void imprimirClientesComPoupanca() {
		// Complete o código aqui
		contas.stream()
		.filter(contas -> contas.getType() == AccountEnum.SAVING)
		.map(contas -> contas.getClient().getName())
		.forEach(System.out::println);
	
	}

	/**
	 * 6.
	 * @param agency
	 * @return Retorna uma lista de Strings com o "estado" de todos os clientes da agência
	 */
	public static List<String> getEstadoClientes(int agency) {
		List<String> stateOfAllClients = 
				contas
				.stream()
				.filter(conta -> conta.getAgency() == agency)
				.map(conta -> conta.getClient().getAddress().getState())
				.collect(Collectors.toList());
		return (List<String>) stateOfAllClients;
	}

	/**
	 * 7.
	 * @param country
	 * @return Retorna uma lista de inteiros com os números das contas daquele país
	 */
					
	public static List<Integer> getNumerosContas(String country) {
			// Complete o código aqui
			List<Integer> countryNumbers = 
						contas
						.stream()
						.filter(conta -> conta.getClient().getAddress().getCountry() == country)
						.map(conta -> conta.getNumber())
						.collect(Collectors.toList());
						
				
		return (List<Integer>) countryNumbers;
	}
	

	/**
	 * 8.
	 * Retorna o somatório dos saldos das contas do cliente em questão 
	 * @param clientEmail
	 * @return
	 */
	public static double getMaiorSaldo(String clientEmail) {
		double sumBalance =
				contas
				.stream()
				.filter(conta -> conta.getClient().getEmail().equals(clientEmail))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		return sumBalance;
	}

	/**
	 * 9.
	 * Realiza uma operação de saque na conta de acordo com os parâmetros recebidos
	 * @param agency
	 * @param number
	 * @param value
	 */
	public static void sacar(int agency, int number, double value) {
		contas
		.stream()
		.filter(conta -> conta.getAgency( ) == agency && conta.getNumber() == number)
		.mapToDouble(conta -> { 
			double operacao = conta.getBalance() - value; 
			conta.setBalance(operacao);
			return operacao;
		})
		.sum();	
	}

	/**
	 * 10. Realiza um deposito para todos os clientes do país em questão	
	 * @param country
	 * @param value
	 */
	public static void depositar(String country, double value) {
		contas
		.stream()
		.filter(conta -> conta.getClient().getAddress().getCountry().equals(country))
		.mapToDouble(conta -> {
			double deposito = conta.getBalance() + value;
			conta.setBalance(deposito);
			return deposito;
		})
		.sum();
	}

	/**
	 * 11. Realiza uma transferência entre duas contas de uma agência.
	 * @param agency - agência das duas contas
	 * @param numberSource - conta a ser debitado o dinheiro
	 * @param numberTarget - conta a ser creditado o dinheiro
	 * @param value - valor da transferência
	 */
	public static void transferir(int agency, int numberSource, int numberTarget, double value) {
		contas
		.stream()
		.filter(conta -> conta.getAgency() == agency && conta.getNumber() == numberSource)
		.mapToDouble(conta -> {
			double transferencia = conta.getBalance() - value;
			conta.setBalance(transferencia);
			return transferencia;
		})
		.sum();
		
		contas
		.stream()
		.filter(conta -> conta.getAgency() == agency && conta.getNumber() == numberTarget)
		.mapToDouble(conta -> {
			double valorRecebido = conta.getBalance() + value;
			conta.setBalance(valorRecebido);
			return valorRecebido;
		})
		.sum();
		
	}

	/**
	 * 12.
	 * @param clients
	 * @return Retorna uma lista com todas as contas conjuntas (JOINT) dos clientes
	 */											//List<Client> clients
	public static List<Account> getContasConjuntas(List<Client> clients) {
		// Complete o código aqui
		List<Account> jointAccounts = new ArrayList<Account>();
		 service.listAccounts()
		.stream()
		.filter(conta -> conta.getClient() == clients)
		.map(conta -> conta.getType()== AccountEnum.JOINT)
		.collect(Collectors.toList());
		
		return (List<Account>) jointAccounts;
	}

/* 	public static List<Account> getContasConjuntas(List<Client> clients) {
		List<Account> jointAccounts = new ArrayList<Account>();
		contas
		.stream()
		.filter(conta -> conta.getClient() == clients)
		.map(conta -> conta.getType() == AccountEnum.JOINT)
		.collect(Collectors.toList());
		// Complete o código aqui

		return jointAccounts;
	} */

	/**
	 * 13.
	 * @param state
	 * @return Retorna uma lista com o somatório dos saldos de todas as contas do estado 
	 */
	public static double getSomaContasEstado(String state) {
		double sumAccountState =
				contas
				.stream()
				.filter(conta -> conta.getClient().getAddress().getState().equals(state))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		return sumAccountState;
	}

	/**
	 * 14.
	 * @return Retorna um array com os e-mails de todos os clientes que possuem contas conjuntas
	 */			
	public static List<String> getEmailsClientesContasConjuntas() {
		  	List<String> emailsAllClientsJoinAccounts =
		  		contas
		  		.stream()
		  		.filter(conta -> conta.getType().equals(AccountEnum.JOINT))
		  		.map(conta -> conta.getClient().getEmail())
				.collect(Collectors.toList());
		
		return emailsAllClientsJoinAccounts;		
	}

	/**
	 * 15.
	 * @param number
	 * @return Retorna se o número é primo ou não
	 */
	public static boolean isPrimo(int number) {
		return IntStream
				.rangeClosed(2, (number/2))
				.noneMatch(i -> number % i==0);
	}

	/**
	 * 16.
	 * @param number
	 * @return Retorna o fatorial do número
	 */
	public static int getFatorial(int number) {
		int factorial =
				IntStream.rangeClosed(1, number)
				.reduce(1, (x,y) -> x*y);
		return factorial;
	}
}
