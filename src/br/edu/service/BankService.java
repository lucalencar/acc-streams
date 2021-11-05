package br.edu.service;

import java.util.List;

import br.edu.entities.Account;
import br.edu.entities.Client;

public interface BankService {
	public List<Account> listAccounts();
	public List<Client> listClients();

}


