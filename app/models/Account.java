package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Account extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	private long accountNumber;
	private long balance;
	@OneToMany(targetEntity = BangkingTransaction.class, mappedBy = "senderAccount")
	private List<BangkingTransaction> transactionHistory;

	@JsonProperty("id")
	public long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long l) {
		this.balance = l;
	}

	public void deposit(long l) {
		this.setBalance(getBalance() + l);
	}

	public List<BangkingTransaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(
			List<BangkingTransaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public static Finder<Long, Account> find = new Finder<>(Long.class,
			Account.class);

	public static Account findByAccountNumber(Long acc) {
		return find.where().eq("accountNumber", acc).findUnique();
	}

}
