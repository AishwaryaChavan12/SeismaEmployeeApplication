package com.seisma.employee.controller;

import com.seisma.employee.pojo.PayslipRequest;
import com.seisma.employee.pojo.PayslipResponse;
import com.seisma.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/employee")
public class EmployeeController {

  private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(value = "/payslip/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PayslipResponse>> payslipGenerate(@Valid @RequestBody List<PayslipRequest> payslipRequest) {
    final List<PayslipResponse> pResponse = employeeService.payslipGenerate(payslipRequest);
    return new ResponseEntity<>(pResponse, HttpStatus.OK);
  }

  @RequestMapping(value = "/payslip/generate/csv", method = RequestMethod.POST)
  public ResponseEntity<Resource> payslipGenerateCSV(@RequestParam("file") MultipartFile file) {

    if (file.getContentType().equals("text/csv") || file.getContentType().equals("application/csv")) {

      return ResponseEntity
              .ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"summary.csv\"")
              .header(HttpHeaders.CONTENT_TYPE, "text/csv")
              .body(employeeService.convertCSV(file));
    }

    throw new RuntimeException("Unsupported File Type");
  }
}
