package br.com.hurb.challengebravo.model.repository;

import br.com.hurb.challengebravo.model.domain.BaseCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCurrencyRepository extends CrudRepository<BaseCurrency, String> {}
