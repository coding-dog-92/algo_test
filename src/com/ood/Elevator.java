package com.ood;

import java.util.PriorityQueue;

public class Elevator {

    /**
     * 只考虑一个电梯的情况，是否考虑电梯的容量负载，以及开门关门(超重不能关门),是否考虑扩展？interface+abstract class
     * 1.电梯的方向/状态有三个，up/down/stop，按电梯可以在外面/里面两个位置
     * 2.定义request，包含currentFloor/target/方向/位置
     * 3.Elevator类有currentFloor、方向、向上优先队列，向下优先队列
     * 4.发送请求的时候，如果人在外面，那么先要去对应楼层接人，然后再去target楼层
     * 5.run方法执行，while一个队列不空，就需要处理请求，所有队列为空则电梯状态为stop
     * 6.电梯当前状态为up或stop，则先处理向上队列，再处理向下队列；否则先处理向下队列，再处理向上队列
     * 7.处理向上队列，逐个pop队列的楼层，更新当前电梯的楼层，当队列为空时，如果向下队列不为空，则更改方向为向下，否则更新为stop
     * 8.处理向下队列类似，当队列为空时，如果向上队列不为空，则更为向上，否则为stop
     */

    enum Direction {
        UP,
        DOWN,
        IDLE
    }
    public enum Location {
        INSIDE_ELEVATOR,
        OUTSIDE_ELEVATOR
    }

    public static class Request {

        int currentFloor;
        int desiredFloor;
        Direction direction;
        Location location;

        public Request(int currentFloor, int desiredFloor, Direction direction, Location location) {
            this.currentFloor = currentFloor;
            this.desiredFloor = desiredFloor;
            this.direction = direction;
            this.location = location;
        }
    }

    int currentFloor;
    Direction direction;
    PriorityQueue<Request> upQueue;
    PriorityQueue<Request> downQueue;

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;

        this.direction = Direction.IDLE;

        // use default, which is a Min heap
        upQueue = new PriorityQueue<>((a, b) -> a.desiredFloor - b.desiredFloor);

        // use a max heap
        downQueue =  new PriorityQueue<>((a, b) -> b.desiredFloor - a.desiredFloor);
    }

    public void sendUpRequest(Request upRequest) {
        // If the request is sent from out side of the elevator,
        // we need to stop at the current floor of the requester
        // to pick him up, and then go the the desired floor.
        if (upRequest.location == Location.OUTSIDE_ELEVATOR) {
            // Go pick up the requester who is outside of the elevator
            upQueue.offer(new Request(upRequest.currentFloor,
                    upRequest.currentFloor,
                    Direction.UP,
                    Location.OUTSIDE_ELEVATOR));

            System.out.println("Append up request going to floor " + upRequest.currentFloor + ".");
        }

        // Go to the desired floor
        upQueue.offer(upRequest);

        System.out.println("Append up request going to floor " + upRequest.desiredFloor + ".");
    }

    public void sendDownRequest(Request downRequest)
    {
        // Similar to the sendUpRequest logic
        if (downRequest.location == Location.OUTSIDE_ELEVATOR) {
            downQueue.offer(new Request(downRequest.currentFloor,
                    downRequest.currentFloor,
                    Direction.DOWN,
                    Location.OUTSIDE_ELEVATOR));

            System.out.println("Append down request going to floor " + downRequest.currentFloor + ".");
        }

        // Go to the desired floor
        downQueue.offer(downRequest);

        System.out.println("Append down request going to floor " + downRequest.desiredFloor + ".");
    }

    public void run() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            processRequests();
        }

        System.out.println("Finished all requests.");
        this.direction = Direction.IDLE;
    }

    private void processRequests() {
        if (this.direction == Direction.UP || this.direction == Direction.IDLE) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }

    private void processUpRequest() {
        while (!upQueue.isEmpty()) {
            Request upRequest = upQueue.poll();
            // Communicate with hardware
            this.currentFloor = upRequest.desiredFloor;
            System.out.println("Processing up requests. Elevator stopped at floor " + this.currentFloor + ".");
        }
        if (!downQueue.isEmpty()) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.IDLE;
        }
    }

    private void processDownRequest() {
        while (!downQueue.isEmpty()) {
            Request downRequest = downQueue.poll();
            // Communicate with hardware
            this.currentFloor = downRequest.desiredFloor;
            System.out.println("Processing down requests. Elevator stopped at floor " + this.currentFloor + ".");
        }
        if (!upQueue.isEmpty()) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.IDLE;
        }
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(0);


        Request upRequest1 = new Request(elevator.currentFloor, 5, Direction.UP, Location.INSIDE_ELEVATOR);
        Request upRequest2 = new Request(elevator.currentFloor, 3, Direction.UP, Location.INSIDE_ELEVATOR);

        Request downRequest1 = new Request(elevator.currentFloor, 1, Direction.DOWN, Location.INSIDE_ELEVATOR);
        Request downRequest2 = new Request(elevator.currentFloor, 2, Direction.DOWN, Location.INSIDE_ELEVATOR);
        Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);

        // Two people inside of the elevator pressed button to go up to floor 5 and 3.
        elevator.sendUpRequest(upRequest1);
        elevator.sendUpRequest(upRequest2);

        // One person outside of the elevator at floor 4 pressed button to go down to floor 0
        elevator.sendDownRequest(downRequest3);

        // Two person inside of the elevator pressed button to go down to floor 1 and 2.
        elevator.sendDownRequest(downRequest1);
        elevator.sendDownRequest(downRequest2);


        elevator.run();
    }
}
