public class Runway {
    //region PrivateVariables
    private String sName = "09L";
    private int intDegree = 9;
    private int intTORA = 3660;                          //Take-off Run
    private int intASDA = 3660;                          //TORA + Stopway
    private int intTODA = 3660;                          //TORA + Stopway + Clearway

    private int intLDA = 3660;                           //Landing distance
    private int intStripHeight = 30;
    private int intThreshold;                            //TORA - LDA
    private int intStopway;                              //ASDA - TORA
    private int intClearway;                             //TODA - TORA

    private int intStripEnd = 60;                        //area between the end of the runway	and	the	end	of the runway strip
    private int intVisualStrip = 75;
    private int intCGA = 105;
    private int intInstrumentStrip = 150;

    private int intRESA = 240;
    private int intBlastProtection = 400;                //area behind an aircraft
    private int intALS;                                  //area between top of obstacle and runway(min angle of descent)
    private int intTOCS;                                 //area between top of obstacle and runway(min angle of ascent)
    private int intRunwayStrip;                          //runway + stopways
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

    public String getName() {
        return sName;
    }

    public int getDegree() {
        return intDegree;
    }

    public int getTORA() {
        return intTORA;
    }

    public int getASDA() {
        return intASDA;
    }

    public int getTODA() {
        return intTODA;
    }

    public int getLDA() {
        return intLDA;
    }

    public int getStripHeight() {
        return intStripHeight;
    }

    public int getThreshold() {
        return intThreshold;
    }

    public int getStopway() {
        return intStopway;
    }

    public int getClearway() {
        return intClearway;
    }

    public int getStripEnd() {
        return intStripEnd;
    }

    public int getVisualStrip() {
        return intVisualStrip;
    }

    public int getCGA() {
        return intCGA;
    }

    public int getInstrumentStrip() {
        return intInstrumentStrip;
    }

    public int getRESA() {
        return intRESA;
    }

    public int getBlastProtection() {
        return intBlastProtection;
    }

    public int getALS() {
        return intALS;
    }

    public int getTOCS() {
        return intTOCS;
    }

    public int getRunwayStrip() {
        return intRunwayStrip;
    }

    //endregion
}
