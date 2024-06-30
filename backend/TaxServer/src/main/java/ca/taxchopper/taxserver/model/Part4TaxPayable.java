package ca.taxchopper.taxserver.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Part4TaxPayable {
    /**
     * The unique identifier for the Part IV tax payable record.
     */
    private int part4TaxPayableId;

    /**
     * The base tax code.
     */
    private String taxCode;

    /**
     * Part IV tax on dividends received before 2024, before deductions.
     */
    private BigDecimal dividendsBefore2024;

    /**
     * Part IV tax on dividends received after 2023, before deductions.
     */
    private BigDecimal dividendsAfter2023;

    /**
     * Part IV tax before deductions.
     */
    private String partIVTaxBeforeDeductions;

    /**
     * Part IV.I tax payable on dividends subject to Part IV tax.
     */
    private BigDecimal partIVTaxBeforeDeductionsTotal;

    /**
     * Subtotal (amount L minus line 320).
     */
    private BigDecimal partIVITaxPayable;

    /**
     * Part IV.I tax payable on dividends subject to Part IV tax.
     */
    private BigDecimal subtotalM;

    /**
     * Non-capital losses from previous years claimed to reduce Part IV tax.
     */
    private BigDecimal currentYearNonCapitalLoss;

    /**
     * Current-year farm loss claimed to reduce Part IV tax.
     */
    private BigDecimal previousYearsNonCapitalLoss;

    /**
     * Farm losses from previous years claimed to reduce Part IV tax 345 f.
     */
    private BigDecimal currentYearFarmLoss;

    /**
     * Total losses applied against Part IV tax (total of amounts c to f).
     */
    private BigDecimal previousYearsFarmLoss;

    /**
     * Amount g multiplied by 38 1/3%.
     */
    private BigDecimal totalLossesApplied;

    /**
     * Amount b or M whichever is less.
     */
    private BigDecimal amountGMultiplied;

    /**
     * Amount b or M whichever is less: ÷ 38 1/3% =.
     */
    private BigDecimal amountBOrMWhicheverLess;

    /**
     *
     */
    private BigDecimal amountBOrMDivided;

    /**
     * Amount 1 or g, whichever is less.
     */
    private BigDecimal amount1OrGWhicheverLess;

    /**
     * Amount g minus amount 2.
     */
    private BigDecimal amountGMinusAmount2;

    /**
     * Amount 2.
     */
    private BigDecimal amount2;

    /**
     * Amount 2 × 38 1/3% =.
     */
    private BigDecimal amount2Multiplied;

    /**
     * Amount 3.
     */
    private BigDecimal amount3;

    /**
     * Amount 3 × 38 1/3% =.
     */
    private BigDecimal amount3Multiplied;

    /**
     * Subtotal (amount i plus amount j).
     */
    private BigDecimal subtotalAmountIPlusJ;

    /**
     * Amount h or amount k, whichever applies depending on your tax year start date.
     */
    private BigDecimal amountHOrK;

    /**
     * Part IV tax payable.
     */
    private BigDecimal partIVTaxPayable;

    /**
     * What type is the residential property?
     */
    private String propertyType;
}
