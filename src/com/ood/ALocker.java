package com.ood;

import java.util.Map;

public class ALocker {

    /**
     * 一个快递柜还是多个快递柜子？如果是多个那么首先要找到一个距离用户最近的，且合适的快递柜
     * 1.快递员->找到合适的格子->开门->放包裹->关门->发短信（验证码有时效性，包裹存放超过一定时间会通知用户和快递员）
     * 2.用户输入code->校验->开门->取出包裹->关门->验证码失效
     * 3.柜子和包裹都枚举标准化尺寸
     * 考虑一个格子里面放多个包裹？超时收费通知？管理员操作？用户能不能退货放到柜子里
     * 考虑扩展？interface+abstract class，例如locker有普通货物柜，外卖柜，电动车电池柜
     *
     * Iuser接口，分为快递员(可以找空余的格子)/client/admin三种
     * Lock分为物品柜，外卖柜等等
     * Package带type
     *
     * 设计
     * 1.枚举size和status
     * 2.Slot类代表格子，有id/status/size/packageInfo信息, 方法包括open/close/
     * 3.IUser接口，定义get方法
     * 4.AbstractUser类，有userType类型
     * 5.Client类，有id信息，有get方法
     * 6.Courier类，有id信息，有findClosestLocker(待定，如果只有一个柜子就不用)/put/get
     * 7.Package类，有id/size/lockerId/slotId/status/code/startTime/endTime; 方法包括updateStatus
     * 8.ILocker接口，定义getId/isFull/..
     * 9.AbstractLocker类，有id...
     * 8.GoodsLockerLocker类，有id/location/slotMap(size->{slotId->slot})/codeMap(验证码对应slot)；方法包括isFull/get/put/open/close/generate/valid/checkExpire(calculateFee)
     * 9.Code类存储验证码，有value和expiration；方法包括generate/valid
     */

    interface IUser{
        UserTypeEnum getUserType();
        String getUserId();
    }
    static abstract class User implements IUser{
        UserTypeEnum type;
        String userId;
        @Override
        public UserTypeEnum getUserType() {
            return type;
        }
        @Override
        public String getUserId() {
            return userId;
        }
    }
    static class Client extends User{
    }
    static class Courier extends User{}

    static class Slot{
        String id;
        SizeEnum size;
        Package packageInfo;
        public void get(){}
        public void put(){}
    }

    static class Location{}
    interface ILocker{}
    static abstract class Locker implements ILocker{
        String id;
        Location location;
        Map<String, Slot> slotMap;
        Map<String, Package> codeMap;

        public void lookUp(){};
        public void get(){};
        public void put(){};
        public void generateCode(){};
        public void checkValidCode(){};
        public void isFull(){};
    }
    static class GoodsLocker extends Locker{}

    interface IPackage{}
    static abstract class Package implements IPackage{
        String id;
        SizeEnum size;
        StatusTypeEnum status;
        Code code;
        String slotId;
    }
    static class GoodsPackage extends Package{}

    static class Code{}
    enum SizeEnum{}
    enum UserTypeEnum{}
    enum PackageTypeEnum{}
    enum StatusTypeEnum{}

    static class LockerManager{}

    public static void main(String[] args) {

    }
}
