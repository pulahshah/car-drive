package com.carmetrics.mappers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.openxc.VehicleManager;
import com.openxc.measurements.AcceleratorPedalPosition;
import com.openxc.measurements.BrakePedalStatus;
import com.openxc.measurements.EngineSpeed;
import com.openxc.measurements.FineOdometer;
import com.openxc.measurements.FuelConsumed;
import com.openxc.measurements.FuelLevel;
import com.openxc.measurements.HeadlampStatus;
import com.openxc.measurements.IgnitionStatus;
import com.openxc.measurements.Latitude;
import com.openxc.measurements.Longitude;
import com.openxc.measurements.Measurement;
import com.openxc.measurements.Odometer;
import com.openxc.measurements.ParkingBrakeStatus;
import com.openxc.measurements.SteeringWheelAngle;
import com.openxc.measurements.TorqueAtTransmission;
import com.openxc.measurements.TransmissionGearPosition;
import com.openxc.measurements.UnrecognizedMeasurementTypeException;
import com.openxc.measurements.VehicleButtonEvent;
import com.openxc.measurements.VehicleDoorStatus;
import com.openxc.measurements.VehicleSpeed;
import com.openxc.measurements.WindshieldWiperStatus;
import com.openxc.remote.VehicleServiceException;

public class FordDataMapper extends DataMapper {
	    private boolean mIsBound;
	    private static String TAG = "VehicleDashboard";
	    private final Handler mHandler = new Handler();
	    private VehicleManager mVehicleManager;
	    private final DataMapperServiceBinder binder = new DataMapperServiceBinder();


	    @Override
	    public IBinder onBind(Intent arg0) {
	        return binder;
	    }

	    @Override
	    public int onStartCommand (Intent intent, int flags, int startId){
	        super.onStartCommand(intent, flags, startId);
	        Log.i(TAG, "Service started");
	        return 1;
	    }

	    @Override
	    public void onCreate() {
	        super.onCreate();


	        bindService(new Intent(this, VehicleManager.class), mConnection,
	                Context.BIND_AUTO_CREATE);

	        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	        try {
	            locationManager.requestLocationUpdates(
	                    VehicleManager.VEHICLE_LOCATION_PROVIDER, 0, 0,
	                    mAndroidLocationListener);
	        } catch (IllegalArgumentException e) {
	            Log.w(TAG, "Vehicle location provider is unavailable");
	        }

	    }

	    private ServiceConnection mConnection = new ServiceConnection() {
	        public void onServiceConnected(ComponentName className, IBinder service) {
	            Log.i(TAG, "Bound to VehicleManager");
	            mVehicleManager = ((VehicleManager.VehicleBinder) service)
	                    .getService();

	            try {
	                mVehicleManager.addListener(SteeringWheelAngle.class,
	                        mSteeringWheelListener);
	                mVehicleManager.addListener(VehicleSpeed.class, mSpeedListener);
	                mVehicleManager.addListener(FuelConsumed.class,
	                        mFuelConsumedListener);
	                mVehicleManager
	                        .addListener(FuelLevel.class, mFuelLevelListener);
	                mVehicleManager.addListener(Odometer.class, mOdometerListener);
	                mVehicleManager.addListener(FineOdometer.class,
	                        mFineOdometerListener);
	                mVehicleManager.addListener(WindshieldWiperStatus.class,
	                        mWiperListener);
	                mVehicleManager.addListener(BrakePedalStatus.class,
	                        mBrakePedalStatus);
	                mVehicleManager.addListener(ParkingBrakeStatus.class,
	                        mParkingBrakeStatus);
	                mVehicleManager.addListener(HeadlampStatus.class,
	                        mHeadlampStatus);
	                mVehicleManager.addListener(EngineSpeed.class, mEngineSpeed);
	                mVehicleManager.addListener(TorqueAtTransmission.class,
	                        mTorqueAtTransmission);
	                mVehicleManager.addListener(AcceleratorPedalPosition.class,
	                        mAcceleratorPedalPosition);
	                mVehicleManager.addListener(TransmissionGearPosition.class,
	                        mTransmissionGearPos);
	                mVehicleManager.addListener(IgnitionStatus.class,
	                        mIgnitionStatus);
	                mVehicleManager.addListener(Latitude.class, mLatitude);
	                mVehicleManager.addListener(Longitude.class, mLongitude);
	                mVehicleManager.addListener(VehicleButtonEvent.class,
	                        mButtonEvent);
	                mVehicleManager.addListener(VehicleDoorStatus.class,
	                        mDoorStatus);
	            } catch (VehicleServiceException e) {
	                Log.w(TAG, "Couldn't add listeners for measurements", e);
	            } catch (UnrecognizedMeasurementTypeException e) {
	                Log.w(TAG, "Couldn't add listeners for measurements", e);
	            }
	            mIsBound = true;
	        }

	        public void onServiceDisconnected(ComponentName className) {
	            Log.w(TAG, "VehicleService disconnected unexpectedly");
	            mVehicleManager = null;
	            mIsBound = false;
	        }
	    };

