public class Runway {
    //region PrivateVariables
    private char  charDirection;
    private int   intDegree;

    private double doubleTORA;                          //Take-off Run
    private double doubleASDA;                          //TORA + Stopway
    private double doubleTODA;                          //TORA + Stopway + Clearway
    private double doubleLDA;                           //Landing distance
    private double doubleRunwayStripWidth;

    private double doubleStopway;                              //ASDA - TORA
    private double doubleClearway;                             //TODA - TORA

    private double doubleThresholdStripLength;           //TORA - LDA
    private double doubleThresholdStripWidth;
    private double doubleThresholdStripeNumber;

    private double doubleStripesLength;                   //TORA - LDA
    private double doubleStripesWidth;
    private double doubleStripesDifference;

    private double doubleStripEnd;                        //area between the end of the runway	and	the	end	of the runway strip
    private double doubleVisualStrip;
    private double doubleCGA;
    private double doubleInstrumentStrip;

    private double doubleRESA;
    private double doubleBlastProtection;                //area behind an aircraft
    private double doubleALS;                                  //area between top of obstacle and runway(min angle of descent)
    private double doubleTOCS;                                 //area between top of obstacle and runway(min angle of ascent)
    private double doubleRunwayStrip;                          //runway + stopways

    private double doubleSkyHeight;
    private double doubleGroundHeight;
    private double doubleAirportHeight;
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

    public double getRunwayStripWidth() {
        return doubleRunwayStripWidth;
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

    public void setDirection(char charDirection) {
        this.charDirection = charDirection;
    }

    public void setDegree(int intDegree) {
        this.intDegree = intDegree;
    }

    public void setTORA(double doubleTORA) {
        this.doubleTORA = doubleTORA;
    }

    public void setASDA(double doubleASDA) {
        this.doubleASDA = doubleASDA;
    }

    public void setTODA(double doubleTODA) {
        this.doubleTODA = doubleTODA;
    }

    public void setLDA(double doubleLDA) {
        this.doubleLDA = doubleLDA;
    }

    public void setRunwayStripWidth(double doubleRunwayStripWidth) {
        this.doubleRunwayStripWidth = doubleRunwayStripWidth;
    }

    public void setStopway(double doubleStopway) {
        this.doubleStopway = doubleStopway;
    }

    public void setClearway(double doubleClearway) {
        this.doubleClearway = doubleClearway;
    }

    public void setThresholdStripLength(double doubleThresholdStripLength) {
        this.doubleThresholdStripLength = doubleThresholdStripLength;
    }

    public void setThresholdStripWidth(double doubleThresholdStripWidth) {
        this.doubleThresholdStripWidth = doubleThresholdStripWidth;
    }

    public void setThresholdStripeNumber(double doubleThresholdStripeNumber) {
        this.doubleThresholdStripeNumber = doubleThresholdStripeNumber;
    }

    public void setHorizontalStripesLength(double doubleStripesLength) {
        this.doubleStripesLength = doubleStripesLength;
    }

    public void setHorizontalStripesWidth(double doubleStripesWidth) {
        this.doubleStripesWidth = doubleStripesWidth;
    }

    public void setHorizontalStripesDifference(double doubleStripesDifference) {
        this.doubleStripesDifference = doubleStripesDifference;
    }

    public void setStripEnd(double doubleStripEnd) {
        this.doubleStripEnd = doubleStripEnd;
    }

    public void setVisualStrip(double doubleVisualStrip) {
        this.doubleVisualStrip = doubleVisualStrip;
    }

    public void setCGA(double doubleCGA) {
        this.doubleCGA = doubleCGA;
    }

    public void setInstrumentStrip(double doubleInstrumentStrip) {
        this.doubleInstrumentStrip = doubleInstrumentStrip;
    }

    public void setRESA(double doubleRESA) {
        this.doubleRESA = doubleRESA;
    }

    public void setBlastProtection(double doubleBlastProtection) {
        this.doubleBlastProtection = doubleBlastProtection;
    }

    public void setALS(double doubleALS) {
        this.doubleALS = doubleALS;
    }

    public void setTOCS(double doubleTOCS) {
        this.doubleTOCS = doubleTOCS;
    }

    public void setRunwayStrip(double doubleRunwayStrip) {
        this.doubleRunwayStrip = doubleRunwayStrip;
    }

    public void setSkyHeight(double doubleSkyHeight) {
        this.doubleSkyHeight = doubleSkyHeight;
    }

    public void setGroundHeight(double doubleGroundHeight) {
        this.doubleGroundHeight = doubleGroundHeight;
    }

    public void setAirportHeight(double doubleAirportHeight) {
        this.doubleAirportHeight = doubleAirportHeight;
    }

    @Override
    public String toString() {
        return charDirection+""+intDegree;
    }
    //endregion
}
