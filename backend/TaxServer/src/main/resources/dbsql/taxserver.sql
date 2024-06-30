CREATE USER 'taxserver_dev'@'%' IDENTIFIED BY 'Tax1324';
CREATE database if NOT EXISTS `taxserver_dev` default character set utf8mb4 collate utf8mb4_unicode_ci;
grant all privileges on taxserver_dev.* to 'taxserver_dev'@'%';

CREATE TABLE t_tax_info
(
    tax_id      INT AUTO_INCREMENT PRIMARY KEY,
    tax_code    VARCHAR(50) NOT NULL UNIQUE,
    create_time TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the data was created',
    update_time TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the data was last updated',
    status      VARCHAR(1)  DEFAULT '0' COMMENT 'Status of the data: 0 - inactive; 1 - active',
    note        VARCHAR(250) COMMENT 'Additional notes about the data'
)
    comment 'Base tax table';

CREATE TABLE t_received_dividend
(
    dividend_id       INT AUTO_INCREMENT PRIMARY KEY,
    tax_code          VARCHAR(50)  NOT NULL COMMENT 'Base tax code',
    part1_tableLine   INT          NOT NULL COMMENT 'Received dividend table line',
    payer_corporation VARCHAR(100) NOT NULL COMMENT 'Name of payer corporation(from which the corporation received the dividend)',
    is_connected      VARCHAR(1)   NOT NULL COMMENT 'Is the corporation connected: 0 - no; 1 - yes',
    business_number   VARCHAR(255) NOT NULL COMMENT 'Business Number of connected corporation',
    create_time       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the data was created',
    update_time       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the data was last updated',
    status            VARCHAR(1)   DEFAULT '0' COMMENT 'Status of the data: 0 - inactive; 1 - active',
    note              VARCHAR(250) COMMENT 'Additional notes about the data',
    FOREIGN KEY (tax_code) REFERENCES t_tax_info (tax_code)
)
    comment 'Dividends received in the tax year';

CREATE TABLE t_request_carryback
(
    carryback_id               INT AUTO_INCREMENT PRIMARY KEY,
    tax_code                   VARCHAR(50) NOT NULL COMMENT 'Base tax code',
    first_previous_tax_year    VARCHAR(4)  NOT NULL COMMENT '1st previous tax year',
    first_previous_tax_month   VARCHAR(2)  NOT NULL COMMENT '1st previous tax year month',
    first_previous_tax_day     VARCHAR(2)  NOT NULL COMMENT '1st previous tax year month day',
    first_previous_tax_credit  DECIMAL     NOT NULL COMMENT '1st previous tax year credit to be applied',
    second_previous_tax_year   VARCHAR(4)  NOT NULL COMMENT '2nd previous tax year',
    second_previous_tax_month  VARCHAR(2)  NOT NULL COMMENT '2nd previous tax year month',
    second_previous_tax_day    VARCHAR(2)  NOT NULL COMMENT '2nd previous tax year month day',
    second_previous_tax_credit DECIMAL     NOT NULL COMMENT '2nd previous tax year credit to be applied',
    third_previous_tax_year    VARCHAR(4)  NOT NULL COMMENT '3rd previous tax year',
    third_previous_tax_month   VARCHAR(2)  NOT NULL COMMENT '3rd previous tax year month',
    third_previous_tax_day     VARCHAR(2)  NOT NULL COMMENT '3rd previous tax year month day',
    third_previous_tax_credit  DECIMAL     NOT NULL COMMENT '3rd previous tax year credit to be applied',
    total_credits              DECIMAL     NOT NULL COMMENT 'Total of lines 901 to 903 Enter at amount 5E.',
    create_time                TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was created',
    update_time                TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was last updated',
    status                     VARCHAR(1) DEFAULT '0' COMMENT 'Status of the user: 0 - inactive; 1 - active',
    note                       VARCHAR(250) COMMENT 'Additional notes about the user',
    FOREIGN KEY (tax_code) REFERENCES t_tax_info (tax_code)
)
    comment 'Request for carryback of credit from investments in qualified property';

CREATE TABLE t_eligible_investment_main
(
    investment_main_id INT AUTO_INCREMENT PRIMARY KEY,
    tax_code           VARCHAR(50)  NOT NULL COMMENT 'Base tax code',
    total_investments  DECIMAL      NOT NULL COMMENT 'Total of investments for qualified property',
    tax_year_after2023 VARCHAR(1)   NOT NULL COMMENT 'Is your tax year begins after December 31, 2023: 0 - no; 1 - yes',
    create_time        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was created',
    update_time        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was last updated',
    status             VARCHAR(1)   DEFAULT '0' COMMENT 'Status of the user: 0 - inactive; 1 - active',
    note               VARCHAR(250) COMMENT 'Additional notes about the user',
    FOREIGN KEY (tax_code) REFERENCES t_tax_info (tax_code)
)
    comment 'Eligible investments for qualified property from the current tax year';


