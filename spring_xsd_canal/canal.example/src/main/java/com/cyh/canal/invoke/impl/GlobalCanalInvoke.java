package com.cyh.canal.invoke.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cyh.canal.invoke.ICanalInvoke;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yonghui.cui
 * @version 1.0  2015/1/25
 */
@Service(value = "globalCanalInvoke")
public class GlobalCanalInvoke implements ICanalInvoke {
    @Override
    public void onInsert(CanalEntry.RowData rowData) {
        printColumn(rowData.getAfterColumnsList());
    }

    @Override
    public void onDelete(CanalEntry.RowData rowData) {
        printColumn(rowData.getBeforeColumnsList());
    }

    @Override
    public void onUpdate(CanalEntry.RowData rowData) {
        System.out.println("-------> before");
        printColumn(rowData.getBeforeColumnsList());
        System.out.println("-------> after");
        printColumn(rowData.getAfterColumnsList());
    }

    private static void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }
}
