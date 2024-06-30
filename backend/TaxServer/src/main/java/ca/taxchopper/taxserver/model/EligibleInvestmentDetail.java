package ca.taxchopper.taxserver.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EligibleInvestmentDetail {
    /**
     * The unique identifier for the eligible investment detail record.
     */
    private int investmentDetailId;

    /**
     * The base tax code.
     */
    private String taxCode;

    /**
     * The received dividend table line.
     */
    private int part3TableLine;

    /**
     * Capital cost allowance class number.
     */
    private String capitalCostAllowanceClassNumber;

    /**
     * Description of investment.
     */
    private String descriptionOfInvestment;

    /**
     * Date available for use.
     */
    private String dateAvailableForUse;

    /**
     * Location used in Atlantic Canada (province).
     */
    private String locationUsedInAtlantic;

    /**
     * Amount of investment.
     */
    private BigDecimal amountOfInvestment;
}
