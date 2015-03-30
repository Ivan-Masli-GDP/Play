package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

@Entity
public class BangkingTransaction extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long transaction_id;
	private long amount;
	@ManyToOne(targetEntity = Account.class)
	private Account senderAccount;
	@ManyToOne(targetEntity = Account.class)
	private Account receiverAccount;

	private Date transactionDate;

	public static Finder<Long, BangkingTransaction> find = new Finder<>(
			Long.class, BangkingTransaction.class);

	public static List<BangkingTransaction> findByAccountNumber(Long acc) {
//		String req = "select id,transaction_id,amount,sender_account_id,receiver_account_id,transaction_date from bangking_transaction where sender_account_id == "+acc;
//		RawSql rawSql = RawSqlBuilder
//	            .parse(req)
//	            .create();
//		Query<BangkingTransaction> query = Ebean.find(BangkingTransaction.class);
//		query.setRawSql(rawSql);
//		return query.findList();
		return Ebean.find(BangkingTransaction.class).select("transaction_id").where().eq("senderAccount.accountNumber", acc).findList();
		
	}

	public static class Builder {

		private long amount;
		private long transaction_id;
		private Account senderAccount;
		private Account receiverAccount;
		private Date transactionDate;

		public Builder amount(long value) {
			this.amount = value;
			return this;
		}

		public Builder transaction_id(long id) {
			this.transaction_id = id;
			return this;
		}

		public Builder transactionDate(Date date) {
			this.transactionDate = date;
			return this;
		}

		public Builder senderAccount(Account acc) {
			this.senderAccount = acc;
			return this;
		}

		public Builder receiverAccount(Account acc) {
			this.receiverAccount = acc;
			return this;
		}

		public BangkingTransaction build() {
			return new BangkingTransaction(this);
		}
	}

	public BangkingTransaction(Builder builder) {
		amount = builder.amount;
		transactionDate = builder.transactionDate;
		senderAccount = builder.senderAccount;
		receiverAccount = builder.receiverAccount;
		transaction_id = builder.transaction_id;
	}

}
