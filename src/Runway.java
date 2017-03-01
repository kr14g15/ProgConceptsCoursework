import java.io.Serializable;

public class Runway implements Serializable {
    //region PrivateVariables
    private char  charDirection;
    private int   intDegree;

    private double doubleTORA;                          //Take-off Run
    private double doubleASDA;                          //TORA + Stopway
    private double doubleTODA;                          //TORA + Stopway + Clearway
    private double doubleLDA;                           //Landing distance
    private double doubleRunwayStripWidth;
    private double doubleRunwayStripLength;                          //runway + stopways

    private double doubleStartToThresholdMarkingsLength;
    private double doubleThresholdMarkingsStripLength;
    private double doubleThresholdMarkingsStripWidth;
    private double doubleThresholdMarkingsToLetterLength;
    private double doubleDisplacedThresholdLength;
    private double doubleCharacterLength;
    private double doubleCharacterWidth;
    private double doubleLetterToNumberLength;
    private double doubleNumberToStripeLength;

    private double doubleLeftStopwayLength;                              //ASDA - TORA
    private double doubleLeftClearwayLength;                             //TODA - TORA     //TODA - TORA
    private double doubleLeftClearwayWidth;                             //TODA - TORA     //TODA - TORA

    private double doubleRightStopwayLength;                              //ASDA - TORA
    private double doubleRightClearwayLength;                             //TODA - TORA     //TODA - TORA
    private double doubleRightClearwayWidth;                             //TODA - TORA     //TODA - TORA

    private double doubleThresholdStripLength;           //TORA - LDA
    private double doubleThresholdStripWidth;

    private double doubleStripesLength;                   //TORA - LDA
    private double doubleStripesWidth;
    private double doubleStripesDifference;

    private double doubleStripEnd;                        //area between the end of the runway	and	the	end	of the runway strip
    private double doubleSmallVisualStripWidth;
    private double doubleLargeVisualStripWidth;
    private double doubleRunwayToSmallVisualStripLength;
    private double doubleRunwayToLargeVisualStripLength;
    private double doubleInstrumentStrip;

