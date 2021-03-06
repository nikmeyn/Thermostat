public class LivingArea
{
    //instance variables
    private double lAreaTemp;
    Furnace furn;
    Thermostat thermo; 
    Environment environ;
    private final double SHC = 4.0;
    double time = 300.00;
    double qIn, qLoss; 

    /**
     * Constructor for LivingArea
     **/public LivingArea(double inTemp)
    {
        lAreaTemp = inTemp;
    }

    public double calcNewTemp(int eff, int year, int area, double env, double therm, double over)
    {
        //local variables
        furn =  new Furnace (year, area);
        thermo = new Thermostat(therm, over);
        environ = new Environment (env);        
        int switchYN = thermo.setSwitch(lAreaTemp);
        
        //calculates new room temp
        qIn = furn.getBTU(eff, time);
        qLoss =  environ.getHeatLoss(area, lAreaTemp, time);
        double dec = lAreaTemp - (((qIn - qLoss)/(SHC * area)) + lAreaTemp);
        
        //when furnace is on or off
        if (switchYN == 1)
        {
            lAreaTemp = ((qIn - qLoss)/(SHC * area)) + lAreaTemp;
        }
        else if(switchYN == 0 || switchYN == 2)
        {
            lAreaTemp = ((0 - qLoss)/(SHC * area)) + lAreaTemp;
        }
        time += 300.00;
        return lAreaTemp;

    }
}