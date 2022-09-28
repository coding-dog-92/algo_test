package com.ood;

public class SnakeGame {

    /**
     * https://www.geeksforgeeks.org/design-snake-game/
     * 格子大小固定、地图随机生成一个食物(不能和蛇重合)、蛇可以四个方向移动（不能反向移动）、蛇初始长度固定，吃到食物会增长，
     * 蛇碰到边界或者头碰到身体会终止游戏，游戏状态有运行/暂停/终止、有对应积分，根据蛇长度计算
     *
     * 1. Cell类表示格子，有行列坐标，格子类型(枚举类型：蛇、空白、食物)
     * 2. Snake类表示蛇，有一个双向队列表示蛇的身体，有一个head表示头的坐标；方法包含grow()把head加进去就行，这样下一帧移动的时候相当于只有head往前移动了一下，长度+1了、
     *       move(Cell cell)把head置为cell，tail置为空格子，注意不能反向移动
     *      、checkCrash()、CheckFood()头和食物坐标重合
     * 3. Panel类表示游戏面板，有二维数组维护row*col个cell，有generateFood方法，注意排除掉snake的格子，将panel上的某个empty格子置为food
     * 4. SnakeGame类，包含snake、panel、direction和status(枚举运行、终止和暂停)，有init方法(生成snake和food，积分清零)、update方法(只要没有over，就一直朝当前方向移动，直到结束)和
     *      getNextCell(根据当前坐标和方法生成下一个位置)方法
     */
}
