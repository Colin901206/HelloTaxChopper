package ca.taxchopper.taxserver.service;

import ca.taxchopper.taxserver.common.GlobaException;
import ca.taxchopper.taxserver.dao.TaxDao;
import ca.taxchopper.taxserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaxService {

    @Autowired
    private TaxDao taxDao;

    /**
     * Delete saved tax info by tax code before save
     * @param taxCode
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteTax(String taxCode) {
        taxDao.deleteSectionB(taxCode);
        taxDao.deletePart4(taxCode);
        taxDao.deleteEligibleInvestmentDetail(taxCode);
        taxDao.deleteEligibleInvestmentMain(taxCode);
        taxDao.deletePart2(taxCode);
        taxDao.deletePart1(taxCode);
        taxDao.deleteTaxInfo(taxCode);
    }

    /**
     * Save tax info
     * @param taxInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveTax(String taxCode, TaxInfo taxInfo) {
        if(taxInfo != null) {
            taxInfo.setTaxCode(taxCode);
            taxDao.saveTaxInfo(taxInfo);
        } else {
            throw new GlobaException("Missing parameters.");
        }

        List<ReceivedDividend> part1 = taxInfo.getPart1();
        if(part1 != null && part1.size() > 1) {
            for(ReceivedDividend receivedDividend : part1) {
                receivedDividend.setTaxCode(taxCode);
                taxDao.savePart1(receivedDividend);
            }
        } else {
            throw new GlobaException("Missing parameters.");
        }

        if(taxInfo.getPart2() != null) {
            RequestCarryback requestCarryback = taxInfo.getPart2();
            requestCarryback.setTaxCode(taxCode);
            taxDao.savePart2(requestCarryback);
        } else {
            throw new GlobaException("Missing parameters.");
        }

        if(taxInfo.getPart3() != null) {
            EligibleInvestmentMain eligibleInvestmentMain = taxInfo.getPart3();
            eligibleInvestmentMain.setTaxCode(taxCode);
            taxDao.saveEligibleInvestmentMain(eligibleInvestmentMain);
        } else {
            throw new GlobaException("Missing parameters.");
        }

        List<EligibleInvestmentDetail> investments = taxInfo.getPart3().getInvestments();
        if(investments != null && investments.size() > 1) {
            for(EligibleInvestmentDetail eligibleInvestmentDetail : investments) {
                eligibleInvestmentDetail.setTaxCode(taxCode);
                taxDao.saveEligibleInvestmentDetail(eligibleInvestmentDetail);
            }
        } else {
            throw new GlobaException("Missing parameters.");
        }

        if(taxInfo.getPart4() != null) {
            Part4TaxPayable part4TaxPayable = taxInfo.getPart4();
            part4TaxPayable.setTaxCode(taxCode);
            taxDao.savePart4(part4TaxPayable);
        } else {
            throw new GlobaException("Missing parameters.");
        }

        if(taxInfo.getSectionB() != null) {
            DividendDescription dividendDescription = taxInfo.getSectionB();
            dividendDescription.setTaxCode(taxCode);
            taxDao.saveSectionB(dividendDescription);
        } else {
            throw new GlobaException("Missing parameters.");
        }
    }
}