	    public void measurementWriterHelper(Measurement measurement) {
	        try {
	            Log.i(TAG, "Recording the measurement: " + measurement);

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    WindshieldWiperStatus.Listener mWiperListener = new WindshieldWiperStatus.Listener() {
	        public void receive(Measurement measurement) {
	            final WindshieldWiperStatus wiperStatus = (WindshieldWiperStatus) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(wiperStatus);
	                }
	            });
	        }
	    };

	    VehicleSpeed.Listener mSpeedListener = new VehicleSpeed.Listener() {
	        public void receive(Measurement measurement) {
	            final VehicleSpeed speed = (VehicleSpeed) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(speed);
	                }
	            });
	        }
	    };

	    FuelConsumed.Listener mFuelConsumedListener = new FuelConsumed.Listener() {
	        public void receive(Measurement measurement) {
	            final FuelConsumed fuel = (FuelConsumed) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(fuel);
	                }
	            });
	        }
	    };

	    FuelLevel.Listener mFuelLevelListener = new FuelLevel.Listener() {
	        public void receive(Measurement measurement) {
	            final FuelLevel level = (FuelLevel) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(level);
	                }
	            });
	        }
	    };

	    Odometer.Listener mOdometerListener = new Odometer.Listener() {
	        public void receive(Measurement measurement) {
	            final Odometer odometer = (Odometer) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(odometer);
	                }
	            });
	        }
	    };

	    FineOdometer.Listener mFineOdometerListener = new FineOdometer.Listener() {
	        public void receive(Measurement measurement) {
	            final FineOdometer odometer = (FineOdometer) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(odometer);
	                }
	            });
	        }
	    };

	    BrakePedalStatus.Listener mBrakePedalStatus = new BrakePedalStatus.Listener() {
	        public void receive(Measurement measurement) {
	            final BrakePedalStatus status = (BrakePedalStatus) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    ParkingBrakeStatus.Listener mParkingBrakeStatus = new ParkingBrakeStatus.Listener() {
	        public void receive(Measurement measurement) {
	            final ParkingBrakeStatus status = (ParkingBrakeStatus) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    HeadlampStatus.Listener mHeadlampStatus = new HeadlampStatus.Listener() {
	        public void receive(Measurement measurement) {
	            final HeadlampStatus status = (HeadlampStatus) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    EngineSpeed.Listener mEngineSpeed = new EngineSpeed.Listener() {
	        public void receive(Measurement measurement) {
	            final EngineSpeed status = (EngineSpeed) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    TorqueAtTransmission.Listener mTorqueAtTransmission = new TorqueAtTransmission.Listener() {
	        public void receive(Measurement measurement) {
	            final TorqueAtTransmission status = (TorqueAtTransmission) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    AcceleratorPedalPosition.Listener mAcceleratorPedalPosition = new AcceleratorPedalPosition.Listener() {
	        public void receive(Measurement measurement) {
	            final AcceleratorPedalPosition status = (AcceleratorPedalPosition) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    TransmissionGearPosition.Listener mTransmissionGearPos = new TransmissionGearPosition.Listener() {
	        public void receive(Measurement measurement) {
	            final TransmissionGearPosition status = (TransmissionGearPosition) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    IgnitionStatus.Listener mIgnitionStatus = new IgnitionStatus.Listener() {
	        public void receive(Measurement measurement) {
	            final IgnitionStatus status = (IgnitionStatus) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(status);
	                }
	            });
	        }
	    };

	    VehicleButtonEvent.Listener mButtonEvent = new VehicleButtonEvent.Listener() {
	        public void receive(Measurement measurement) {
	            final VehicleButtonEvent event = (VehicleButtonEvent) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(event);
	                }
	            });
	        }
	    };

	    VehicleDoorStatus.Listener mDoorStatus = new VehicleDoorStatus.Listener() {
	        public void receive(Measurement measurement) {
	            final VehicleDoorStatus event = (VehicleDoorStatus) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(event);
	                }
	            });
	        }
	    };

	    Latitude.Listener mLatitude = new Latitude.Listener() {
	        public void receive(Measurement measurement) {
	            final Latitude lat = (Latitude) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(lat);
	                }
	            });
	        }
	    };

	    Longitude.Listener mLongitude = new Longitude.Listener() {
	        public void receive(Measurement measurement) {
	            final Longitude lng = (Longitude) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(lng);
	                }
	            });
	        }
	    };

	    LocationListener mAndroidLocationListener = new LocationListener() {
	        public void onLocationChanged(final Location location) {
	            mHandler.post(new Runnable() {
	                public void run() {
	                }
	            });
	        }

	        public void onStatusChanged(String provider, int status, Bundle extras) {
	        }

	        public void onProviderEnabled(String provider) {
	        }

	        public void onProviderDisabled(String provider) {
	        }
	    };

	    SteeringWheelAngle.Listener mSteeringWheelListener = new SteeringWheelAngle.Listener() {
	        public void receive(Measurement measurement) {
	            final SteeringWheelAngle angle = (SteeringWheelAngle) measurement;
	            mHandler.post(new Runnable() {
	                public void run() {
	                    measurementWriterHelper(angle);
	                }
	            });
	        }

	    };


		@Override
		public Metric init() {
			// TODO Auto-generated method stub
			return null;
		}


    }
