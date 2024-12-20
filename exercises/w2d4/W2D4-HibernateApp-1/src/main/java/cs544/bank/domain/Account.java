package cs544.bank.domain;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	private long accountnumber;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public Account(long accountnr) {
		this.accountnumber = accountnr;
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entryList) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(new Date(), amount, "deposit", "", "");
		entryList.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw", "", "");
		entryList.add(entry);
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(
				new Date(),
				-amount,
				description,
				"" + toAccount.getAccountnumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(
				new Date(),
				amount,
				description,
				"" + toAccount.getAccountnumber(),
				toAccount.getCustomer().getName());
		entryList.add(fromEntry);
		toAccount.addEntry(toEntry);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

}
