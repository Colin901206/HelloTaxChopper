package ca.taxchopper.taxserver.controller;

import ca.taxchopper.taxserver.common.GlobaException;
import ca.taxchopper.taxserver.common.ResponseModel;
import ca.taxchopper.taxserver.model.TaxInfo;
import ca.taxchopper.taxserver.service.TaxService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TaxController {

    @Autowired
    private TaxService taxService;

    /**
     * submit tax
     *
     * @return
     */
    @RequestMapping(value = "/tax/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel submitTax(@RequestBody TaxInfo taxInfo) {
        Long start = System.currentTimeMillis();
        Gson gson = new Gson();
        String jsonString = gson.toJson(taxInfo);
        log.info("TaxInfo: ");
        log.info(jsonString);
        String taxCode = taxInfo.getTaxCode();
        if(StringUtils.isEmpty(taxCode)) {
            taxCode = String.valueOf(System.currentTimeMillis());
        }
        taxService.deleteTax(taxCode);
        taxService.saveTax(taxCode, taxInfo);
        log.info("Interface [ /tax/save ] time costs: " + (System.currentTimeMillis() - start) + " ms");
        return new ResponseModel(200, true, "Tax info save success.", taxCode);
    }

    /**
     * Query tax info by tax code
     *
     * @param taxCode
     * @return
     */
    @RequestMapping(value = "/tax/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel queryTax(String taxCode) {
        Long start = System.currentTimeMillis();
        log.info("TaxCode: " + taxCode);
        if(StringUtils.isEmpty(taxCode)) {
            throw new GlobaException("Tax code cannot be null.");
        }
        TaxInfo taxInfo = taxService.queryTax(taxCode);
        log.info("Interface [ /tax/info ] time costs: " + (System.currentTimeMillis() - start) + " ms");
        return new ResponseModel(200, true, "SUCCESS", taxInfo);
    }
}
