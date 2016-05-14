package com.workSolutionProject.Helpers;

import java.util.ArrayList;

/**
 * Created by Марина on 04.05.2016.
 */
public class DatabaseRowsList<Object> extends ArrayList<Object> {



    public IDatabaseRow containsByDatabaseId(IDatabaseRow obj)
    {
        for (Object e : this)
        {
             IDatabaseRow currObj = (IDatabaseRow)e;
             if (currObj.equalsById(obj)) return currObj;
        }
        return  null;
    }

    public IDatabaseRow containsByDatabaseId(Integer id)
    {
        for (Object e : this)
        {
            IDatabaseRow currObj = (IDatabaseRow)e;
            if (currObj.equalsById(id)) return currObj;
        }
        return  null;
    }

}
