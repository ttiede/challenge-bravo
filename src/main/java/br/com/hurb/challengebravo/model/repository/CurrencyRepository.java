package br.com.hurb.challengebravo.model.repository;

import br.com.hurb.challengebravo.model.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {}
