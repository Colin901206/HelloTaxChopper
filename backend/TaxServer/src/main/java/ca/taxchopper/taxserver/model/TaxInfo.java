package ca.taxchopper.taxserver.model;

import lombok.Data;

import java.util.List;

@Data
public class TaxInfo {

    /**
     * The unique tax id
     */
    private Integer taxId;

    /**
     * Base tax code
     */
    private String taxCode;

    private List<ReceivedDividend> part1;

    private RequestCarryback part2;

    private EligibleInvestmentMain part3;

    private Part4TaxPayable part4;

    private DividendDescription sectionB;
}
