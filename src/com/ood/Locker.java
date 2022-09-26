package com.ood;

public class Locker {

    /**
     * 用户操作：
     * 1.快递员找到合适的柜子，放包裹
     * 2.用户根据code取柜子里的快递
     * 考虑一个格子里面放多个包裹？超时收费通知？管理员操作？
     * 是否考虑扩展？interface+abstract class
     *
     * 设计
     * 1.Slot类代表格子，有id/status/size/packageInfo/code信息；方法包括put(生成验证码)/get(校验验证码)
     * 2.Package类，有id/size/lockerId/slotId/status; 方法包括updateStatus
     * 3.Locker类，有id/address/slotMap(size->list)/codeMap(验证码对应slot)；方法包括isFull/get/put/open/close/generate/valid/checkExpire(calculateFee)
     * 4.枚举类size/status
     * 5.Code类存储验证码，有value和expiration；方法包括generate/valid
     */
}
