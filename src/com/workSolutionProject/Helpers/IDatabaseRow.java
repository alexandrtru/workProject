package com.workSolutionProject.Helpers;

/**
 * Created by user on 03.05.2016.
 */
public interface IDatabaseRow {
    int getId();
    boolean equalsById(IDatabaseRow databaseRecord);
    boolean equalsById(Integer id);
}
