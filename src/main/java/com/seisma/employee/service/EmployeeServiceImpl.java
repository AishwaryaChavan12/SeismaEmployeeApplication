package com.seisma.employee.service;

import com.seisma.employee.pojo.PayslipRequest;
import com.seisma.employee.pojo.PayslipResponse;
import com.seisma.employee.util.SalaryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

  @Autowired
  private SalaryUtil salaryUtil;

  String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

  @Override
  public List<PayslipResponse> payslipGenerate(List<PayslipRequest> payslipRequests) {
    List<PayslipResponse> pResponse = new ArrayList<>();
    for(PayslipRequest request:payslipRequests){
        PayslipResponse pres = new PayslipResponse();
        Integer gross = salaryUtil.calculateMonthlyGrossIncome(request.getAnnualSalary());
        Integer tax = salaryUtil.calculateMonthlyIncomeTax(request.getAnnualSalary(), Month.of(request.getPaymentMonth()));
        Integer net = salaryUtil.calculateNetIncome(gross, tax);
        Long superannuation = salaryUtil.calculateSuperannuation(gross, request.getSuperRate()*100);
        pres.setEmployee(request);
        pres.setGrossIncome(gross);
        pres.setIncomeTax(tax);
        pres.setNetIncome(net);
        pres.setSuperannuation(superannuation);
        pResponse.add(pres);
        YearMonth yearMonth = YearMonth.of( Calendar.getInstance().get(Calendar.YEAR), request.getPaymentMonth() );
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        LocalDate last = yearMonth.atEndOfMonth();
        pres.setFromDate(firstOfMonth.getDayOfMonth()+" "+monthNames[request.getPaymentMonth()-1]);
        pres.setToDate(last.getDayOfMonth()+" "+monthNames[request.getPaymentMonth()-1]);
    }

    return pResponse;
  }

    @Override
    public Resource convertCSV(MultipartFile file) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PayslipRequest> pRequests = new ArrayList<>();
        List<String> dataLines = new ArrayList<>();
        dataLines.add(convertToCSV(new String[]
                { "Name", "Pay Period", "Gross Income", "Income Tax", "Net Income", "Super" }));

        for(List<String> record:records){
            Integer gross = salaryUtil.calculateMonthlyGrossIncome(Integer.valueOf(record.get(2)));
            Integer tax = salaryUtil.calculateMonthlyIncomeTax(Integer.valueOf(record.get(2)), Month.of(Integer.valueOf(record.get(4))));
            Integer net = salaryUtil.calculateNetIncome(gross, tax);
            Long superannuation = salaryUtil.calculateSuperannuation(gross, Double.valueOf(record.get(3))*100);
            YearMonth yearMonth = YearMonth.of( Calendar.getInstance().get(Calendar.YEAR), Integer.valueOf(record.get(4)) );
            LocalDate firstOfMonth = yearMonth.atDay( 1 );
            LocalDate last = yearMonth.atEndOfMonth();
            dataLines.add(convertToCSV(new String[]
                    { record.get(0)+" "+record.get(1), firstOfMonth.getDayOfMonth()+" "+monthNames[Integer.valueOf(record.get(4))-1]+" - " + last.getDayOfMonth()+" "+monthNames[Integer.valueOf(record.get(4))-1] , String.valueOf(gross), String.valueOf(tax), String.valueOf(net), String.valueOf(superannuation) }));
        }


        Resource resource = null;

        ByteArrayOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        outputStream = new ByteArrayOutputStream();
        for (String line : dataLines) {
            try {
                outputStream.write(line.getBytes());
                outputStream.write("\r\n".getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Internal Server Error");
            }
        }

        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        resource = new InputStreamResource(inputStream);
        return  resource;
    }


private String convertToCSV(String[] data) {
    return Stream.of(data)
            .map(this::escapeSpecialCharacters)
            .collect(Collectors.joining(","));
}

private String escapeSpecialCharacters(String data) {

    String escapedData = data.replaceAll("\\R", " ");
    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
        data = data.replace("\"", "\"\"");
        escapedData = "\"" + data + "\"";
    }
    return escapedData;
}

}