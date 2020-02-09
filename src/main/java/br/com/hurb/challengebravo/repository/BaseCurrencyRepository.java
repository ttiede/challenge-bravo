package br.com.hurb.challengebravo.repository;

import br.com.hurb.challengebravo.domain.BaseCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCurrencyRepository extends CrudRepository<BaseCurrency, String> {}
