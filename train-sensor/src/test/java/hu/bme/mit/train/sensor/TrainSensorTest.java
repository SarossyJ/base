package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainSensor sensor; 
    TrainController controller;
    TrainUser user;  

    @Before
    public void before() {
        // TODO Add initializations
        controller = mock(TrainController.class); 
        user = mock(TrainUser.class); 
        sensor = new TrainSensorImpl(controller, user);

    }

    @Test
    public void alarmOverLimit() {
        sensor.overrideSpeedLimit(501);

        verify(user, times(1)).setAlarmState(true); 
    }

    @Test
    public void alarmMinusSpeed() {
        sensor.overrideSpeedLimit(-1);

        verify(user, times(1)).setAlarmState(true); 
    }

    @Test
    public void noAlarmAtNormalSpeed() {
        sensor.overrideSpeedLimit(300);

        verify(user, never()).setAlarmState(true); 
    }

    @Test
    public void noAlarmAtNormalSpeedReturn() {
        when(controller.getReferenceSpeed().thenReturn(202)); 
        sensor.overrideSpeedLimit(100);

        verify(user, times(1)).setAlarmState(true); 
    }
}
