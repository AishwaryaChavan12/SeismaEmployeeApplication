package com.seisma.employee.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;

@Component
public class SalaryUtil {

  private static final Logger LOG = LoggerFactory.getLogger(SalaryUtil.class);

  private static final float MONTHS_IN_YEAR = 12;

  private static final float MAX_PERCENTAGE = 100;

  @Autowired
  private TaxUtil taxUtil;

  public Integer calculateMonthlyGrossIncome(final Integer income) {
    return Math.round((income / MONTHS_IN_YEAR));
  }

  public Integer calculateMonthlyIncomeTax(final Integer income, final Month month) {

    final TaxRatesConfiguration.Bracket bracket = taxUtil.determineTaxBracket(income, taxUtil.getTaxBrackets(month));
    final float tax = ((bracket.getOffset() + (income - calculatePreviousMax(bracket.getMin())) * bracket.getCents()) / MONTHS_IN_YEAR);
    return Math.round(tax);
  }

  public Integer calculateNetIncome(final Integer gross, final Integer tax) {
    return (gross - tax);
  }

  public Long calculateSuperannuation(final Integer gross, final Double superannuationPercentage) {
    final Double superannuation = (gross * (superannuationPercentage / MAX_PERCENTAGE));
    return Math.round(superannuation);
  }

  private Integer calculatePreviousMax(final Integer currentMin) {
    return (currentMin == 0) ? 0 : (currentMin - 1);
  }
}
