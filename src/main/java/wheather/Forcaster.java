package wheather;

import com.github.dvdme.ForecastIOLib.ForecastIO;

public class Forcaster {

    private static ForecastIO forecastIO;

    public static ForecastIO getForcastIO() {
        if (forecastIO != null) {
            return forecastIO;
        } else {
            forecastIO = new ForecastIO("38fc1ce7101657f4f8b35adb641133eb");
            forecastIO.setUnits((ForecastIO.UNITS_SI));
            forecastIO.setExcludeURL("hourly,minutely");
            return forecastIO;
        }
    }
}
