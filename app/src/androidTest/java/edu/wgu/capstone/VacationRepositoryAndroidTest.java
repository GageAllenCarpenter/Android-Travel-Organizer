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
import edu.wgu.capstone.controller.dao.VacationDao;
import edu.wgu.capstone.controller.repository.VacationRepository;
import edu.wgu.capstone.model.Vacation;

/**
 * Tests for the {@link VacationRepository} class, which represents a vacation repository.
 */
@RunWith(MockitoJUnitRunner.class)
public class VacationRepositoryAndroidTest {

    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock private AppDatabase appDatabase;
    @Mock private VacationDao vacationDao;
    @Mock private Application application;

    @Before
    public void setUp(){
        when(appDatabase.vacationDao()).thenReturn(vacationDao);
        when(application.getApplicationContext()).thenReturn(ApplicationProvider.getApplicationContext());
    }

    /**
     * Tests the {@link VacationRepository#getVacation(long)} method.
     */
    @Test
    public void testGetVacation(){
        VacationRepository vacationRepository = new VacationRepository(application);
        Vacation vacation = new Vacation("Test Vacation", "Test Hotel", "Test Description", new Date(), new Date());
        vacation.setId(1L);
        MutableLiveData<Vacation> liveData = new MutableLiveData<>();
        liveData.postValue(vacation);
        when(vacationDao.getVacation(vacation.getId())).thenReturn(liveData);
        vacationRepository.getVacation(vacation.getId()).observeForever(vacations -> {
            assertNotNull(vacation);
            assertEquals((Long) 1L, vacation.getId());
            assertEquals("Test Vacation", vacation.getTitle());
            assertEquals("Test Description", vacation.getDescription());
        });
    }

    /**
     * Tests the {@link VacationRepository#deleteVacation(Vacation)} method.
     */
    @Test
    public void testDeleteVacation(){
        VacationRepository vacationRepository = new VacationRepository(application);
        Vacation vacation = new Vacation("Test Vacation", "Test Hotel", "Test Description", new Date(), new Date());
        vacation.setId(1L);
        MutableLiveData<Vacation> liveData = new MutableLiveData<>();
        liveData.postValue(vacation);
        when(vacationDao.addVacation(vacation)).thenReturn(1L);
        CountDownLatch latch = new CountDownLatch(1);
        Vacation[] result = new Vacation[1];
        vacationRepository.getVacation(1L).observeForever(excursions -> {
            result[0] = vacation;
            latch.countDown();
        });
        assertNotNull(result[0]);
        assertEquals((Long) 1L, result[0].getId());
        assertEquals("Test Vacation", result[0].getTitle());
        assertEquals("Test Description", result[0].getDescription());
        vacationRepository.deleteVacation(vacation);
        when(vacationDao.getVacation(1L)).thenReturn(null);
    }

    /**
     * Tests the {@link VacationRepository#updateVacation(Vacation)} method.
     */
    @Test
    public void testUpdateVacation(){
        VacationRepository vacationRepository = new VacationRepository(application);
        Vacation vacation = new Vacation("Test Vacation", "Test Hotel", "Test Description", new Date(), new Date());
        vacation.setId(1L);
        MutableLiveData<Vacation> liveData = new MutableLiveData<>();
        liveData.postValue(vacation);
        when(vacationDao.getVacation(1L)).thenReturn(liveData);
        CountDownLatch latch = new CountDownLatch(1);
        Vacation[] result = new Vacation[1];
        vacationRepository.getVacation(1L).observeForever(excursions -> {
            result[0] = vacation;
            latch.countDown();
        });
        assertNotNull(result[0]);
        assertEquals((Long) 1L, result[0].getId());
        assertEquals("Test Vacation", result[0].getTitle());
        assertEquals("Test Description", result[0].getDescription());
        vacation.setTitle("Updated Test Vacation");
        vacation.setDescription("Updated Test Description");
        vacationRepository.updateVacation(vacation);
        when(vacationDao.getVacation(1L)).thenReturn(liveData);
        CountDownLatch latch2 = new CountDownLatch(1);
        Vacation[] result2 = new Vacation[1];
        vacationRepository.getVacation(1L).observeForever(excursions -> {
            result2[0] = vacation;
            latch2.countDown();
        });
        assertNotNull(result2[0]);
        assertEquals((Long) 1L, result2[0].getId());
        assertEquals("Updated Test Vacation", result2[0].getTitle());
        assertEquals("Updated Test Description", result2[0].getDescription());
    }
}
