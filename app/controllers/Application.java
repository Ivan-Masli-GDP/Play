package controllers;

import javax.persistence.PersistenceException;

import models.Account;
import models.BangkingTransaction;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;
public class Application extends Controller {

    public static Result index() {
        return ok(index.render(play.core.PlayVersion.current()));
    }
    public static Result saveAccount(){
    	JsonNode json = request().body().asJson();
    	Account newAccount = Json.fromJson(json.get("Account"), Account.class);
    	try{
    		newAccount.save();
    	} catch (PersistenceException e){
    		return badRequest("Account has already exist");
    	}
    	
    	return ok("Accepted");
    }
//    public static Result transferFunds(){
//    	JsonNode json = request().body().asJson();
//    	json.get("")
//    	try {
//    		newTransaction.initiate();
//    	}
//    }

}
