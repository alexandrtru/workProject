package com.workSolutionProject;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Марина on 06.05.2016.
 */
public class JavaLearn {

    @Test
    public void dateFormatting(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date();
        System.out.print(dateFormatter.format(nowDate));
    }
}
