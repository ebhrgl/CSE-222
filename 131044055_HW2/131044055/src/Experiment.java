public class Experiment {
    /**
     *
     */
    private String setup;
    private int day;
    private String time;
    private boolean completed;
    private float accuracy;
    private int index;

    /**
     *
     * @param day
     * @param index
     */
    public Experiment(int day, int index){
        this.index = 0;
        this.day = 0;
    }

    /**
     *
     */
    public Experiment(){
        this.setup  = null;
        this.day = 0;
        this.time = null;
        this.completed = false;
        this.accuracy = 0;
    }

    /**
     *
     * @param setup
     * @param time
     * @param completed
     * @param day
     * @param acc
     */
    public  Experiment(String setup, String time , boolean completed, int day, float acc){
       this.setup = setup;
       this.day = day;
       this.time = time;
       this.completed = completed;
       this.accuracy = acc;
    }

    /**
     *
     * @param setup
     * @param day
     * @param time
     * @param completed
     * @param accuracy
     */
    public Experiment(String setup, int day, String time, boolean completed, float accuracy) {
        this.setup = setup;
        this.day = day;
        this.time = time;
        this.completed = completed;
        this.accuracy = accuracy;
    }

    /**
     *
     * @return
     */
    public String getSetup() {
        return setup;
    }

    /**
     *
     * @param setup
     */
    public void setSetup(String setup) {
        this.setup = setup;
    }

    /**
     *
     * @return
     */
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     *
     * @param completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     *
     * @return
     */
    public float getAccuracy() {
        return accuracy;
    }

    /**
     *
     * @param accuracy
     */
    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", completed=" + completed +
                ", accuracy=" + accuracy +
                '}';
    }

}
