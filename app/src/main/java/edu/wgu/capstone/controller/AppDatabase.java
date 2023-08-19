package edu.wgu.capstone.controller;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import edu.wgu.capstone.controller.convertor.DateConverter;
import edu.wgu.capstone.controller.dao.ExcursionDao;
import edu.wgu.capstone.controller.dao.VacationDao;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * The Room database for the application.
 */
@Database(entities = {Vacation.class, Excursion.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract VacationDao vacationDao();
    public abstract ExcursionDao excursionDao();

}
