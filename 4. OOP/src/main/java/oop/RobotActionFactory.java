package oop;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Your task is to control a robot using a sequence of textual
 * commands. The robot can move forward, turn left, or turn right. The
 * robot is controlled through the following set of 4 basic commands:
 *
 * - FORWARD
 *   => Move the robot forward
 *
 * - LEFT
 *   => Turn the robot to the left
 *
 * - RIGHT
 *   => Turn the robot to the right
 *
 * - REPEAT N
 *   ...
 *   END REPEAT
 *   => Repeat "N" times the commands "..."
 *
 * For instance, here is a sample sequence of textual commands:
 *
 *  FORWARD
 *  REPEAT 3
 *  FORWARD
 *  RIGHT
 *  END REPEAT
 *  FORWARD
 *  FORWARD
 *
 * If applied to a robot that turns at right angles, this sample
 * sequence would generate the following pattern, where "x" denotes
 * the starting point of the robot, and "^" is its final position:
 *
 *      ^
 *      |
 *      |
 * x---->---->
 *      |    |
 *      |    |
 *      <----<
 *
 * Pay attention to the fact that the "REPEAT" commands can be nested
 * (i.e. a "REPEAT" command may recursively contain other "REPEAT"
 * commands).
 *
 * Using the "Factory" design pattern, you must convert an array of
 * strings containing commands into an "Action" object that can be
 * used to control one instance of the "Robot" interface.
 **/

public class RobotActionFactory {

    /**
     * Interface defining an abstract robot to be controlled.
     **/
    public static interface Robot {

        /**
         * Move the robot forward.
         **/
        void moveForward();

        /**
         * Turn the robot to the left.
         **/
        void turnLeft();

        /**
         * Turn the robot to the right.
         **/
        void turnRight();
    }


    /**
     * Interface defining an abstract action that modifies the state
     * of the given robot. An action can correspond to one isolated
     * command (move forward, turn left, or turn right), to one
     * sequence of actions, or to one repetition of an action.
     **/
    public static interface Action {

        /**
         * Apply this action to the given robot.
         * @param robot The robot.
         **/
        void apply(Robot robot);
    }


    /**
     * This type of "Action" moves the robot forward.
     **/
    private static class MoveForwardAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.moveForward();
        }
    }


    /**
     * This type of "Action" turns the robot to the left.
     **/
    private static class TurnLeftAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnLeft();
        }
    }


    /**
     * This type of "Action" turns the robot to the right.
     **/
    private static class TurnRightAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnRight();
        }
    }


    /**
     * This type of "Action" represents a sequence of actions to be
     * applied to the robot.
     **/
    private static class SequenceOfActions implements Action {
        private List<Action> actions = new LinkedList<Action>();

        /**
         * Append a new action to the end of the sequence of actions.
         * @param action The action to be added.
         **/
        public void add(Action action) {
            actions.add(action);
        }

        @Override
        public void apply(Robot robot) {
            for (Action act : actions){
                act.apply(robot);
            }
        }
    }


    /**
     * This type of "Action" executes another action, for a given
     * number of times.
     **/
    private static class RepeatAction implements Action {
        private int times;
        private Action action;

        /**
         * Constructor for a repetition of one action.
         * @param times The number of times the action must be executed.
         * @param action The action to be repeated.
         **/
        RepeatAction(int times,
                     Action action) {
            this.times = times;
            this.action = action;
        }

        @Override
        public void apply(Robot robot) {
            for (int i=0; i<times; i++){
                this.action.apply(robot);
            }
        }
    }

    /**
     * The factory method you have to implement.
     *
     * NB 1: In order to parse an integer from some string "s", you
     * can use the standard function "Integer.parseInt(s)".
     *
     * NB 2: If the array of commands cannot be parsed (e.g. because
     * of an unknown action, or because of a "REPEAT" command without
     * an "END REPEAT"), you must throw an exception of class
     * "IllegalArgumentException".
     *
     * @param commands The array of commands to drive the robot.
     * @return An "Action" object that will move the robot
     * according to the commands.
     **/
    public Action parse(String commands[]) {
        SequenceOfActions sequence = new SequenceOfActions();

        // TODO Implement the body of this method by filling the "sequence" object

        int index = 0;
        while (index < commands.length){
            String s = commands[index];

            if (s == "LEFT") {
                sequence.add(new TurnLeftAction());
                index ++;
            }
            else if (s == "RIGHT") {
                sequence.add(new TurnRightAction());
                index ++;
            }
            else if (s == "FORWARD") {
                sequence.add(new MoveForwardAction());
                index ++;
            }
            else if (s.startsWith("REPEAT")){

                int times = Integer.parseInt(s.split(" ")[1]);

                System.out.println(times);

                int howmany_commands = index + 1;
                int count_begin_end_to_know_for_error = 1;

                while (howmany_commands < commands.length && count_begin_end_to_know_for_error > 0){
                    if (commands[howmany_commands].startsWith("REPEAT")){
                        count_begin_end_to_know_for_error++;
                    }
                    else if (commands[howmany_commands].equals("END REPEAT")){
                        count_begin_end_to_know_for_error --;
                    }
                    howmany_commands ++;
                }


                if(count_begin_end_to_know_for_error == 0){//recursively use this method
                    Action act = parse(Arrays.copyOfRange(commands, index + 1, howmany_commands -1));
                    sequence.add(new RepeatAction(times, act));
                    index = howmany_commands ;

                }
                else{
                    throw new IllegalArgumentException("Missing END REPEAT");
                }

            }

        }

        return sequence;
    }



}
