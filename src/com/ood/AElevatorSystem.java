package com.ood;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class AElevatorSystem {
    /**
     * 只考虑一台电梯的情况，是否考虑电梯的容量负载，以及开门关门(超重不能关门),是否考虑扩展？interface+abstract class
     * 是否支持取消，考虑加入队列的时候维护一个hashmap，取消的时候直接在map里删掉，遍历到队列元素的时候去map中check是否请求还有效
     * ！！电梯往上的时候，在外侧能按向下，在里面不能按向下?空闲时候先处理哪个队列？根据当前楼层和目的地的距离？上下各自的请求数量？还是默认先上后下使用strategy实现
     * 是否考虑开门关门动作
     *
     * cases：
     * 1.电梯状态有up/down/idle
     * 2.电梯方向有up/down
     * 3.用户可以在电梯外和电梯内按下按钮，因此有内外两种请求，如果人在外面，那么先要去对应楼层接人，然后再去target楼层
     * 4.!!!有IStrategy接口定义实现，可扩展多种运行策略
     * 4.使用两个优先队列处理请求
     * 5.run方法执行，while一个队列不空，就需要处理请求，所有队列为空则电梯状态为idle，两个队列交替执行
     * 6.电梯当前状态为up或idle，则先处理向上队列，再处理向下队列；否则先处理向下队列，再处理向上队列
     * 7.处理向上队列，逐个pop队列的楼层，更新当前电梯的楼层，当队列为空时，如果向下队列不为空，则更改方向为向下，否则更新为stop
     * 8.处理向下队列类似，当队列为空时，如果向上队列不为空，则更为向上，否则为stop
     */

    enum Status{
        UP,
        DOWN,
        IDLE
    }
    enum Direction{UP,DOWN}
    enum Location{IN,OUT}

    interface IRequest{
        int getTargetLevel();
        Direction getDirection();
        Location getLocation();
    }

    static abstract class Request implements IRequest{
        int targetLevel;
        Direction direction;
        Location location;
        public Request(int targetLevel, Direction direction, Location location) {
            this.targetLevel = targetLevel;
            this.direction = direction;
            this.location = location;
        }
        @Override
        public int getTargetLevel() {
            return targetLevel;
        }
        @Override
        public Direction getDirection() {
            return direction;
        }
        @Override
        public Location getLocation() {
            return location;
        }
    }

    static class ExternalRequest extends Request{
        int currentLevel;

        public ExternalRequest(int targetLevel, Direction direction, Location location, int currentLevel) {
            super(targetLevel, direction, location);
            this.currentLevel = currentLevel;
        }
        public int getCurrentLevel() {return currentLevel;}
    }
    static class InternalRequest extends Request{
        public InternalRequest(int targetLevel, Direction direction, Location location) {
            super(targetLevel, direction, location);
        }
    }
    static class AElevator{
        int currentFloor, minFloor, maxFloor;
        Status status;
        PriorityQueue<Request> upQueue, downQueue;
        Queue<Request> waitUpQueue, waitDownQueue;

        public AElevator(int currentFloor, int minFloor, int maxFloor) {
            this.currentFloor = currentFloor;
            this.minFloor = minFloor;
            this.maxFloor = maxFloor;
            status = Status.IDLE;
            upQueue = new PriorityQueue<>((a,b)->a.getTargetLevel()-b.getTargetLevel());
            downQueue = new PriorityQueue<>((a,b)->b.getTargetLevel()-a.getTargetLevel());
            waitUpQueue = new ArrayDeque<>();
            waitDownQueue = new ArrayDeque<>();
        }
        public void callOutSide (int curFloor, Direction direction){
            if(direction==Direction.UP) {
                if(status==Status.UP||status==Status.IDLE) {
                    if(curFloor>=currentFloor) {
                        System.out.println("Outside user at level: "+curFloor+" press up button...");
                        upQueue.offer(new ExternalRequest(curFloor, Direction.UP, Location.OUT, curFloor));
                    } else {
                        // 当前电梯在20层，我在3层按向上，不能直接把3层插入向上队列
                        System.out.println("Outside user at level: "+curFloor+" press up button, add to wait queue...");
                        waitUpQueue.offer(new ExternalRequest(curFloor, Direction.UP, Location.OUT, curFloor));
                    }
                } else {
                    System.out.println("Outside user at level: "+curFloor+" press up button...");
                    upQueue.offer(new ExternalRequest(curFloor, Direction.UP, Location.OUT, curFloor));
                }
            } else {
                if(status==Status.DOWN||status==Status.IDLE) {
                    if(curFloor<=currentFloor) {
                        System.out.println("Outside user at level: "+curFloor+" press down button...");
                        upQueue.offer(new ExternalRequest(curFloor, Direction.DOWN, Location.OUT, curFloor));
                    } else {
                        // 当前电梯在3层，我在20层按向下，不能直接把20层插入向下队列
                        System.out.println("Outside user at level: "+curFloor+" press down button, add to wait queue...");
                        waitDownQueue.offer(new ExternalRequest(curFloor, Direction.DOWN, Location.OUT, curFloor));
                    }
                } else {
                    System.out.println("Outside user at level: "+curFloor+" press down button...");
                    downQueue.offer(new ExternalRequest(curFloor, Direction.DOWN, Location.OUT, curFloor));
                }
            }

//            if(currentFloor<=curFloor) {
//                System.out.println("Outside user at level: "+curFloor+" press up button...");
//                upQueue.offer(new ExternalRequest(curFloor, Direction.UP, Location.OUT, curFloor));
//            } else {
//                System.out.println("Outside user at level: "+curFloor+" press down button...");
//                downQueue.offer(new ExternalRequest(curFloor, Direction.DOWN, Location.OUT, curFloor));
//            }
        }
        public void sendRequest(int target){
            if((status==Status.UP || status==Status.IDLE)&&target>=currentFloor) {
                System.out.println("In user wanna go up to level: "+target);
                upQueue.offer(new InternalRequest(target, Direction.UP, Location.IN));
            } else if((status==Status.DOWN || status==Status.IDLE)&&target<=currentFloor) {
                System.out.println("In user wanna go down to level: "+target);
                downQueue.offer(new InternalRequest(target, Direction.DOWN, Location.IN));
            } else {
                System.out.println("In user wanna go to level: "+target+", but direction is opposite...");
            }
        }
        // execute
        public void run(){
            while (!upQueue.isEmpty()||!downQueue.isEmpty()) {
                handle();
            }
            System.out.println("Elevator becomes idle...");
            status = Status.IDLE;
        }
        private void handle(){
            System.out.println("===================start to handle requests...================");
            if(status == Status.UP || status==Status.IDLE) {
                handleUpRequest();
                handleDownRequest();
            } else {
                handleDownRequest();
                handleUpRequest();
            }
        }
        private void handleUpRequest() {
            while (!upQueue.isEmpty()){
                Request request = upQueue.poll();
                if(request instanceof ExternalRequest) {
                    System.out.println("Elevator go up and pick up user at level: "+request.getTargetLevel());
                } else {
                    System.out.println("Elevator go up and stop at level: "+request.getTargetLevel());
                }
                currentFloor = request.getTargetLevel();
            }
            if(!downQueue.isEmpty()) status = Status.DOWN;
            else status = Status.IDLE;
            while (!waitUpQueue.isEmpty()) upQueue.offer(waitUpQueue.poll());
        }
        private void handleDownRequest() {
            while (!downQueue.isEmpty()){
                Request request = downQueue.poll();
                if(request instanceof ExternalRequest) {
                    System.out.println("Elevator go down and pick up user at level: "+request.getTargetLevel());
                } else {
                    System.out.println("Elevator go down and stop at level: "+request.getTargetLevel());
                }
                currentFloor = request.getTargetLevel();
            }
            if(!upQueue.isEmpty()) status = Status.UP;
            else status = Status.IDLE;
            while (!waitDownQueue.isEmpty()) downQueue.offer(waitDownQueue.poll());
        }
    }

    public static void main(String[] args) {
        AElevator elevator = new AElevator(1, -2, 34);
        elevator.sendRequest(2);
        elevator.callOutSide(3, Direction.UP);
        elevator.sendRequest(32);
        elevator.callOutSide(34, Direction.DOWN);

        elevator.run();
    }
}
