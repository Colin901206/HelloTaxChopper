package ca.taxchopper.taxserver.model;

import lombok.Data;

@Data
public class DividendDescription {
    /**
     * The unique identifier for the Part IV tax payable record.
     */
    private int dividendDescriptionId;

    /**
     * The base tax code.
     */
    private String taxCode;

    /**
     * What scientific or technological uncertainties did you attempt to overcome
     */
    private String uncertainties;

    /**
     * What scientific or technological advancements did you achieve or attempt to achieve as a result of the work described in line 242?
     */
    private String advancements;
}
