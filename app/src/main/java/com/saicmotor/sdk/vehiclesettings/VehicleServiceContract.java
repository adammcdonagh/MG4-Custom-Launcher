package com.saicmotor.sdk.vehiclesettings;

import com.saicmotor.sdk.vehiclesettings.bean.AirConditionBean;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleChargingBean;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleConditionBean;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleControlBean;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleSettingBean;
import com.saicmotor.sdk.vehiclesettings.manager.BaseManager;
import com.saicmotor.sdk.vehiclesettings.manager.PageManager;

/* loaded from: classes2.dex */
public class VehicleServiceContract {

    public interface IAirConditionCallback {
        void onAirConditionChangeEvent(AirConditionBean airConditionBean);

        void onAirConditionErrorEvent(String str, int i);
    }

    public interface IPageServiceListener {
        void onServiceConnected(PageManager pageManager);

        void onServiceDisconnected();
    }

    public interface IVehicleChargingCallback {
        void onVehicleChargingChangeEvent(VehicleChargingBean vehicleChargingBean);

        void onVehicleChargingErrorEvent(String str, int i);
    }

    public interface IVehicleConditionCallback {
        void onVehicleConditionChangeEvent(VehicleConditionBean vehicleConditionBean);

        void onVehicleConditionErrorEvent(String str, int i);
    }

    public interface IVehicleControlCallback {
        void onVehicleControlChangeEvent(VehicleControlBean vehicleControlBean);

        void onVehicleControlErrorEvent(String str, int i);
    }

    public interface IVehicleScreenCallback {
        void onScreenStateChange(int i, int i2);

        void onStateChanged(int i);
    }

    public interface IVehicleServiceListener {
        void onServiceConnected(BaseManager baseManager);

        void onServiceDisconnected();
    }

    public interface IVehicleSettingCallback {
        void onVehicleSettingChangeEvent(VehicleSettingBean vehicleSettingBean);

        void onVehicleSettingErrorEvent(String str, int i);
    }
}
