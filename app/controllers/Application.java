package controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;

import models.Account;
import models.BangkingTransaction;
import models.BangkingTransaction.Builder;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render(play.core.PlayVersion.current()));
	}

	public static Result saveAccount() {
		JsonNode json = request().body().asJson();
		Account newAccount = Json.fromJson(json.get("Account"), Account.class);
		try {
			newAccount.save();
		} catch (PersistenceException e) {
			return badRequest("Account has already exist");
		}

		return ok("Accepted");
	}

	public static Result transferFunds() throws ParseException {
		JsonNode json = request().body().asJson();
		long senderAccountNumber = Long.parseLong(json.get("from").asText());

		Account senderAccount = Account
				.findByAccountNumber(senderAccountNumber);
		if (senderAccount == null) {
			return badRequest("Sender account is not registered");
		}

		long receiverAccountNumber = Long.parseLong(json.get("to").asText());
		Account receiverAccount = Account
				.findByAccountNumber(receiverAccountNumber);
		if (receiverAccount == null) {
			return badRequest("Receiver account is not registered");
		}

		long transferAmount = Long.parseLong(json.get("amount").asText());

		if (senderAccount.getBalance() < transferAmount) {
			return badRequest("Insufficient fund");
		}

		senderAccount.setBalance(senderAccount.getBalance() - transferAmount);
		receiverAccount.setBalance(receiverAccount.getBalance()
				+ transferAmount);

		long transfer_id = Long.parseLong(json.get("transfer_id").asText());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
		Date transactionDate = new Date(format.parse(json.get("date").asText())
				.getTime());
		Builder build = new BangkingTransaction.Builder();
		build.amount(transferAmount).transaction_id(transfer_id)
				.receiverAccount(receiverAccount).senderAccount(senderAccount)
				.transactionDate(transactionDate).build().save();
		return ok("Transaction success");

	}

	public static Result depositFund() {
		JsonNode json = request().body().asJson();
		long AccountNumber = Long.parseLong(json.get("account").asText());
		long depositAmmount = Long.parseLong(json.get("amount").asText());

		Account account = Account.findByAccountNumber(AccountNumber);
		if (account == null) {
			return badRequest("Sender account is not registered");
		}
		account.deposit(depositAmmount);
		return ok("Deposit sucess");
	}

	public static Result transactions() {
		JsonNode json = request().body().asJson();
		long AccountNumber = Long.parseLong(json.get("account").asText());

		return ok(Json.toJson(BangkingTransaction.findByAccountNumber(AccountNumber)));
		
		
	}
}
