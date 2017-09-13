package it.interview.web.controller;

import it.interview.exception.AccountException;
import it.interview.model.Account;
import it.interview.model.annotation.PATCH;
import it.interview.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/v1/accounts/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GET
    @Path("/{account_id}")
    @Produces("application/json")
    public Response getAccount(@PathParam("account_id") String accountId) {

        Response response;
        try {
            Account account = accountService.getAccount(accountId);
            response = Response.status(200).entity(account).build();
        } catch (AccountException e) {
            response = Response.status(500).build();
        }
        return response;
    }

    @POST
    @Consumes("application/json")
    public Response createAccount(Account account) {

        Response response;
        try {
            accountService.createAccount(account);
            response = Response.status(200).build();
        } catch (AccountException e) {
            response = Response.status(500).build();
        }
        return response;
    }

    @PATCH
    @Path("/{account_id}")
    @Consumes("application/json")
    public Response updateAccount(@PathParam("account_id") String accountId, Account account) {

        Response response;
        try {
            accountService.updateAccount(accountId, account);
            response = Response.status(200).build();
        } catch (AccountException e) {
            response = Response.status(500).build();
        }
        return response;
    }

    @GET
    @Produces("application/json")
    public Response getAccounts() {

        Response response;
        try {
            List<Account> accounts = accountService.getAccounts();
            response = Response.status(200).entity(accounts).build();
        } catch (AccountException e) {
            response = Response.status(500).build();
        }
        return response;
    }

}
