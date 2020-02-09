package br.com.hurb.challengebravo.service;

import br.com.hurb.challengebravo.domain.Currency;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CurrenciesServices {
        @Cacheable("currency")
        public Currency getById(String id)
        {
            try
            {
                System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            UUID idtest = UUID.randomUUID();
            return new Currency(idtest.toString(),"Sajal" ,"V");
        }
    }