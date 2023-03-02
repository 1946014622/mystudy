package com.atguigu.calculatorLog.impl;

import com.atguigu.calculatorLog.CalculatorLog;
import java.util.Arrays;
public class CalculatorLogImpl implements CalculatorLog {
    @Override
    public int add(int... args) {
        int [] result = {0};
        Arrays.stream(args).forEach(arg ->
                result[0] += arg
                );
        return result[0];
    }
}
