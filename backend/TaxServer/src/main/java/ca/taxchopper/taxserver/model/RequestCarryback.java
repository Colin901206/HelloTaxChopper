package ca.taxchopper.taxserver.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestCarryback {
    /**
     * The unique identifier for the carryback record.
     */
    private Integer carrybackId;

    /**
     * The base tax code.
     */
    private String taxCode;

    /**
     * The first previous tax year.
     */
    private String firstPreviousTaxYear;

    /**
     * The first previous tax year month.
     */
    private String firstPreviousTaxMonth;

    /**
     * The first previous tax year month day.
     */
    private String firstPreviousTaxDay;

    /**
     * The first previous tax year credit to be applied.
     */
    private BigDecimal firstPreviousTaxCredit;

    /**
     * The second previous tax year.
     */
    private String secondPreviousTaxYear;

    /**
     * The second previous tax year month.
     */
    private String secondPreviousTaxMonth;

    /**
     * The second previous tax year month day.
     */
    private String secondPreviousTaxDay;

    /**
     * The second previous tax year credit to be applied.
     */
    private BigDecimal secondPreviousTaxCredit;

    /**
     * The third previous tax year.
     */
    private String thirdPreviousTaxYear;

    /**
     * The third previous tax year month.
     */
    private String thirdPreviousTaxMonth;

    /**
     * The third previous tax year month day.
     */
    private String thirdPreviousTaxDay;

    /**
     * The third previous tax year credit to be applied.
     */
    private BigDecimal thirdPreviousTaxCredit;

    /**
     * The total of lines 901 to 903. Enter at amount 5E.
     */
    private BigDecimal totalCredits;
}
