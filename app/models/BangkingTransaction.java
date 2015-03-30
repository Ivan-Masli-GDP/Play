package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class BangkingTransaction extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int amount;
	@ManyToOne(targetEntity = Account.class)
	private Account senderAccount;
	@ManyToOne(targetEntity = Account.class)
	private Account receiverAccount;
	public void initiate() {
		
		
	}
}
