/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icss.regie.workflow.demo;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.drools.core.util.MVELSafeHelper;
import org.junit.Test;
import org.mvel2.MVEL;

/**
 *
 * @author johns
 */
public class MvelTest {

    @Test
    public void testArray() {
        String expression = "results.stream().map((v) ->{return v?1:0;}).reduce(0, (sum, item) -> {return sum + item;})";
        List<Boolean> results = Arrays.asList(new Boolean[] {true,true,true,false});
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("results", results);
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); // true
        
    }
    
    @Test
    public void testEqual() {
        String expression = "a == b";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("a", "123");
        paramMap.put("b", 123);
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); // true
    }
    @Test
    public void testCount() {
        String expression = "count = 0;\n" +
                "\n" +
                "foreach(item: results){\n" +
                "  if(item==true)count++;\n" +
                "}\n" +
                "\n" +
                "return count>2;";
        List<Boolean> results = Arrays.asList(new Boolean[] {true,true,true,false});
        results.size();
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("results", results);
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); // true
        object = MVELSafeHelper.getEvaluator().eval(expression, paramMap);
        System.out.println(object); // true
    }
    @Test
    public void testFunction() {
        String expression = "count=0;for(i:results){if(i)count++;};return count>1";
        List<Boolean> results = Arrays.asList(new Boolean[] {true,true,true,false});
        results.size();
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("results", results);
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); // true
        expression = "count=0;for(i=0;i<results.size();i++){if(results.get(i))count++;};return count>1";
        object = MVEL.eval(expression, paramMap);
        System.out.println(object); // true
        object = MVELSafeHelper.getEvaluator().eval(expression, paramMap);
        System.out.println(object); // true
    }

}
