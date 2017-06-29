package com.rkylin.risk.boss.restController;

import com.rkylin.risk.core.entity.Authorization;
import com.rkylin.risk.core.entity.RiskGradeCust;
import com.rkylin.risk.core.service.OperatorLogService;
import com.rkylin.risk.core.service.RiskGradeCustService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 201508031790 on 2015/9/21.
 */
@RestController
@RequestMapping("/api/1/amlGradeCust")
public class AmlGradeCustRestAction {

  @Resource
  private RiskGradeCustService riskGradeCustService;
  @Resource
  private OperatorLogService operatorLogService;

  //设置客户反洗钱风险等级
  @RequestMapping(value = "updateAmlGrade", method = RequestMethod.POST)
  public RiskGradeCust updateGrade(@RequestParam String ids, @RequestParam String updateGrade,
      @RequestParam String reason, HttpServletRequest request) {
    Authorization auth = (Authorization) request.getSession().getAttribute("auth");
    if (riskGradeCustService.updateRiskGradeList(ids, updateGrade, reason, auth)) {
      operatorLogService.insert(auth.getUserId(), auth.getUsername(), "RiskGradeCust", "更新");
      return new RiskGradeCust();
    } else {
      return null;
    }
  }

  //审核客户反洗钱风险等级
  @RequestMapping(value = "updateAmlGradeStatus", method = RequestMethod.POST)
  public RiskGradeCust updateGradeStrtus(@RequestParam String ids, @RequestParam String status,
      HttpServletRequest request) {
    Authorization auth = (Authorization) request.getSession().getAttribute("auth");
    if (riskGradeCustService.updateRiskStatusList(ids, status, auth)) {
      operatorLogService.insert(auth.getUserId(), auth.getUsername(), "RiskGradeCust", "审核");
      return new RiskGradeCust();
    } else {
      return null;
    }
  }
}
