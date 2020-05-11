import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args)
    {
        if(args.length >= 1 && (args[0].equals("--help") || args[0].equals("-h")))
        {
            System.out.println("Aplikace se používá ve formátu city_distance MĚSTO1 MĚSTO2 [JEDNOTKY]");
            System.out.println("MĚSTO1 a 2 jsou názvy měst jejichž vzdálenost chceme měřit");
            System.out.println("JEDNOTKY lze nastavit na míle použítím \"míle\" jinak budou km");
            return;
        }

        else if(args.length < 2)
        {
            System.out.println("Pro nápovědu použijte --help");
            return;
        }



        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("a3feeafb5757495288e4c182d6799060");
        Logger.getRootLogger().setLevel(Level.ERROR);
        JOpenCageLatLng latLon = getCityLatLng(args[0], jOpenCageGeocoder);
        if(latLon == null)
            return;
        double lat1 = latLon.getLat();
        double lon1 = latLon.getLng();

        latLon = getCityLatLng(args[1], jOpenCageGeocoder);
        if(latLon == null)
            return;
        double lat2 = latLon.getLat();
        double lon2 = latLon.getLng();
/*
        Haversine
        formula: 	a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
        c = 2 ⋅ atan2( √a, √(1−a) )
        d = R ⋅ c
        where 	φ is latitude, λ is longitude, R is earth’s radius (mean radius = 6,371km);
        note that angles need to be in radians to pass to trig functions!*/

        int R = 6371000;
        double lat_arg = (lat2 - lat1)/2;
        double lon_arg = (lon2 - lon1)/2;

        double sin_lat = Math.pow((Math.sin(Math.toRadians(lat_arg))),2);
        double sin_lon = Math.pow((Math.sin(Math.toRadians(lon_arg))),2);

        double cos_1 = Math.cos(Math.toRadians(lat1));
        double cos_2 = Math.cos(Math.toRadians(lat2));

        double a = sin_lat + cos_1 * cos_2 * sin_lon;

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double d = R * c;
        String distance;
        String units;
        if(args.length == 3 && args[2].equals("míle"))
        {
            distance = String.format("%.2f", (d/1000)/1.6);
            units = " mil";
        }

        else
        {
            distance = String.format("%.2f", d/1000);
            units = " km";
        }

        System.out.println("Vzdálenost mezi městy je: " + distance + units);

    }

    private static JOpenCageLatLng getCityLatLng(String city, JOpenCageGeocoder jOpenCageGeocoder)
    {
        JOpenCageForwardRequest request;
        try {
            request = new JOpenCageForwardRequest(city);
        }
        catch (IllegalArgumentException e) {
            return null;
        }

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        if(response == null)
            return null;
        if(response.getResults().size() == 0)
            return null;
        JOpenCageResult result = response.getResults().get(0);

        return result.getGeometry();
    }
}
