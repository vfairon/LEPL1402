package oop;

import java.util.ArrayList;

/**
 * This class can be used to build simple arithmetic expression
 * with binary operator +,-,* and involving one variable 'x'.
 *
 * The expression can be
 * 1) evaluated by replacing the variable x with a specific value
 * 2) derivated to obtain a new expression
 *
 * You must modify this class to make it work
 * You can/should extend this class with inner classes the way you want.
 * You can also modify it but you are not allowed to modify the signature
 * of existing methods
 *
 * As a reminder, the formulas for the derivations as are followed
 *  - (f + g)' = f' + g'
 *  - (f*g)' = f'g + fg'
 *  - (x)' = 1
 *  - (C)' = 0 with C a constant
 */
public abstract class Expression {


    public ArrayList<Float> coef = new ArrayList<>();

    /**
     * Creates the basic variable expression 'x'
     * @return the expression 'x'
     */
    public static Expression x() {
        return new X();

    }

    /**
     * Creates the basic constant expression 'v'
     * @return the expression 'v'
     */
    public static Expression value(double v) {
         return new Constant(v);
    }

    /**
     * Creates the binary expression 'this + r'
     * @param r the right operator
     * @return the binary expression 'this + r'
     */
    public Expression plus(Expression r) {
         return new BinairyExpression('+',this,r);
    }

    /**
     * Creates the binary expression 'this - r'
     * @param r the right operator
     * @return the binary expression 'this - r'
     */
    public Expression minus(Expression r) {
         return new BinairyExpression('-',this,r);
    }

    /**
     * Creates the binary expression 'this * r'
     * @param r the right operator
     * @return the binary expression 'this * r'
     */
    public Expression mul(Expression r) {
        return new BinairyExpression('*',this,r);
    }

    /**
     * Evaluate the expression with fixed value for x
     * @param xValue the value taken by x for the evaluation
     * @return the evaluation of the expression considering x=xValue
     */
    public abstract double evaluate(double xValue);

    /**
     * Derivate the expression wrt to 'x'
     * @return the derivative of the expression with respect to 'x'
     */
    public abstract Expression derivate();


    private static class  BinairyExpression extends Expression{


        Expression Left;
        Expression Right;

        char operator;

        BinairyExpression(char op, Expression L, Expression R){
            this.Left = L;
            this.Right = R;
            this.operator = op;
        }




        @Override
        public double evaluate(double xValue) {
            double Left_Val = Left.evaluate(xValue);
            double Right_Val = Right.evaluate(xValue);
            switch(operator){
                case '+':
                    return Left_Val + Right_Val;
                case '-' :
                    return Left_Val - Right_Val;
                case '*' :
                    return Left_Val * Right_Val;
                default:
                    throw new IllegalArgumentException("unkown operator " + operator);

            }
        }

        @Override
        public Expression derivate() {
            Expression L = Left.derivate();
            Expression R = Right.derivate();

            switch(operator){
                case '+':
                    return L.plus(R);
                case '-' :
                    return L.minus(R) ;
                case '*' :
                    return L.mul(Right).plus(Left.mul(R));
                default:
                    throw new IllegalArgumentException("unkown operator " + operator);

            }
        }
    }

    private static class X extends Expression {


            X(){
            }
            @Override
            public double evaluate(double xValue) {
                return xValue;
            }

            @Override
            public Expression derivate() {
                return new Constant(1);
            }
    }

    private static class Constant extends Expression{

        double value;
        Constant(double value){
            this.value = value;
        }
        @Override
        public double evaluate(double xValue) {
            return value;
        }

        @Override
        public Expression derivate() {
            return new Constant(0);
        }
    }



}
