package it.interview.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.interview.exception.AccountException;
import it.interview.model.Account;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class AccountService {

    private static final String ACCOUNTS_JSON = "accounts.json";

    private ObjectMapper mapper = new ObjectMapper();

    public Account getAccount(final String accountId) throws AccountException {

        try {
            List<Account> accounts = readAccounts();
            Optional<Account> account = accounts
                    .stream()
                    .filter(a -> a.getId().equals(Long.valueOf(accountId)))
                    .findFirst();
            return account.orElseThrow(() -> new AccountException("Can't find account with id: " + accountId));
        } catch (IOException e) {
            throw new AccountException("Internal server error");
        }
    }

    public void createAccount(final Account account) throws AccountException {

        try {
            List<Account> accounts = readAccounts();
            accounts.add(account);
            writeAccounts(accounts);
        } catch (IOException e) {
            throw new AccountException("Internal server error");
        }
    }

    public void updateAccount(final String accountId, final Account account) throws AccountException {

        try {
            List<Account> accounts = readAccounts();
            List<Account> newAccounts = accounts.stream()
                    .map(acc -> {
                        if(acc.getId().equals(Long.valueOf(accountId))) {
                            return account;
                        } else {
                            return acc;
                        }
                    }).collect(toList());
            writeAccounts(newAccounts);
        } catch (IOException e) {
            throw new AccountException("Internal server error");
        }
    }

    public List<Account> getAccounts() throws AccountException {

        try {
            return readAccounts();
        } catch (IOException e) {
            throw new AccountException("Internal server error");
        }
    }

    private List<Account> readAccounts() throws AccountException, IOException {

        return mapper.readValue(
                new File(ACCOUNTS_JSON),
                new TypeReference<List<Account>>() { }
        );
    }

    private void writeAccounts(List<Account> accounts) throws AccountException, IOException {

        mapper.writeValue(new File(ACCOUNTS_JSON), accounts);
    }
}
