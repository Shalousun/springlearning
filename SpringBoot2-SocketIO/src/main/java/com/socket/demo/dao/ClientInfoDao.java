package com.socket.demo.dao;

import com.socket.demo.model.ClientInfo;

/**
 * @author yu 2019/1/20.
 */
public interface ClientInfoDao {

    /**
     * 根据客服端id查找
     * @param clientId
     * @return
     */
    ClientInfo findClientByclientId(String clientId);

    /**
     * 保存
     * @param clientInfo
     * @return
     */
    int save(ClientInfo clientInfo);
}
