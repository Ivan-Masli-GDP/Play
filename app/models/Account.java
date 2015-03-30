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
	@Column(unique=true)
	private long accountNumber;
	private int balance;
	@OneToMany(targetEntity=BangkingTransaction.class,mappedBy="senderAccount")
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public List<BangkingTransaction> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<BangkingTransaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

}
