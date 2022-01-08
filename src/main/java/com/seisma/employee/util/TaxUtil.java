package com.seisma.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@Component
public class TaxUtil {

  @Autowired
  private TaxRatesConfiguration taxRatesConfiguration;

  public List<TaxRatesConfiguration.Bracket> getTaxBrackets(final Month month) {
    return taxRatesConfiguration.getRates();
  }

  public TaxRatesConfiguration.Bracket determineTaxBracket(final Integer income, final List<TaxRatesConfiguration.Bracket> brackets) {

    Optional<TaxRatesConfiguration.Bracket> result =
            brackets.stream().filter(bracket -> (income >= bracket.getMin() && (bracket.getMax() == null || income <= bracket.getMax()))).findFirst();

    return result.isPresent() ? result.get() : null;
  }

}
