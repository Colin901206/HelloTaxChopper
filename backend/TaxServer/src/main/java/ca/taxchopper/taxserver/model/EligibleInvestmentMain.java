package ca.taxchopper.taxserver.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EligibleInvestmentMain {
    /**
     * The unique identifier for the eligible investment main record.
     */
    private Integer investmentMainId;

    /**
     * The base tax code.
     */
    private String taxCode;

    /**
     * Total of investments for qualified property.
     */
    private BigDecimal totalInvestments;

    /**
     * Indicates if the tax year begins after December 31, 2023: 0 - no; 1 - yes.
     */
    private String taxYearAfter2023;

    private List<EligibleInvestmentDetail> investments;
}
