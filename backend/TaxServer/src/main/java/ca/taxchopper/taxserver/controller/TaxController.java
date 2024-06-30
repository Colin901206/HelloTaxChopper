package ca.taxchopper.taxserver.controller;

import ca.taxchopper.taxserver.common.ResponseModel;
import ca.taxchopper.taxserver.model.TaxInfo;
import ca.taxchopper.taxserver.service.TaxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
public class TaxController {

    @Autowired
    private TaxService taxService;

    /**
     * Query tax info by tax code
     * @param taxCode
     * @return
     */
    @RequestMapping(value = "/tax/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel queryTax(String taxCode) {
        return new ResponseModel(200, true, "SUCCESS", taxCode);
    }

    /**
     * submit tax
     * @return
     */
    @RequestMapping(value = "/tax/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel submitTax(@RequestBody TaxInfo taxInfo) {
        String taxCode = String.valueOf(System.currentTimeMillis());
        taxService.deleteTax(taxCode);
        taxService.saveTax(taxCode, taxInfo);
        return new ResponseModel(200, true, "Tax info save success.", taxCode);
    }
}
