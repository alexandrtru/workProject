package com.workSolutionProject.Model;

import com.workSolutionProject.Helpers.DatabaseWorker;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Марина on 04.05.2016.
 */
public class ModelTest {

    @Test
    public void emptyDatabaseTesting() throws Exception{ //проверка инициализации листов в слушчае если БД пуста
        Model model = new Model();
        Assert.assertEquals(model.clientsList.isEmpty(), false);
        Assert.assertEquals(model.devicesList.isEmpty(), false);
    }
}