    private double doubleRESA;
    private double doubleBlastProtection;                //area behind an aircraft
    private double doubleALS;                                  //area between top of obstacle and runway(min angle of descent)
    private double doubleTOCS;                                 //area between top of obstacle and runway(min angle of ascent)

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
        return doubleRunwayStripLength;
    }

    public double getASDA() {
        return doubleRunwayStripLength + doubleRightStopwayLength;
    }

    public double getTODA() {
        return doubleRunwayStripLength + doubleRightClearwayLength;
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


    public double getStripesLength() {
        return doubleStripesLength;
    }

    public double getStripesWidth() {
        return doubleStripesWidth;
    }

    public double getStripesDifference() {
        return doubleStripesDifference;
    }

    public double getLeftStopwayLength() {
        return doubleLeftStopwayLength;
    }

    public double getRightStopwayLength() {
        return doubleRightStopwayLength;
    }

    public double getLeftClearwayLength() {
        return doubleLeftClearwayLength;
    }

    public double getLeftClearwayWidth() {
        return doubleLeftClearwayWidth;
    }

    public double getRightClearwayLength() {
        return doubleRightClearwayLength;
    }

    public double getRightClearwayWidth() {
        return doubleRightClearwayWidth;
    }


    public double getStripEnd() {
        return doubleStripEnd;
    }

    public double getSmallVisualStripWidth() {
        return doubleSmallVisualStripWidth;
    }

    public double getLargeVisualStripWidth() {
        return doubleLargeVisualStripWidth;
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

    public void setLeftStopwayLength(double doubleStopway) {
        this.doubleLeftStopwayLength = doubleStopway;
    }

    public void setRightStopwayLength(double doubleStopway) {
        this.doubleRightStopwayLength = doubleStopway;
    }

    public void setLeftClearwayLength(double doubleClearway) {
        this.doubleLeftClearwayLength = doubleClearway;
    }

    public void setLeftClearwayWidth(double doubleClearway) {
        this.doubleLeftClearwayWidth = doubleClearway;
    }

    public void setRightClearwayLength(double doubleClearway) {
        this.doubleRightClearwayLength = doubleClearway;
    }

    public void setRightClearwayWidth(double doubleClearway) {
        this.doubleRightClearwayWidth = doubleClearway;
    }


    public void setThresholdStripLength(double doubleThresholdStripLength) {
        this.doubleThresholdStripLength = doubleThresholdStripLength;
    }

    public void setThresholdStripWidth(double doubleThresholdStripWidth) {
        this.doubleThresholdStripWidth = doubleThresholdStripWidth;
    }


    public void setStripesLength(double doubleStripesLength) {
        this.doubleStripesLength = doubleStripesLength;
    }

    public void setStripesWidth(double doubleStripesWidth) {
        this.doubleStripesWidth = doubleStripesWidth;
    }

    public void setStripesDifference(double doubleStripesDifference) {
        this.doubleStripesDifference = doubleStripesDifference;
    }

    public void setStripEnd(double doubleStripEnd) {
        this.doubleStripEnd = doubleStripEnd;
    }

    public void setSmallVisualStripWidth(double doubleVisualStrip) {
        this.doubleSmallVisualStripWidth = doubleVisualStrip;
    }

    public void setLargeVisualStripWidth(double doubleVisualStrip) {
        this.doubleLargeVisualStripWidth = doubleVisualStrip;
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

    public void setSkyHeight(double doubleSkyHeight) {
        this.doubleSkyHeight = doubleSkyHeight;
    }

    public void setGroundHeight(double doubleGroundHeight) {
        this.doubleGroundHeight = doubleGroundHeight;
    }

    public void setAirportHeight(double doubleAirportHeight) {
        this.doubleAirportHeight = doubleAirportHeight;
    }

    public double getStartToThresholdMarkingsLength() {
        return doubleStartToThresholdMarkingsLength;
    }

    public void setStartToThresholdMarkingsLength(double startToThresholdMarkingsLength) {
        this.doubleStartToThresholdMarkingsLength = startToThresholdMarkingsLength;
    }

    public double getThresholdMarkingsStripLength() {
        return doubleThresholdMarkingsStripLength;
    }

    public double getThresholdMarkingsStripWidth() {
        return doubleThresholdMarkingsStripWidth;
    }

    public double getThresholdMarkingsToLetterLength() {
        return doubleThresholdMarkingsToLetterLength;
    }

    public double getCharacterLength() {
        return doubleCharacterLength;
    }

    public double getCharacterWidth() {
        return doubleCharacterWidth;
    }

    public double getLetterToNumberLength() {
        return doubleLetterToNumberLength;
    }

    public double getNumberToStripeLength() {
        return doubleNumberToStripeLength;
    }

    public void setThresholdMarkingsStripLength(double doubleThresholdMarkingsStripLength) {
        this.doubleThresholdMarkingsStripLength = doubleThresholdMarkingsStripLength;
    }

    public void setThresholdMarkingsStripWidth(double doubleThresholdMarkingsStripWidth) {
        this.doubleThresholdMarkingsStripWidth = doubleThresholdMarkingsStripWidth;
    }

    public void setThresholdMarkingsToLetterLength(double doubleThresholdMarkingsToLetterLength) {
        this.doubleThresholdMarkingsToLetterLength = doubleThresholdMarkingsToLetterLength;
    }

    public void setCharacterLength(double doubleCharacterLength) {
        this.doubleCharacterLength = doubleCharacterLength;
    }

    public void setCharacterWidth(double doubleCharacterWidth) {
        this.doubleCharacterWidth = doubleCharacterWidth;
    }

    public void setLetterToNumberLength(double doubleLetterToNumberLength) {
        this.doubleLetterToNumberLength = doubleLetterToNumberLength;
    }

    public void setNumberToStripeLength(double doubleNumberToStripeLength) {
        this.doubleNumberToStripeLength = doubleNumberToStripeLength;
    }

    public double getRunwayToSmallVisualStripLength() {
        return doubleRunwayToSmallVisualStripLength;
    }

    public double getRunwayToLargeVisualStripLength() {
        return doubleRunwayToLargeVisualStripLength;
    }

    @Override
    public String toString() {
        return charDirection+""+intDegree;
    }

    public void setRunwayToSmallVisualStripLength(double runwayToVisualStripLength) {
        this.doubleRunwayToSmallVisualStripLength = runwayToVisualStripLength;
    }

    public void setRunwayToLargeVisualStripLength(double runwayToInstrumentStripLength) {
        this.doubleRunwayToLargeVisualStripLength = runwayToInstrumentStripLength;
    }

    public void setRunwayStripLength(double runwayStripLength) {
        this.doubleRunwayStripLength = runwayStripLength;
    }

    public double getRunwayStripLength() {
        return doubleRunwayStripLength;
    }

    public double getDisplacedThresholdLength() {
        return doubleDisplacedThresholdLength;
    }

    public void setDisplacedThresholdLength(double displacedThreshold) {
        this.doubleDisplacedThresholdLength = displacedThreshold;
    }

    //endregion
}
