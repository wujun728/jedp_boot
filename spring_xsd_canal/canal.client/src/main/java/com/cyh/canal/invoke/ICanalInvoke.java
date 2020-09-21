package com.cyh.canal.invoke;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;

/**
 * @author yonghui.cui
 * @version 1.0  2015/1/25
 */
public interface ICanalInvoke {
    public void onInsert(CanalEntry.RowData rowData);
    public void onDelete(CanalEntry.RowData rowData);
    public void onUpdate(CanalEntry.RowData rowData);
}
