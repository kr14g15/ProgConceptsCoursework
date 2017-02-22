public class Runway {
    //region PrivateVariables
    private char  charDirection = 'L';
    private int intDegree = 9;
    private double doubleSkyHeight = 300;
    private double doubleGroundHeight = 300;
    private double doubleAirportHeight = 30;


    private double doubleTORA = 3660;                          //Take-off Run
    private double doubleASDA = 3660;                          //TORA + Stopway
    private double doubleTODA = 3660;                          //TORA + Stopway + Clearway

    private double doubleLDA = 3660;                           //Landing distance

    private double doubleStripWidth = 30;
    private double doubleStopway;                              //ASDA - TORA
    private double doubleClearway;                             //TODA - TORA

    private double doubleThresholdStripLength = 200;           //TORA - LDA
    private double doubleThresholdStripWidth = 2;
    private double doubleThresholdStripeNumber = 8;

    private double doubleStripesLength = 100;                   //TORA - LDA
    private double doubleStripesWidth = 2;
    private double doubleStripesDifference = 50;

    private double doubleStripEnd = 60;                        //area between the end of the runway	and	the	end	of the runway strip
    private double doubleVisualStrip = 75;
    private double doubleCGA = 105;
    private double doubleInstrumentStrip = 150;

    private double doubleRESA = 240;
    private double doubleBlastProtection = 400;                //area behind an aircraft
    private double doubleALS;                                  //area between top of obstacle and runway(min angle of descent)
    private double doubleTOCS;                                 //area between top of obstacle and runway(min angle of ascent)
    private double doubleRunwayStrip;                          //runway + stopways


    //endregion

    //region PublicVariables
    //endregion

    //region PrivateMethods
    //endregion

    //region PublicMethods
    //endregion

    //region Events
    //endregion

    //region Getters

    public char getDirection() {
        return charDirection;
    }

    public double getSkyHeight() {
        return doubleSkyHeight;
    }

    public double getGroundHeight() {
        return doubleGroundHeight;
    }

    public int getDegree() {
        return intDegree;
    }

    public double getTORA() {
        return doubleTORA;
    }

    public double getASDA() {
        return doubleASDA;
    }

    public double getTODA() {
        return doubleTODA;
    }

    public double getLDA() {
        return doubleLDA;
    }

    public double getStripWidth() {
        return doubleStripWidth;
    }

    public double getThresholdStripLength() {
        return doubleThresholdStripLength;
    }

    public double getThresholdStripWidth() {
        return doubleThresholdStripWidth;
    }

    public double getThresholdStripNumber() {
        return doubleThresholdStripeNumber;
    }

    public double getStripesLength() {
        return doubleStripesLength;
    }

    public double getStripesWidth() {
        return doubleStripesWidth;
    }

    public double getStripesDifference() {
        return doubleStripesDifference;
    }

    public double getStopway() {
        return doubleStopway;
    }

    public double getClearway() {
        return doubleClearway;
    }

    public double getStripEnd() {
        return doubleStripEnd;
    }

    public double getVisualStrip() {
        return doubleVisualStrip;
    }

    public double getCGA() {
        return doubleCGA;
    }

    public double getInstrumentStrip() {
        return doubleInstrumentStrip;
    }

    public double getRESA() {
        return doubleRESA;
    }

    public double getBlastProtection() {
        return doubleBlastProtection;
    }

    public double getALS() {
        return doubleALS;
    }

    public double getTOCS() {
        return doubleTOCS;
    }

    public double getRunwayStrip() {
        return doubleRunwayStrip;
    }

    public double getAirportHeight() {
        return doubleAirportHeight;
    }

    //endregion
}
