package com.common.service;

/**
 * Created by Bodil on 2016/9/14.
 * 服务执行结果
 */
public class ServiceResult<T> {
    private boolean success;//是否执行成功
    private String message;//返回消息
    private T affected_object;//受影响的对象

    /**
     * 成功的服务执行结果
     */
    public final static ServiceResult<Object> Success_Service_Result = new ServiceResult<>(true);

    /**
     * 构造一个服务执行结果
     * @param success 是否执行成功
     */
    public ServiceResult(boolean success) {
        this.success = success;
    }

    /**
     * 构造一个服务执行结果
     * @param success 是否执行成功
     * @param message 返回消息
     */
    public ServiceResult(boolean success, String message) {
        this(success);
        this.message = message;
    }

    /**
     * 构造一个服务执行结果
     * @param success 是否执行成功
     * @param message 返回消息
     * @param affected_object 受到影响的对象（可以是Entity对象，也可以是其它对象）
     */
    public ServiceResult(boolean success, String message, T affected_object) {
        this(success, message);
        this.affected_object = affected_object;
    }

    /**
     * 获取是否执行成功
     * @return true：成功；false：失败
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置服务是否成功
     * @param success 是否执行成功
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取消息
     * @return 服务执行后返回的消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置服务执行后返回的消息
     * @param message 消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取受到影响的对象
     * @return 受到影响的对象
     */
    public T getAffected_object() {
        return affected_object;
    }

    /**
     * 设置受到影响的对象
     * @param affected_object 收到影响的对象
     */
    public void setAffected_object(T affected_object) {
        this.affected_object = affected_object;
    }
}
