package ca.taxchopper.taxserver.dao;

import ca.taxchopper.taxserver.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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


//    @Select("SELECT " +
//            "T.word_id AS wordId, " +
//            "T.word AS word " +
//            "FROM t_words T " +
//            "WHERE T.status = '1' " +
//            "AND T.word = #{word}")
//    Word queryWord(String word);
//
//    @Select("SELECT " +
//            "word_form_id          AS wordFormId," +
//            "word_id               AS wordId," +
//            "part_of_speech        AS partOfSpeech," +
//            "word_phonetic         AS wordPhonetic," +
//            "base_form             AS baseForm," +
//            "is_countable          AS isCountable," +
//            "plural_form           AS pluralForm," +
//            "possessive_case       AS possessiveCase," +
//            "third_person_singular AS thirdPersonSingular," +
//            "present_participle    AS presentParticiple," +
//            "past_simple           AS pastSimple," +
//            "past_participle       AS pastParticiple," +
//            "comparative           AS comparative," +
//            "superlative           AS superlative," +
//            "nominative            AS nominative," +
//            "objective             AS objective," +
//            "possessive_adjectives AS possessiveAdjectives," +
//            "possessive_nouns      AS possessiveNouns," +
//            "reflexive_pronouns    AS reflexivePronouns " +
//            "FROM t_word_forms T " +
//            "WHERE T.status = '1' " +
//            "AND T.word_id = #{wordId} " +
//            "order by T.word_form_id asc")
//    List<WordForm> queryWordForms(Integer wordId);
//
//    @Select("SELECT " +
//            "meaning_id      AS meaningId," +
//            "word_form_id    AS wordFormId," +
//            "chinese_meaning AS chineseMeaning," +
//            "english_meaning AS englishMeaning," +
//            "example         AS example," +
//            "translate       AS translate " +
//            "FROM t_word_meanings T " +
//            "WHERE T.status = '1' " +
//            "AND T.word_form_id = #{wordFormId} " +
//            "order by T.meaning_id asc")
//    List<WordMeaning> queryWordMeanings(Integer wordFormId);
//
//    @Select("SELECT " +
//            "phrase_id         AS phraseId," +
//            "meaning_id        AS meaningId," +
//            "english_phrases   AS englishPhrases," +
//            "phrases_translate AS phrasesTranslate " +
//            "FROM t_word_phrases T " +
//            "WHERE T.status = '1' " +
//            "AND T.meaning_id = #{meaningId} " +
//            "order by T.phrase_id asc")
//    List<WordPhrase> queryWordPhrases(Integer meaningId);
}
