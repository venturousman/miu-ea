package cs544.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cs544.bank.dao.AccountDAO;
import cs544.bank.jms.JMSSender;
import cs544.bank.logging.Logger;
import cs544.bank.service.CurrencyConverter;

@Configuration
@ComponentScan("cs544.bank")
public class Config {
    @Bean
    public CurrencyConverter currencyConverter() {
        return new CurrencyConverter();
    }

    @Bean
    public AccountDAO accountDAO() {
        return new AccountDAO();
    }

    @Bean
    public JMSSender jmsSender() {
        return new JMSSender();
    }

    @Bean
    public Logger logger() {
        return new Logger();
    }
}