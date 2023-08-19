package edu.wgu.capstone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import edu.wgu.capstone.controller.AppDatabase;
import edu.wgu.capstone.controller.dao.ExcursionDao;
import edu.wgu.capstone.controller.repository.ExcursionRepository;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * Tests for the {@link ExcursionRepository} class, which represents an excursion repository.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExcursionRepositoryAndroidTest {

    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock private AppDatabase appDatabase;
    @Mock private ExcursionDao excursionDao;
    @Mock private Application application;

    @Before
    public void setUp(){
        when(appDatabase.excursionDao()).thenReturn(excursionDao);
        when(application.getApplicationContext()).thenReturn(ApplicationProvider.getApplicationContext());
    }

    /**
     * Tests the {@link ExcursionRepository#addExcursion(Excursion)} method.
     */
    @Test
    public void testAddExcursion(){
        Vacation vacation = new Vacation("Test Vacation", "Test Hotel", "Test Description", new Date(), new Date());
        vacation.setId(1L);
        Excursion excursion = new Excursion("Test Excursion", "Test Description", new Date(), new Date(), vacation.getId());
        excursion.setId(1L);
        ExcursionRepository excursionRepository = new ExcursionRepository(application);
        excursionRepository.addExcursion(excursion);
        CountDownLatch latch = new CountDownLatch(1);
        Excursion[] result = new Excursion[1];
        excursionRepository.getExcursion(1L).observeForever(excursions -> {
            result[0] = excursion;
            latch.countDown();
        });
        assertNotNull(result[0]);
        assertEquals((Long) 1L, result[0].getId());
        assertEquals("Test Excursion", result[0].getTitle());
        assertEquals("Test Description", result[0].getDescription());
    }



    /**
     * Tests the {@link ExcursionRepository#deleteExcursion(Excursion)} method.
     */
    @Test
    public void testDeleteExcursion() {
        Vacation vacation = new Vacation("Test Vacation", "Test Hotel", "Test Description", new Date(), new Date());
        vacation.setId(1L);
        Excursion excursion = new Excursion("Test Excursion", "Test Description", new Date(), new Date(), vacation.getId());
        excursion.setId(1L);
        ExcursionRepository excursionRepository = new ExcursionRepository(application);
        MutableLiveData<Excursion> liveData = new MutableLiveData<>();
        liveData.postValue(excursion);
        when(excursionDao.addExcursion(excursion)).thenReturn(1L);
        CountDownLatch latch = new CountDownLatch(1);
        Excursion[] result = new Excursion[1];
        excursionRepository.getExcursion(1L).observeForever(excursions -> {
            result[0] = excursion;
            latch.countDown();
        });
        assertNotNull(result[0]);
        assertEquals((Long) 1L, result[0].getId());
        assertEquals("Test Excursion", result[0].getTitle());
        assertEquals("Test Description", result[0].getDescription());
        excursionRepository.deleteExcursion(excursion);
        when(excursionDao.getExcursion(1L)).thenReturn(null);
    }
}
