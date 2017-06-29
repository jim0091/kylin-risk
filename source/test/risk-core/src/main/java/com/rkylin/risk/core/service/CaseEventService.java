package com.rkylin.risk.core.service;

import com.rkylin.risk.core.entity.CaseEvent;

/**
 * Created by lina on 2016-3-28.
 */
public interface CaseEventService {
    /**
     * 添加案例风险事件关联表
     * @param caseEvent
     * @return
     */
    CaseEvent insert(CaseEvent caseEvent);
}
