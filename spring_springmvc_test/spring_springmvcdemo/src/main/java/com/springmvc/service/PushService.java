package com.springmvc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by zsq on 16/12/21.
 * 定时任务
 */
@Service
public class PushService {

    private DeferredResult<String> deferredResult;

    /**
     * 返回一个 DeferredResult
     * @return
     */
    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    /**
     * 定时任务, 每5s 更新一次
     */
    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }
}
