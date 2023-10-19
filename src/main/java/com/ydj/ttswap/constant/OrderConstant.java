package com.ydj.ttswap.constant;

public class OrderConstant {
    public final static int USER_ORDER_TIME_LIMIT = 60*60*1000*3;//用户下单付款交易时限
    public final static int ORDER_STATUS_TIME_LIMIT = 60*60*1000*24*3;//订单状态变更确认时限
    public final static double DEDUCTION_BASE = 0.1;//币商违规扣款总基数
    public final static double DEDUCTIONS_BASE = 0.05;//币商违规门户扣款基数
    public final static int ORDER_MONITORING = 10*60*1000;//用户下单时间限制任务间隔 10分钟
    public final static int STATUS_MONITORING = 60*60*1000;//订单状态变更时间限制任务间隔 1小时
    public final static int DELAYED = 60*60*1000;//定时任务延时时长

    public enum StatusEnum {

        PLACE_ORDER(1001,"下单成功"),
        CANCELLATION_OF_ORDER(1002,"取消订单"),
        M_C_P(1003,"币商确认收款"),
        U_C_C(1004,"用户确认收币"),
        TRANSACTION_COMPLETED(1005,"交易完成"),
        M_NO_P_USER_UP_IMG(1006,"币商未收款用户上传图片凭证"),
        M_NO_P_MERCHANT_UP_IMG(1007,"币商未收款币商上传图片凭证"),
        M_NO_P_USER_UP_VIDEO(1008,"币商未收款用户上传视频凭证"),
        M_NO_P_MERCHANT_UP_VIDEO(1009,"币商未收款币商上传视频凭证"),
        O_E_W_F_P_P(1010,"订单异常等待平台处理"),
        FAIL(1011,"交易失败"),
        EXCEPTION(1012,"异常交易"),
        U_NO_C_USER_UP_IMG(1013,"用户未收币用户上传图片凭证"),
        U_NO_C_MERCHANT_UP_IMG(1014,"用户未收币币商上传图片凭证"),
        U_NO_C_USER_UP_VIDEO(1015,"用户未收币用户上传视频凭证"),
        U_NO_C_MERCHANT_UP_VIDEO(1016,"用户未收币币商上传视频凭证"),
        M_C_C(1017,"币商确认收币"),
        U_C_P(1018,"用户确认收款"),
        M_NO_C_USER_UP_IMG(1019,"币商未收币用户上传图片凭证"),
        M_NO_C_MERCHANT_UP_IMG(1020,"币商未收币币商上传图片凭证"),
        M_NO_C_USER_UP_VIDEO(1021,"币商未收币用户上传视频凭证"),
        M_NO_C_MERCHANT_UP_VIDEO(1022,"币商未收币币商上传视频凭证"),
        U_NO_P_USER_UP_IMG(1023,"用户未收款用户上传图片凭证"),
        U_NO_P_MERCHANT_UP_IMG(1024,"用户未收款币商上传图片凭证"),
        U_NO_P_USER_UP_VIDEO(1025,"用户未收款用户上传视频凭证"),
        U_NO_P_MERCHANT_UP_VIDEO(1026,"用户未收款币商上传视频凭证");
        private int code;
        private String msg;

        StatusEnum(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum JsEnum {
        C_M(1,"币商"),
        USER(2,"用户");
        private int code;
        private String msg;

        JsEnum(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