CREATE TABLE t_eligible_investment_detail
(
    investment_detail_id                INT AUTO_INCREMENT PRIMARY KEY,
    tax_code                            VARCHAR(50) NOT NULL COMMENT 'Base tax code',
    part3_tableLine                     INT         NOT NULL COMMENT 'Received dividend table line',
    capital_cost_allowance_class_number VARCHAR(50) NOT NULL COMMENT 'Capital cost allowance class number',
    description_of_investment           VARCHAR(99) NOT NULL COMMENT 'Description of investment',
    date_available_for_use              VARCHAR(20) NOT NULL COMMENT 'Date available for use',
    location_used_in_atlantic           VARCHAR(99) NOT NULL COMMENT 'Location used in Atlantic Canada (province)',
    amount_of_investment                DECIMAL     NOT NULL COMMENT 'Amount of investment',
    create_time                         TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was created',
    update_time                         TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was last updated',
    status                              VARCHAR(1) DEFAULT '0' COMMENT 'Status of the user: 0 - inactive; 1 - active',
    note                                VARCHAR(250) COMMENT 'Additional notes about the user',
    FOREIGN KEY (tax_code) REFERENCES t_tax_info (tax_code)
)
    comment 'Eligible investments for qualified property from the current tax year';


CREATE TABLE t_part4_tax_payable
(
    part4_tax_payable_id               INT AUTO_INCREMENT PRIMARY KEY,
    tax_code                           VARCHAR(50) NOT NULL COMMENT 'Base tax code',
    dividends_before2024               DECIMAL     NOT NULL COMMENT 'Part IV tax on dividends received before 2024, before deductions',
    dividends_after2023                DECIMAL     NOT NULL COMMENT 'Part IV tax on dividends received after 2023, before deductions',
    partiv_tax_before_deductions       VARCHAR(50) NOT NULL COMMENT 'Part IV tax before deductions',
    partiv_tax_before_deductions_total DECIMAL     NOT NULL COMMENT 'Part IV tax before deductions (amount a plus amount b) L',
    partiv_i_tax_payable               DECIMAL     NOT NULL COMMENT 'Part IV.I tax payable on dividends subject to Part IV tax',
    subtotal_m                         DECIMAL     NOT NULL COMMENT 'Subtotal (amount L minus line 320)',
    current_year_non_capital_loss      DECIMAL     NOT NULL COMMENT 'Part IV.I tax payable on dividends subject to Part IV tax',
    previous_years_non_capital_loss    DECIMAL     NOT NULL COMMENT 'Non-capital losses from previous years claimed to reduce Part IV tax',
    current_year_farm_loss             DECIMAL     NOT NULL COMMENT 'Current-year farm loss claimed to reduce Part IV tax',
    previous_years_farm_loss           DECIMAL     NOT NULL COMMENT 'Farm losses from previous years claimed to reduce Part IV tax 345 f',
    total_losses_applied               DECIMAL     NOT NULL COMMENT 'Total losses applied against Part IV tax',
    amountg_multiplied                 DECIMAL     NOT NULL COMMENT 'Amount g multiplied by 38 1/3%',
    amount_borm_whicheverless          DECIMAL     NOT NULL COMMENT 'Amount b or M whichever is less',
    amount_borm_divided                DECIMAL     NOT NULL COMMENT 'Amount b or M whichever is less:	÷ 38 1/3% =',
    amount_1org_whicheverless          DECIMAL     NOT NULL COMMENT 'Amount 1 or g, whichever is less',
    amountg_minus_amount2              DECIMAL     NOT NULL COMMENT 'Amount g minus amount 2',
    amount2                            DECIMAL     NOT NULL COMMENT 'Amount 2',
    amount2_multiplied                 DECIMAL     NOT NULL COMMENT 'Amount 2 × 38 1/3% =',
    amount3                            DECIMAL     NOT NULL COMMENT 'Amount 3',
    amount3_multiplied                 DECIMAL     NOT NULL COMMENT 'Amount 3 × 38 1/3% =',
    subtotal_amount_iplusj             DECIMAL     NOT NULL COMMENT 'Subtotal (amount i plus amount j)',
    amount_hork                        DECIMAL     NOT NULL COMMENT 'Amount h or amount k, whichever applies depending on your tax year start date',
    partiv_tax_payable                 DECIMAL     NOT NULL COMMENT 'Part IV tax payable',
    property_type                      VARCHAR(30) NOT NULL COMMENT 'What type is the residential property?',
    create_time                        TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was created',
    update_time                        TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the user was last updated',
    status                             VARCHAR(1) DEFAULT '0' COMMENT 'Status of the user: 0 - inactive; 1 - active',
    note                               VARCHAR(250) COMMENT 'Additional notes about the user',
    FOREIGN KEY (tax_code) REFERENCES t_tax_info (tax_code)
)
    comment 'Calculation of Part IV tax payable';

CREATE TABLE t_dividend_description
(
    dividend_description_id INT AUTO_INCREMENT PRIMARY KEY,
    tax_code                VARCHAR(50) NOT NULL COMMENT 'Base tax code',
    uncertainties           VARCHAR(350) NOT NULL COMMENT 'What scientific or technological uncertainties did you attempt to overcome?',
    advancements            VARCHAR(350) NOT NULL COMMENT 'What scientific or technological advancements did you achieve or attempt to achieve as a result of the work described in line 242?',
    create_time             TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the data was created',
    update_time             TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the data was last updated',
    status                  VARCHAR(1) DEFAULT '0' COMMENT 'Status of the data: 0 - inactive; 1 - active',
    note                    VARCHAR(250) COMMENT 'Additional notes about the data',
    FOREIGN KEY (tax_code) REFERENCES t_tax_info (tax_code)
)
    comment 'Dividends descriptions';
