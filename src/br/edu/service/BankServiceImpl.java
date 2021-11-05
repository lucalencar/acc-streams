package br.edu.service;

import java.util.List;

import br.edu.entities.Account;
import br.edu.entities.Client;
import br.edu.helper.LoadEntities;

public class BankServiceImpl implements BankService {

	protected BankServiceImpl() { }
	
	@Override
	public List<Account> listAccounts() {
		return LoadEntities.ACCOUNTS;
	}

	@Override
	public List<Client> listClients() {
		return LoadEntities.CLIENTS;
	}
	
}
