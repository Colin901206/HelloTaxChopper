package ca.taxchopper.taxserver.dao;

import ca.taxchopper.taxserver.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaxDao {

    @Delete("DELETE FROM t_dividend_description WHERE tax_code = #{taxCode}")
    int deleteSectionB(String taxCode);

    @Delete("DELETE FROM t_part4_tax_payable WHERE tax_code = #{taxCode}")
    int deletePart4(String taxCode);

    @Delete("DELETE FROM t_eligible_investment_detail WHERE tax_code = #{taxCode}")
    int deleteEligibleInvestmentDetail(String taxCode);

    @Delete("DELETE FROM t_eligible_investment_main WHERE tax_code = #{taxCode}")
    int deleteEligibleInvestmentMain(String taxCode);

    @Delete("DELETE FROM t_request_carryback WHERE tax_code = #{taxCode}")
    int deletePart2(String taxCode);

    @Delete("DELETE FROM t_received_dividend WHERE tax_code = #{taxCode}")
    int deletePart1(String taxCode);

    @Delete("DELETE FROM t_tax_info WHERE tax_code = #{taxCode}")
    int deleteTaxInfo(String taxCode);

    @Insert("INSERT INTO t_tax_info (tax_code) VALUES (#{taxCode})")
    @Options(useGeneratedKeys = true, keyProperty = "taxId", keyColumn = "tax_id")
    int saveTaxInfo(TaxInfo taxInfo);

    @Insert("INSERT INTO t_received_dividend (" +
                "tax_code," +
                "part1_tableLine," +
                "payer_corporation," +
                "is_connected," +
                "business_number" +
            ") VALUES (" +
                "#{taxCode}," +
                "#{part1TableLine}," +
                "#{payerCorporation}," +
                "#{isConnected}," +
                "#{businessNumber}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "dividendId", keyColumn = "dividend_id")
    int savePart1(ReceivedDividend receivedDividend);


    @Insert("INSERT INTO t_request_carryback (" +
                "tax_code," +
                "first_previous_tax_year," +
                "first_previous_tax_month," +
                "first_previous_tax_day," +
                "first_previous_tax_credit," +
                "second_previous_tax_year," +
                "second_previous_tax_month," +
                "second_previous_tax_day," +
                "second_previous_tax_credit," +
                "third_previous_tax_year," +
                "third_previous_tax_month," +
                "third_previous_tax_day," +
                "third_previous_tax_credit," +
                "total_credits" +
            ") VALUES (" +
                "#{taxCode}," +
                "#{firstPreviousTaxYear}," +
                "#{firstPreviousTaxMonth}," +
                "#{firstPreviousTaxDay}," +
                "#{firstPreviousTaxCredit}," +
                "#{secondPreviousTaxYear}," +
                "#{secondPreviousTaxMonth}," +
                "#{secondPreviousTaxDay}," +
                "#{secondPreviousTaxCredit}," +
                "#{thirdPreviousTaxYear}," +
                "#{thirdPreviousTaxMonth}," +
                "#{thirdPreviousTaxDay}," +
                "#{thirdPreviousTaxCredit}," +
                "#{totalCredits}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "carrybackId", keyColumn = "carryback_id")
    int savePart2(RequestCarryback requestCarryback);

    @Insert("INSERT INTO t_eligible_investment_main (" +
                "tax_code," +
                "total_investments," +
                "tax_year_after2023" +
            ") VALUES (" +
                "#{taxCode}," +
                "#{totalInvestments}," +
                "#{taxYearAfter2023}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "investmentMainId", keyColumn = "investment_main_id")
    int saveEligibleInvestmentMain(EligibleInvestmentMain part3);

    @Insert("INSERT INTO t_eligible_investment_detail (" +
                "tax_code," +
                "part3_tableLine," +
                "capital_cost_allowance_class_number," +
                "description_of_investment," +
                "date_available_for_use," +
                "location_used_in_atlantic," +
                "amount_of_investment" +
            ") VALUES (" +
                "#{taxCode}," +
                "#{part3TableLine}," +
                "#{capitalCostAllowanceClassNumber}," +
                "#{descriptionOfInvestment}," +
                "#{dateAvailableForUse}," +
                "#{locationUsedInAtlantic}," +
                "#{amountOfInvestment}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "investmentDetailId", keyColumn = "investment_detail_id")
    int saveEligibleInvestmentDetail(EligibleInvestmentDetail eligibleInvestmentDetail);

    @Insert("INSERT INTO t_part4_tax_payable (" +
                "tax_code," +
                "dividends_before2024," +
                "dividends_after2023," +
                "partiv_tax_before_deductions," +
                "partiv_tax_before_deductions_total," +
                "partiv_i_tax_payable," +
                "subtotal_m," +
                "current_year_non_capital_loss," +
                "previous_years_non_capital_loss," +
                "current_year_farm_loss," +
                "previous_years_farm_loss," +
                "total_losses_applied," +
                "amountg_multiplied," +
                "amount_borm_whicheverless," +
                "amount_borm_divided," +
                "amount_1org_whicheverless," +
                "amountg_minus_amount2," +
                "amount2," +
                "amount2_multiplied," +
                "amount3," +
                "amount3_multiplied," +
                "subtotal_amount_iplusj," +
                "amount_hork," +
                "partiv_tax_payable," +
                "property_type" +
            ") VALUES (" +
                "#{taxCode}," +
                "#{dividendsBefore2024}," +
                "#{dividendsAfter2023}," +
                "#{partIVTaxBeforeDeductions}," +
                "#{partIVTaxBeforeDeductionsTotal}," +
                "#{partIVITaxPayable}," +
                "#{subtotalM}," +
                "#{currentYearNonCapitalLoss}," +
                "#{previousYearsNonCapitalLoss}," +
                "#{currentYearFarmLoss}," +
                "#{previousYearsFarmLoss}," +
                "#{totalLossesApplied}," +
                "#{amountGMultiplied}," +
                "#{amountBOrMWhicheverLess}," +
                "#{amountBOrMDivided}," +
                "#{amount1OrGWhicheverLess}," +
                "#{amountGMinusAmount2}," +
                "#{amount2}," +
                "#{amount2Multiplied}," +
                "#{amount3}," +
                "#{amount3Multiplied}," +
                "#{subtotalAmountIPlusJ}," +
                "#{amountHOrK}," +
                "#{partIVTaxPayable}," +
                "#{propertyType}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "investmentMainId", keyColumn = "investment_main_id")
    int savePart4(Part4TaxPayable part4);

    @Insert("INSERT INTO t_dividend_description (" +
                "tax_code," +
                "uncertainties," +
                "advancements" +
            ") VALUES (" +
                "#{taxCode}," +
                "#{uncertainties}," +
                "#{advancements}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "dividendDescriptionId", keyColumn = "dividend_description_id")
    int saveSectionB(DividendDescription sectionB);

    @Select("SELECT " +
                "T.tax_id AS taxId, " +
                "T.tax_code AS taxCode " +
            "FROM t_tax_info T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    TaxInfo queryTaxInfo(String taxCode);

    @Select("SELECT " +
                "T.dividend_id AS dividendId, " +
                "T.tax_code AS taxCode, " +
                "T.part1_tableLine AS part1TableLine, " +
                "T.payer_corporation AS payerCorporation, " +
                "T.is_connected AS isConnected, " +
                "T.business_number AS businessNumber " +
            "FROM t_received_dividend T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    List<ReceivedDividend> queryPart1(String taxCode);

    @Select("SELECT " +
                "T.carryback_id AS carrybackId, " +
                "T.tax_code AS taxCode, " +
                "T.first_previous_tax_year AS firstPreviousTaxYear, " +
                "T.first_previous_tax_month AS firstPreviousTaxMonth, " +
                "T.first_previous_tax_day AS firstPreviousTaxDay, " +
                "T.first_previous_tax_credit AS firstPreviousTaxCredit, " +
                "T.second_previous_tax_year AS secondPreviousTaxYear, " +
                "T.second_previous_tax_month AS secondPreviousTaxMonth, " +
                "T.second_previous_tax_day AS secondPreviousTaxDay, " +
                "T.second_previous_tax_credit AS secondPreviousTaxCredit, " +
                "T.third_previous_tax_year AS thirdPreviousTaxYear, " +
                "T.third_previous_tax_month AS thirdPreviousTaxMonth, " +
                "T.third_previous_tax_day AS thirdPreviousTaxDay, " +
                "T.third_previous_tax_credit AS thirdPreviousTaxCredit, " +
                "T.total_credits AS totalCredits " +
            "FROM t_request_carryback T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    RequestCarryback queryPart2(String taxCode);

    @Select("SELECT " +
                "T.investment_main_id AS investmentMainId, " +
                "T.tax_code AS taxCode, " +
                "T.total_investments AS totalInvestments, " +
                "T.tax_year_after2023 AS taxYearAfter2023 " +
            "FROM t_eligible_investment_main T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    EligibleInvestmentMain queryEligibleInvestmentMain(String taxCode);

    @Select("SELECT " +
                "T.investment_detail_id AS investmentDetailId, " +
                "T.tax_code AS taxCode, " +
                "T.part3_tableLine AS part3TableLine, " +
                "T.capital_cost_allowance_class_number AS capitalCostAllowanceClassNumber, " +
                "T.description_of_investment AS descriptionOfInvestment, " +
                "T.date_available_for_use AS dateAvailableForUse, " +
                "T.location_used_in_atlantic AS locationUsedInAtlantic, " +
                "T.amount_of_investment AS amountOfInvestment " +
            "FROM t_eligible_investment_detail T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    List<EligibleInvestmentDetail> queryEligibleInvestmentDetail(String taxCode);

    @Select("SELECT " +
                "T.part4_tax_payable_id AS part4TaxPayableId, " +
                "T.tax_code AS taxCode, " +
                "T.dividends_before2024 AS dividendsBefore2024, " +
                "T.dividends_after2023 AS dividendsAfter2023, " +
                "T.partiv_tax_before_deductions AS partIVTaxBeforeDeductions, " +
                "T.partiv_tax_before_deductions_total AS partIVTaxBeforeDeductionsTotal, " +
                "T.partiv_i_tax_payable AS partIVITaxPayable, " +
                "T.subtotal_m AS subtotalM, " +
                "T.current_year_non_capital_loss AS currentYearNonCapitalLoss, " +
                "T.previous_years_non_capital_loss AS previousYearsNonCapitalLoss, " +
                "T.current_year_farm_loss AS currentYearFarmLoss, " +
                "T.previous_years_farm_loss AS previousYearsFarmLoss, " +
                "T.total_losses_applied AS totalLossesApplied, " +
                "T.amountg_multiplied AS amountGMultiplied, " +
                "T.amount_borm_whicheverless AS amountBOrMWhicheverLess, " +
                "T.amount_borm_divided AS amountBOrMDivided, " +
                "T.amount_1org_whicheverless AS amount1OrGWhicheverLess, " +
                "T.amountg_minus_amount2 AS amountGMinusAmount2, " +
                "T.amount2 AS amount2, " +
                "T.amount2_multiplied AS amount2Multiplied, " +
                "T.amount3 AS amount3, " +
                "T.amount3_multiplied AS amount3Multiplied, " +
                "T.subtotal_amount_iplusj AS subtotalAmountIPlusJ, " +
                "T.amount_hork AS amountHOrK, " +
                "T.partiv_tax_payable AS partIVTaxPayable, " +
                "T.property_type AS propertyType " +
            "FROM t_part4_tax_payable T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    Part4TaxPayable queryPart4(String taxCode);

    @Select("SELECT " +
                "T.dividend_description_id AS dividendDescriptionId, " +
                "T.tax_code AS taxCode, " +
                "T.uncertainties AS uncertainties, " +
                "T.advancements AS advancements " +
            "FROM t_dividend_description T " +
            "WHERE T.status = '0' " +
            "AND T.tax_code = #{taxCode}")
    DividendDescription querySectionB(String taxCode);

}
