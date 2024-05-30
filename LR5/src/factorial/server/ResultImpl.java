package factorial.server;

import java.io.Serializable;
import factorial.interfaces.Result;

public class ResultImpl implements Serializable, Result{

	private static final long serialVersionUID = 1L;
	private long time;
	private Object measure;

	public ResultImpl(Object measure, long time){
		this.setTime(time);
		this.setMeasure(measure);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Object getMeasure()
	{
		return measure;
	}

	public void setMeasure(Object measure) {
		this.measure = measure;
	}
	
	public String toString()
	{
		return "Measure - " + getMeasure().toString() + "; time - " + time + "ns";
	}

	

}
