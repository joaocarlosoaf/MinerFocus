package pojo;

/**
 * Created by joaoc on 06/01/2018.
 */

public class CalculatorDataWorker {

    private String accountValue;
    private String hashrateValue;
    private String btcForHourValue;
    private String usdForHourValue;
    private String btcForDayValue;
    private String usdForDayValue;
    private String btcForWeekValue;
    private String usdForWeekValue;
    private String btcForMonthValue;
    private String usdForMonthValue;

    public String getAccountValue() {
        return this.accountValue;
    }

    public String getHashrateValue() {

        if(this.hashrateValue.length() > 5){
            this.hashrateValue = this.hashrateValue.substring(0,5);
        }

        return this.hashrateValue+"MH/s";
    }

    public String getBtcForHourValue() {
        return this.btcForHourValue;
    }

    public String getUsdForHourValue() {
        return this.usdForHourValue;
    }

    public String getBtcForDayValue() {
        return this.btcForDayValue;
    }

    public String getUsdForDayValue() {
        return this.usdForDayValue;
    }

    public String getBtcForWeekValue() {
        return this.btcForWeekValue;
    }

    public String getUsdForWeekValue() {
        return this.usdForWeekValue;
    }

    public String getBtcForMonthValue() {
        return this.btcForMonthValue;
    }

    public String getUsdForMonthValue() {
        return this.usdForMonthValue;
    }

    public CalculatorDataWorker(String accountValue, String hashrateValue, String btcForHourValue,
                                String usdForHourValue, String btcForDayValue, String usdForDayValue, String btcForWeekValue,
                                String usdForWeekValue, String btcForMonthValue, String usdForMonthValue){

    this.accountValue = accountValue;
    this.hashrateValue = hashrateValue;
    this.btcForHourValue = btcForHourValue;
    this.usdForHourValue = usdForHourValue;
    this.btcForDayValue = btcForDayValue;
    this.usdForDayValue = usdForDayValue;
    this.btcForWeekValue = btcForWeekValue;
    this.usdForWeekValue = usdForWeekValue;
    this.btcForMonthValue = btcForMonthValue;
    this.usdForMonthValue = usdForMonthValue;

}

}
