package ca.taxchopper.taxserver.model;

import lombok.Data;

@Data
public class ReceivedDividend {
    /**
     * The unique identifier for the received dividend record.
     */
    private Integer dividendId;

    /**
     * The base tax code.
     */
    private String taxCode;

    /**
     * The received dividend table line.
     */
    private Integer part1TableLine;

    /**
     * The name of the payer corporation (from which the corporation received the dividend).
     */
    private String payerCorporation;

    /**
     * Indicates if the corporation is connected: 0 - no; 1 - yes.
     */
    private String isConnected;

    /**
     * The business number of the connected corporation.
     */
    private String businessNumber;

}
