package com.seisma.employee.service;


import com.seisma.employee.pojo.PayslipRequest;
import com.seisma.employee.pojo.PayslipResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
  List<PayslipResponse> payslipGenerate(List<PayslipRequest> incomeSummary);
  Resource convertCSV(MultipartFile file);

}
